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
import org.springframework.stereotype.Controller;
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
		
		String jsonData = this.toJSON(object);

		JSON json = JSONSerializer.toJSON(jsonData);

		XMLSerializer serializer = new XMLSerializer();
		serializer.setRootName(rootName);
		serializer.setTypeHintsEnabled(false);
		
		String xml = serializer.write(json);
		
		return xml;

	}
	  
	
	public String toJSON(Object object) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
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
