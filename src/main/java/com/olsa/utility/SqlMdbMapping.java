/**
 * 
 */
package com.olsa.utility;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * @author parthl
 *
 */
@Component
public class SqlMdbMapping {
	/**
	 * @return the sqlmdbProperties
	 */
	public Properties getSqlmdbProperties() {
		return sqlmdbProperties;
	}

	/**
	 * @param sqlmdbProperties the sqlmdbProperties to set
	 */
	public void setSqlmdbProperties(Properties sqlmdbProperties) {
		this.sqlmdbProperties = sqlmdbProperties;
	}

	@Resource(name="sqlmdbProperties")
	  private Properties sqlmdbProperties;
	
	
}
