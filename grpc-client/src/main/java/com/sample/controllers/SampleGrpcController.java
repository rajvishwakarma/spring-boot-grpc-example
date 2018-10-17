package com.sample.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.services.GrpcClientService;

@RestController
public class SampleGrpcController {
	
	private Logger LOGGER = LoggerFactory.getLogger(SampleGrpcController.class);
	
	@Autowired
	GrpcClientService clientService;
	
	@GetMapping("/grpc")
	public ResponseEntity<String> helloApi(@RequestParam("fname") String fname, @RequestParam("lname") String lname){
		LOGGER.info("In SampleRestController: Params- fname="+fname+" lname="+lname);
		
		return new ResponseEntity<String>(clientService.sayhello(fname, lname), HttpStatus.OK);
	}
}
