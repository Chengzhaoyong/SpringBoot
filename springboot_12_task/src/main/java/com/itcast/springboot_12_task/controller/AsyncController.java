package com.itcast.springboot_12_task.controller;

import com.itcast.springboot_12_task.Service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @ResponseBody
    @GetMapping("/hello")
    public String asyncController() throws InterruptedException {
       asyncService.asyncTest();
         return "success";

    }
}
