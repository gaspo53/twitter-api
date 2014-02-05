package com.gaspar.twitter.dao;

import java.util.List;

import com.gaspar.twitter.common.entities.Token;
import com.gaspar.twitter.exception.DataException;

public interface TokenDao {

	public void create(String token) throws DataException;

	public List<Token> get(String token) throws DataException;

	public List<Token> getAll() throws DataException;
	
	public void delete(String token) throws DataException;
	
	public void deleteAll() throws DataException;
}
