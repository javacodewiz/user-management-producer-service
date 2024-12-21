package com.javacodewiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableCaching
public class UserManagementProducerServiceApplication {

	@GetMapping("/hello")
	public String hello()
	{
		return  "hello-world";
	}

	public static void main(String[] args) {
		SpringApplication.run(UserManagementProducerServiceApplication.class, args);
	}

}
