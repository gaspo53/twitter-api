package com.gaspar.twitter.dao.impl;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gaspar.twitter.common.entities.Token;
import com.gaspar.twitter.dao.TokenDao;
import com.gaspar.twitter.exception.DataException;
import com.gaspar.twitter.jdbc.mappers.TokenRowMapper;

@Repository
public class TokenDaoImpl implements TokenDao {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	
	//When setting dataSource, creates JdbcTemplate
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.setJdbcTemplate(new JdbcTemplate(dataSource));
	}

	
	@Override
	public void create(String token) throws DataException{
		Date created_at = new Date();
		getJdbcTemplate().update("INSERT INTO token (token_string, created_at) VALUES(?,?)", new Object[] { token, created_at});
	}

	@Override
	public List<Token> get(String token) throws DataException{
		return getJdbcTemplate().query("SELECT id, token_string, created_at FROM token WHERE token_string = ?", new Object[] { token },
				new TokenRowMapper());
	}

	@Override
	public List<Token> getAll() throws DataException{
		return getJdbcTemplate().query("SELECT * FROM token", new TokenRowMapper());
	}

	@Override
	public void deleteAll() throws DataException{
		getJdbcTemplate().update("DELETE FROM token");
	}

	@Override
	public void delete(String token) throws DataException{
		getJdbcTemplate().update("DELETE FROM token WHERE token_string= ?", new Object[] { token });
	}


	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}


}
