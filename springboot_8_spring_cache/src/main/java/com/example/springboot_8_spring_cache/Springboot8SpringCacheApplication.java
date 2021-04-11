package com.example.springboot_8_spring_cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Springboot8SpringCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot8SpringCacheApplication.class, args);
    }

}
