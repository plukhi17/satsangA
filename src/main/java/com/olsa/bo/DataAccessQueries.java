package com.olsa.bo;

public interface DataAccessQueries {
	String O_RC = "o_rc";
	String I_USER_ID = "i_user_id";
	String O_LOGGER_MESSAGE = "o_logger_message";
	String O_USER_MESSAGE = "o_user_message";
	String O_ERROR_CODE = "o_error_code";
	
	
	
	public static final String GET_VALIDATE_USER= "select count(username) "
			+" from staff  "
			+" where username = ? ";
	
	public static final String GET_USER_DETAILS= "select * "
			+" from staff  "
			+" where username = ? ";
	
}
