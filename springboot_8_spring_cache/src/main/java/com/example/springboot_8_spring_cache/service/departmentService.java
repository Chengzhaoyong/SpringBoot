package com.example.springboot_8_spring_cache.service;

import com.example.springboot_8_spring_cache.bean.Department;
import com.example.springboot_8_spring_cache.mapper.departmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@CacheConfig(cacheNames="emp")
@Component
@Service
public class departmentService {
    @Autowired
  departmentMapper departmentMapper;

@Cacheable()
    public Department test(Integer id){
        System.out.println("查询"+id+"记录");
        return departmentMapper.findDeptById(id);
    }

    @CachePut(key = "#department.id")
    public Department updateDept(Department department){
      departmentMapper.updateDept(department);
      return department;

    }

    @CacheEvict(key="#id")
    public void delete(Integer id){
     //  departmentMapper.deleteDept(id);
        System.out.println("delete:"+id);
    }

}
