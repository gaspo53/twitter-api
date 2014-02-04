package com.gaspar.twitter.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class ApiController extends BaseController {

	@RequestMapping(value = "/{username}/followers.json", 
					method = RequestMethod.GET, 
					produces = "application/json")
	@ResponseBody
	public String followersJSON(@PathVariable("username") String username,
											  HttpServletRequest request, HttpServletResponse response) {

		String jsonResponse = null;
		
		try{
			//Use the method instead of returning the object directly, to do a pretty print
			jsonResponse = toJsonString(this.getTwitterService().followers(username));
		}
		catch(Exception e){
			//TODO handle errors
		}
		
		return jsonResponse;
	}

	@RequestMapping(value = "/{username}/followers.xml", 
					method = RequestMethod.GET, 
					produces = "application/xml")
	@ResponseBody
	public String followersXML(@PathVariable("username") String username, 
							   HttpServletRequest request, HttpServletResponse response) {

		List<TwitterProfile> followers = this.getTwitterService().followers(username);

		String xmlResponse = null;
		
		try{
			xmlResponse = this.toXML(followers, "TwitterProfiles");
		}
		catch(Exception e){
			//TODO handle errors
			e.printStackTrace();
		}
		
		return xmlResponse;
	}

	@RequestMapping(value = "/{username}/following.json", 
					method = RequestMethod.GET, 
					produces = "application/json")
	@ResponseBody
	public String followingJSON(@PathVariable("username") String username,
											  HttpServletRequest request, HttpServletResponse response) {

		String jsonResponse = null;
		
		try{
			jsonResponse = toJsonString(getTwitterService().following(username));
		}
		catch(Exception e){
			//TODO handle errors
		}
		
		return jsonResponse;

	}

	@RequestMapping(value = "/{username}/following.xml", 
					method = RequestMethod.GET, 
					produces = "application/xml")
	@ResponseBody
	public String followingXML(@PathVariable("username") String username,
							   HttpServletRequest request, HttpServletResponse response){

		List<TwitterProfile> following = this.getTwitterService().following(username);
		
		String xmlResponse = null;
		
		try{
			xmlResponse = this.toXML(following, "TwitterProfiles");
		}
		catch(Exception e){
			//TODO handle errors
		}
		
		return xmlResponse;
	}

}