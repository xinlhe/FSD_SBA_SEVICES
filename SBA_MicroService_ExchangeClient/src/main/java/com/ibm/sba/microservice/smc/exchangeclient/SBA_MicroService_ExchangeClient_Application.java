package com.ibm.sba.microservice.smc.exchangeclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@RestController
@SpringBootApplication
@Import({SBA_MicroService_ExchangeClient_Config.class })
public class SBA_MicroService_ExchangeClient_Application {
 
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext();		
		SpringApplication.run(SBA_MicroService_ExchangeClient_Application.class, args);
	}
}
