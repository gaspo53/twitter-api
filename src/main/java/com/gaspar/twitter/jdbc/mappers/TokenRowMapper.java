package com.gaspar.twitter.jdbc.mappers;

/**
 * @author Gaspar Rajoy
 **/

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gaspar.twitter.common.entities.Token;

public class TokenRowMapper implements RowMapper<Token> {

	@Override
	public Token mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Token token = new Token();
		
		token.setId(rs.getLong("id"));
		token.setCreatead_at(rs.getTimestamp("created_at"));
		token.setToken(rs.getString("token_string"));
		
		return token;
	}
	
	

}
