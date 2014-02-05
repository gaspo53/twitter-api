package com.gaspar.twitter.exception;

public class DataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;

	//Default constructor
	public DataException(){}
	
	public DataException(String message){
		super(message);
	}
}
