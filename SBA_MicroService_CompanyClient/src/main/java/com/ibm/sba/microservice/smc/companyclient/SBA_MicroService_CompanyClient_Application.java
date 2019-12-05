package com.ibm.sba.microservice.smc.companyclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@RestController
@SpringBootApplication
@Import({SBA_MicroService_CompanyClient_Config.class })
public class SBA_MicroService_CompanyClient_Application {
 
	public static void main(String[] args) {
	    
		SpringApplication.run(SBA_MicroService_CompanyClient_Application.class, args);
	}
}
