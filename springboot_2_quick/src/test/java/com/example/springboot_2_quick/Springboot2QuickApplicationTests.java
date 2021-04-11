package com.example.springboot_2_quick;

import com.example.springboot_2_quick.bean.Person;
import com.example.springboot_2_quick.bean.student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class Springboot2QuickApplicationTests {
    @Autowired
    Person person;
    @Autowired
    student student;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        System.out.println(person);
    }

    @Test
    public void iocTest() {
        boolean b = applicationContext.containsBean("myStudent");
        System.out.println(b);
    }


}
