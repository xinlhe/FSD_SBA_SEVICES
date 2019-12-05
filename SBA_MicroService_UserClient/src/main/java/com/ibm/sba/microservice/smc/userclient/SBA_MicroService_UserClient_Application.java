package com.ibm.sba.microservice.smc.userclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Import;

/**
 * This is starter of user client.
 * 
 * @author XinLongHe
 *
 */
// @RestController
@SpringBootApplication
@EnableHystrix
@EnableEurekaClient  
@Import({SBA_MicroService_UserClient_Config.class })
public class SBA_MicroService_UserClient_Application {
 
	public static void main(String[] args) {
		
		SpringApplication.run(SBA_MicroService_UserClient_Application.class, args);
	}
	
}
