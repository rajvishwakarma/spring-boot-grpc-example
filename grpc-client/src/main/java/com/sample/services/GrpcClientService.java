package com.sample.services;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sample.grpc.services.Greeting;
import com.sample.grpc.services.HelloServiceGrpc;
import com.sample.grpc.services.User;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class GrpcClientService {
	
	private Logger LOGGER = LoggerFactory.getLogger(GrpcClientService.class);
	
	private HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub;
	
	@PostConstruct
    private void initializeClient() {
		ManagedChannel managedChannel = ManagedChannelBuilder
		        .forAddress("localhost", 6565).usePlaintext(true).build();
		
		helloServiceBlockingStub = HelloServiceGrpc.newBlockingStub(managedChannel);
    }
	
	public String sayhello(String fname, String lname) {
		
		User user = User.newBuilder().setFirstName(fname).setLastName(lname).build();
		LOGGER.info("client sending {}", user);
		
		Greeting greeting = helloServiceBlockingStub.sayHello(user);
		LOGGER.info("client received {}", greeting);
			
		return greeting.getMessage();
	}
	
	
}
