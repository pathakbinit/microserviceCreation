package com.zuul.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class Test {
	@RequestMapping(value = "/available")
	public String available() {
		return "Spring in Action";
	}

	@RequestMapping(value = "/checked-out")
	public String checkedOut() {
		return "Spring Boot in Action";
	}

	public static void main(String[] args) {
		SpringApplication.run(Test.class, args);
	}

}