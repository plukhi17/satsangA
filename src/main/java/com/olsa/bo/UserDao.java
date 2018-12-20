package com.olsa.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.mongodb.client.MongoCollection;
import com.olsa.pojo.ResultObject;
import com.olsa.pojo.User;

public class UserDao extends BaseDao implements DataAccessQueries{
	static final Logger logger = Logger.getLogger(UserDao.class);
	/*
	 * This method should be use to validate user object 
	 */
	public boolean validateUser(String userId, String password) throws Exception{
		try{
			Long count = getOlsaJdbcTemplate().queryForObject(GET_VALIDATE_USER, new Object[] {userId}, Long.class);
				return ((count != null && count > 0));
		}
		catch (Exception e) {
			throw new Exception(e);
		}
	}
	

	/*
	 * This method should be use to pull user object 
	 */

	public ResultObject geteUserData(String userID) {
		logger.info("validateUser : "+ userID);
		ResultObject ro = null;
		try {	
			User user = getOlsaJdbcTemplate().queryForObject(GET_VALIDATE_USER, new Object[] {userID},
			 new RowMapper<User>() {
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					logger.info(GET_VALIDATE_USER);
					User user = new User();
					 user.setUserFirstName((rs.getString(("first_name"))));
					 user.setUserFirstName((rs.getString(("password"))));
					 logger.info("user name " + rs.getString(("username")));
					 return user;
			 	}
	         });
			} catch (EmptyResultDataAccessException e) {
				logger.error(GET_VALIDATE_USER);	
				e.printStackTrace();
			}
		return ro;
		}
		
}
