package com.example.springboot_5_jdbc.mapper;

import com.example.springboot_5_jdbc.bean.Department;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Mapper
public interface departmentMapper {
    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(departmentName) values(#{departmentName})")
     int insertDepartment(Department department);


    @Delete("delete from department where id=#{id}")
    public int deleteDepartment(Integer id);


    @Update("update department set departmentName=#{department} where id=#{id}")
     int updateDepartment(Department department);

}
