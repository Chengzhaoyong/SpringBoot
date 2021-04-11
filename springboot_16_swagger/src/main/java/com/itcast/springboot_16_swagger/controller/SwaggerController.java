package com.itcast.springboot_16_swagger.controller;


import com.itcast.springboot_16_swagger.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwaggerController {
@ApiOperation("hello注释")
    @GetMapping("/hello/{pass}")
    public String helloSwagger(@ApiParam("pass") String hello){
        return hello;
    }

    @GetMapping("/user")
    public User getUser(){
        return new User();
    }
}
