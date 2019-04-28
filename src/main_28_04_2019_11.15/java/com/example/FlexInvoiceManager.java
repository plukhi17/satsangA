package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.olsa.pojo.ResultObject;

public class FlexInvoiceManager {
	static final Logger logger = Logger.getLogger(FlexInvoiceManager.class);
	
	
		public ResultObject generateFlexInvoiceSchedule(int invoiceAmount, int serviceDurationMonths, int userOptions  ) throws ParseException{
		
		FlexInvoice flex = new FlexInvoice();	
		flex.setInvoiceAmount(invoiceAmount); // set invoice bill amount
		flex.setServiceDurationMonths(serviceDurationMonths); // set total service duration 
		flex.setEmi(flex.getInvoiceAmount()/flex.getServiceDurationMonths()); // system will calculate the emi 
		flex.setUserOptions(userOptions); // set user options like user need to pay in 5 installments
		flex.setUserOptionsAmount(flex.getInvoiceAmount()/userOptions); // system will calculate the amount for each billing cycle 
		flex.setBillingCycleMonths(flex.getServiceDurationMonths()/flex.getUserOptions()); // Billing cycle every months
		
		logger.info("Invoice Amount = "+flex.getInvoiceAmount());
		logger.info("Service suration = "+flex.getServiceDurationMonths());
		logger.info("User want to split the invoice amount in  "+flex.getUserOptions() + " equle Installments");
		logger.info("System will generate invoice after every "+flex.getBillingCycleMonths() + " months");
		logger.info("Every billing cycle user will pay invoice for = "+flex.getUserOptionsAmount());
		
		String dt = "01-01-2017";  // Start date
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		Calendar c = Calendar.getInstance();
		logger.info("Invoice Start Date " + dt + " Invoice End Date " + endSerivceDate(dt,flex.getBillingCycleMonths()) +  "  Invoice Amount  "+ flex.getUserOptionsAmount());
		ResultObject result = new ResultObject();
		
		List<FlexSchedule> flexSchedule = new ArrayList<FlexSchedule>();
		FlexSchedule fs = new FlexSchedule();
		fs.setSrNO(1);
		fs.setStartDate(dt);
		fs.setEndDate(endSerivceDate(dt,flex.getBillingCycleMonths()));
		fs.setAmount(flex.getUserOptionsAmount());
		flexSchedule.add(fs);
		
		for (int i = 0; i<userOptions-1;i++){
			c.setTime(sdf.parse(dt));
			c.add(Calendar.MONTH, (int) flex.getBillingCycleMonths());  // number of days to add
			dt = sdf.format(c.getTime());  // dt is now the new date
			String dateBefore30Days = dt;
			logger.info("Invoice Start Date " + dt + " Invoice End Date " + endSerivceDate(dt,flex.getBillingCycleMonths()) +  "  Invoice Amount  "+ flex.getUserOptionsAmount());
			fs = new FlexSchedule();
			fs.setSrNO(i+2);
			fs.setStartDate(dt);
			fs.setEndDate(endSerivceDate(dt,flex.getBillingCycleMonths()));
			fs.setAmount(flex.getUserOptionsAmount());
			flexSchedule.add(fs);
		}
		
		flex.setFlexSchedule(flexSchedule);
		result.setObject1(flex);
		return result;
	}
		
	public String endSerivceDate(String startDate, double d) throws ParseException{
		String eDt = startDate;  // Start date
		int sMonths = (int) d;
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(eDt));
		c.add(Calendar.MONTH, sMonths );  // number of days to add
		eDt = sdf.format(c.getTime());  // dt is now the new date
		c.add(Calendar.DATE, -1);  // number of days to add
		eDt = sdf.format(c.getTime());  // dt is now the new date
		return eDt;
	}
		
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		FlexInvoiceManager flex = new FlexInvoiceManager();
		ResultObject result = flex.generateFlexInvoiceSchedule(100, 24, 6);
		
	}

}
