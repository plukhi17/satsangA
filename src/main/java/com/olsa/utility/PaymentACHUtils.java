package com.olsa.utility;

import java.io.Serializable;

public class PaymentACHUtils implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String amount;
	private String accName;
	private String bankRoutingNo;
	private String chAccNo;
	private String dlNo;
	private String contact;
	private String familyCode;
	
	
	
	
	/**
	 * @return the accName
	 */
	public String getAccName() {
		return accName;
	}




	/**
	 * @param accName the accName to set
	 */
	public void setAccName(String accName) {
		this.accName = accName;
	}




	/**
	 * @return the bankRoutingNo
	 */
	public String getBankRoutingNo() {
		return bankRoutingNo;
	}




	/**
	 * @param bankRoutingNo the bankRoutingNo to set
	 */
	public void setBankRoutingNo(String bankRoutingNo) {
		this.bankRoutingNo = bankRoutingNo;
	}




	/**
	 * @return the chAccNo
	 */
	public String getChAccNo() {
		return chAccNo;
	}




	/**
	 * @param chAccNo the chAccNo to set
	 */
	public void setChAccNo(String chAccNo) {
		this.chAccNo = chAccNo;
	}




	/**
	 * @return the dlNo
	 */
	public String getDlNo() {
		return dlNo;
	}




	/**
	 * @param dlNo the dlNo to set
	 */
	public void setDlNo(String dlNo) {
		this.dlNo = dlNo;
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




	@Override
	public String toString() {
		return "PaymentACHUtils [accName=" + accName + ", bankRoutingNo=" + bankRoutingNo +" dlNo=" + dlNo + ", chAccNo=" + chAccNo + "]";
	}
	
	
	
}
