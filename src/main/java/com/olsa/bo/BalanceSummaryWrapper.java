/**
 * 
 */
package com.olsa.bo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author parthl
 *
 */
public class BalanceSummaryWrapper implements Serializable {
	
   /**
	 * 
	 */
	private static final long serialVersionUID = 7447836846702654777L;

@JsonProperty("headType")
	private String headType;
   
   @JsonProperty("count")
	private int count;
   
   @JsonProperty("count")
	private double amount;
   
   @JsonProperty("date")
	private Date date; 
   
	/**
	 * @return the headType
	 */
	public String getHeadType() {
		return headType;
	}
	/**
	 * @param headType the headType to set
	 */
	public void setHeadType(String headType) {
		this.headType = headType;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
