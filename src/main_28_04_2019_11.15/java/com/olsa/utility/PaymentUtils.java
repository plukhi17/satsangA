package com.olsa.utility;

import java.io.Serializable;
import java.util.Date;

public class PaymentUtils implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String amount;
	private String cardNumber;
	private String expirationDate;
	private String cvv;
	private String contact;
	private String familyCode;
	private Date transDate;
	
	
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getFamilyCode() {
		return familyCode;
	}
	public void setFamilyCode(String familyCode) {
		this.familyCode = familyCode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	
	/**
	 * @return the transDate
	 */
	public Date getTransDate() {
		return transDate;
	}
	/**
	 * @param transDate the transDate to set
	 */
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	@Override
	public String toString() {
		return "PaymentUtils [amount=" + amount + ", cardNumber=" + cardNumber + ", expirationDate=" + expirationDate
				+ ", cvv=" + cvv + ", contact=" + contact + ", familyCode=" + familyCode + "]";
	}
	
	
	
}