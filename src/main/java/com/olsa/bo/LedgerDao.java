package com.olsa.bo;

import java.util.List;

import com.olsa.pojo.ResultObject;
import com.olsa.pojo.SAArghyaDpsitSmmaryMDB;
import com.olsa.utility.Code;

public interface LedgerDao {

	public String saveCodeDetails(Code code);
	public List<Code> viewAllCode();
	public String saveLedger(SAArghyaDpsitSmmaryMDB ledger);
	public ResultObject getLedgerEntries(ResultObject response);

}
