package com.gaspar.twitter.service;

import java.util.List;

import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;

@Service(value="twitterService")
public interface TwitterService {
	
	public List<Tweet> tweets(String username, String search);

	public List<TwitterProfile> followers(String username);

	public List<TwitterProfile> following(String username);
	
	public boolean follow(String username);

	public boolean unfollow(String username);
}
