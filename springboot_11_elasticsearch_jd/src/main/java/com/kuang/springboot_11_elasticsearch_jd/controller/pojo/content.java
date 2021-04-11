package com.kuang.springboot_11_elasticsearch_jd.controller.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class content {
    private String img;
    private String price;
    private String title;
}
