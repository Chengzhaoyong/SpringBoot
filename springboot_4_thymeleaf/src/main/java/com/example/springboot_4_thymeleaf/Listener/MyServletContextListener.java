package com.example.springboot_4_thymeleaf.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("销毁");
    }
}
