package com.olsa.utility;

import java.io.Serializable;
import java.util.Date;

public class ReportDTO implements Serializable {
	private static final long serialVersionUID = -40873363523262585L;
	private String contact;
	private String familyCode;
	private String receiptNo;
	private Date toDate;
	private Date fromDate;

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

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	
	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@Override
	public String toString() {
		return "ReportDTO [contact=" + contact + ", familyCode=" + familyCode + ", receiptNo=" + receiptNo + ", toDate="
				+ toDate + ", fromDate=" + fromDate + "]";
	}

}
