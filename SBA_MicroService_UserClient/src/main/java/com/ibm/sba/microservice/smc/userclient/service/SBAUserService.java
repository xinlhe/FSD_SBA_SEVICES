package com.ibm.sba.microservice.smc.userclient.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.ibm.sba.microservice.smc.userclient.dao.SBAUserRepository;
import com.ibm.sba.microservice.smc.userclient.filter.JWTUserTokenUtil;
import com.ibm.sba.microservice.smc.userclient.util.SBAMailsUtil;
import com.ibm.sba.microservice.smc.userclient.util.VerifyCodeUtil;
import net.bluefsd.entity.SBAUserEntity;


/**
 * User Service.
 * 
 * @author XinLongHe
 *
 */
@Service
public class SBAUserService implements UserDetailsService {

    Logger log = LoggerFactory.getLogger(SBAUserService.class);

    @Autowired
    SBAUserRepository sbaUserRepository;

    @Autowired
    SBAMailsUtil sbaMailsUtil;

    @Autowired
    private JWTUserTokenUtil jwtUserTokenUtil;

    public SBAUserEntity getUserBean(SBAUserEntity sbaUserEntity) {
        
        return sbaUserRepository.getUserInfoByUserName(sbaUserEntity.getUserName());
    }
    /**
     * loadUserByUsername
     * 
     * @author XinLongHe
     * 
     * @param String input
     */
    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {

        SBAUserEntity sbaUserEntity = sbaUserRepository.getUserInfoByUserName(input);
        
        if (null == sbaUserEntity) {

            throw new UsernameNotFoundException("User Account doesn't exist, Please double check your username and password !!!");
        }

        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();

        SimpleGrantedAuthority simpleGrantedAuthority =
                new SimpleGrantedAuthority(sbaUserEntity.getRole());

        simpleGrantedAuthorityList.add(simpleGrantedAuthority);

        User u = new User(sbaUserEntity.getUserName(), sbaUserEntity.getPassword(),
                simpleGrantedAuthorityList);

        return u;
    }

    /**
     * doLogin
     * 
     * @author XinLongHe
     * 
     * @param String username, String password
     */
    public String doLogin(String username, String password) throws Exception {

        if (username.isEmpty() || password.isEmpty()) {

            throw new Exception("User name or password is invalid !!!");
        }

        final UserDetails userDetails = loadUserByUsername(username);

        if (null == userDetails) {

            throw new Exception("User Account doesn't exist !!!");
        }

        if (new BCryptPasswordEncoder().matches(password, userDetails.getPassword())) {

            return jwtUserTokenUtil.generateToken(userDetails);

        } else {

            throw new Exception("User Password is incorrect !!!");

        }
    }

    /**
     * createUserAccount
     * 
     * @author XinLongHe
     * 
     * @param SBAUserEntity sbaUserEntity
     */
    public SBAUserEntity createUserAccount(SBAUserEntity sbaUserEntity) throws Exception {

        if (sbaUserRepository.getUserInfoByUserName(sbaUserEntity.getUserName()) != null) {

            throw new Exception("User " + sbaUserEntity.getUserName() + " already exists !!!");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        sbaUserEntity.setPassword(encoder.encode(sbaUserEntity.getPassword()));

        sbaUserEntity.setVerifyCode(VerifyCodeUtil.generateVerifyCode(32));

        sbaUserEntity.setConfirmed("N");

        return sbaUserRepository.save(sbaUserEntity);
    }
    
    /**
     * createUserAccount
     * 
     * @author XinLongHe
     * 
     * @param SBAUserEntity sbaUserEntity
     */
    public SBAUserEntity findUserInfoByName(SBAUserEntity sbaUserEntity) throws Exception {

        return sbaUserRepository.getUserInfoByUserName(sbaUserEntity.getUserName());
    }

    /**
     * saveOrUpdateProfile
     * 
     * @author XinLongHe
     * 
     * @param String input
     */
    public void saveOrUpdateProfile(SBAUserEntity sbaUserEntity) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        sbaUserEntity.setPassword(encoder.encode(sbaUserEntity.getPassword()));

        sbaUserRepository.save(sbaUserEntity);
    }

    /**
     * sendMail
     * 
     * @author XinLongHe
     * 
     * @param String input
     */
    private void sendMail(SBAUserEntity userToAdd) {

        sbaMailsUtil.sendMail(userToAdd);
    }

    /**
     * verify
     * 
     * @author XinLongHe
     * 
     * @param String input
     */
    public String verify(String verifyCode) {

        SBAUserEntity sbaUserInfo = sbaUserRepository.findUserByVerifyCode(verifyCode);

        if (null != sbaUserInfo) {

            sbaUserInfo.setConfirmed("Y");

            sbaUserRepository.save(sbaUserInfo);

            return sbaUserInfo.getUserName();
        }

        return null;
    }

}
