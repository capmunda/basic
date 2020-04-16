package com.example.workflow;

import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Logger;

import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
//@EnableProcessApplication
public class Application {

	/*
	 * @Autowired private RuntimeService runtimeService;
	 */
	 
	 private final static Logger LOGGER = Logger.getLogger(Application.class.getName());
  public static void main(String[] args) {
    SpringApplication.run(Application.class);
    
    
    ExternalTaskClient client = ExternalTaskClient.create().baseUrl("http://localhost:8080/engine-rest")
			.asyncResponseTimeout(10000) // long polling timeout
			.build();

	// subscribe to an external task topic as specified in the process
	client.subscribe("forecast").lockDuration(1000) // the default lock duration is 20 seconds, but you can
	// override this
	.handler((externalTask, externalTaskService) -> {
	String day=externalTask.getVariable("day");
	
	LOGGER.info("/n Day:"+ day +"/n weather is awesome/n" + new Timestamp(new Date().getTime()));
		externalTaskService.complete(externalTask);
	}).open();
  }
  
	/*
	 * @EventListener private void processPostDeploy(PostDeployEvent event) {
	 * runtimeService.startProcessInstanceByKey("demo1-process"); }
	 */
 
	/*
	 * @Bean public ServletContextInitializer initializer() { return new
	 * ServletContextInitializer() {
	 * 
	 * @Override public void onStartup(ServletContext servletContext) throws
	 * ServletException { servletContext.addListener(new
	 * CustomJacksonDateFormatListener()); servletContext.setInitParameter(
	 * "org.camunda.bpm.engine.rest.jackson.dateFormat", "yyyy-MM-dd'T'HH:mm:ss"); }
	 * }; }
	 */
}