package com.gaspar.twitter.exception;

public class TwitterUnauthorizedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TwitterUnauthorizedException(){}

	public TwitterUnauthorizedException(String message){
		super(message);
	}

}