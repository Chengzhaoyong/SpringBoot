package com.itcast.springboot_16_swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class Springboot16SwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot16SwaggerApplication.class, args);
    }

}
