package com.techshop.web.busines.services;

import com.techshop.web.model.dto.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {

    @Autowired
    private JavaMailSender javaMailSendet;

    public void sendMailMail(EmailDto emailDto){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(emailDto.getFrom());
        mail.setTo(emailDto.getTo());
        mail.setSubject(emailDto.getSubject());
        mail.setText(emailDto.getBody());
        javaMailSendet.send(mail);
    }
}
