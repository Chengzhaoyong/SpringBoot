package com.example.springboot_5_jdbc.controller;

import com.alibaba.druid.sql.dialect.odps.parser.OdpsExprParser;
import com.example.springboot_5_jdbc.bean.Department;
import com.example.springboot_5_jdbc.bean.Employee;
import com.example.springboot_5_jdbc.mapper.departmentMapper;
import com.example.springboot_5_jdbc.mapper.employeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class deptController {
    @ResponseBody
    @GetMapping("/haha")
    public  String haha(){
        return "hahah";
    }

   @Autowired
    departmentMapper departmentMapper;

    @GetMapping("/dept/{id}")
    @ResponseBody
    public Department getDepartment(@PathVariable("id") Integer id){
        return departmentMapper.getDeptById(id);
    }


    @Autowired
    employeeMapper employeeMapper;

    @GetMapping("/emp/{id}")
    @ResponseBody
    public Employee getEmp(@PathVariable("id") Integer id){
        return employeeMapper.getEmpById(id);
    }

    @GetMapping("/dept")
    public Department get(Department department){
        departmentMapper.insertDepartment(department);
        return department;
    }

}
