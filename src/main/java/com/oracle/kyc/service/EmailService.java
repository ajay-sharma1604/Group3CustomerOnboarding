package com.oracle.kyc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendKycRejectionEmail(String toEmail, String customerId, String remark) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("KYC Rejection Notice for Customer ID: " + customerId);
        message.setText("Dear Customer,\n\nYour KYC has been rejected for the following reason:\n\n" + remark + "\n\nPlease correct the issue and re-submit.\n\nThanks,\nBank Admin");
        mailSender.send(message);
    }
}
