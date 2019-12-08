package com.ibm.sba.microservice.smc.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableHystrix
public class SBA_MicroService_Ribbon_Application 
{
    public static void main( String[] args )
    {
        SpringApplication.run(SBA_MicroService_Ribbon_Application.class, args);
    }
}
