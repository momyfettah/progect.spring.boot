package net.java.springboot.springsecurity.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.java.springboot.springsecurity.model.User;
import net.java.springboot.springsecurity.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}