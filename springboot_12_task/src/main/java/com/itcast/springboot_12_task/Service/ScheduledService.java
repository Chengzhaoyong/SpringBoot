package com.itcast.springboot_12_task.Service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

  //  @Scheduled(cron = "0 * * * * *")
    @Scheduled(fixedDelay = 1000)
    public void scheduledtest(){
        System.out.println("success");
    }

    
}
