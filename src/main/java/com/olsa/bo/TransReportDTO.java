package com.olsa.bo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class TransReportDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String trnNO;
	

	private String phoneNo;
	
	
	private String familyId;
	
	private String receiptNo;
	
	/**
	 * @return the trnNO
	 */
	public String getTrnNO() {
		return trnNO;
	}



	/**
	 * @param trnNO the trnNO to set
	 */
	public void setTrnNO(String trnNO) {
		this.trnNO = trnNO;
	}



	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}



	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}



	/**
	 * @return the familyId
	 */
	public String getFamilyId() {
		return familyId;
	}



	/**
	 * @param familyId the familyId to set
	 */
	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}



	@Override
	public String toString() {
		return "ReportDTO [trnNO=" + trnNO + "]";
	}



	/**
	 * @return the receiptNo
	 */
	public String getReceiptNo() {
		return receiptNo;
	}



	/**
	 * @param receiptNo the receiptNo to set
	 */
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	

}
