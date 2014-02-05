package com.gaspar.twitter.exception;

public class BusinessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	
	public BusinessException(){}
	
	public BusinessException(String message){
		super(message);
	}
}
