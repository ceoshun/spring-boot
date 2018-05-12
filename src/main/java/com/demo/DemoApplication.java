package com.demo;

import com.demo.interceptor.DemoInterceptor;
import com.demo.listener.MyHttpSessionListener;
import com.demo.listener.MyServletContextListener;
import com.demo.servlet.MyServlet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ImportResource({
	//"classpath:some-context.xml",
	//"classpath:*.properties"
})
//@ServletComponentScan
public class DemoApplication {

	@Value("${server.port}")
	private String serverPort;

	@Value("${server.context-path}")
	private String serverContext;

	@RequestMapping("/")
	String index(){
		return "hello spring-boot";// + serverPort + serverContext;
	}

	public static void main(String[] args) {
		//SpringApplication.run(DemoApplication.class, args);

		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.run(args);

		// 关闭banner显示
		app.setBannerMode(Mode.OFF);
	}
}
