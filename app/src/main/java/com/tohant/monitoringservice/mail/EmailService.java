package com.tohant.monitoringservice.mail;

import com.tohant.monitoringservice.mail.model.MonitoringMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${default.email.address}")
    private String address;

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(MonitoringMail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("noreply@tohant.com");
        mailMessage.setTo(address);
        mailMessage.setSubject("Report regarding resource availability");
        mailMessage.setText(mail.getReport());
        mailSender.send(mailMessage);
    }
}
