package com.gaspar.twitter.common.entities;

/**
 * non-persistent
   Container class to keep the messgae of an Exception, and serialize into various formats
 * @author Gaspar Rajoy

 **/
public class StatusErrorWrapper {
	
	private String message;
	
	public StatusErrorWrapper(Exception e){
		this.setMessage(e.getMessage());
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
