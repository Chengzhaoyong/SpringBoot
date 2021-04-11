package com.example.springboot_8_spring_cache.bean;

import java.io.Serializable;

public class Department implements Serializable {
    private Integer id;
    private String departmentName;
    public Department(Integer id,String departmentName){
        this.departmentName=departmentName;
        this.id=id;

    }
    public Department(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
