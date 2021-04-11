package com.itcast.springboot_12_task.controller.Scheduled;

import com.itcast.springboot_12_task.Service.ScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ScheduledController {

    @Autowired
    private ScheduledService scheduledService;
    @ResponseBody
    @GetMapping("/Scheduled")
    public void scheduledController(){
        scheduledService.scheduledtest();

    }
}
