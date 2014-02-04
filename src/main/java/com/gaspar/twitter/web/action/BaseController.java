package com.gaspar.twitter.web.action;

import java.io.IOException;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.social.UncategorizedApiException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.gaspar.twitter.service.TwitterService;

@Controller
public class BaseController {
	
	private ModelAndView view;
	
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
	  
	
	public String toJsonString(Object object) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
	}


	@ExceptionHandler(UncategorizedApiException.class)
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

}
