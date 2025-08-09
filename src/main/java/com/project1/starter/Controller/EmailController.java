package com.project1.starter.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project1.starter.Services.EmailService;
import com.project1.starter.util.email.EmailDetails;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendMail(@RequestBody EmailDetails details){
        return emailService.sendSimpleMail(details).toString();
    }
}