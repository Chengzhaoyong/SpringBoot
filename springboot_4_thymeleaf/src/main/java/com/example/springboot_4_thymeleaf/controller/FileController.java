package com.example.springboot_4_thymeleaf.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.UUID;

@Controller
public class FileController {
    //向文件上传页面跳转
    @GetMapping("/toUpload")
    public String toUpload() {
        return "UpLoad";
    }

    //文件上传管理
    @PostMapping("/uploadFile")
    public String uploadFile(MultipartFile[] fileUpload, Model model) {
        //默认文件上传成功，并返回转态消息
        model.addAttribute("uploadStatus", "上传文件成功！");
        for (MultipartFile file : fileUpload) {
            //获取文件名和后缀
            String fileName = file.getOriginalFilename();
            //重新生成文件名
            fileName = UUID.randomUUID() + "_" + fileName;
            //指定上传文件本地存储目录，不存在则需要提前创建
            String dirPath = "E:/file/";

            File FilePath = new File(dirPath);
            if (!FilePath.exists()) {
                FilePath.mkdir();
            }

            try {
                file.transferTo(new File(dirPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
                //上传失败
                model.addAttribute("UploadStatus", "上传失败：" + e.getMessage());
            }
        }

        return "UpLoad";
    }

    @GetMapping("/toDownload")
    public String toDownload() {
        return "download";
    }

    //文件下载管理
    @GetMapping("/download")
    public ResponseEntity<byte[]> fileDownload(String filename, HttpServletRequest request) throws UnsupportedEncodingException {
        //指定要下载的文件根路径
        String dirPath = "E:/file/";


        File file = new File(dirPath + File.separator + filename);
        //设置响应头
        HttpHeaders headers = new HttpHeaders();

        //下载前对文件名转码
        filename = getFilename(request, filename);

        //通知浏览器打开
        headers.setContentDispositionFormData("attachment", filename);
        //定义以流的方式下载返回文件数据
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        try {
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<byte[]>(e.getMessage().getBytes(), HttpStatus.EXPECTATION_FAILED);

        }


    }

    public String getFilename(HttpServletRequest request, String filename) throws UnsupportedEncodingException {
        String[] ieBrowersKeyWords = {"MSIE", "Trident", "Edge"};

        //获取请求头代理信息
        String userAgent = request.getHeader("User-Agent");
        for (String keyWord : ieBrowersKeyWords) {
            if (userAgent.contains(keyWord)) {
                return URLEncoder.encode(filename, "utf-8").replace("+", " ");

            }
        }

        return new String(filename.getBytes("utf-8"), "ISO-8859-1");
    }

}
