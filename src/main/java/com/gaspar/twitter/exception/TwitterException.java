package com.gaspar.twitter.exception;

/**
 * @author Gaspar Rajoy
 **/

public class TwitterException extends Exception{

	private static final long serialVersionUID = 4L;
	
	//Default constructor
	public TwitterException(){}
	
	public TwitterException(String message){
		super(message);
		
	}

}
