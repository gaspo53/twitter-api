package com.gaspar.twitter.service;

import java.util.List;

import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;

/**
 * Service interface based on Spring Social (using it's entities).

* @author grajoy - Flux IT

*
 */
@Service(value="twitterService")
public interface TwitterService {
	
	/**
	 * Retrieves all tweets for a given user, and filter those tweets by keyword (if given).
	 * @param username - The user account
	 * @param search (optional)
	 * @return
	 */
	public List<Tweet> tweets(String username, String search);

	/**
	 * Retrieves all the followers profiles for a given user.
	 * @param username - The user account
	 * @return
	 */
	public List<TwitterProfile> followers(String username);

	/**
	 * Retrieves all the following profiles for a given user.
	 * @param username - The user account
	 * @return
	 */
	public List<TwitterProfile> following(String username);
	
	/**
	 * Follows a user for the configured API key
	 * 
	 * @param username - The user account
	 * @param userToFollow - The username to follow
	 * @return
	 */
	public boolean follow(String username);

	/**
	 * Unfollows a user for the configured API key
	 * 
	 * @param username - The user account
	 * @param userToFollow - The username to follow
	 * @return
	 */
	public boolean unfollow(String username);
}
