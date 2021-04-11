package com.itcast.springboot_12_task.Service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
    @Async
    public void  asyncTest() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("success");
    }
}
