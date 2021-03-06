package com.olsa.services;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.olsa.bo.LedgerDao;
import com.olsa.pojo.ResultObject;
import com.olsa.pojo.SAArghyaDpsitSmmaryMDB;
import com.olsa.utility.Code;
import com.olsa.utility.SubCode;

public class LedgerServiceImpl implements LedgerService{
	static final Logger logger = Logger.getLogger(LedgerServiceImpl.class);


	private LedgerDao ledgerDao1;
	
	
	
	/**
	 * @return the ledgerDao1
	 */
	public LedgerDao getLedgerDao1() {
		return ledgerDao1;
	}

	/**
	 * @param ledgerDao1 the ledgerDao1 to set
	 */
	public void setLedgerDao1(LedgerDao ledgerDao1) {
		this.ledgerDao1 = ledgerDao1;
	}

	@Override
	public String addCode(Code code) {
		return ledgerDao1.saveCodeDetails(code);
	}
	@Override
	public String addSubCode(SubCode code) {
		return ledgerDao1.saveSubCodeDetails(code);
	}
	
	@Override
	public String saveLedger(SAArghyaDpsitSmmaryMDB ledger) {
		if(ledger.getHeadType().toLowerCase().equals("income")) {
			ledger.setBalance(ledger.getBalance()+ledger.getAmount());
		}else if(ledger.getHeadType().toLowerCase().equals("expense")) {
			ledger.setBalance(ledger.getBalance()-ledger.getAmount());
		}
		return ledgerDao1.saveLedger(ledger);
	}
	
	@Override
	public List<Code> getAllCode(String headType) {
		return ledgerDao1.viewAllCode(headType);
	}
	
	@Override
	public List<Code> getAllSubCodesByCode(String codeName) {
		return ledgerDao1.getAllSubCodesByCode(codeName);
	}
	
	public ResultObject getLedgerEntries() {
		ResultObject response = new ResultObject();
	
		response = ledgerDao1.getLedgerEntries(response);
		return response;
	}
	
	public ResultObject getBalanceSummary(Date sDate) {
		
	
		return ledgerDao1.getBalanceSummary(sDate);
		
	}

	@Override
	public String getNextIncCode(String seqName) {
		
		return ledgerDao1.getNextIncCode(seqName);
	}
}
