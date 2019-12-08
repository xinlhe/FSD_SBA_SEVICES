package com.ibm.sba.microservice.smc.ribbon.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import net.bluefsd.entity.SBAUserEntity;

public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;
    
    /**
     * if SBA_MicroService_UserClient service is down, will feedback "User Service System is busy now, Please wait for a moment!"
     * @param userEntity
     * @return
     * @throws AuthenticationException
     */
    @HystrixCommand(fallbackMethod="usersRibbonLoginBack")
    @RequestMapping(value = "/usersRibbonLogin", method = RequestMethod.POST)
    public Map usersRibbonLogin(SBAUserEntity userEntity) {
        
        Map<?, ?> map = (Map<?, ?>) restTemplate.postForObject("http://SBA_MicroService_UserClient/login", userEntity, SBAUserEntity.class);
        
        return map;
    }
    
    public String usersRibbonLoginBack() {
        
        return "User Service System is busy now, Please wait for a moment!";
    }

}
