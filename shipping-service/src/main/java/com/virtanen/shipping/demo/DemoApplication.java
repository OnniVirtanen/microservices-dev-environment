package com.virtanen.shipping.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.virtanen")
public class DemoApplication {

    public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
