package com.ibm.sba.microservice.smc.userclient;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

/**
 * this is configration of user client
 * 
 * @author XinLongHe
 *
 * @since 2019.11.29
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan({ "net.bluefsd.model",
		/* "net.bluefsd.security.cfg", */ "com.ibm.sba.microservice.smc.userclient.filter", "net.bluefsd.comm",
		"net.bluefsd.comm.log", "com.ibm.sba.microservice.smc.userclient.service",
		"com.ibm.sba.microservice.smc.userclient.controller" })
@EntityScan("net.bluefsd.entity")
@EnableJpaRepositories("com.ibm.sba.microservice.smc.userclient.dao")
public class SBA_MicroService_UserClient_Config {

    @Bean // init Bean
    @LoadBalanced // load balance
    public RestTemplate restTemplate() {
        
        return new RestTemplate();
    }
}
