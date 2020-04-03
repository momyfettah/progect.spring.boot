package net.java.springboot.springsecurity.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

@Service
public class MailServiceImpl implements MailService{

	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@Autowired
	private MailHtml mailHtml;

	public void inviaMail(String destinatarioMail, String oggettoMail, String messaggioMail) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setSubject(oggettoMail);
		mimeMessageHelper.setTo(destinatarioMail);
		String content = mailHtml.build(messaggioMail);
		mimeMessageHelper.setText(content, true);
		javaMailSender.send(mimeMessage);
	}

}