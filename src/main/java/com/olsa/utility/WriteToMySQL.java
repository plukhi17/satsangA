package com.olsa.utility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.itextpdf.text.DocumentException;

public class WriteToMySQL {
	static final Logger logger = Logger.getLogger(WriteToMySQL.class);
	public void WriteToMySQL() {
		
	}
	
	public Connection executeSelectSQL(String sql) {

		//JDBC driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	
		Connection conn = null;
		Statement stmt = null;
		Statement stmt1 = null;
		Statement stmt2 = null;
		Statement stmt3 = null;

		ResultSet rs=null; 


		try {

			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			logger.info("Connecting to database...");
			conn = DriverManager.getConnection(OnlineSAConstants.SQL_DB_LOCAL,OnlineSAConstants.SQL_DB_LOCAL_USER ,OnlineSAConstants.SQL_DB_LOCAL_PASS);
			//conn = DriverManager.getConnection(OnlineSAConstants.SQL_DB_SERVER,OnlineSAConstants.SQL_DB_SERVER_USER ,OnlineSAConstants.SQL_DB_SERVER_PASS);

			//STEP 4: Execute a query
			logger.info("Creating statement...");
			sql="select FIRST_NAME from sa_family_master where sa_family_code='SA0000001' and SA_MEM_CODE='SA0000001-01'"; 
			stmt= conn.createStatement();
			rs = stmt.executeQuery(sql);
			//int record_count=0;
			if(rs.next()) {

				logger.info("FIRST_NAME IS "+rs.getString("FIRST_NAME"));  

			}
			rs.close();

		}
		catch(SQLException se){
			logger.info(se.getMessage()+" Class:"+se.getClass().getName());
		}catch(Exception e){
			logger.info("Successfully PDF Got Created !!! "+e.getMessage());
			logger.error("GHGIRI Exception Occurred !!"+e.getMessage());
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				logger.info(se.getMessage()+"       Class:"+se.getClass().getName());
			}//end finally try
		}//end try

		return conn;
	}
	
	public static void main(String[] args) throws IOException, DocumentException {
		try {
		   String sql="";
		   Connection con = new WriteToMySQL().executeSelectSQL(sql);   //(DEST);
		}
		catch(Exception ex) {
			logger.error("Execption in side Main method :"+ex);
		}
	}
	
}
