package com.itcast.springboot_12_task.controller;

import com.itcast.springboot_12_task.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {

    @Autowired
    private MailService mailService;
    @ResponseBody
    @GetMapping("/Controller")
    public void mailController(){
        mailService.mail();

    }
}
