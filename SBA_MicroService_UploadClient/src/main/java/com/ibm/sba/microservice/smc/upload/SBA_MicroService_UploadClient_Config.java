package com.ibm.sba.microservice.smc.upload;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan({ "net.bluefsd.model",  "net.bluefsd.*.cfg", "net.bluefsd.*.filter", "net.bluefsd.comm",
		"net.bluefsd.comm.log", "net.bluefsd.*.service", "net.bluefsd.*.controller" })
@EntityScan("net.bluefsd.entity")
@EnableJpaRepositories("net.bluefsd.dao")
public class SBA_MicroService_UploadClient_Config {

}
