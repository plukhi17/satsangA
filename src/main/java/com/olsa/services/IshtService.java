package com.olsa.services;

import java.util.List;

import com.olsa.bo.TransReportDTO;
import com.olsa.pojo.IshtMDB;
import com.olsa.pojo.ResultObject;
import com.olsa.pojo.RootMDB;
import com.olsa.utility.ReportDTO;

public interface IshtService {
	
	ResultObject getUserIshtObjectJSON(String loginDetails);
	ResultObject saveIshtObjectJSON(IshtMDB ishtMDB);
	ResultObject getIshtTran(String phoneNo);
	ResultObject approveIshtTran(ResultObject resObj);
	ResultObject rejectIshtTran(ResultObject resObj);
	ResultObject getIshtTranAdmin(String phoneNo);
	String formatDate(String dateValue);
	ResultObject getRootMDBObject(ResultObject resObj);
	List<IshtMDB> findReport(ReportDTO reportDTO);
	ResultObject loadIshtProp(String ishtProp);
	ResultObject downLoadReceipt(TransReportDTO reportDTO, RootMDB rootMdb);
	
}
