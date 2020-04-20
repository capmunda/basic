package com.cap.camunda.msone.demo;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Logger;

import org.camunda.bpm.client.ExternalTaskClient;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cap.camunda.msone.demo.utility.RestUtility;

@SpringBootApplication
public class DemoApplication {
	private final static Logger LOGGER = Logger.getLogger(DemoApplication.class.getName());
	
	
	
	public static void main(final String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		    
		    ExternalTaskClient client = ExternalTaskClient.create().baseUrl("http://localhost:8180/CamundaWorkflow/rest/engine/default")
					.asyncResponseTimeout(10000) // long polling timeout
					.build();

		    //Task-1 Call activity with external task
		    //BPMN WeatherCheck.bpmn (parent) printForecast.bpmn(child)
			// subscribe to an external task topic as specified in the process
			client.subscribe("forecast").lockDuration(1000) // the default lock duration is 20 seconds, but you can
			// override this
			.handler((externalTask, externalTaskService) -> {
			String day=externalTask.getVariable("day");
			
			LOGGER.info("\n Day:"+ day +"\n weather is awesome\n" + new Timestamp(new Date().getTime()));
				externalTaskService.complete(externalTask);
			}).open();
			
			
			//Task-3 Spin out new process from external task
			//BPMN externalChildTrigger.bpmn(parent) childProcess.bpmn(child)
			// subscribe to an external task topic as specified in the process
			RestUtility restCall = new RestUtility();
			client.subscribe("triggerChild").lockDuration(1000) // the default lock duration is 20 seconds, but you can
					// override this
					.handler((externalTask, externalTaskService) -> {
						//String day = externalTask.getVariable("day");
						restCall.triggerChild();
						LOGGER.info("\n In external task \n" + new Timestamp(new Date().getTime()));
						externalTaskService.complete(externalTask);
					}).open();

		  }
	
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseaServer() throws SQLException {
	return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "8182");
	}
	}

// 	@Override
// public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//     registry.addResourceHandler("/resources/**")
//             .addResourceLocations("/resources/", "file:resources/");
// }


