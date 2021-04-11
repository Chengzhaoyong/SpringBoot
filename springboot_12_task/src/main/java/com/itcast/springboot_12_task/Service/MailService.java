package com.itcast.springboot_12_task.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String from1;
    @Scheduled(fixedDelay = 2000)
    public void mail(){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setSubject("通知-今晚看片");
        simpleMailMessage.setText("啊啊啊啊啊啊地叫");
        simpleMailMessage.setFrom(from1);

        simpleMailMessage.setTo("815838767@qq.com");
        javaMailSender.send(simpleMailMessage);
    }
}
