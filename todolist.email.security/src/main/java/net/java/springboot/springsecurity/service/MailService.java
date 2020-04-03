package net.java.springboot.springsecurity.service;

import javax.mail.MessagingException;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public interface MailService {
	public void inviaMail(String destinatarioMail, String oggettoMail, String messaggio) throws MessagingException;

}