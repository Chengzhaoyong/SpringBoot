package com.example.springboot_8_spring_cache.mapper;

import com.example.springboot_8_spring_cache.bean.Department;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface departmentMapper  {

 @Select("select * from department where id=#{id}")
  Department findDeptById(Integer id);

 @Insert("insert into department(departmentName) values(#{departmentName})")
    int insertDept(Department department);

 @Delete("delete from department where id=#{id}")
    int deleteDept(Integer id);

 @Update("update department set departmentName=#{departmentName} where id=#{id}")
    int updateDept(Department department);
}
