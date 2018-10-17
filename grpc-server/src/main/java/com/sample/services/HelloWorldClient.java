package com.sample.services;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sample.grpc.services.Greeting;
import com.sample.grpc.services.HelloServiceGrpc;
import com.sample.grpc.services.User;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class HelloWorldClient {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(HelloWorldClient.class);

  private HelloServiceGrpc.HelloServiceBlockingStub helloWorldServiceBlockingStub;

  @PostConstruct
  private void init() {
    ManagedChannel managedChannel = ManagedChannelBuilder
        .forAddress("localhost", 6565).usePlaintext().build();

    helloWorldServiceBlockingStub =
        HelloServiceGrpc.newBlockingStub(managedChannel);
  }

  public String sayHello(String firstName, String lastName) {
    User person = User.newBuilder().setFirstName(firstName)
        .setLastName(lastName).build();
    LOGGER.info("client sending {}", person);

    Greeting greeting =
        helloWorldServiceBlockingStub.sayHello(person);
    LOGGER.info("client received {}", greeting);

    return greeting.getMessage();
  }

}
