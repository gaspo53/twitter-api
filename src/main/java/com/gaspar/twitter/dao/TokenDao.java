package com.gaspar.twitter.dao;

/**
 * @author Gaspar Rajoy
 **/


import java.util.List;

import com.gaspar.twitter.common.entities.Token;
import com.gaspar.twitter.exception.DataException;

public interface TokenDao {

	/**
	 * Inserts the ${token} in the storage
	 * @param token
	 * @throws DataException
	 */
	public void create(String token) throws DataException;

	/**
	 * Returns a List with the given token in it. Empty List if not found
	 * @param token
	 * @return
	 * @throws DataException
	 */
	public List<Token> get(String token) throws DataException;

	
	/**
	 * Returns a List with all the tokens in the storage 
	 * @return
	 * @throws DataException
	 */
	public List<Token> getAll() throws DataException;
	
	/**
	 * Deletes a ${token} from the storage (if exists) 
	 * @param token
	 * @throws DataException
	 */
	public void delete(String token) throws DataException;
	
	/**
	 * Truncates the storage of tokens
	 * @throws DataException
	 */
	public void deleteAll() throws DataException;
}
