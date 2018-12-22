/**
 * 
 */
package com.olsa.utility;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Parth
 *
 */
public class ManualPaymentUtils implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String amount;
	private String bankName;
	private String trasnRef;
	private Date date;
	private String contact;
	private String familyCode;
	
	
	
	
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}




	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}




	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}




	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}




	/**
	 * @return the trasnRef
	 */
	public String getTrasnRef() {
		return trasnRef;
	}




	/**
	 * @param trasnRef the trasnRef to set
	 */
	public void setTrasnRef(String trasnRef) {
		this.trasnRef = trasnRef;
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




	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}




	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}




	/**
	 * @return the familyCode
	 */
	public String getFamilyCode() {
		return familyCode;
	}




	/**
	 * @param familyCode the familyCode to set
	 */
	public void setFamilyCode(String familyCode) {
		this.familyCode = familyCode;
	}




	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Override
	public String toString() {
		return "ManualPaymentUtils [amount=" + amount + ", bankName=" + bankName + ", trasnRef=" + trasnRef
				+ ", date=" + date + ", contact=" + contact + ", familyCode=" + familyCode + "]";
	}
	
	
}	