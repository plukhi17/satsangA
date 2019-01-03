/**
 * 
 */
package com.olsa.utility;

import java.io.Serializable;

/**
 * @author Parth
 *
 */
public class ACHDetailsDTO implements Serializable {
	private static final long serialVersionUID = -3745684431500938685L;
	private String accName;
	private String routingNo;
	private String chAccNo;
	private String dlNo;

	
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
	 * @return the routingNo
	 */
	public String getRoutingNo() {
		return routingNo;
	}


	/**
	 * @param routingNo the routingNo to set
	 */
	public void setRoutingNo(String routingNo) {
		this.routingNo = routingNo;
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


	@Override
	public String toString() {
		return "ACHDetailsDTO [accName=" + accName + ", routingNo=" + routingNo + ", chAccNo=" + chAccNo + ", dlNo=" + dlNo + "]";
	}
}