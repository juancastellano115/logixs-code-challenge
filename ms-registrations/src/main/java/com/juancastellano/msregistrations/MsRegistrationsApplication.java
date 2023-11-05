package com.juancastellano.msregistrations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsRegistrationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsRegistrationsApplication.class, args);
	}

}
