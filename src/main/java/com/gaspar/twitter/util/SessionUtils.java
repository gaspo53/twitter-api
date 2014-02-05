package com.gaspar.twitter.util;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

public class SessionUtils {
	
	public static String generateToken(HttpSession session){
		String token;
		
		if (StringUtils.isBlank(getAttr("token", session))){
			token = RandomStringUtils.random(16,true,true);
			session.setAttribute("token", token);
		}else{
			token = getAttr("token", session);
		}
		return token;
	}

	public static void removeAttr(String attr, HttpSession session) {
		session.removeAttribute(attr);
	}

	public static String getAttr(String attr, HttpSession session) {
		return (String) session.getAttribute(attr);
	}

}
