/**
 * 
 */
package com.olsa.utility;

import java.io.Serializable;

/**
 * @author parthl
 *
 */
public class SubCode implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1017461588197030447L;
	private String subCodeName;
	private String subCodeDesc;
	/**
	 * @return the subCodeName
	 */
	public String getSubCodeName() {
		return subCodeName;
	}
	/**
	 * @param subCodeName the subCodeName to set
	 */
	public void setSubCodeName(String subCodeName) {
		this.subCodeName = subCodeName;
	}
	/**
	 * @return the subCodeDesc
	 */
	public String getSubCodeDesc() {
		return subCodeDesc;
	}
	/**
	 * @param subCodeDesc the subCodeDesc to set
	 */
	public void setSubCodeDesc(String subCodeDesc) {
		this.subCodeDesc = subCodeDesc;
	}


}
