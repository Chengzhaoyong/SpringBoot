package com.itcast.demo;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {
@Autowired
RabbitTemplate rabbitTemplate;
    @Test
    void contextLoads() {

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("msg","这是第3个消息");
        map.put("data", Arrays.asList("helloworld",123,true));

        rabbitTemplate.convertAndSend("exchanges:direct","itcast.news",map);
    }

    @Test
    public void test01(){
        Object o=rabbitTemplate.receiveAndConvert("itcast.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }
    @Autowired
    AmqpAdmin amqpAdmin;

    ///创建交换机
    @Test
    public void createExchange(){
        amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));
        System.out.println("创建成功");

    }

//绑定
    @Test
    public void Binding(){
     amqpAdmin.declareBinding(new Binding("itcast.news", Binding.DestinationType.QUEUE,"amqpAdmin.exchange","itcast.news",null));


    }



}
