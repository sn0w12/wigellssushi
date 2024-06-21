package com.example.wigellssushi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WigellssushiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WigellssushiApplication.class, args);
	}

}
