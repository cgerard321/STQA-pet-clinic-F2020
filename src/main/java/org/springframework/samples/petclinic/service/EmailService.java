package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.samples.petclinic.model.EmailPackage;
import org.springframework.stereotype.Service;

// Contains the simple business logic used to send an email to an owner.
@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(EmailPackage emailPackage) throws MailException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(emailPackage.getReceiverEmail());
        mailMessage.setFrom(emailPackage.getSenderEmail());
        mailMessage.setSubject(emailPackage.getSubject());
        mailMessage.setText(emailPackage.getMessage());

        javaMailSender.send(mailMessage);
    }
}
