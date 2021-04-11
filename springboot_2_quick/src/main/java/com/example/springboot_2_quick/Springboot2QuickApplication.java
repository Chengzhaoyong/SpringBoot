package com.example.springboot_2_quick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:student.xml")
@SpringBootApplication
public class Springboot2QuickApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot2QuickApplication.class, args);
    }

}
