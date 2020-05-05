package com.cap.camunda.msone.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.sql.SQLException;

import org.h2.tools.Server;

@SpringBootApplication
public class DemoApplication {

	public static void main(final String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	//allows to have h2 on a defined port in a shared mode. 
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseaServer() throws SQLException {
	return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "8083");
	}


// 	@Override
// public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//     registry.addResourceHandler("/resources/**")
//             .addResourceLocations("/resources/", "file:resources/");
// }

}
