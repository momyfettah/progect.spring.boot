package net.java.springboot.springsecurity.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.java.springboot.springsecurity.model.Activity;
import net.java.springboot.springsecurity.model.Role;
import net.java.springboot.springsecurity.model.User;
import net.java.springboot.springsecurity.repository.UserRepository;
import net.java.springboot.springsecurity.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    /*
    @Override
	public User save(UserRegistrationDto registration) {
		// TODO Auto-generated method stub
	    User user = new User();
	    user.setEmail(registration.getEmail()); 
	    user.setPassword(passwordEncoder.encode(registration.getPassword()));
		try {
			user.setImage(registration.getImage().getBytes());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	    user.setRoles(Arrays.asList(new Role("USER")));
	    return userRepository.save(user);
	}
	
	*/
    
    
    public User save(UserRegistrationDto registration) {
    	
    	
    	
    	
        User user = new User();
        
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail()); 
        user.setBirthday(registration.getBirthday());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        try {
			user.setImage(registration.getImage().getBytes());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }
    
    public Long count() {

		return userRepository.count();
	}
   
    public void deleteById(Long userId) {

		userRepository.deleteById(userId);
	}



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
            user.getPassword(),
            mapRolesToAuthorities(user.getRoles()));
    }


	private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection <Role> roles) {
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
    }

	@Override
	public void addActivities(User user, List<Activity> activities) {
		
		
		
	}
}