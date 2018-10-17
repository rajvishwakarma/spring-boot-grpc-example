package com.sample.services;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sample.grpc.services.Greeting;
import com.sample.grpc.services.HelloServiceGrpc;
import com.sample.grpc.services.User;

import io.grpc.stub.StreamObserver;

@GRpcService
public class HelloService extends HelloServiceGrpc.HelloServiceImplBase {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloService.class);

	@Override
	public void sayHello(User request, StreamObserver<Greeting> responseObserver) {
		LOGGER.info("server received {}", request);

		String message = "Hello " + request.getFirstName() + " " + request.getLastName() + "!";
		Greeting greeting = Greeting.newBuilder().setMessage(message).build();
		LOGGER.info("server responded {}", greeting);

		responseObserver.onNext(greeting);
		responseObserver.onCompleted();
	}
}
