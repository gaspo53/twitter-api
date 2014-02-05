package com.gaspar.twitter.service;

import java.util.List;

import com.gaspar.twitter.common.entities.Token;
import com.gaspar.twitter.exception.BusinessException;

public interface TokenService {
	
	public String newToken(String token) throws BusinessException;

	public List<Token> getAllToken() throws BusinessException;
	
	public void deleteToken(String token) throws BusinessException;
	
	public void deleteAllToken() throws BusinessException;
	
	public boolean isTokenValid(String token);

}
