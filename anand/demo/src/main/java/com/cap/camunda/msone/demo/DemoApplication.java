package com.cap.camunda.msone.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
public class DemoApplication {

	public static void main(final String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}



// 	@Override
// public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//     registry.addResourceHandler("/resources/**")
//             .addResourceLocations("/resources/", "file:resources/");
// }

}
