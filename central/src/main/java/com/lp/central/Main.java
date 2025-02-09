package com.lp.central;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableRabbit
@SpringBootApplication
@EnableDiscoveryClient
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
