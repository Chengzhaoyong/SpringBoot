package com.example.springboot_7_start_test.controller;

import com.example.itcastspringbootstartautoconfigurer.start.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    @ResponseBody
    @GetMapping("/hello")
    public String get(){
    return helloService.getHello();
}
}
