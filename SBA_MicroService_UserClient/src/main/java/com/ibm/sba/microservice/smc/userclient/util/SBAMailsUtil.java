package com.ibm.sba.microservice.smc.userclient.util;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import net.bluefsd.entity.SBAUserEntity;

/**
 * Mail Service
 * 
 * @author XinLongHe
 *
 */
@Service
public class SBAMailsUtil {
    
    public static String vCode;
	
	@Value("${spring.mail.username}")
	private String from;
	
	@Value("")
	private String verifyLink;

	@Autowired
	private JavaMailSender mailSender;

	private String subject = "Verify your registration";

	public void sendMail(SBAUserEntity user) {

		try {
		    
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(user.getEmail());
			message.setSubject(subject);
			message.setFrom(from);
			message.setText(composeSimpleContent(user));

			mailSender.send(message);
			
		} catch (Exception e) {
		    
			e.printStackTrace();
		}
	}

	private static String composeSimpleContent(SBAUserEntity user) {
	    
	    vCode = String.valueOf((int)((Math.random()*9+1)*1000));
	    
		StringBuffer sb = new StringBuffer();
		
		sb.append("Hi " + user.getUserName() + ":\r\n");
		sb.append("\r\n");
		sb.append("Thanks for your registration.\r\n");
		sb.append("\r\n");
		sb.append("Please enter below link verify code in registe page :");
		sb.append("\r\n");
		sb.append(vCode);
		sb.append("\r\n");
		sb.append("Thanks");
		sb.append("\r\n");
		sb.append("from FSD server");

		return sb.toString();
	}

	@SuppressWarnings("unused")
	private static String composeContent(SBAUserEntity user) {
	    
		String link = "";
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<body>");
		sb.append("<p>");
		sb.append("Hi " + user.getUserName() + ":");
		sb.append("</p>");
		sb.append("<p>");
		sb.append("Thanks for your registration.");
		sb.append("</p>");
		sb.append("<p>");
		sb.append("Please click below link to verify you account:");
		sb.append("</p>");
		sb.append("<p>");
		sb.append("<a href=\"" + link + "\">" + link + "</a>");
		sb.append("</p>");
		sb.append("<p></p>");
		sb.append("<p>");
		sb.append("Thanks");
		sb.append("</p>");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
}
