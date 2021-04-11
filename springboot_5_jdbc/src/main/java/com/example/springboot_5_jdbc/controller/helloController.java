package com.example.springboot_5_jdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {
    @GetMapping("/hello1")
    @ResponseBody
    public String getd(){
        return "hellO";
    }
}
