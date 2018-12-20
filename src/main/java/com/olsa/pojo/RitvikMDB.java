package com.olsa.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
public class RitvikMDB {

	private BigDecimal _id;
	@JsonProperty("IND_FAMILY_CODE")
	private String indiaFamilyCode;
	@JsonProperty("SA_SPR_ID")
	private String saID;
	@JsonProperty("FIRST_NAME")
	private String firstName;
	@JsonProperty("LAST_NAME")
	private String lastName;
	@JsonProperty("ADDRESS")
	private String address;
	@JsonProperty("PANJA_DATE")
	private String panjaDate;
	@JsonProperty("PANJA_RENEWAL")
	private String panjaRenewal;
	@JsonProperty("LIVING_STATUS")
    private String status;
	private String fullName;
	private List<RitvikMDB> ritvikList;
	
    public List<RitvikMDB> getRitvikList() {
		return ritvikList;
	}
	public void setRitvikList(List<RitvikMDB> ritvikList) {
		this.ritvikList = ritvikList;
	}
	public BigDecimal get_id() {
		return _id;
	}
	public void set_id(BigDecimal _id) {
		this._id = _id;
	}
	public String getIndiaFamilyCode() {
		return indiaFamilyCode;
	}
	public void setIndiaFamilyCode(String indiaFamilyCode) {
		this.indiaFamilyCode = indiaFamilyCode;
	}
	public String getSaID() {
		return saID;
	}
	public void setSaID(String saID) {
		this.saID = saID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPanjaDate() {
		return panjaDate;
	}
	public void setPanjaDate(String panjaDate) {
		this.panjaDate = panjaDate;
	}
	public String getPanjaRenewal() {
		return panjaRenewal;
	}
	public void setPanjaRenewal(String panjaRenewal) {
		this.panjaRenewal = panjaRenewal;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getFullName() {
		return this.firstName+" "+ this.lastName;
	}
	public void setFullName() {
		this.fullName = this.firstName+" "+ this.lastName;
		
	}
	
    
}