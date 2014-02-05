/*
 * Fluxit S.A
 * La Plata - Buenos Aires - Argentina
 * http://www.fluxit.com.ar
 * Author: grajoy
 * Date:  Feb 5, 2014 - 11:34:43 AM
 */
package com.gaspar.twitter.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaspar.twitter.common.entities.Token;
import com.gaspar.twitter.dao.TokenDao;
import com.gaspar.twitter.exception.BusinessException;
import com.gaspar.twitter.exception.DataException;
import com.gaspar.twitter.service.TokenService;
import com.gaspar.twitter.util.LogHelper;

/**

 * @author grajoy - Flux IT

 **/
@Service
@Transactional
public class TokenServiceImpl implements TokenService{
	
	@Autowired
	private TokenDao tokenDao;

	@Override
	public String newToken(String token) throws BusinessException{
		try {
			this.getTokenDao().create(token);
			return token;
		} catch (DataException e) {
			LogHelper.error(this, e);
			throw new BusinessException();
		}
		
	}

	@Override
	public List<Token> getAllToken() throws BusinessException{
		try {
			return this.getTokenDao().getAll();
		} catch (DataException e) {
			LogHelper.error(this, e);
			throw new BusinessException();
		}
	}

	@Override
	public void deleteToken(String token) throws BusinessException{
		try {
			this.getTokenDao().delete(token);
		} catch (DataException e) {
			LogHelper.error(this, e);
			throw new BusinessException();
		}
	}

	@Override
	public void deleteAllToken() throws BusinessException{
		try {
			this.getTokenDao().deleteAll();
		} catch (DataException e) {
			LogHelper.error(this, e);
			throw new BusinessException();
		}
	}

	@Override
	public boolean isTokenValid(String token) {
		boolean isValid = false;
		
		try {
			List<Token> tokenList = this.getTokenDao().get(token);
			Token tokenToCompare = new Token(token);
			isValid = CollectionUtils.isNotEmpty(tokenList) && (tokenList.contains(tokenToCompare));
			
		} catch (DataException e) {
			LogHelper.error(this, e);
		}
		
		return isValid;
	}
	
	/**
	 * @return the tokenDao
	 */
	public TokenDao getTokenDao() {
		return tokenDao;
	}

	/**
	 * @param tokenDao the tokenDao to set
	 */
	public void setTokenDao(TokenDao tokenDao) {
		this.tokenDao = tokenDao;
	}

}
