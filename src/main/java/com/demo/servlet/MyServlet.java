package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
Servlet注册的两种方式
1、在启动类中注册Servlet
    @Bean
    public ServletRegistrationBean MyServlet() {
        return new ServletRegistrationBean(new MyServlet(), "/myServlet/*");
    }

2、使用注解
  step1、添加注解
     @WebServlet(urlPatterns="/myServlet/*", description="Servlet的说明")
  step2、在启动类中进行扫描
     @ServletComponentScan

*/

//@WebServlet(urlPatterns="/myServlet/*", description="Servlet的说明")
public class MyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(">>>>>>>>>>doGet()<<<<<<<<<<<");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(">>>>>>>>>>doPost()<<<<<<<<<<<");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>这是：MyServlet</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}