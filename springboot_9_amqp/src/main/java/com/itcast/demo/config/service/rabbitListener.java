package com.itcast.demo.config.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class rabbitListener {
    @RabbitListener(queues = "itcast.news")
    public void rabbitlistener(Map map){
        System.out.println(map+"监听");
    }
}
