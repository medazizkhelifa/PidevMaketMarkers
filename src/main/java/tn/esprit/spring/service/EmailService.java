package tn.esprit.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
//import javax.mail.*;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;//qui est l'interface de Spring Boot pour envoyer des e-mails.

    public void sendEmail(String to, String subject, String body) {
        System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("binab0985@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        emailSender.send(message);
        System.out.println("Mail sent successfully ...............");
    }
}
