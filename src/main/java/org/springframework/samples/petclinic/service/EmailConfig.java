package org.springframework.samples.petclinic.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

// Contains the configuration needed for the SMTP client to send a successful request to the SMTP server.
@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        // I know, I know, this is horrible... Thank god this isn't a security course! :)
        mailSender.setUsername("merozwilliam@gmail.com");
        mailSender.setPassword("3Ke6^H%#IKxNhq8");

        // Since we are using Google's SMTP server, we have to enable the tls protocol.
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
