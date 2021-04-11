package com.example.springboot_5_jdbc.repository;

import com.example.springboot_5_jdbc.bean.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Component
@Repository
public interface departmentRepository extends JpaRepository<Department,Integer> {

    @Transactional
    @Modifying
    @Query("update department set departmentName =?1 where id=?2")
    int updateDepartment(String departmentName,Integer id);
}
