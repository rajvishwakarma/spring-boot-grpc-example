package com.sample.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sample.models.User;

@RestController
public class SampleRestController {
	
	private Logger LOGGER = LoggerFactory.getLogger(SampleRestController.class);
	
	@GetMapping("/rest")
	public ResponseEntity<String> helloApi(@RequestParam("fname") String fname, @RequestParam("lname") String lname){
		LOGGER.info("In SampleRestController: Params- fname="+fname+" lname="+lname);
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<User> entity = new HttpEntity<>(new User(fname, lname) , requestHeaders);

        ResponseEntity<String> responseEntity = new RestTemplate().exchange("http://localhost:8081/hello",
                    HttpMethod.POST, entity, String.class);
        
        if(! responseEntity.getStatusCode().equals(HttpStatus.OK)) {
        	LOGGER.info("In SampleRestController: Something Went Wrong!");
        	return new ResponseEntity<String>("Something Went Wrong!", HttpStatus.BAD_REQUEST);
        }
        
        LOGGER.info("In SampleRestController: SUCCESS");
		return new ResponseEntity<String>(responseEntity.getBody(), HttpStatus.OK);
	}
}
