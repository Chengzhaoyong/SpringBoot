package com.itcast.springboot_12_task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot12TaskApplicationTests {

@Autowired
private JavaMailSenderImpl javaMailSender;

@Value("${spring.mail.username}")
private String from;
    @Test
    void contextLoads() throws MessagingException {
       //定制复杂的信息
        MimeMessage mimeMailMessage=javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMailMessage,true);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo("815838767@qq.com");
        mimeMessageHelper.setSubject("看片不啊");
        mimeMessageHelper.setText("乔老师");

        mimeMessageHelper.addAttachment("1.jpg",new File("C:\\Users\\阳光小伙\\Pictures\\Saved Pictures\\1.jpg"));
        mimeMessageHelper.addAttachment("2.jpg",new File("C:\\Users\\阳光小伙\\Pictures\\Saved Pictures\\2.jpg"));
       javaMailSender.send(mimeMailMessage);
    }

}
