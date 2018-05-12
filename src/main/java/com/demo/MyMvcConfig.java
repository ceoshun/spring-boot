package com.demo;

import com.demo.filter.MyFilter;
import com.demo.interceptor.DemoInterceptor;
import com.demo.listener.MyHttpSessionListener;
import com.demo.listener.MyServletContextListener;
import com.demo.servlet.MyServlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {// 2

	/*
	添加静态资源映射 
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		/* InternalResourceViewResolver 实现了ViewResolver接口
		 */
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		// 配置JSP的ViewResolver，映射路径和实际页面的位置。
		// 可以从编译好的xxx-SNAPSHOT中看到此路径
		viewResolver.setPrefix("/WEB-INF/classes/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		// 对外暴露的访问路径
		registry.addResourceHandler("/assets/**")
		// 指定文件放置目录，
		.addResourceLocations("classpath:/assets/");
	}



	// 添加拦截器配置
	@Bean
	public DemoInterceptor demoInterceptor(){
		return new DemoInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(demoInterceptor()).addPathPatterns("/*")
		.excludePathPatterns("/login","/verify");  

		super.addInterceptors(registry);
	}
	

	@Bean
    public ServletRegistrationBean MyServlet() {
        return new ServletRegistrationBean(new MyServlet(), "/myServlet/*");
	}
	
	@Bean
    public ServletListenerRegistrationBean myServletContextListener() {
		ServletListenerRegistrationBean listener = new ServletListenerRegistrationBean<>();
		listener.setListener(new MyServletContextListener());
        return listener;
	}
	
	@Bean
    public ServletListenerRegistrationBean myHttpSessionListener() {
        return new ServletListenerRegistrationBean(new MyHttpSessionListener());
	}
	
	@Bean FilterRegistrationBean myFilter(){
		FilterRegistrationBean filter = new FilterRegistrationBean<>();
		filter.setFilter(new MyFilter());
		filter.addUrlPatterns("/myServlet/*");
		return filter;
	}
}
