package com.gaspar.twitter.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gaspar.twitter.common.entities.Token;
import com.gaspar.twitter.exception.BusinessException;

@Service
public interface TokenService {
	
	/**
	 * Inserts the ${token} in the storage
	 * @param token
	 * @return
	 * @throws BusinessException
	 */
	public String newToken(String token) throws BusinessException;
	
	/**
	 * Retrieves all the tokens in the storage
	 * @return
	 * @throws BusinessException
	 */
	public List<Token> getAllToken() throws BusinessException;
	
	/**
	 * Deletes the ${token} from the storage
	 * @param token
	 * @throws BusinessException
	 */
	public void deleteToken(String token) throws BusinessException;
	
	/**
	 * Deletes all the tokens in the storage (truncates)
	 * @throws BusinessException
	 */
	public void deleteAllToken() throws BusinessException;
	
	/**
	 * Returns true or false, depending whether the ${token} is in the storage or not
	 * @param token
	 * @return
	 */
	public boolean isTokenValid(String token);

}
