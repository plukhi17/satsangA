
package com.olsa.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.olsa.utility.CustomDateDeSerializer;
import com.olsa.utility.CustomDateSerializer;


public class IshtMDB {

	private BigDecimal _id;
   
    private Date transactionDate;
    private String phoneNo;
    @JsonProperty("stTrnNo")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String trnDetails;
    @JsonProperty("dtIshtDate")
    @JsonSerialize(using= CustomDateSerializer.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String collectedOn;
    private String collectedBy;
    @JsonProperty("pmtMethod")
    private String paymentMethod;

    @JsonProperty("stChqNo")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String checqNo;
    @JsonProperty("stBankName")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String chequeIssueBank;
    @JsonProperty("chqDate")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize(using= CustomDateSerializer.class)
    @JsonDeserialize(using= CustomDateDeSerializer.class)
    private Date checqDate;
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
  
    private Date submittedOn;
    
    
    private String accName;
	private String bankRoutingNo;
	private String chAccNo;
	private String dlNo;
     
    
    public Date getSubmittedOn() {
		return submittedOn;
	}
	public void setSubmittedOn(Date submittedOn) {
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
    	if(chequeIssueBank!=null) {
    		return chequeIssueBank.toUpperCase();
    	}else {
    		return  chequeIssueBank; 
    	}
        
    }

    public void setChequeIssueBank(String chequeIssueBank) {
        this.chequeIssueBank = chequeIssueBank.toUpperCase();
    }

    public Date getChecqDate() {
        return checqDate;
    }

    public void setChecqDate(Date checqDate) {
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
    
	
}
