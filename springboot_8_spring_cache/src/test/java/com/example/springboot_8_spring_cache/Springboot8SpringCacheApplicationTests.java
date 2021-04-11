package com.example.springboot_8_spring_cache;

import com.example.springboot_8_spring_cache.bean.Department;
import com.example.springboot_8_spring_cache.mapper.departmentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class Springboot8SpringCacheApplicationTests {
@Autowired
departmentMapper departmentMapper;
@Autowired
StringRedisTemplate stringRedisTemplate;
@Autowired
    RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        String s=stringRedisTemplate.opsForValue().get("msg");
        System.out.println(s);
    }
@Autowired
RedisTemplate<Object,Department> DeptRedisTemplate;
    @Test
    public void test01(){
       Department department= departmentMapper.findDeptById(1);
        DeptRedisTemplate.opsForValue().set("emp-01",department);
    }

    @Test
    public void test02(){
     Department department=DeptRedisTemplate.opsForValue().get("emp-01");
        System.out.println(department);
    }
}
