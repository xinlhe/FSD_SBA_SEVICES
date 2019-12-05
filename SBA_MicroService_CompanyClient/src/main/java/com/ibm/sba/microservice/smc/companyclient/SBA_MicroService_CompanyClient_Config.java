package com.ibm.sba.microservice.smc.companyclient;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan({ "net.bluefsd.model",  "net.bluefsd.comm",
		"net.bluefsd.comm.log", "com.ibm.sba.microservice.smc.*.service", "com.ibm.sba.microservice.smc.*.controller" })
@EntityScan("net.bluefsd.entity")
@EnableJpaRepositories({"com.ibm.sba.microservice.smc.*.dao", "net.bluefsd.dao"})
public class SBA_MicroService_CompanyClient_Config {

}
