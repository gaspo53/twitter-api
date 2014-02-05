package com.gaspar.twitter.web.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.gaspar.twitter.exception.TwitterUnauthorizedException;
import com.gaspar.twitter.service.TokenService;
import com.gaspar.twitter.service.TwitterService;
import com.gaspar.twitter.util.LogHelper;
import com.gaspar.twitter.util.SessionUtils;

@Controller
public class BaseController {
	
	private ModelAndView view;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private RequestMappingHandlerMapping handlerMapping;

	@Autowired
	@Qualifier("twitterService")
	private TwitterService twitterService;
	
	public BaseController(){
		this.view = new ModelAndView();
	}

	
	
	public String toXML(Object object, String rootName) throws Exception {
		
		String jsonString = this.toJsonString(object);

		JSON jsonObject = JSONSerializer.toJSON(jsonString);

		XMLSerializer serializer = new XMLSerializer();
		serializer.setRootName(rootName);
		serializer.setElementName("TwitterProfile");
		serializer.setTypeHintsEnabled(false);
		
		String xml = serializer.write(jsonObject,"ISO-8859-1");
		
		return xml;

	}
	  
	/**
	 * 
	 * @param object
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String toJsonString(Object object) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
	}

	
	/** Generates a new token and save it into the DB
	 * @param session
	 * @return
	 */
	public String generateToken(HttpSession session) {
		
		String token = null;
		if (StringUtils.isBlank(SessionUtils.getAttr("token", session))){
			try {
				token = this.getTokenService().newToken(SessionUtils.generateToken(session));
			} catch (Exception e) {
				LogHelper.error(this, e);
			}
		}else{
			token = SessionUtils.getAttr("token", session);
		}
		
		return token;
	}

	
	/**
	 * Checks if the ${token} given is in the database
	 * @param token
	 * @param request
	 * @param response
	 * @return
	 * @throws TwitterUnauthorizedException
	 */
	public void checkToken(String token, HttpServletRequest request, HttpServletResponse response) throws TwitterUnauthorizedException{
		
		if (!this.getTokenService().isTokenValid(token)){
			SessionUtils.removeAttr("token", request.getSession());
			throw new TwitterUnauthorizedException();
		}
	}
	
	
	//Exception handlers
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ResponseBody
	@ExceptionHandler(TwitterUnauthorizedException.class)
	public String unauthorized(HttpServletRequest request, HttpServletResponse response){
		return "401 - UNAUTHORIZED (INVALID TOKEN)";
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleUnknownException(){
		this.setView(new ModelAndView("error/twitter_api"));
		
		return this.getView();
	}
	
	
	
	//Getters and Setters
	public ModelAndView getView() {
		return view;
	}

	public void setView(ModelAndView view) {
		this.view = view;
	}



	public TwitterService getTwitterService() {
		return twitterService;
	}



	public void setTwitterService(TwitterService twitterService) {
		this.twitterService = twitterService;
	}



	public RequestMappingHandlerMapping getHandlerMapping() {
		return handlerMapping;
	}

	/**
	 * @return the tokenService
	 */
	public TokenService getTokenService() {
		return tokenService;
	}



	/**
	 * @param tokenService the tokenService to set
	 */
	public void setTokenService(TokenService tokenService) {
		this.tokenService = tokenService;
	}



	/**
	 * @return the env
	 */
	public Environment getEnv() {
		return env;
	}



	/**
	 * @param env the env to set
	 */
	public void setEnv(Environment env) {
		this.env = env;
	}

}
