package com.demo.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/*
Listener注册的两种方式
1、在启动类中注册
    @Bean
    public ServletListenerRegistrationBean myHttpSessionListener() {
        return new ServletListenerRegistrationBean(new MyHttpSessionListener());
    }
    
2、使用注解
  step1、添加注解
     @WebListener
  step2、在启动类中进行扫描
     @ServletComponentScan

*/
// @WebListener
public class MyHttpSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Session 被创建");
    }


    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("ServletContex初始化");
    }

}