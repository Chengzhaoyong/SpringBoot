package com.example.springboot_2_quick.config;

import com.example.springboot_2_quick.bean.student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Bean
    public student myStudent() {
        return new student();
    }
}
