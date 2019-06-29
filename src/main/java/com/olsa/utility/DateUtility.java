package com.olsa.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.itextpdf.text.log.SysoCounter;

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
	
	
	public String getCurrentDate4()
	{
		try {
			//Date date = formatter.parse("2018-06-04");
			Date currentDate = new Date();
			strDate = formatter4.format(currentDate);
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

	   public static String formatDate(String dateValue) throws ParseException{
	    	
	    	SimpleDateFormat dateTimeFormat = new SimpleDateFormat(OnlineSAConstants.DATE_TIME_FORMAT_MONGO);
			SimpleDateFormat dateFormat = new SimpleDateFormat(OnlineSAConstants.DATE_FORMAT_MONGO);
			SimpleDateFormat writeFormat = new SimpleDateFormat(OnlineSAConstants.DATE_FORMAT_MONGO);
			
			Date curDt = new Date();
			Date currentDate = dateTimeFormat.parse(dateValue);
			String formattedCurDate = writeFormat.format(currentDate);
			Date currentFormatDate = dateFormat.parse(formattedCurDate.toString());
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(currentFormatDate);
			String formatedDate = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.YEAR);
			return formatedDate;
	    }

	public static Date formateDate2(String  dt) throws ParseException {
		  DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	      //Desired format: 24 hour format: Change the pattern as per the need
	      DateFormat outputformat = new SimpleDateFormat(OnlineSAConstants.DATE_TIME_FORMAT_MONGO);
	      Date date = null;
	      String output = null;
	      try{
	         //Converting the input String to Date
	    	 date= df.parse(dt);
	         //Changing the format of date and storing it in String
	    	 output = outputformat.format(date);
	         //Displaying the date
	    	 //System.out.println(output);
	    	 date=outputformat.parse(output);
	      }catch(ParseException pe){
	    	  System.out.println("=============="+dt);
	         pe.printStackTrace();
	         throw pe;
	         
	       }
	      return date;
	}
	
	

	public static String formateDate1(Date date) throws ParseException {
		SimpleDateFormat writeFormat = new SimpleDateFormat(OnlineSAConstants.DATE_FORMAT_MONGO);
		String dateStr = writeFormat.format(date);
		
		return dateStr;
	}
	

	public static String formateDate3(String date) throws ParseException {
		SimpleDateFormat writeFormat = new SimpleDateFormat(OnlineSAConstants.DATE_FORMAT_MONGO1);
		String dateStr = writeFormat.format(date);
		
		return dateStr;
	}
	
	
	public static Date atEndOfDay(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);
	    calendar.set(Calendar.MILLISECOND, 999);
	    return calendar.getTime();
	}

	public static Date atStartOfDay(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    return calendar.getTime();
	}
	
	public static void main(String[] arg) throws ParseException {
		
		System.out.println(formateDate2("30-04-2019"));
	}



	public static String formateDate4(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
