package com.example.springboot_5_jdbc;

import com.example.springboot_5_jdbc.mapper.departmentMapper;
import com.example.springboot_5_jdbc.repository.departmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Springboot5JdbcApplicationTests {

    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() throws SQLException {
                System.out.println(dataSource.getClass());
        Connection connection=dataSource.getConnection();

        System.out.println(connection);
        connection.close();
    }


    @Autowired
    departmentMapper departmentMapper;
    @Test
    void hah(){
        System.out.println(departmentMapper.getDeptById(1));
    }


    @Autowired
    departmentRepository departmentRepository;

    @Test
    public void get(){
       int i=departmentRepository.updateDepartment("你爱我",2);
        System.out.println(i);
    }

}
