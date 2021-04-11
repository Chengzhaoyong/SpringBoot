package com.itcast.springboot_16_swagger.config;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.http.server.PathContainer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {


    @Bean
    public Docket getDocket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("王海煜");
    }
    @Bean
    public Docket getDocket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("周锦彬");
    }


    @Bean
    public Docket getDocket(Environment environment){
        Profiles profile= Profiles.of("dev");
        boolean b=environment.acceptsProfiles(profile);
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("程兆永")
                .apiInfo(getApiInfo())
                //使swagger是否启动
                .enable(b)
                .select()
                //RequestHandlerSelectors  配置扫描的方式
                //any() 所有   none() 都不扫描
                //withMethodAnnotation  含有该注解的类才被扫描
                .apis(RequestHandlerSelectors.basePackage("com.itcast.springboot_16_swagger.controller"))

                //过滤的路径
             // .paths(PathSelectors.ant("包名"))
                .build();
    }

    public ApiInfo getApiInfo(){
        Contact  contact=new Contact("程兆永","https://www.bilibili.com/","1658020255@qq.com");
        return new ApiInfo(
                 "程兆永的Swagger文档",
                "即使在小的帆也能远航",
                "v1.0",
                "https://www.bilibili.com/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/license/LICENSE-2.0",
                new ArrayList()

        );
    }
}
