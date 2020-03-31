package net.java.springboot.springsecurity.service;

import java.util.List;

import net.java.springboot.springsecurity.model.Activity;

public interface ActivityService {

    Activity save(Activity activity);
    Activity findByID(Long id);
    void delete(Long id);
	List<Activity> getAllActivities();	
    
}