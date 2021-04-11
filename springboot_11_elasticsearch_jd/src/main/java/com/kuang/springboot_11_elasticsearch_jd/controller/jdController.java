package com.kuang.springboot_11_elasticsearch_jd.controller;

import com.kuang.springboot_11_elasticsearch_jd.service.Jdservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class jdController {
@Autowired
private Jdservice jdservice;
    @RequestMapping("/index")
    public String test(){
        return "index";
    }

    //爬取数据，存到es
    @GetMapping("/content/{keyword}")
    @ResponseBody
    public boolean test01(@PathVariable("keyword") String keyword) throws IOException {
      return jdservice.parseContent(keyword);
    }
    // es查询数据
    @ResponseBody
    @GetMapping("/Search/{keyword}/{pageNo}/{pageSize}")
    public List<Map<String,Object>>  SearchEs(@PathVariable("keyword") String keyword,
                                              @PathVariable("pageNo") int pageNo,
                                              @PathVariable("pageSize") int pageSize) throws IOException {
        return jdservice.searchEs(keyword,pageNo,pageSize);
    }


}
