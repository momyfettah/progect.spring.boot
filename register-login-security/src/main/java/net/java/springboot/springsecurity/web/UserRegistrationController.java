package net.java.springboot.springsecurity.web;

import javax.validation.Valid;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.java.springboot.springsecurity.model.User;
import net.java.springboot.springsecurity.service.UserService;
import net.java.springboot.springsecurity.service.UserServiceImpl;
import net.java.springboot.springsecurity.service.MailService;
import net.java.springboot.springsecurity.web.dto.UserRegistrationDto;


@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;
    
    @Autowired
	private MailService mailService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
        BindingResult result) throws MessagingException {

        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "registration";
        }

        userService.save(userDto);
        mailService.inviaMail(userDto.getEmail(), "ti sei appena registrato al nostro sito", "Registrazione effettuata correttamente");
		return "index";
    }
}