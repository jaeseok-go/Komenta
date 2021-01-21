package com.komenta.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@RestController
@RequestMapping("/check")
public class Testmail  {
    @Autowired
    public JavaMailSender javaMailSender;

    @GetMapping("/sendEmail")
    public void sendMail(String EmailAddress) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(EmailAddress);
//        message.setFrom("komento201@gmail.com");
        message.setSubject("Test Email");
        message.setText("Did you get it?");
        javaMailSender.send(message);
    }
}
