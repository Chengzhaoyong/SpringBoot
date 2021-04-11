package com.itcast.springboot_16_swagger.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户实体类")
public class User {
    @ApiModelProperty("名字")
    public String name;
    @ApiModelProperty("年龄")
    public int age;
}
