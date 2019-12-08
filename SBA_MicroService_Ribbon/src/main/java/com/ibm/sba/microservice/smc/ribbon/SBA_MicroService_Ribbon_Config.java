package com.ibm.sba.microservice.smc.ribbon;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * this is configration of user client
 * 
 * @author XinLongHe
 *
 * @since 2019.11.29
 */
@Configuration
public class SBA_MicroService_Ribbon_Config {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        
        return new RestTemplate();
    }
}
