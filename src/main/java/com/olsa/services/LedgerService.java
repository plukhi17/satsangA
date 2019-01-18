/**
 * 
 */
package com.olsa.services;

import java.util.List;

import com.olsa.pojo.ResultObject;
import com.olsa.pojo.SAArghyaDpsitSmmaryMDB;
import com.olsa.utility.Code;


/**
 * @author parthl
 *
 */
public interface LedgerService {
	String addCode(Code code);
	List<Code> getAllCode();
	String saveLedger(SAArghyaDpsitSmmaryMDB code);
	ResultObject getLedgerEntries();
}