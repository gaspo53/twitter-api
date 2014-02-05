package com.gaspar.twitter.util;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

public class SessionUtils {
	
	public static String generateToken(HttpSession session){
		String token;
		
		if (StringUtils.isBlank((String) session.getAttribute("token"))){
			token = RandomStringUtils.random(16,true,true);
			session.setAttribute("token", token);
		}else{
			token = (String) session.getAttribute("token");
		}
		return token;
	}

}
