package com.olsa.utility;

import java.io.Serializable;

public class ReportDTO implements Serializable {
	private static final long serialVersionUID = -40873363523262585L;
	private String contact;
	private String familyCode;
	private String receiptNo;
	private String toDate;
	private String fromDate;

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

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	@Override
	public String toString() {
		return "ReportDTO [contact=" + contact + ", familyCode=" + familyCode + ", receiptNo=" + receiptNo + ", toDate="
				+ toDate + ", fromDate=" + fromDate + "]";
	}

}
