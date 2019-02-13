package com.olsa.bo;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.olsa.utility.Code;

public class BaseDao  {
	static final Logger logger = Logger.getLogger(BaseDao.class);
	private JdbcTemplate olsaJdbcTemplate;
	
	public JdbcTemplate getOlsaJdbcTemplate() {
		return olsaJdbcTemplate;
	}

	public void setOlsaJdbcTemplate(JdbcTemplate olsaJdbcTemplate) {
		this.olsaJdbcTemplate = olsaJdbcTemplate;
		logger.info("setOlsaJdbcTemplate Called to test");
	}

	public List<Code> viewAllCode() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
