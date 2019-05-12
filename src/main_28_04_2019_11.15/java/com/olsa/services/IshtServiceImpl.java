package com.olsa.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.olsa.bo.IshtMDBDao;
import com.olsa.bo.MongoBaseDao;
import com.olsa.bo.UserProfileMDBDao;
import com.olsa.pojo.IshtMDB;
import com.olsa.pojo.ResultObject;
import com.olsa.pojo.RootMDB;
import com.olsa.utility.ReportDTO;

public class IshtServiceImpl implements IshtService{
	static final Logger logger = Logger.getLogger(IshtServiceImpl.class);
	private MongoBaseDao mongoBaseDao;
	private UserProfileMDBDao userProfileMDBDao;
	private IshtMDBDao ishtMDBDao;
	
	public MongoBaseDao getMongoBaseDao() {
		return mongoBaseDao;
	}

	public void setMongoBaseDao(MongoBaseDao mongoBaseDao) {
		this.mongoBaseDao = mongoBaseDao;
	}

	public UserProfileMDBDao getUserProfileMDBDao() {
		return userProfileMDBDao;
	}

	public void setUserProfileMDBDao(UserProfileMDBDao userProfileMDBDao) {
		this.userProfileMDBDao = userProfileMDBDao;
	}

	public IshtMDBDao getIshtMDBDao() {
		return ishtMDBDao;
	}

	public void setIshtMDBDao(IshtMDBDao ishtMDBDao) {
		this.ishtMDBDao = ishtMDBDao;
	}

	public ResultObject getUserIshtObjectJSON(String loginDetails) {
		ResultObject result = new ResultObject();
		RootMDB root = userProfileMDBDao.fetchRootDocument(loginDetails);
		logger.info("Inside getUserIshtObjectJSON Service Proxy name "+ root.getFirstName());
		result = ishtMDBDao.getIshtJSONObject(root);
		if(result!=null && result.isSuccess()){
			result.setObject1(result.getObject1());
			result.setObject2(root);
			logger.info("Inside getUserIshtObjectJSON Service Proxy name "+ root.getFirstName());
		}else{
			result.setSuccess(false);
		}
		return result;
	}

	public ResultObject saveIshtObjectJSON(IshtMDB ishtMDB) {
		ResultObject response = new ResultObject();
		response.setObject1(ishtMDB);
		response = ishtMDBDao.saveIshtJSONObject(response);
		return response;
	}

	public ResultObject getIshtTran(String phoneNo) {
		ResultObject response = new ResultObject();
		response.setObject1(phoneNo);
		response = ishtMDBDao.getIshtTran(response);
		return response;
	}

	public ResultObject getIshtTranAdmin(String phoneNo) {
		ResultObject response = new ResultObject();
		response.setObject1(phoneNo);
		response = ishtMDBDao.getIshtTranAdmin(response);
		return response;
	}

	public ResultObject approveIshtTran(ResultObject resObj) {
		logger.info("inside approveIshtTranServiceIMPL" );	
    	 String receiptNo = (String)resObj.getObject1();
    	 String collectedOn = (String)resObj.getObject3();
		ResultObject resultObject = new ResultObject();
		 resultObject.setObject1(receiptNo);
		 RootMDB rootMDB= (RootMDB)resObj.getObject2();
		 resultObject.setObject2(rootMDB);
		 resultObject.setObject3(collectedOn);
		 resultObject  = getIshtMDBDao().approveIshtTran(resultObject);
		
		 return resultObject;
	}

	public ResultObject rejectIshtTran(ResultObject resObj) {
		 
		 ResultObject resultObject = new ResultObject();
		 String receiptNo = (String)resObj.getObject1();
		 resultObject.setObject1(receiptNo);
		 RootMDB rootMDB= (RootMDB)resObj.getObject2();
		 resultObject.setObject2(rootMDB);
		 resultObject  = getIshtMDBDao().approveIshtTran(resultObject);
		 return resultObject;
	}

	public String formatDate(String dateValue) {
		String date = null;
		try {
			date= getIshtMDBDao().formatDate(dateValue);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public ResultObject getRootMDBObject(ResultObject resObj) {
		
		 ResultObject retrunObject = new ResultObject();
		 
		 retrunObject  = getIshtMDBDao().getRootMDBObject(resObj);
		 return retrunObject;
	}

	@Override
	public List<IshtMDB> findReport(ReportDTO reportDTO) {
		List<IshtMDB> reportRootMDB=new ArrayList<IshtMDB>();
		
		reportRootMDB=getIshtMDBDao().findReport(reportDTO);
		
		return reportRootMDB;
	}
}