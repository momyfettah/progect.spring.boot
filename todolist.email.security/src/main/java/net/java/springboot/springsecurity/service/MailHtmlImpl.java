package net.java.springboot.springsecurity.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailHtmlImpl implements MailHtml {
	
    private TemplateEngine templateEngine;
    
    @Autowired
    public MailHtmlImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }	

	@Override
	public String build(String messaggio) {
        Context context = new Context();
        context.setVariable("messaggio", messaggio);
        return templateEngine.process("mailFile", context);
	}

}