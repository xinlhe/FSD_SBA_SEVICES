package com.ibm.sba.microservice.smc.userclient.controller.test;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

/**
 * This is use add test data to DB
 * 
 * @author XinLongHe
 *
 * @since 2019.11.29
 */
public class UserManager {
    
	public static void createUser(MockHttpServletRequestBuilder request) {
	    
		request.param("id", "123736");
		
		request.param("userName", "xinlhe");
		
		request.param("password", enCodePass());
		
		request.param("email", "xinlhe@cn.ibm.com");
		
		request.param("mobileNumber", "0411-98976");
		
		request.param("role", "user");
	}

	public static void createAdmin(MockHttpServletRequestBuilder request) {
		
	    request.param("userName", "admsky");
		
	    request.param("password", enCodePass());
		
	    request.param("email", "xinlhe@cn.ibm.com");
		
	    request.param("mobileNumber", "0411-98976");
		
	    request.param("role", "admin");
	}

	public static void update(MockHttpServletRequestBuilder request, int userId) {
		
	    request.param("id", "" + 2);
		
	    request.param("userName", "xinlhe");
		
	    request.param("password", enCodePass());
		
	    request.param("email", "xinlhe@cn.ibm.com");
		
	    request.param("mobileNumber", "0411-98976");
	}

	public static String enCodePass() {
		
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
	    String rawPassword = "123456";
		
	    String enPwd = encoder.encode(rawPassword);
		
	    return enPwd;
	}

	public static void main(String[] args) {
		
	    /* 可逆算法 */
		String base64 = "123456";
		
		byte[] byte64 = Base64.encodeBase64(base64.getBytes(), true);
		
		System.out.println("加密后:" + new String(byte64));

		String rev64 = new String(byte64);
		
		byte[] rev = Base64.decodeBase64(rev64.getBytes());
		
		System.out.println("解密后:" + new String(rev));

	}
}
