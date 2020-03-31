package net.java.springboot.springsecurity.model;


import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;

import net.java.springboot.springsecurity.service.MailService;

public class MyRun implements Runnable {
	
	@Autowired
	MailService mail;
	
	private Activity activity;
	
	public MyRun(Activity activity, MailService mail) {
		this.activity = activity;
		this.mail = mail;
	}

	@Override
	public void run() {
		User currUser = activity.getUser();
		try {
			mail.inviaMail(currUser.getEmail(), "Reminder " + activity.getActivityTitle(), "This activity will expired in 30 minutes.");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}

}