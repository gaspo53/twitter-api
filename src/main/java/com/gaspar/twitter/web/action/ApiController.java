package com.gaspar.twitter.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaspar.twitter.common.entities.StatusErrorWrapper;
import com.gaspar.twitter.exception.TwitterUnauthorizedException;
import com.gaspar.twitter.util.LogHelper;

/**
 * The API Controller. It's defines all the API mappings
 * @author Gaspar Rajoy
 **/


@Controller
@RequestMapping("/api")
public class ApiController extends BaseController {

	/*
	 * (non-Javadoc)
	 * 
	 * All the exceptions that methods can throw, are handled by
	 * BaseController.unauthorized() or BaseController.handleUnknownException()
	 */

	/**
	 * Consumes the TwitterService to retrieve the followers for the given
	 * ${username} Response is JSON
	 * 
	 * @param username
	 * @param request
	 * @param response
	 * @return
	 * @throws TwitterUnauthorizedException
	 */

	@RequestMapping(value = "/{username}/followers.json", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String followersJSON(@PathVariable("username")
	String username, @RequestParam(value = "token", required = false)
	String token, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String jsonResponse = null;

		this.checkToken(token, request, response);

		try {
			/*
			 * Use the method toJsonString() instead of returning the object
			 * directly, to do a pretty print
			 */
			jsonResponse = toJsonString(this.getTwitterService().followers(username));
		} catch (Exception e) {
			LogHelper.error(this, e);
			jsonResponse = toJsonString(new StatusErrorWrapper(e));
		}

		return jsonResponse;
	}

	/**
	 * Consumes the TwitterService to retrieve the followers for the given
	 * ${username} Response is XML
	 * 
	 * @param username
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{username}/followers.xml", method = RequestMethod.GET, produces = "application/xml")
	@ResponseBody
	public String followersXML(@PathVariable("username")
	String username, @RequestParam(value = "token", required = false)
	String token, HttpServletRequest request, HttpServletResponse response) throws Exception {

		this.checkToken(token, request, response);

		String xmlResponse = null;
		try {
			List<TwitterProfile> followers = this.getTwitterService().followers(username);
			xmlResponse = this.toXmlString(followers, "TwitterProfiles");
		} catch (Exception e) {
			LogHelper.info(this, e);
			xmlResponse = this.toXmlString(new StatusErrorWrapper(e), "TwitterProfiles");
		}

		return xmlResponse;
	}

	/**
	 * Consumes the TwitterService to retrieve the following users for the given
	 * ${username} Response is JSON
	 * 
	 * @param username
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{username}/following.json", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String followingJSON(@PathVariable("username")
	String username, @RequestParam(value = "token", required = false)
	String token, HttpServletRequest request, HttpServletResponse response) throws Exception {

		this.checkToken(token, request, response);

		String jsonResponse = null;

		try {
			jsonResponse = toJsonString(getTwitterService().following(username));
		} catch (Exception e) {
			LogHelper.error(this, e);
			jsonResponse = toJsonString(new StatusErrorWrapper(e));
		}

		return jsonResponse;

	}

	/**
	 * Consumes the TwitterService to retrieve the following users for the given
	 * ${username} Response is XML
	 * 
	 * @param username
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{username}/following.xml", method = RequestMethod.GET, produces = "application/xml")
	@ResponseBody
	public String followingXML(@PathVariable("username")
	String username, @RequestParam(value = "token", required = false)
	String token, HttpServletRequest request, HttpServletResponse response) throws Exception {

		this.checkToken(token, request, response);

		String xmlResponse = null;

		try {
			List<TwitterProfile> following = this.getTwitterService().following(username);
			xmlResponse = this.toXmlString(following, "TwitterProfiles");
		} catch (Exception e) {
			LogHelper.info(this, e);
			xmlResponse = this.toXmlString(new StatusErrorWrapper(e), "TwitterProfiles");
		}

		return xmlResponse;
	}

	/**
	 * Consumes the TwitterService to retrieve the tweets for the given
	 * ${username} Response is JSON
	 * 
	 * @param username
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{username}/tweets.json", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String tweetsJSON(@PathVariable("username")
	String username, @RequestParam(value = "token", required = false)
	String token, @RequestParam(value = "search", required = false)
	String search, HttpServletRequest request, HttpServletResponse response) throws Exception {

		this.checkToken(token, request, response);

		String jsonResponse = null;

		try {
			List<Tweet> tweets = this.getTwitterService().tweets(username, search);
			jsonResponse = toJsonString(tweets);
		} catch (Exception e) {
			LogHelper.error(this, e);
			jsonResponse = toJsonString(new StatusErrorWrapper(e));
		}

		return jsonResponse;
	}

	/**
	 * Consumes the TwitterService to retrieve the tweets for the given
	 * ${username} Response is XML
	 * 
	 * @param username
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{username}/tweets.xml", method = RequestMethod.GET, produces = "application/xml")
	@ResponseBody
	public String tweetsXML(@PathVariable("username")
	String username, @RequestParam(value = "token", required = false)
	String token, @RequestParam(value = "search", required = false)
	String search, HttpServletRequest request, HttpServletResponse response) throws Exception {

		this.checkToken(token, request, response);

		String xmlResponse = null;

		try {
			List<Tweet> tweets = this.getTwitterService().tweets(username, search);
			xmlResponse = this.toXmlString(tweets, "Tweets");
		} catch (Exception e) {
			LogHelper.info(this, e);
			xmlResponse = this.toXmlString(new StatusErrorWrapper(e), "Tweets");
		}

		return xmlResponse;
	}

	/**
	 * Consumes the TwitterService to start following the given ${userToFollow}
	 * for the logged user in the Twitter API Response is JSON
	 * 
	 * @param username
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{userToFollow}/follow.json", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String followJSON(@PathVariable("userToFollow")
	String userToFollow, @RequestParam(value = "token", required = false)
	String token, HttpServletRequest request, HttpServletResponse response) throws Exception {

		this.checkToken(token, request, response);

		String jsonResponse = null;

		try {
			Boolean responseStatus = this.getTwitterService().follow(userToFollow);
			jsonResponse = toJsonString(responseStatus);
		} catch (Exception e) {
			LogHelper.error(this, e);
			jsonResponse = toJsonString(new StatusErrorWrapper(e));
		}

		return jsonResponse;
	}

	/**
	 * Consumes the TwitterService to start following the given ${userToFollow}
	 * for the logged user in the Twitter API Response is XML
	 * 
	 * @param username
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{userToFollow}/follow.xml", method = RequestMethod.GET, produces = "application/xml")
	@ResponseBody
	public String followXML(@PathVariable("userToFollow")
	String userToFollow, @RequestParam(value = "token", required = false)
	String token, HttpServletRequest request, HttpServletResponse response) throws Exception {

		this.checkToken(token, request, response);

		String xmlResponse = null;

		try {
			Boolean responseStatus = this.getTwitterService().follow(userToFollow);
			xmlResponse = this.toXmlString(responseStatus, "FollowStatus");
		} catch (Exception e) {
			LogHelper.info(this, e);
			xmlResponse = this.toXmlString(new StatusErrorWrapper(e), "FollowStatus");
		}

		return xmlResponse;
	}

	/**
	 * Consumes the TwitterService to start following the given ${userToFollow}
	 * for the logged user in the Twitter API Response is JSON
	 * 
	 * @param username
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{userToFollow}/unfollow.json", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String unfollowJSON(@PathVariable("userToFollow")
	String userToFollow, @RequestParam(value = "token", required = false)
	String token, HttpServletRequest request, HttpServletResponse response) throws Exception {

		this.checkToken(token, request, response);

		String jsonResponse = null;

		try {
			Boolean responseStatus = this.getTwitterService().unfollow(userToFollow);
			jsonResponse = toJsonString(responseStatus);
		} catch (Exception e) {
			LogHelper.error(this, e);
			jsonResponse = toJsonString(new StatusErrorWrapper(e));
		}

		return jsonResponse;
	}

	/**
	 * Consumes the TwitterService to start following the given ${userToFollow}
	 * for the logged user in the Twitter API Response is XML
	 * 
	 * @param username
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{userToFollow}/unfollow.xml", method = RequestMethod.GET, produces = "application/xml")
	@ResponseBody
	public String unfollowXML(@PathVariable("userToFollow")
	String userToFollow, @RequestParam(value = "token", required = false)
	String token, HttpServletRequest request, HttpServletResponse response) throws Exception {

		this.checkToken(token, request, response);

		String xmlResponse = null;

		try {
			Boolean responseStatus = this.getTwitterService().unfollow(userToFollow);
			xmlResponse = this.toXmlString(responseStatus, "UnfollowStatus");
		} catch (Exception e) {
			LogHelper.info(this, e);
			xmlResponse = this.toXmlString(new StatusErrorWrapper(e), "UnfollowStatus");
		}

		return xmlResponse;
	}
}