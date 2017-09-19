package com.mail;


import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;


public class EmailService {
	
	
  @Autowired
   private JavaMailSender mailSender;
  @Autowired
  private SpringTemplateEngine templateEngine;
  
  private final String INVITATION_TEMPLATE = "invitationMail";
    /* 
     * Send HTML mail  
     */
    public void sendEmail(final Locale locale, EmailData data) throws MessagingException,Exception {
        
        // Prepare the Thymeleaf evaluation context
        final Context thymeContext = new Context(locale);
        thymeContext.setVariable("data", data);
          
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,"UTF-8");
        message.setSubject(data.getSubject());
 
        // could have CC, BCC, will also take an array of Strings
        message.setTo(data.getRecipientEmail());
        message.setFrom(data.getSenderEmail());
        
        //Select the template
        final String htmlContent = this.templateEngine.process(INVITATION_TEMPLATE, thymeContext);
        message.setText(htmlContent, true /* isHtml */);

        this.mailSender.send(mimeMessage);

    }
    
    public void testInject(){
    	System.out.println("injected..");
    }

 
}
