package com.olsa.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtility {
	static final Logger logger = Logger.getLogger(DateUtility.class);
	
	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat formatter1 = new SimpleDateFormat("MMM");
	DateFormat formatter2 = new SimpleDateFormat("YYYY");
	DateFormat formatter3 = new SimpleDateFormat("YYYY");
	DateFormat formatter4 = new SimpleDateFormat("MM/dd/yyyy");
	DateFormat formatter5 = new SimpleDateFormat("MM-dd-yyyy");
	DateFormat formatter6 = new SimpleDateFormat(OnlineSAConstants.DATE_TIME_FORMAT_MONGO); 
	DateFormat formatter7 = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
	
	String strDate="",retDate="";

	public String getMonthYear(String stDate)
	{
		try {
			//Date date = formatter.parse("2018-06-04");
			//Date date = formatter.parse("Mon Jun 18 00:00:00 IST 2012");
			Date date = formatter7.parse(stDate);
		
			strDate = formatter1.format(date).toUpperCase()+"_"+formatter2.format(date).toUpperCase();
		}
		catch(Exception ex) {
			logger.error("Exception :"+ex);	
		}
		return strDate.toUpperCase();
	}
	


	public String getCurrentDate()
	{
		try {
			//Date date = formatter.parse("2018-06-04");
			Date currentDate = new Date();
			strDate = formatter6.format(currentDate);
		}
		catch(Exception ex) {
			logger.error("Exception occure while get current date :"+ex.getMessage());	
		}
		return strDate.toUpperCase();
	}
	
	public String getCurrentDate1()
	{
		try {
			//Date date = formatter.parse("2018-06-04");
			Date currentDate = new Date();
			strDate = formatter7.format(currentDate);
		}
		catch(Exception ex) {
			logger.error("Exception occure while get current date :"+ex.getMessage());	
		}
		return strDate.toUpperCase();
	}
	
	public Date getCurrentDateInDate() throws ParseException
	{
		try {
			//Date date = formatter.parse("2018-06-04");
			Date currentDate = new Date();
			strDate = formatter6.format(currentDate);
		}
		catch(Exception ex) {
			logger.error("Exception occure while get current date :"+ex.getMessage());	
		}
		return formatter6.parse(strDate);
	}
    
	public String getformattedDateToWrite(String sdate)
	{
		try {
			//Date date = formatter.parse("2018-06-04");
			//Date currentDate = new Date();
			Date date = formatter.parse(sdate);
			//retDate = formatter.format(date);
			retDate = formatter6.format(date);
		}
		catch(Exception ex) {
			logger.error("Exception occure while getformattedDateToWrite() :"+ex);	
		}
		return retDate.toUpperCase();
	}
	
	/*
	
	public static void main (String args[]) {

		DateUtility dtl=new DateUtility();
		String strDate = dtl.getformattedDate("2018-06-04");
		System.out.println(" strDate : "+strDate);
	}
*/
	
}
