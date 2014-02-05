package com.gaspar.twitter.common.entities;

import java.util.Date;

public class Token {
	
	private Long id;
	private String token;
	private Date createad_at;
	
	
	public Token() {}

	public Token(String token) {
		this.token = token;
	}

	@Override
	public int hashCode() {
		return token.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the createad_at
	 */
	public Date getCreatead_at() {
		return createad_at;
	}
	/**
	 * @param createad_at the createad_at to set
	 */
	public void setCreatead_at(Date createad_at) {
		this.createad_at = createad_at;
	}

	
	
	
}
