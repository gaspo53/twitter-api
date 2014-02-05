package com.gaspar.twitter.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import com.gaspar.twitter.exception.TwitterException;
import com.gaspar.twitter.service.TwitterService;
import com.gaspar.twitter.util.LogHelper;

@Service
public class TwitterServiceImpl implements TwitterService {
	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessTokenSecret;

	@Autowired
	private Environment env;

	private Twitter twitter;

	@PostConstruct
	public void initialize() {
		this.consumerKey = getEnv().getProperty("twitter.consumerKey");
		this.consumerSecret = getEnv().getProperty("twitter.consumerSecret");
		this.accessToken = getEnv().getProperty("twitter.accessToken");
		this.accessTokenSecret = getEnv().getProperty("twitter.accessTokenSecret");

		twitter = new TwitterTemplate(getConsumerKey(), getConsumerSecret(), getAccessToken(), getAccessTokenSecret());
	}

	@Override
	public List<Tweet> tweets(String username, String search) throws TwitterException {

		try {
			List<Tweet> allTweets = new ArrayList<Tweet>();
			allTweets.addAll(getTwitter().timelineOperations().getUserTimeline(username));

			for (TwitterProfile following : following(username)) {
				allTweets.addAll(getTwitter().timelineOperations().getUserTimeline(following.getScreenName()));
			}

			if (StringUtils.isNotBlank(search)) {
				// Have to filter the list by hand, since Twiter API doesn't
				// allow to filter a user timeline by keyword
				List<Tweet> tweetsNotMatchingSearch = new ArrayList<Tweet>();
				for (Tweet tweet : allTweets) {
					if (!StringUtils.contains(tweet.getText(), search)) {
						tweetsNotMatchingSearch.add(tweet);
					}
				}

				allTweets.removeAll(tweetsNotMatchingSearch);
			}

			return allTweets;
		} catch (Exception e) {
			LogHelper.info(this, e);
			throw new TwitterException(e.getMessage());
		}

	}

	@Override
	public List<TwitterProfile> followers(String username) throws TwitterException {
		try {
			return this.getTwitter().friendOperations().getFollowers(username);
		} catch (Exception e) {
			LogHelper.info(this, e);
			throw new TwitterException(e.getMessage());
		}

	}

	@Override
	public List<TwitterProfile> following(String username) throws TwitterException {
		try {
			return this.getTwitter().friendOperations().getFriends(username);
		} catch (Exception e) {
			LogHelper.info(this, e);
			throw new TwitterException(e.getMessage());
		}
	}

	@Override
	public boolean follow(String username) throws TwitterException {
		boolean result = false;

		try {
			this.getTwitter().friendOperations().follow(username);
			result = true;
		} catch (Exception e) {
			LogHelper.info(this, e);
			throw new TwitterException(e.getMessage());
		}

		return result;
	}

	@Override
	public boolean unfollow(String username) throws TwitterException {
		boolean result = false;

		try {
			this.getTwitter().friendOperations().unfollow(username);
			result = true;
		} catch (Exception e) {
			LogHelper.info(this, e);
			throw new TwitterException(e.getMessage());
		}

		return result;
	}

	// Getters & Setters

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}

	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}

	public Environment getEnv() {
		return env;
	}

	public void setEnv(Environment env) {
		this.env = env;
	}

}
