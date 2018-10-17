package com.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.models.User;

@RestController
public class SampleRestController {
	
	private Logger LOGGER = LoggerFactory.getLogger(SampleRestController.class);
	
	@PostMapping("/hello")
	public ResponseEntity<String> sayHello(@RequestBody User user){
		LOGGER.info("In SampleRestController: Params- user="+user);
		
		String response = "Hello " + user.getFname() + " " + user.getLname() + "!";
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}
