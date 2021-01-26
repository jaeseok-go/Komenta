package com.komenta.be.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "이메일 인증", notes = "인증번호를 만들어서 비밀번호와 함께 클라이언트에는 반환하고 회원 메일로는 이메일 전송")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_email", value = "회원 아이디", dataType = "String", required = true)
    })
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
