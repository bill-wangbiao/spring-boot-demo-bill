package com.bill.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
//@EnableApolloConfig
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
