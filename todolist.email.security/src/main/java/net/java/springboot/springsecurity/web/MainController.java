package net.java.springboot.springsecurity.web;

import java.util.Base64;
import java.util.List;
import java.util.TimeZone;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.java.springboot.springsecurity.web.dto.UserRegistrationDto;
import net.java.springboot.springsecurity.model.Activity;
import net.java.springboot.springsecurity.model.MyRun;
import net.java.springboot.springsecurity.model.User;
import net.java.springboot.springsecurity.service.ActivityService;
import net.java.springboot.springsecurity.service.MailService;
import net.java.springboot.springsecurity.service.UserService;

@EnableScheduling
@Controller
public class MainController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private TaskScheduler scheduler;
	
	@Autowired
	private MailService mailService;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user/home")
    public String userIndex(Model model, Activity activity) throws MessagingException {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User user = userService.findByEmail(auth.getName());   	    
	    List<Activity> activities = user.getActivities();	    
	    model.addAttribute("authUser", user.getEmail());
	    model.addAttribute("authUserImage", Base64.getEncoder().encodeToString(user.getImage()));
        model.addAttribute("activities", activities);
        model.addAttribute("activity", new Activity());
        model.addAttribute("title", "Activities");    		    
        return "user/index";
    }
    
    @PostMapping(value="/save")
    public String save (@ModelAttribute Activity activity, RedirectAttributes redirectAttributes, Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User user = userService.findByEmail(auth.getName());   	
	    activity.setUser(user);
        Activity currActivity = activityService.save(activity);
        List<Activity> activities = user.getActivities();
        activities.add(currActivity);
        userService.addActivities(user, activities);
        if(currActivity != null) {
            LocalDateTime date = currActivity.getExpiredDate();
			int minute = date.getMinute();
			int hours = date.getHour();
			int day = date.getDayOfMonth();
			int month = date.getMonth().getValue();
			String expression = " 0 " + (minute - 2) + " " + hours + " " + day + " " + month + " ?";
			CronTrigger trigger = new CronTrigger(expression, TimeZone.getTimeZone(TimeZone.getDefault().getID()));
			MyRun myRunnable = new MyRun(currActivity, mailService);
            redirectAttributes.addFlashAttribute("successmessage", "Activity is saved successfully");
            scheduler.schedule(myRunnable, trigger);
            return "redirect:/user/home";
        }else {
            model.addAttribute("errormessage", "Activity is not save, Please try again");
            model.addAttribute("activity", activity);
            return "user/index";
        }
    }
    
    @GetMapping("/userAdmin/home")
    public String userAdminAccess() {
        return "index";
    }
}