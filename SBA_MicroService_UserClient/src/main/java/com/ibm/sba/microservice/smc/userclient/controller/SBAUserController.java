package com.ibm.sba.microservice.smc.userclient.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ibm.sba.microservice.smc.userclient.service.SBAUserService;
import com.ibm.sba.microservice.smc.userclient.util.SBAMailsUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import net.bluefsd.comm.controller.BaseController;
import net.bluefsd.entity.SBAUserEntity;

/**
 * User controller
 * 
 * @author XinLongHe
 *
 * @since 2019.11.29
 */
@RestController
@CrossOrigin
@RequestMapping("/smcuser")
public class SBAUserController extends BaseController {

    @Autowired
    public SBAUserService sbaUserService;

    @Autowired
    public SBAMailsUtil mailsUtil;
    
    @Autowired
    private RestTemplate restTemplate;
    
    /*
     * createToken
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map createToken(@RequestBody SBAUserEntity userEntity) throws Exception {
        
        try {
            
            Map<String, Object> map = new HashMap<>();
            
            String token = sbaUserService.doLogin(userEntity.getUserName(), userEntity.getPassword());
            
            userEntity = sbaUserService.getUserBean(userEntity);

            if(userEntity.getUserName().equalsIgnoreCase("admin")) {
                
                map.put("role", "admin");
            }else {
                
                map.put("role", "user");
            }
            
            map.put("status", "0");
            map.put("token", token);
            map.put("retMsg", "success");
            map.put("uid", userEntity.getId());
            
            return map;
            
        } catch (Exception ex) {
            
            return errorMessage(ex.getMessage());
            
        }
    }
    
    
    /*
     * createUserAccount
     *  
     * @author : xinlhe
     * 
     * @params : SBAUserEntity
     * 
     * @version : v1
     */
    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public Map createUserAccount(@RequestBody SBAUserEntity userEntity) throws JsonProcessingException {
        
        if (userEntity.getUserName().isEmpty() || userEntity.getPassword().isEmpty()) {
            
            return super.errorMessage("User name or User password could not be empty !!!");
            
        }
        
        try {
            
                if(userEntity.getId() != 0) {
                    
                    sbaUserService.saveOrUpdateProfile(userEntity);
                }
                else {
                    
                    sbaUserService.createUserAccount(userEntity);
                }
                
                Map resultMap = successMessage("User Account be created or update successfully !!!");
                
                return resultMap;

        } catch (Exception ex) {
            
                return errorMessage(ex.getMessage());
                
        }

    }
    
    /*
     * createUserAccount
     *  
     * @author : xinlhe
     * 
     * @params : SBAUserEntity
     * 
     * @version : v1
     */
    @RequestMapping(value = "/finduser", method = RequestMethod.POST)
    public Map findUserInfoByName(@RequestBody SBAUserEntity userEntity) throws JsonProcessingException {
        
        try {
                SBAUserEntity user = sbaUserService.findUserInfoByName(userEntity);
                
                Map resultMap = new HashMap<String, Object>();
                
                resultMap.put("user", user);
                
                return resultMap;

        } catch (Exception ex) {
            
                return errorMessage(ex.getMessage());
                
        }

    }

    /*
     * createUserAccount
     * 
     * @author : xinlhe
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map updateProfile(SBAUserEntity userEntity) throws JsonProcessingException {
        
        sbaUserService.saveOrUpdateProfile(userEntity);
        
        return successMessage();
        
    }

    /*
     * createUserAccount
     * 
     * @author : xinlhe
     */
    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    public Map sendMail(@RequestBody SBAUserEntity userEntity) {
        
        mailsUtil.sendMail(userEntity);
        
        Map resultMap = successMessage("User Account be created successfully !!!");
        
        resultMap.put("vcode", mailsUtil.vCode);
        
        return resultMap;
        
    }

    /*
     * sbaUserActivate
     * 
     * @author : xinlhe
     */
    @RequestMapping(value = "/activate", method = RequestMethod.POST)
    public Map sbaUserActivate(String verifyCode) {
        
        String userName = sbaUserService.verify(verifyCode);
        
        if (userName != null) {
            
            return successMessage("User " + userName + " complete verification successfully! You can signin with your account now.");
            
        } else {
            
            return errorMessage("Invalid verification code.");
            
        }
    }
    
    /*
     * createUserAccount
     * 
     * @author : xinlhe
     */
    @RequestMapping(value = "/canaccess", method = RequestMethod.POST)
    public boolean verifyToken(String token) throws AuthenticationException {
        
        return true;
        
    }
    
    /**
     * if SBA_MicroService_UserClient service is down, will feedback "User Service System is busy now, Please wait for a moment!"
     * @param userEntity
     * @return
     * @throws AuthenticationException
     */
    @HystrixCommand(fallbackMethod="usersRibbonLoginBack")
    @RequestMapping(value = "/usersRibbonLogin", method = RequestMethod.POST)
    public Map usersRibbonLogin(SBAUserEntity userEntity) throws AuthenticationException {
        
        Map<?, ?> map = (Map<?, ?>) restTemplate.postForObject("http://SBA_MicroService_UserClient/login", userEntity, SBAUserEntity.class);
        
        return map;
    }
    
    public String usersRibbonLoginBack() {
        
        return "User Service System is busy now, Please wait for a moment!";
    }

    
}
