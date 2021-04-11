package com.example.springboot_5_jdbc.controller;

import com.example.springboot_5_jdbc.bean.Department;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class jdbcController {

    @Autowired
    JdbcTemplate template;

    @ResponseBody
    @GetMapping("/hello")
    public List select(){
        String sql="select * from department";
      List list= template.queryForList(sql);
      return list;
    }
}
