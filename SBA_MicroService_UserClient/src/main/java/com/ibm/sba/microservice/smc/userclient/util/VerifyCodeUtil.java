package com.ibm.sba.microservice.smc.userclient.util;

import java.util.Random;

/**
 * 
 * VerifyCodeUtil
 * 
 * @author XinLongHe
 *
 * @since 2019.11.29
 */
public class VerifyCodeUtil {
	
    public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    public static String generateVerifyCode(int verifySize) {
        
        return generateVerifyCode(verifySize, VERIFY_CODES);
    }

    public static String generateVerifyCode(int verifySize, String sources) {
        
        if(sources == null || sources.length() == 0) {
        
            sources = VERIFY_CODES;
        }
        
        int codesLen = sources.length();
        
        Random rand = new Random(System.currentTimeMillis());
        
        StringBuilder verifyCode = new StringBuilder(verifySize);
        
        for(int i = 0; i < verifySize; i++) {
            
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }
        
        return verifyCode.toString();}
 
}