package com.oracle.kyc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.oracle.kyc.proxy")
public class KycServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KycServiceApplication.class, args);
	}

}
