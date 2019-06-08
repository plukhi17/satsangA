package com.olsa.services;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.olsa.bo.IshtMDBDao;
import com.olsa.bo.MongoBaseDao;
import com.olsa.bo.TransReportDTO;
import com.olsa.bo.UserProfileMDBDao;
import com.olsa.pojo.IshtMDB;
import com.olsa.pojo.ResultObject;
import com.olsa.pojo.RootMDB;
import com.olsa.utility.CreateNSendArghyaPraswasti;
import com.olsa.utility.MailThread;
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
	public ResultObject downLoadReceipt(TransReportDTO reportDto,RootMDB root) {
		ResultObject result = new ResultObject();
		
		result = getIshtTranByReceipt(reportDto.getReceiptNo());
		IshtMDB isht= ((IshtMDB) result.getObject1());
		ByteArrayOutputStream outputStream = null;
		if (result.isSuccess()) {
			
			String input_family_code=root.getFamilyID() ,input_month_year=isht.getMonthYear();
        	String filename="ARGHYA_PRASWASTI_"+input_family_code+"_"+""+input_month_year+".pdf"; 
        	       	
        	 //now write the PDF content to the output stream
             outputStream = new ByteArrayOutputStream();
            CreateNSendArghyaPraswasti createAP = new CreateNSendArghyaPraswasti();
            try {
				createAP.buildArghyaPraswasti(outputStream,null,isht,root);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            byte[] bytes = outputStream.toByteArray();
            result.setObject1(bytes);
		}
		
		return result;
	}
	public ResultObject saveIshtObjectJSON(IshtMDB ishtMDB) {
		ResultObject response = new ResultObject();
		response.setObject1(ishtMDB);
		response = ishtMDBDao.saveIshtJSONObject(response);
		return response;
	}
	
	public ResultObject getIshtTranByReceipt(String receiptNo) {
		ResultObject response = new ResultObject();
		response.setObject1(receiptNo);
		response.setObject1(ishtMDBDao.getIshtTranByTran(response));
		return response;
	}

	public ResultObject getIshtTran(String phoneNo) {
		ResultObject response = new ResultObject();
		response.setObject1(phoneNo);
		response = ishtMDBDao.getIshtTran(response);
		return response;
	}
	
	public ResultObject loadIshtProp(String domain) {
		ResultObject response = new ResultObject();
		response.setObject1(domain);
		response = ishtMDBDao.laodIshtProp(response);
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
