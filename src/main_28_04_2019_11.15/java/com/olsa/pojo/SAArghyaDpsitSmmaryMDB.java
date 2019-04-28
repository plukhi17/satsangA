
package com.olsa.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SAArghyaDpsitSmmaryMDB {

	private BigDecimal id;
   
    private Date transactionDate;
    
    @JsonProperty("amount")
    private Double amount;
    
    @JsonProperty("amountDesc")
    private String amountDesc;
    
   
    @JsonProperty("headType")
    private String headType;
    
    @JsonProperty("headCode")
    private String headCode;
    
    @JsonProperty("headCodeDesc")
    private String headCodeDesc;
    
    @JsonProperty("headSubCode")
    private String headSubCode;
    
    
    @JsonProperty("headSubCodeDesc")
    private String headSubCodeDesc;
    
    @JsonProperty("submittedOn")
    private String submittedOn;
    
    
    private Date createdDt;
    
    private String createdBy;
    
    private Date updatedDt;
    
    private String updatedBy;

	/**
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the amountDesc
	 */
	public String getAmountDesc() {
		return amountDesc;
	}

	/**
	 * @param amountDesc the amountDesc to set
	 */
	public void setAmountDesc(String amountDesc) {
		this.amountDesc = amountDesc;
	}

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
	 * @return the createdDt
	 */
	public Date getCreatedDt() {
		return createdDt;
	}

	/**
	 * @param createdDt the createdDt to set
	 */
	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the updatedDt
	 */
	public Date getUpdatedDt() {
		return updatedDt;
	}

	/**
	 * @param updatedDt the updatedDt to set
	 */
	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the id
	 */
	public BigDecimal getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(BigDecimal id) {
		this.id = id;
	}

	/**
	 * @return the headCode
	 */
	public String getHeadCode() {
		return headCode;
	}

	/**
	 * @param headCode the headCode to set
	 */
	public void setHeadCode(String headCode) {
		this.headCode = headCode;
	}

	/**
	 * @return the headCodeDesc
	 */
	public String getHeadCodeDesc() {
		return headCodeDesc;
	}

	/**
	 * @param headCodeDesc the headCodeDesc to set
	 */
	public void setHeadCodeDesc(String headCodeDesc) {
		this.headCodeDesc = headCodeDesc;
	}

	/**
	 * @return the headSubCode
	 */
	public String getHeadSubCode() {
		return headSubCode;
	}

	/**
	 * @param headSubCode the headSubCode to set
	 */
	public void setHeadSubCode(String headSubCode) {
		this.headSubCode = headSubCode;
	}

	/**
	 * @return the headSubCodeDesc
	 */
	public String getHeadSubCodeDesc() {
		return headSubCodeDesc;
	}

	/**
	 * @param headSubCodeDesc the headSubCodeDesc to set
	 */
	public void setHeadSubCodeDesc(String headSubCodeDesc) {
		this.headSubCodeDesc = headSubCodeDesc;
	}

	/**
	 * @return the submittedOn
	 */
	public String getSubmittedOn() {
		return submittedOn;
	}

	/**
	 * @param submittedOn the submittedOn to set
	 */
	public void setSubmittedOn(String submittedOn) {
		this.submittedOn = submittedOn;
	}


    
    
    

   
}
