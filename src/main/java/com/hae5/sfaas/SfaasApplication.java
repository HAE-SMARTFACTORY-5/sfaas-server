package com.hae5.sfaas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SfaasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SfaasApplication.class, args);
	}

}
