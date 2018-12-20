package com.olsa.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
public class RootMDB

{
    private BigDecimal _id;
    private String familyID;
    private String personalId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNo;
    private String password;
    private AddressMDB address;
    private List<FamilyMDB> family;
    private String ritwikID;
    private boolean ritwikStatus;
    private boolean active;
    private String createdOn;
    private String lastUpdatedOn;
    private String userType;
    private String userName;
    private String userRole;
	private String rName;
    private String pprFlag;
    private String pseronalize;
    private String indfamilyCode;
    
 

	public String getIndfamilyCode() {
		return indfamilyCode;
	}
	public void setIndfamilyCode(String indfamilyCode) {
		this.indfamilyCode = indfamilyCode;
	}
	public String getPseronalize() {
		return pseronalize;
	}
	public void setPseronalize(String pseronalize) {
		this.pseronalize = pseronalize;
	}
	public String getPprFlag() {
		return pprFlag;
	}
	public void setPprFlag(String pprFlag) {
		this.pprFlag = pprFlag;
	}
	public void set_id(BigDecimal _id){
        this._id = _id;
    }
    public BigDecimal get_id(){
        return this._id;
    }
    public void setFamilyID(String familyID){
        this.familyID = familyID;
    }
    public String getFamilyID(){
        return this.familyID;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public void setMiddleName(String middleName){
        this.middleName = middleName;
    }
    public String getMiddleName(){
        return this.middleName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setPhoneNo(String phoneNo){
        this.phoneNo = phoneNo;
    }
    public String getPhoneNo(){
        return this.phoneNo;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setAddress(AddressMDB address){
        this.address = address;
    }
    public AddressMDB getAddress(){
        return this.address;
    }
    public void setFamily(List<FamilyMDB> family){
        this.family = family;
    }
    public List<FamilyMDB> getFamily(){
        return this.family;
    }
    public void setRitwikID(String ritwikID){
        this.ritwikID = ritwikID;
    }
    public String getRitwikID(){
        return this.ritwikID;
    }
    
    public void setRitvikName(String rName){
        this.rName = rName;
    }
    public String getRitvikName(){
        return this.rName;
    }
    
    
    
    
    public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public void setRitwikStatus(boolean ritwikStatus){
        this.ritwikStatus = ritwikStatus;
    }
    public boolean getRitwikStatus(){
        return this.ritwikStatus;
    }
    public void setActive(boolean active){
        this.active = active;
    }
    public boolean getActive(){
        return this.active;
    }
    public void setCreatedOn(String createdOn){
        this.createdOn = createdOn;
    }
    public String getCreatedOn(){
        return this.createdOn;
    }
    public void setLastUpdatedOn(String lastUpdatedOn){
        this.lastUpdatedOn = lastUpdatedOn;
    }
    public String getLastUpdatedOn(){
        return this.lastUpdatedOn;
    }
    
    public void setUserType(String userType){
        this.userType = userType;
    }
    public String getUserType(){
        return this.userType;
    }
    public String getUserName(){
    	return this.firstName +" "+this.lastName;
    }
    public String getPersonalId() {
		return personalId;
	}
	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}
	 public String getUserRole() {
			return userRole;
	}
		public void setUserRole(String userRole) {
			this.userRole = userRole;
	}
		@Override
		public String toString() {
			return "RootMDB [_id=" + _id + ", familyID=" + familyID + ", personalId=" + personalId + ", firstName="
					+ firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", email=" + email
					+ ", phoneNo=" + phoneNo + ", password=" + password + ", address=" + address + ", family=" + family
					+ ", ritwikID=" + ritwikID + ", ritwikStatus=" + ritwikStatus + ", active=" + active
					+ ", createdOn=" + createdOn + ", lastUpdatedOn=" + lastUpdatedOn + ", userType=" + userType
					+ ", userName=" + userName + ", userRole=" + userRole + ", rName=" + rName + ", pprFlag=" + pprFlag
					+ ", pseronalize=" + pseronalize + ", indfamilyCode=" + indfamilyCode + "]";
		}
		
	
}