package net.java.springboot.springsecurity.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.java.springboot.springsecurity.model.Activity;
import net.java.springboot.springsecurity.model.User;
import net.java.springboot.springsecurity.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
    //parte implementata
    Long count();
    
    void deleteById(Long userId);

	void addActivities(User user, List<Activity> activities);

	
	

}
