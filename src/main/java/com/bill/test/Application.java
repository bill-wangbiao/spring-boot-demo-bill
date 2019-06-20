package com.bill.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @EnableApolloConfig
 */
@SpringBootApplication
@EnableAsync
/**
 * @author: wangbiao
 * @version 1.0.0
 * @Project spring-boot-demo-bill
 */
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
