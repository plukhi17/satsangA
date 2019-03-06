/**
 * 
 */
package com.olsa.bo;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 * @author parthl
 *
 */
public class SQLTemplate {
	 private DataSource dataSource;
     private JdbcTemplate jdbcTemplateObject;
	   
	   public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   }
	   
	   public JdbcTemplate getJdbcTemplateObject() {
		   return this.jdbcTemplateObject;
	   }
}
