package com.demo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/*
Listener注册的两种方式
1、在启动类中注册
    @Bean
    public ServletListenerRegistrationBean myServletContextListener() {
        return new ServletListenerRegistrationBean(new MyServletContextListener());
    }
    
2、使用注解
  step1、添加注解
     @WebListener
  step2、在启动类中进行扫描
     @ServletComponentScan

*/

//@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

        System.out.println("ServletContex销毁");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {

        System.out.println("ServletContex初始化");
    }

}