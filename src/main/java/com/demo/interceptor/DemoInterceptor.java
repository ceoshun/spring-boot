package com.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
实现自定义拦截器只需要3步： 
1、创建我们自己的拦截器类并实现 HandlerInterceptor 接口。 
2、创建一个Java类继承WebMvcConfigurerAdapter，并重写 addInterceptors 方法。 
3、实例化我们自定义的拦截器，然后将对像手动添加到拦截器链中（在addInterceptors方法中添加）。 
*******只有经过DispatcherServlet 的请求，才会走拦截器链，我们自定义的Servlet 请求是不会被拦截的***********
*/
public class DemoInterceptor extends HandlerInterceptorAdapter{
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");

        long endTime = System.currentTimeMillis();

        System.out.println("本次请求的处理时间为："+new Long(endTime-startTime)+"ms");
        request.setAttribute("handlingTime", endTime-startTime);
        
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        HttpSession session=request.getSession();  

        session.setAttribute("User", "admin");
        if(session.getAttribute("User")!=null){  
            return true;  
        }  
        String url = "/login.html";  
        request.getRequestDispatcher(url).forward(request,response);  
        return true;
    }
}