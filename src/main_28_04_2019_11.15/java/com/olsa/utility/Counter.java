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
public class Counter implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5642177444668170955L;
	private String seqName;
	private Long counter;
	/**
	 * @return the seqName
	 */
	public String getSeqName() {
		return seqName;
	}
	/**
	 * @param seqName the seqName to set
	 */
	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}
	/**
	 * @return the counter
	 */
	public Long getCounter() {
		return counter;
	}
	/**
	 * @param counter the counter to set
	 */
	public void setCounter(Long counter) {
		this.counter = counter;
	}
	
	
	
	

}
