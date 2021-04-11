package com.example.springboot_4_thymeleaf.controller;

import com.example.springboot_4_thymeleaf.dao.DepartmentDao;
import com.example.springboot_4_thymeleaf.dao.EmployeeDao;
import com.example.springboot_4_thymeleaf.entities.Department;
import com.example.springboot_4_thymeleaf.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @GetMapping("/emp/list")
    public String list(Model model) {
        Collection<Employee> e = employeeDao.getAll();
        model.addAttribute("emps", e);

        return "emp/list";


    }

    //    添加页面
    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/emp")
    public String add(Model model) {
        Collection<Department> collection = departmentDao.getDepartments();
        model.addAttribute("dept", collection);
        return "emp/add";
    }


    @PostMapping("/emp")
    public String save(Employee employee) {
        employeeDao.save(employee);
        //重定向
        return "redirect:/emp/list";
    }

    @GetMapping("/emp/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {

        Employee e1 = employeeDao.get(id);
        System.out.println(e1);
        model.addAttribute("emp", e1);

        Collection<Department> collection = departmentDao.getDepartments();
        model.addAttribute("dept", collection);

        return "emp/add";
    }

    @PutMapping("/emp")
    public String update(Employee employee) {
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emp/list";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emp/list";
    }


}
