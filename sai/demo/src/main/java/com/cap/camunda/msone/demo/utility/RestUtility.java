package com.cap.camunda.msone.demo.utility;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestUtility {
	public void triggerChild() {
		String url = "http://localhost:8181/CamundaWorkflow/rest/engine/default/message";

		// create an instance of RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);
		// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		// request body parameters
		Map<String, Object> map = new HashMap<>();
		map.put("messageName", "OrderDone");
		map.put("businessKey", "2");

		HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(map, headers);
		// send POST request
		ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
		System.out.println(response.getStatusCode());
		// check response
		if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
		    System.out.println("Request Successful");
		} else {
		    System.out.println("Request Failed");
		}	}
}
