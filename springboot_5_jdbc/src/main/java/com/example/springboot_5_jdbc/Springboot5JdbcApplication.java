package com.example.springboot_5_jdbc;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot5JdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot5JdbcApplication.class, args);
    }

}
