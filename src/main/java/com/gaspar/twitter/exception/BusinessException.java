package com.gaspar.twitter.exception;

/**
 * @author Gaspar Rajoy
 **/

public class BusinessException extends Exception{
	private static final long serialVersionUID = 2L;

	//Default constructor
	public BusinessException(){}
	
	public BusinessException(String message){
		super(message);
	}
}
