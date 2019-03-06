package com.olsa.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.olsa.bo.BaseDao;
import com.olsa.pojo.AddressMDB;
import com.olsa.pojo.IshtLineMDB;
import com.olsa.pojo.IshtMDB;
import com.olsa.pojo.RootMDB;

public class SQLUtility {
	
	static final Logger logger = Logger.getLogger(SQLUtility.class);
	
	
	private JdbcTemplate olsaJdbcTemplate;
	
	
	/**
	 * @return the olsaJdbcTemplate
	 */
	public JdbcTemplate getOlsaJdbcTemplate() {
		return olsaJdbcTemplate;
	}



	/**
	 * @param olsaJdbcTemplate the olsaJdbcTemplate to set
	 */
	public void setOlsaJdbcTemplate(JdbcTemplate olsaJdbcTemplate) {
		this.olsaJdbcTemplate = olsaJdbcTemplate;
	}



	public String executeSQL(Object bean) throws IOException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		String result = "";
		InputStream inputStream;
		Properties prop;
		String propFileName;
		if(bean.getClass().getName()==IshtMDB.class.getName()) {
			 prop= new Properties();
			 propFileName = "istadeposite.properties";
			 StringBuilder qryString ;
			    StringBuilder valueString ;
			    inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			    Field ishtLineFld = bean.getClass().getDeclaredField("line");
			    ishtLineFld.setAccessible(true);
			    List<IshtLineMDB> ishtLine = (List<IshtLineMDB>) ishtLineFld.get(bean); 
			   
				if (inputStream != null) {
					prop.load(inputStream);
					 for(IshtLineMDB ishtLIne: ishtLine){
					   System.out.println("==================================");
					   qryString = new StringBuilder();
					   valueString = new StringBuilder();
					for(Object key: prop.keySet()) {
						 Object value = null;
						  Field field = null;
						try {
							if(key.toString().contains(".")){
								//System.out.println("---key------------"+key);
								//String istLineKey=key.toString().split("\\.")[1];
								//System.out.println("---------------"+istLineKey);
								field=ishtLIne.getClass().getDeclaredField(key.toString().split("\\.")[1]);
								qryString.append(prop.getProperty(key.toString()));
								field.setAccessible(true);
								
								if(field.getType()==Double.class){
									value = (Double)field.get(ishtLIne);
								}else if(field.getType()==String.class){
									value = (String)field.get(ishtLIne);
									value="'"+value+"'";
								}
								
							}else{
								field = bean.getClass().getDeclaredField(key.toString());
								qryString.append(prop.getProperty(key.toString()));
								field.setAccessible(true);
								if(field.getType()==Double.class){
									value = (Double)field.get(bean);
								}else if(field.getType()==String.class){
									value = (String)field.get(bean);
									value="'"+value+"'";
								}
								else if(field.getType()==Date.class){
									Date dateVar= (Date)field.get(bean);
									if(dateVar!=null) {
										value=new java.sql.Date(dateVar.getTime());
									}else {
										value=null;
									}
									
									System.out.println("value is "+value);
									//value=java.sql.Date.valueOf((String) field.get(bean));
								}
							}
						
							
							
							 //value = field.get(bean);
							 //System.out.println("value is "+value);
							 
						    	
						    	
						    	valueString.append(value);
						    	qryString.append(",");
						    	valueString.append(",");
						    	
						    	
						    	
						} catch (NoSuchFieldException e) {
							
							
							
							System.out.println("##No key found for "+key.toString());
						}   
						
						
					}

			       

					 //ApplicationContext ctx
		               // = new FileSystemXmlApplicationContext(
		                    //    "src/main/webapp/WEB-INF/applicationContext.xml"
		                	 //   );

		        //JdbcTemplate olsaJdbcTemplate = (JdbcTemplate) ctx.getBean("olsaJdbcTemplate");

					
					String res = qryString.length() > 0 ? qryString.toString().substring(0, qryString.toString().length() - 1): "";
					String res1 = valueString.length() > 0 ? valueString.toString().substring(0, valueString.toString().length() - 1): "";
					StringBuilder sb= new StringBuilder();
					sb.append("INSERT INTO istavrity_deposit_summary ("+res+") values ("+res1+")");
					System.out.println(sb.toString());
					//System.out.println(res);
					//System.out.println(res1);
						int jdbcRes=getOlsaJdbcTemplate().update(sb.toString());
						System.out.println("JDBC result "+jdbcRes);
					 }
					    	
					    	
					    	
						
						
					
				
				
					
				} else {
					throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
				}
			 
		}else if(bean.getClass().getName()==RootMDB.class.getName()) {
			for (Field field : bean.getClass().getDeclaredFields()) {
			    field.setAccessible(true); // You might want to set modifier to public first.
			   
			    
			        //System.out.println(field.getName() + "=" );
			 
			}
			//String sql= 
			System.out.println("This is bean");
			 prop= new Properties();
			 propFileName = "rootfamily.properties";
		    StringBuilder qryString = new StringBuilder();
		    StringBuilder valueString = new StringBuilder();
		    inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
				for(Object key: prop.keySet()) {
					//System.out.println("Key is "+key);
					  Field field = null;
					try {
					
						field = bean.getClass().getDeclaredField(key.toString());
						field.setAccessible(true);
						
						  Object value = field.get(bean);
						  //System.out.println("value is "+value);
						 
					    	qryString.append(prop.getProperty(key.toString()));
					    	if(value!=null) {
					    		value="'"+value+"'";
					    	}
					    	valueString.append(value);
					    	qryString.append(",");
					    	valueString.append(",");
					    	
					    	
					    	
					} catch (NoSuchFieldException e) {
						
						
						
						//System.out.println("##No key found for "+key.toString());
					}    
				}
						Field faddMdb= bean.getClass().getDeclaredField("address");
						faddMdb.setAccessible(true);
						AddressMDB addMdb= (AddressMDB) faddMdb.get(bean);
						
					 	qryString.append("ADDRESS1");
				    	qryString.append(",");
				    	
				    	valueString.append("'"+addMdb.getAddressLine1()+"'");
				    	valueString.append(",");
				    	
				    	qryString.append("ADDRESS2");
				    	qryString.append(",");
				    	valueString.append("'"+addMdb.getAddressLine2()+"'");
				    	valueString.append(",");
				    	
				    	qryString.append("ADDRESS3");
				    	qryString.append(",");
				    	
				    	valueString.append("'"+addMdb.getAddressLine3()+"'");
				    	valueString.append(",");
				    	
				    	qryString.append("CITY");
				    	qryString.append(",");
				    	
				    
				    	valueString.append("'"+addMdb.getCity()+"'");
				    	valueString.append(",");
				    	
				    	
				    	qryString.append("STATE");
				    	qryString.append(",");
				    	
				    
				    	valueString.append("'"+addMdb.getState()+"'");
				    	valueString.append(",");
				    	
				    	qryString.append("COUNTRY");
				    	qryString.append(",");
				    	
				    	
				    	valueString.append("'"+addMdb.getCountry()+"'");
				    	valueString.append(",");
				    	
				    	qryString.append("ZIP_CODE");
				    	qryString.append(",");
				    	
				    	
				    	valueString.append("'"+addMdb.getZipCode()+"'");
				    	valueString.append(",");
				    	
				
					
					
				
				
				String res = qryString.length() > 0 ? qryString.toString().substring(0, qryString.toString().length() - 1): "";
				String res1 = valueString.length() > 0 ? valueString.toString().substring(0, valueString.toString().length() - 1): "";
				StringBuilder sb= new StringBuilder();
				sb.append("INSERT INTO sa_family_master ("+res+") values ("+res1+")");
				System.out.println(sb.toString());
				System.out.println(res);
				System.out.println(res1);
			
				
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			Date time = new Date(System.currentTimeMillis());
 
		
			System.out.println(result + "\nProgram Ran on " + time + " by user=" );
		}

	
	
	
		

			
		
		return result;
 	
	
	}


	
	public static void main(String[] arg) throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		IshtMDB isht=new IshtMDB();
		RootMDB rootMDB= new RootMDB();
		rootMDB.setEmail("THis is sample email");
		AddressMDB addm = new AddressMDB();
		
		addm.setAddressLine1("Hello");
		addm.setAddressLine2("This");
		
		rootMDB.setFamilyID("11111");
		rootMDB.setAddress(addm);
		
		isht.setFamilyID("SA111111");
		IshtLineMDB line1= new IshtLineMDB();
		line1.setAcharyavrity(11111.0);
		
		
		IshtLineMDB line2= new IshtLineMDB();
		line2.setAcharyavrity(11111.0);
		
		List<IshtLineMDB> line= new ArrayList<IshtLineMDB>();
		line.add(line1);
		line.add(line2);
		isht.setLine(line);
		isht.setSubmittedOn(new Date());
		SQLUtility util= new SQLUtility();
		util.executeSQL(isht);
				
	}
	
	
}
