package com.example.springboot_5_jdbc.mapper;

import com.example.springboot_5_jdbc.bean.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface employeeMapper {
     Employee getEmpById(Integer id);

     void insertEmp(Employee employee);
}
