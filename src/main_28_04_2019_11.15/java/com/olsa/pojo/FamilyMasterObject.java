/**
 * 
 */
package com.olsa.pojo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ghgiri
 *
 */
public class FamilyMasterObject {
	
	private String SA_FAMILY_CODE;
	private String SA_MEM_CODE;	
	private String IND_FAMILY_CODE;	
	private String FIRST_NAME;	
	private String MIDDLE_NAME;	
	private String LAST_NAME;	
	private String RITWIK_STATUS;	
	private String RITWIK_FIRST_NAME;	
	private String RITWIK_MIDDLE_NAME;
	private String 	RITWIK_LAST_NAME;	
	private String ADDRESS1;	
	private String ADDRESS2;	
	private String ADDRESS3;
	private String CITY;	
	private String STATE;	
	private String COUNTRY;	
	private String ZIP_CODE;	
	private String ARGHYA_PRASWASTI_PAPERLESS_SETUP;	
	private String EMAIL_ID;	
	private String PRIMARY_PHONE;
	private String PERSONALIZE;
	
	private HashMap address;
	private ArrayList family;
	
	public ArrayList getFamily() {
		return family;
	}
	public void setFamily(ArrayList family) {
		this.family = family;
	}
	public HashMap getAddress() {
		return address;
	}
	public void setAddress(HashMap address) {
		this.address = address;
	}
	
	public String getSA_FAMILY_CODE() {
		return SA_FAMILY_CODE;
	}
	public String getSA_MEM_CODE() {
		return SA_MEM_CODE;
	}
	public String getIND_FAMILY_CODE() {
		return IND_FAMILY_CODE;
	}
	public String getFIRST_NAME() {
		return FIRST_NAME;
	}
	public String getMIDDLE_NAME() {
		return MIDDLE_NAME;
	}
	public String getLAST_NAME() {
		return LAST_NAME;
	}
	public String getRITWIK_STATUS() {
		return RITWIK_STATUS;
	}
	public String getRITWIK_FIRST_NAME() {
		return RITWIK_FIRST_NAME;
	}
	public String getRITWIK_MIDDLE_NAME() {
		return RITWIK_MIDDLE_NAME;
	}
	public String getRITWIK_LAST_NAME() {
		return RITWIK_LAST_NAME;
	}
	public String getADDRESS1() {
		return ADDRESS1;
	}
	public String getADDRESS2() {
		return ADDRESS2;
	}
	public String getADDRESS3() {
		return ADDRESS3;
	}
	public String getCITY() {
		return CITY;
	}
	public String getSTATE() {
		return STATE;
	}
	public String getCOUNTRY() {
		return COUNTRY;
	}
	public String getZIP_CODE() {
		return ZIP_CODE;
	}
	public String getARGHYA_PRASWASTI_PAPERLESS_SETUP() {
		return ARGHYA_PRASWASTI_PAPERLESS_SETUP;
	}
	public String getEMAIL_ID() {
		return EMAIL_ID;
	}
	public String getPRIMARY_PHONE() {
		return PRIMARY_PHONE;
	}
	public String getPERSONALIZE() {
		return PERSONALIZE;
	}
	public void setSA_FAMILY_CODE(String sA_FAMILY_CODE) {
		SA_FAMILY_CODE = sA_FAMILY_CODE;
	}
	public void setSA_MEM_CODE(String sA_MEM_CODE) {
		SA_MEM_CODE = sA_MEM_CODE;
	}
	public void setIND_FAMILY_CODE(String iND_FAMILY_CODE) {
		IND_FAMILY_CODE = iND_FAMILY_CODE;
	}
	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}
	public void setMIDDLE_NAME(String mIDDLE_NAME) {
		MIDDLE_NAME = mIDDLE_NAME;
	}
	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}
	public void setRITWIK_STATUS(String rITWIK_STATUS) {
		RITWIK_STATUS = rITWIK_STATUS;
	}
	public void setRITWIK_FIRST_NAME(String rITWIK_FIRST_NAME) {
		RITWIK_FIRST_NAME = rITWIK_FIRST_NAME;
	}
	public void setRITWIK_MIDDLE_NAME(String rITWIK_MIDDLE_NAME) {
		RITWIK_MIDDLE_NAME = rITWIK_MIDDLE_NAME;
	}
	public void setRITWIK_LAST_NAME(String rITWIK_LAST_NAME) {
		RITWIK_LAST_NAME = rITWIK_LAST_NAME;
	}
	public void setADDRESS1(String aDDRESS1) {
		ADDRESS1 = aDDRESS1;
	}
	public void setADDRESS2(String aDDRESS2) {
		ADDRESS2 = aDDRESS2;
	}
	public void setADDRESS3(String aDDRESS3) {
		ADDRESS3 = aDDRESS3;
	}
	public void setCITY(String cITY) {
		CITY = cITY;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public void setCOUNTRY(String cOUNTRY) {
		COUNTRY = cOUNTRY;
	}
	public void setZIP_CODE(String zIP_CODE) {
		ZIP_CODE = zIP_CODE;
	}
	public void setARGHYA_PRASWASTI_PAPERLESS_SETUP(
			String aRGHYA_PRASWASTI_PAPERLESS_SETUP) {
		ARGHYA_PRASWASTI_PAPERLESS_SETUP = aRGHYA_PRASWASTI_PAPERLESS_SETUP;
	}
	public void setEMAIL_ID(String eMAIL_ID) {
		EMAIL_ID = eMAIL_ID;
	}
	public void setPRIMARY_PHONE(String pRIMARY_PHONE) {
		PRIMARY_PHONE = pRIMARY_PHONE;
	}
	public void setPERSONALIZE(String pERSONALIZE) {
		PERSONALIZE = pERSONALIZE;
	}
	
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		  
		    sb.append("{");
	        sb.append("\"SA_FAMILY_CODE:\"").append("\""+this.getSA_FAMILY_CODE()+"\",");
	        sb.append("\"SA_MEM_CODE:\"").append("\""+this.getSA_MEM_CODE()+"\",");
	        sb.append("\"IND_FAMILY_CODE:\"").append("\""+this.getIND_FAMILY_CODE()+"\",");
	        sb.append("\"FIRST_NAME:\"").append("\""+this.getFIRST_NAME()+"\",");
	        sb.append("\"MIDDLE_NAME:\"").append("\""+this.getMIDDLE_NAME()+"\",");
	        sb.append("\"LAST_NAME:\"").append("\""+this.getLAST_NAME()+"\",");
	        sb.append("\"RITWIK_STATUS:\"").append("\""+this.getRITWIK_STATUS()+"\",");
	        sb.append("\"RITWIK_FIRST_NAME:\"").append("\""+this.getRITWIK_FIRST_NAME()+"\",");
	        sb.append("\"RITWIK_MIDDLE_NAME:\"").append("\""+this.getRITWIK_MIDDLE_NAME()+"\",");
	        sb.append("\"RITWIK_LAST_NAME:\"").append("\""+this.getRITWIK_LAST_NAME()+"\",");
	        sb.append("\"ADDRESS1:\"").append("\""+this.getADDRESS1()+"\",");
	        sb.append("\"ADDRESS2:\"").append("\""+this.getADDRESS2()+"\",");
	        sb.append("\"ADDRESS3:\"").append("\""+this.getADDRESS3()+"\",");
	        sb.append("\"CITY:\"").append("\""+this.getCITY()+"\",");
	        sb.append("\"STATE:\"").append("\""+this.getSTATE()+"\",");
	        sb.append("\"COUNTRY:\"").append("\""+this.getCOUNTRY()+"\",");
	        sb.append("\"ZIP_CODE:\"").append("\""+this.getZIP_CODE()+"\",");
	        sb.append("\"ARGHYA_PRASWASTI_PAPERLESS_SETUP:\"").append("\""+this.getARGHYA_PRASWASTI_PAPERLESS_SETUP()+"\",");
	        sb.append("\"EMAIL_ID:\"").append("\""+this.getEMAIL_ID()+"\",");
	        sb.append("\"PRIMARY_PHONE:\"").append("\""+this.getPRIMARY_PHONE()+"\","); 
	        sb.append("\"PERSONALIZE:\"").append("\""+this.getPERSONALIZE()+"\","); 
	        sb.append("\"PASSWORD:\"").append("\"Temp123\""); 
	        sb.append("}");      	       
	   	
		 return sb.toString();
	}


}
