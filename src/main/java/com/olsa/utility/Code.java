/**
 * 
 */
package com.olsa.utility;

import java.io.Serializable;
import java.util.List;

/**
 * @author parthl
 *
 */
public class Code implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5865873884593512504L;
	
	private String codeName;
	private String codeDesc;
	private List<SubCode> subCodes;
	
	private String codeType;
	/**
	 * @return the codeName
	 */
	public String getCodeName() {
		return codeName;
	}
	/**
	 * @param codeName the codeName to set
	 */
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	/**
	 * @return the codeDesc
	 */
	public String getCodeDesc() {
		return codeDesc;
	}
	/**
	 * @param codeDesc the codeDesc to set
	 */
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	/**
	 * @return the subCodes
	 */
	public List<SubCode> getSubCodes() {
		return subCodes;
	}
	/**
	 * @param subCodes the subCodes to set
	 */
	public void setSubCodes(List<SubCode> subCodes) {
		this.subCodes = subCodes;
	}
	/**
	 * @return the codeType
	 */
	public String getCodeType() {
		return codeType;
	}
	/**
	 * @param codeType the codeType to set
	 */
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	
	

}
