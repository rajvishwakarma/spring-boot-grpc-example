package com.sample;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.services.HelloWorldClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrpcServerApplicationTests {

	@Autowired
	private HelloWorldClient helloWorldClient;

	@Test
	public void testSayHello() {
		assertThat(helloWorldClient.sayHello("Scott", "Tiger"))
		.isEqualTo("Hello Scott Tiger!");
	}

}
