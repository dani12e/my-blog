package com.project1.starter.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class AppConfig {

    @Value("${spring.mail.host}")
    private String mailHost;
    @Value("${spring.mail.port}")
    private String mailPort;
    @Value("${spring.mail.username}")
    private String mailUsername;
    @Value("${spring.mail.password}")
    private String mailPassword;
    @Value("${spring.mail.protocol}")
    private String mailTransportProtocol;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private Boolean mailSmtpAuth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private Boolean mailSmtpEnable;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(Integer.parseInt(mailPort));
        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", mailTransportProtocol);
        props.put("mail.smtp.auth", mailSmtpAuth.toString());
        props.put("mail.smtp.starttls.enable", mailSmtpEnable.toString());
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.trust", mailHost);


        return mailSender;
    }
}
