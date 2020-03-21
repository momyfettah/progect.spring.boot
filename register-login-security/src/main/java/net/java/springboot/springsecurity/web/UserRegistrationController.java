package net.java.springboot.springsecurity.web;



import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.java.springboot.springsecurity.model.User;
import net.java.springboot.springsecurity.service.ImageService;
import net.java.springboot.springsecurity.service.MailService;
import net.java.springboot.springsecurity.service.UserService;
import net.java.springboot.springsecurity.web.dto.UserRegistrationDto;






@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;
    
    @Autowired
	private MailService mailService;
    
    @Autowired
	private ImageService imageService;

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
    
    
    
    @DeleteMapping("/users/{id}")
	public void delete(@PathVariable String id) {
		Long userId = Long.parseLong(id);
		userService.deleteById(userId);
	}
    
    @GetMapping("/downloadFile/{id}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id) { // parte di download
		// Load file from database
		User dbFile = imageService.recoverFile(id);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getTypeImage()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; typeImage=\"" + dbFile.getLastName() + "\"")
				.body(new ByteArrayResource(dbFile.getImage()));
	}
    
    
}  







