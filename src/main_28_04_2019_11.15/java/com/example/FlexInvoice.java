package com.example;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class FlexInvoice {
	static final Logger logger = Logger.getLogger(FlexInvoice.class);
	private int invoiceAmount ;
	private int serviceDurationMonths ;
	private double emi;
	private int userOptions;
	private double userOptionsAmount;
	private Date billingStart;
	private Date billingEnd;
	private List<FlexSchedule> flexSchedule;
	
	public List<FlexSchedule> getFlexSchedule() {
		return flexSchedule;
	}

	public void setFlexSchedule(List<FlexSchedule> flexSchedule) {
		this.flexSchedule = flexSchedule;
	}

	public double getBillingCycleMonths() {
		return billingCycleMonths;
	}

	public void setBillingCycleMonths(double billingCycleMonths) {
		this.billingCycleMonths = billingCycleMonths;
	}

	public void setUserOptionsAmount(double userOptionsAmount) {
		this.userOptionsAmount = userOptionsAmount;
	}

	private double billingCycleMonths;
	
	
	public int getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(int invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public int getServiceDurationMonths() {
		return serviceDurationMonths;
	}

	public void setServiceDurationMonths(int serviceDurationMonths) {
		this.serviceDurationMonths = serviceDurationMonths;
	}

	public double getEmi() {
		return emi;
	}

	public void setEmi(double emi) {
		this.emi = emi;
	}

	public int getUserOptions() {
		return userOptions;
	}

	public void setUserOptions(int userOptions) {
		this.userOptions = userOptions;
	}

	public double getUserOptionsAmount() {
		return userOptionsAmount;
	}

	public void setUserOptionsAmount(int userOptionsAmount) {
		this.userOptionsAmount = userOptionsAmount;
	}

	public Date getBillingStart() {
		return billingStart;
	}

	public void setBillingStart(Date billingStart) {
		this.billingStart = billingStart;
	}

	public Date getBillingEnd() {
		return billingEnd;
	}

	public void setBillingEnd(Date billingEnd) {
		this.billingEnd = billingEnd;
	}

	public void generateFlexInvoiceSchedule(int invoiceAmount, int serviceDurationMonths, int userOptions  ){
		
		setInvoiceAmount(invoiceAmount);
		setServiceDurationMonths(serviceDurationMonths);
		setUserOptions(userOptions);
		setEmi(getInvoiceAmount()/getServiceDurationMonths());
		setUserOptionsAmount(getInvoiceAmount()/userOptions);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FlexInvoice flex = new FlexInvoice();
		flex.generateFlexInvoiceSchedule(100, 24, 7);

	}

}
