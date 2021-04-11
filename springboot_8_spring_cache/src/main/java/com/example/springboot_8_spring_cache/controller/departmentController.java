package com.example.springboot_8_spring_cache.controller;

import ch.qos.logback.core.util.InvocationGate;
import com.example.springboot_8_spring_cache.bean.Department;
import com.example.springboot_8_spring_cache.service.departmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class departmentController {

    @Autowired
    departmentService departmentService;
    @ResponseBody
    @GetMapping("/dept1")
    public Department findDeptById(@RequestParam("id") Integer id)
    {

        return departmentService.test(id);
    }


    @GetMapping("/update")
    @ResponseBody
    public Department update(@RequestParam("id") Integer id,@RequestParam("departmentName") String departmentName){
     Department department=new Department(id,departmentName);
     return departmentService.updateDept(department);

    }

    @ResponseBody
    @GetMapping("/delete")
    public String delete(Integer id){
        departmentService.delete(id);
      return "success";
    }



}
