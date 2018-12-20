
package com.olsa.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.olsa.mongo.ConnectMongo;


public class IshtMDB {

	private BigDecimal _id;
    private String receiptNO;
    private Date transactionDate;
    private String phoneNo;
    @JsonProperty("stTrnNo")
    private String trnDetails;
    @JsonProperty("dtIshtDate")
    private String collectedOn;
    private String collectedBy;
    @JsonProperty("pmtMethod")
    private String paymentMethod;
    @JsonProperty("stChqNo")
    private String checqNo;
    @JsonProperty("stBankName")
    private String chequeIssueBank;
    @JsonProperty("chqDate")
    private String checqDate;
    private Double total;
    private String familyID;
    private String notificatonMethod;
    private String active;
    private List<IshtMDB> trnList;
    private String issuedFlag; // flag will N when user will enter the data , it will be changed when admin approved
    private String receiptNo; // receipt no , auto generated : SA/YEAR/7 digit
    private String receiptDate; // Receipt Generation date  
    private String approvedBy;
    private String approvedOn;

	private String name; 
    private String monthYear;
    private String submittedOn;
     
    
    public String getSubmittedOn() {
		return submittedOn;
	}
	public void setSubmittedOn(String submittedOn) {
		this.submittedOn = submittedOn;
	}
	public String getMonthYear() {
		return monthYear;
	}
	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}
	public String getIssuedFlag() {
		return issuedFlag;
	}
	public void setIssuedFlag(String issuedFlag) {
		this.issuedFlag = issuedFlag;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}
    public List<IshtMDB> getTrnList() {
		return trnList;
	}
	public void setTrnList(List<IshtMDB> trnList) {
		this.trnList = trnList;
	}

	private AddressMDB address = null;
    private List<IshtLineMDB> line = null;

    public void set_id(BigDecimal _id){
        this._id = _id;
    }
    public BigDecimal get_id(){
        return this._id;
    }
    
        public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getTrnDetails() {
        return trnDetails;
    }

    public void setTrnDetails(String trnDetails) {
        this.trnDetails = trnDetails;
    }

    public String getCollectedOn() {
        return collectedOn;
    }

    public void setCollectedOn(String collectedOn) {
        this.collectedOn = collectedOn;
    }

    public String getCollectedBy() {
        return collectedBy;
    }

    public void setCollectedBy(String collectedBy) {
        this.collectedBy = collectedBy;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getChecqNo() {
        return checqNo;
    }

    public void setChecqNo(String checqNo) {
        this.checqNo = checqNo;
    }

    public String getChequeIssueBank() {
        return chequeIssueBank.toUpperCase();
    }

    public void setChequeIssueBank(String chequeIssueBank) {
        this.chequeIssueBank = chequeIssueBank.toUpperCase();
    }

    public String getChecqDate() {
        return checqDate;
    }

    public void setChecqDate(String checqDate) {
        this.checqDate = checqDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

  public void setAddress(AddressMDB address){
        this.address = address;
    }
    public AddressMDB getAddress(){
        return this.address;
    }
    public List<IshtLineMDB> getLine() {
        return line;
    }

    public void setLine(List<IshtLineMDB> line) {
        this.line = line;
    }

    public String getFamilyID() {
        return familyID;
    }

    public void setFamilyID(String familyID) {
        this.familyID = familyID;
    }

    public String getNotificatonMethod() {
    	return notificatonMethod;
    }
    
    public void setNotificatonMethod(String notificatonMethod) {
    	this.notificatonMethod = notificatonMethod;
    }

    public String getActive() {
    	return active;
    }
    
    public void setActive(String active) {
    	this.active = active;
    }

    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    public String getApprovedBy() {
		return approvedBy;
	}
	public String getApprovedOn() {
		return approvedOn;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public void setApprovedOn(String approvedOn) {
		this.approvedOn = approvedOn;
	}
    
}
