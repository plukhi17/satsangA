package com.olsa.bo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.olsa.mongo.OsMongoClient;
import com.olsa.pojo.FamilyMDB;
import com.olsa.pojo.IshtLineMDB;
import com.olsa.pojo.IshtMDB;
import com.olsa.pojo.IshtRefVal;
import com.olsa.pojo.ResultObject;
import com.olsa.pojo.RootMDB;
import com.olsa.utility.DateUtility;
import com.olsa.utility.MongoConstants;
import com.olsa.utility.OnlineSAConstants;
import com.olsa.utility.ReportDTO;

public class IshtMDBDao extends MongoBaseDao {
	
	static final Logger logger = Logger.getLogger(IshtMDBDao.class);
	private final ThreadLocal<Random> random = new ThreadLocal<Random>();
	private MongoBaseDao mongoBaseDao;
	private OsMongoClient osMongoClient;
	 
    public MongoBaseDao getMongoBaseDao() {
        return this.mongoBaseDao;
    }

    public void setMongoBaseDao(MongoBaseDao mongoBaseDao) {
        this.mongoBaseDao = mongoBaseDao;
    }

    public OsMongoClient getOoMongoClient() {
        return this.osMongoClient;
    }

    public void setOoMongoClient(OsMongoClient osMongoClient) {
        this.osMongoClient = osMongoClient;
    }

    public MongoDatabase getMongoDataBase() {
        if (null != this.osMongoClient) {
            return this.osMongoClient.getMongoClient().getDatabase(this.osMongoClient.getMongoDbName());
        } else {
            return null;
        }
    }     
  
    public ResultObject getIshtJSONObject(RootMDB root){

    	ResultObject resultObject = new ResultObject();
    	List<IshtLineMDB> line = new ArrayList<IshtLineMDB>();
    	List<IshtLineMDB> ishtLineMDBList =  new ArrayList <IshtLineMDB>(); //Creating new list add RitwikName
    	
    	List<FamilyMDB> familyList = root.getFamily();
    	//logger.info("family list count" + familyList.size());
    	IshtMDB isht = new IshtMDB();
    	try {
    		isht.setFamilyID(root.getFamilyID());
    		isht.setAddress(root.getAddress());

    		List<IshtLineMDB> list = this.fetchIshtLines(root.getPhoneNo());

    		logger.info("Size of list before if : "+list);

    		if (list!=null && !list.isEmpty() ) { //||   (test != null && !test.isEmpty()

    			logger.info("Size of list after if  : "+list);

    			JSONObject userJSONObject = new JSONObject(isht);
    			logger.info("ist lines  " +userJSONObject);

    			List list1 = new ArrayList();
    			List list2 = new ArrayList();

    			ListIterator<IshtLineMDB> ishtLine=list.listIterator();  
    			while(ishtLine.hasNext()){  
    				IshtLineMDB ishtLineObj = (IshtLineMDB)ishtLine.next();
    				
    				list1.add(ishtLineObj.getId());
        			/*added blcok for RitwikName */
    				if(familyList!=null && !familyList.isEmpty()){
        				ListIterator<FamilyMDB> itr=familyList.listIterator();  
        				while(itr.hasNext()){  
        					FamilyMDB family = (FamilyMDB)itr.next();
        						if(ishtLineObj.getId().equalsIgnoreCase(family.getPersonalID())) {
        						logger.info("family.getPersonalID() :  " +family.getPersonalID());
            					logger.info("ishtLineObj.getId() : " +ishtLineObj.getId());
        						ishtLineObj.setRitwik(family.getrName());
        						ishtLineMDBList.add(ishtLineObj);	
        						
        					}
        				}
        			}
        			/*added blcok for RitwikName */
    				
    			}

    			if(familyList!=null && !familyList.isEmpty()){
    				ListIterator<FamilyMDB> itr=familyList.listIterator();  
    				while(itr.hasNext()){  
    					FamilyMDB family = (FamilyMDB)itr.next();
    					list2.add(family.getPersonalID());
    				}
    				list2.add(root.getPersonalId());
    			}
    			list2.removeAll(list1);
    			logger.info("getting remaning objects of family "+ list2);
    			logger.info("getting remaning objects of family "+ list2.size());
    			
    			
    			for(int i=0; i<=list2.size()-1;i++ ){
    				logger.info("List 2 contents "+list2.get(i).toString());
    				ListIterator<FamilyMDB> itr=familyList.listIterator();  
    				while(itr.hasNext()){  
    					FamilyMDB family = (FamilyMDB)itr.next();
    					if(family.getPersonalID().equalsIgnoreCase(list2.get(i).toString())){
    						logger.info("got the diff" +family.getPersonalID());       
    						IshtLineMDB istLine = new IshtLineMDB();
    						istLine.setName(family.getFirstName().toUpperCase()+ " "+family.getLastName().toUpperCase());
    						istLine.setId(family.getPersonalID());
    						istLine.setSwastyayani(0.00d);
    						istLine.setIstavrity(0.00d);
    						istLine.setAcharyavrity(0.00d);
    						istLine.setDakshina(0.00d);
    						istLine.setSangathani(0.00d);
    						istLine.setPronami(0.00d);
    						istLine.setSurplus(0.00d);
    						istLine.setRitwiki(0.00d);
    						istLine.setTotal(0.00d);
    						istLine.setRitwik(family.getrName());
    						list.add(istLine);
    						ishtLineMDBList.add(istLine);
    					}
    				}
    			}
    			isht.setLine(list);
    			//isht.setLine(ishtLineMDBList);
    		}
    		else
    		{
    			logger.info("Inside else part");
    			/**
    			 * add primary member
    			 */
    			IshtLineMDB ist = new IshtLineMDB();
    			ist.setName(root.getFirstName()+ " "+root.getLastName());
    			ist.setId(root.getPersonalId());
    			ist.setSwastyayani(0.00d);
    			ist.setIstavrity(0.00d);
    			ist.setAcharyavrity(0.00d);
    			ist.setDakshina(0.00d);
    			ist.setSangathani(0.00d);
    			ist.setPronami(0.00d);
    			ist.setSurplus(0.00d);
    			ist.setRitwiki(0.00d);
    			ist.setTotal(0.00d);
    			ist.setRitwik(root.getrName());
    			line.add(ist);
    			ishtLineMDBList.add(ist);

    			/**
    			 * add family member
    			 */
    			if(familyList!=null && !familyList.isEmpty()){
    				ListIterator<FamilyMDB> itr=familyList.listIterator();  
    				while(itr.hasNext()){  
    					FamilyMDB family = (FamilyMDB)itr.next();
    					IshtLineMDB istLine = new IshtLineMDB();
    					istLine.setName(family.getFirstName()+ " "+family.getLastName());
    					istLine.setId(family.getPersonalID());
    					istLine.setSwastyayani(0.00d);
    					istLine.setIstavrity(0.00d);
    					istLine.setAcharyavrity(0.00d);
    					istLine.setDakshina(0.00d);
    					istLine.setSangathani(0.00d);
    					istLine.setPronami(0.00d);
    					istLine.setSurplus(0.00d);
    					istLine.setRitwiki(0.00d);
    					istLine.setTotal(0.00d);
    					istLine.setRitwik(family.getrName());
    					line.add(istLine);
    					ishtLineMDBList.add(istLine);
    				}  
    			}
    			isht.setLine(line);
    			//isht.setLine(ishtLineMDBList);
    		}
    		resultObject.setObject1(isht);
    		resultObject.setSuccess(true);
    	}
    	catch(Exception ex) {
    		logger.info("Exception in getIshtJSONObject :"+ex);
    	}
    	return resultObject;
    }        



    public String getIshtSeqID(){
    	BigDecimal seqID=  getIshtSequenceIdForSeqName((MongoConstants.ISHT_SEQ));  
    	String familyID=null ;
    	logger.info("seqID   " + seqID.toString());
    	if (Integer.valueOf(seqID.intValue())<=9){
    		familyID = getYearMonth()+"/000000"+ seqID.toString();
    	}else if(Integer.valueOf(seqID.intValue())<=99){
    		familyID =getYearMonth()+"/00000"+ seqID.toString();
    	}else if(Integer.valueOf(seqID.intValue())<=999){
    		familyID =getYearMonth()+"/0000"+ seqID.toString();
    	}else if(Integer.valueOf(seqID.intValue())<=9999){
    		familyID = getYearMonth()+"/000"+ seqID.toString();
    	}else if(Integer.valueOf(seqID.intValue())<=99999){
    		familyID =getYearMonth()+"/00"+ seqID.toString();
	    }else if(Integer.valueOf(seqID.intValue())<=999999){
			familyID =getYearMonth()+"/0"+ seqID.toString();
		}
	    else if(Integer.valueOf(seqID.intValue())<=999999){
			familyID = getYearMonth()+"/"+ seqID.toString();
		}
    	
    	return familyID;
    }
    
    public ResultObject saveIshtJSONObject(ResultObject response){
    	response.setSuccess(true);
    	IshtMDB ishtMDB = (IshtMDB)response.getObject1();
    	ishtMDB.setReceiptNo(getIshtSeqID());
    	if(ishtMDB.getPaymentMethod().equals("MANUAL")) {
    		ishtMDB.setIssuedFlag("N");
    	}else {
    		ishtMDB.setIssuedFlag("Y");
    	}
    	
    	ishtMDB.setActive("Y");
    	MongoCollection<IshtMDB > rootSubmit = getMongoClient().
				getDatabase(getMongoDbName()).getCollection("Isht", IshtMDB.class);
		rootSubmit.insertOne(ishtMDB);
		response.setSuccess(true);
		return response;
    }

    public ResultObject getIshtTran(ResultObject response){
    	
    	try {

    		String phoneNo= (String)response.getObject1();
    		logger.info(" Inside the getIshtTran , PhoneNo : "+phoneNo);

    		MongoCursor<Document> cursor= null;
    		List<IshtMDB> listIshtMDB = new ArrayList<IshtMDB>();

    		cursor = getMongoClient().getDatabase(getMongoDbName()).getCollection(OnlineSAConstants.ISHT_COLLECTION).find(Filters.and(Filters.eq("phoneNo", phoneNo))).iterator();
    		if(cursor!=null){
    			while(cursor.hasNext()){
    				Document result = cursor.next();
    				IshtMDB ishtMDB = new IshtMDB();
    				if(result.get("collectedOn")!=null) {
    					logger.info("Collected On : "+result.get("collectedOn").toString());
        				ishtMDB.setCollectedOn(formatDate((result.get("collectedOn").toString())));
        				
    				}else {
    					//ishtMDB.setCollectedOn(formatDate((result.get("submittedOn").toString())));
    				}
    				if(result.get("trnDetails")!=null) {
        				ishtMDB.setTrnDetails(result.get("trnDetails").toString());
        				
        				
    				}
    				if(result.get("chequeIssueBank")!=null) {
    					ishtMDB.setChequeIssueBank(result.get("chequeIssueBank").toString().toUpperCase());
    				}
    				
    				ishtMDB.setFamilyID(result.get("familyID").toString());

    				//ishtMDB.setChecqNo(result.get("checqNo").toString());
    				ishtMDB.setTotal((Double) result.get("total"));
    				ishtMDB.setIssuedFlag(result.get("issuedFlag").toString());
    				ishtMDB.setReceiptNo(result.get("receiptNo").toString());
    				listIshtMDB.add(ishtMDB);
    			}

    		}	
    		IshtMDB i = new IshtMDB();
    		i.setTrnList(listIshtMDB);
    		logger.info("list size " + listIshtMDB.size());
    		response.setObject1(i);

    		response.setSuccess(true);

    	}
    	catch(Exception ex) {
    		ex.printStackTrace();
    		logger.info("Exception in getIshtTranAdmin  :"+ex);
    	}
    	
    	return response;
    }

    public ResultObject getIshtTranAdmin(ResultObject response){
    	
    	try {
    		logger.info("Inside Admin.. ishtMDBDao");
    		String phoneNo= (String)response.getObject1();
    		logger.info("Phone No :"+phoneNo);

    		MongoCursor<Document> cursor= null;
    		List<IshtMDB> listIshtMDB = new ArrayList<IshtMDB>();

    		cursor = getMongoClient().getDatabase(getMongoDbName()).getCollection(OnlineSAConstants.ISHT_COLLECTION).find(Filters.and(Filters.eq("issuedFlag", "N"))).iterator();
    		if(cursor!=null){
    			while(cursor.hasNext()){
    				Document result = cursor.next();
    				IshtMDB ishtMDB = new IshtMDB();
    				try {
    					ishtMDB.setCollectedOn(formatDate((result.get("collectedOn").toString())));
    				} catch (ParseException e) {
    					e.printStackTrace();
    					logger.info("Exception getIshtTranAdmin :"+e);
    				}
    				ishtMDB.setFamilyID(result.getString("familyID").toString());
    				ishtMDB.setName(result.get("name").toString());
    				ishtMDB.setTrnDetails(result.get("trnDetails").toString());
    				//ishtMDB.setChecqNo(result.get("checqNo").toString());
    				ishtMDB.setChequeIssueBank(result.get("chequeIssueBank").toString());
    				ishtMDB.setTotal((Double) result.get("total"));
    				ishtMDB.setIssuedFlag(result.get("issuedFlag").toString());
    				ishtMDB.setReceiptNo(result.get("receiptNo").toString());
    				//ishtMDB.setCollectedOn(result.get("receiptNo").toString());
    				listIshtMDB.add(ishtMDB);
    				//logger.info("I am here 1");
    				logger.info(" receiptNo : "+ result.get("receiptNo").toString());
    			}
    			//logger.info("I am here 2 ");
    		}	
    		IshtMDB i = new IshtMDB();
    		i.setTrnList(listIshtMDB);
    		logger.info("list size  :" + listIshtMDB.size());
    		response.setObject1(i);
    		response.setSuccess(true);

    	}
    	catch(Exception ex) {
    		
    		logger.info("Exception in getIshtTranAdmin  :"+ex);
    	}
    
    	return response;
    }
    
    public String getYearMonth(){
    	Calendar c = Calendar.getInstance();
    	int year = c.get(Calendar.YEAR);
    	int month = c.get(Calendar.MONTH);
    	return "SA/"+year+"/"+month;
    	
    }
    
    public String formatDate(String dateValue) throws ParseException{
    	
    	SimpleDateFormat dateTimeFormat = new SimpleDateFormat(OnlineSAConstants.DATE_TIME_FORMAT_MONGO);
		SimpleDateFormat dateFormat = new SimpleDateFormat(OnlineSAConstants.DATE_FORMAT_MONGO);
		SimpleDateFormat writeFormat = new SimpleDateFormat(OnlineSAConstants.DATE_FORMAT_MONGO);
		
		Date curDt = new Date();
		Date currentDate = dateTimeFormat.parse(dateValue);
		String formattedCurDate = writeFormat.format(currentDate);
		Date currentFormatDate = dateFormat.parse(formattedCurDate.toString());
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentFormatDate);
		String formatedDate = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.YEAR);
		return formatedDate;
    }
    
    public ResultObject approveIshtTran(ResultObject resultObject){
    	
        try {
    	//added by Shyam on June 05th
    	//ResultObject resultObj= getUserService().getUserObjectJSON(userJSONObject.getPhoneNo());
    	RootMDB root = (RootMDB)resultObject.getObject2();
    	String approverName=root.getFirstName()+" "+root.getMiddleName()+" "+root.getLastName();
    	//added by shyam on June 05th
    	logger.info(" approverName :"+approverName);
    	IshtMDB userJSONObject = fetchIshtDocument(resultObject.getObject1().toString());
    	String collectedOn  = resultObject.getObject3().toString();
    	logger.info("collectedDate  :"+collectedOn);
    	DateUtility dtl=new DateUtility();
    	String receiptDate =  dtl.getCurrentDate();
    	String approvedOn=  dtl.getCurrentDate();
    	String collectedDate = dtl.getformattedDateToWrite(collectedOn);
    	logger.info("collectedDate  :"+collectedDate);
    	logger.info("receiptDate  :"+receiptDate);
    	logger.info("approvedOn  :"+approvedOn);
    	
    	BasicDBObject query = new BasicDBObject();
		BasicDBObject update = new BasicDBObject();
		BasicDBObject value = new BasicDBObject();
		logger.info("receiptNo :"+resultObject.getObject1().toString());
		
		query.append("receiptNo",  resultObject.getObject1().toString());
		 //update.append("$set", new BasicDBObject().append("issuedFlag","Y"));
		 value.put("issuedFlag", "Y");
		 value.put("receiptDate", receiptDate);
		 value.put("approvedOn", approvedOn);
		 value.put("approvedBy", approverName);
		 value.put("collectedOn", collectedDate);
		
		 update.append("$set",value); 
			
		fetchIshtCollection().updateOne(query, update);
		
    	//fetchIshtCollection().findOneAndUpdate(eq("receiptNo", resultObject.getObject1().toString()),new Document("$set",
    	//new Document("issuedFlag", "Y")));
    	//receiptDate=DateUtility dtl=new DateUtility();
    	//approvedBy = approverName;
       //approvedOn 
    	
    	
    	resultObject.setSuccess(true);
    	resultObject.setObject1(userJSONObject);
        }
        catch(Exception ex)
        {
        	logger.error("Exception in approveIshtTran  :"+ex);
        	ex.printStackTrace();
        }
    	return resultObject;
    }
    
    public ResultObject getRootMDBObject(ResultObject resObj) {
    	
    	return resObj;
    }

	public List<IshtMDB> findReport(ReportDTO reportDTO) {//personalId
		logger.info("Inside find report ");
		List<IshtMDB> reportRoot = new ArrayList<IshtMDB>();
		FindIterable<IshtMDB> findIterable = null;
		try {
			if (reportDTO.getFamilyCode() == null && reportDTO.getReceiptNo()==null && reportDTO.getToDate()==null && reportDTO.getFromDate()==null) {
				findIterable = fetchIshtCollection().find();
				
			} else {
				  BasicDBObject whereQuery = new BasicDBObject();
				  		if(reportDTO.getFamilyCode()!=null && !StringUtils.isEmpty(reportDTO.getFamilyCode())) {
				  		  whereQuery.put("familyID", reportDTO.getFamilyCode());
				  		}
				  		if(reportDTO.getReceiptNo()!=null && !StringUtils.isEmpty(reportDTO.getReceiptNo())) {
				  		  whereQuery.put("receiptNo", reportDTO.getReceiptNo());
				  		}
				  	
				  		if(reportDTO.getToDate()!=null && !StringUtils.isEmpty(reportDTO.getToDate())) {
				  			
				  			 whereQuery.put("checqDate", new BasicDBObject("$lte", (reportDTO.getToDate())));
				  		}
				  		if(reportDTO.getFromDate()!=null && !StringUtils.isEmpty(reportDTO.getFromDate())) {
				  			
				  			 whereQuery.put("checqDate", new BasicDBObject("$gte", reportDTO.getFromDate()));
				  		}
				  		if(reportDTO.getFromDate()!=null && !StringUtils.isEmpty(reportDTO.getFromDate()) && reportDTO.getFromDate()!=null && !StringUtils.isEmpty(reportDTO.getFromDate())) {
				  			
			  			 whereQuery.put("checqDate", BasicDBObjectBuilder.start("$gte", reportDTO.getFromDate()).add("$lte", reportDTO.getToDate()).get());
				  		}
					
					
					  findIterable=fetchIshtCollection().find(whereQuery);
				 
				  
			}
			for (IshtMDB ishtMDB : findIterable) {
				if(ishtMDB.getCollectedOn()!=null) {
					logger.info("Collected On : "+ishtMDB.getCollectedOn().toString());
    				try {
						ishtMDB.setCollectedOn(formatDate((ishtMDB.getCollectedOn().toString())));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				
				}
				reportRoot.add(ishtMDB);
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("Exception occure in iterate (copy");
		}
		return reportRoot;
	}

	public ResultObject laodIshtProp(ResultObject response) {

    	
    	try {

    		String domainName= (String)response.getObject1();
    		logger.info(" Inside the laodIshtProp , domainName : "+domainName);

    		MongoCursor<Document> cursor= null;
    		List<IshtRefVal> listIshtRefMDB = new ArrayList<IshtRefVal>();

    		cursor = getMongoClient().getDatabase(getMongoDbName()).getCollection(OnlineSAConstants.ISHT_REF_VAL).find(Filters.and(Filters.eq("domain", domainName))).iterator();
    		if(cursor!=null){
    			while(cursor.hasNext()){
    				Document result = cursor.next();
    				IshtRefVal ishtMDB = new IshtRefVal();
    			
    				
    				ishtMDB.setKey(result.get("key").toString());

    				
    				ishtMDB.setValue(result.get("value").toString());
    				ishtMDB.setDomain(result.get("domain").toString());
    				listIshtRefMDB.add(ishtMDB);
    			}

    		}	
  
    		
    		response.setObject1(listIshtRefMDB);

    		response.setSuccess(true);

    	}
    	catch(Exception ex) {
    		ex.printStackTrace();
    		logger.info("Exception in getIshtTranAdmin  :"+ex);
    	}
    	
    	return response;
    
	}

	public ResultObject getIshtTranByTran1(ResultObject response) {

    	
    	try {

    		String phoneNo= (String)response.getObject1();
    		logger.info(" Inside the getIshtTran , trnDetails : "+phoneNo);

    		MongoCursor<Document> cursor= null;
    		List<IshtMDB> listIshtMDB = new ArrayList<IshtMDB>();

    		cursor = getMongoClient().getDatabase(getMongoDbName()).getCollection(OnlineSAConstants.ISHT_COLLECTION).find(Filters.and(Filters.eq("trnDetails", phoneNo))).iterator();
    		if(cursor!=null){
    			while(cursor.hasNext()){
    				Document result = cursor.next();
    				IshtMDB ishtMDB = new IshtMDB();
    				if(result.get("collectedOn")!=null) {
    					logger.info("Collected On : "+result.get("collectedOn").toString());
        				ishtMDB.setCollectedOn(formatDate((result.get("collectedOn").toString())));
        				
    				}else {
    					//ishtMDB.setCollectedOn(formatDate((result.get("submittedOn").toString())));
    				}
    				if(result.get("trnDetails")!=null) {
        				ishtMDB.setTrnDetails(result.get("trnDetails").toString());
        				
        				
    				}
    				if(result.get("chequeIssueBank")!=null) {
    					ishtMDB.setChequeIssueBank(result.get("chequeIssueBank").toString().toUpperCase());
    				}
    				
    				ishtMDB.setFamilyID(result.get("familyID").toString());
    				ishtMDB.setChecqDate((Date)result.get("checqDate"));
    				ishtMDB.setChecqNo(result.get("checqNo").toString());
    				//ishtMDB.setChecqNo(result.get("checqNo").toString());
    				ishtMDB.setTotal((Double) result.get("total"));
    				ishtMDB.setIssuedFlag(result.get("issuedFlag").toString());
    				ishtMDB.setReceiptNo(result.get("receiptNo").toString());
    				listIshtMDB.add(ishtMDB);
    			}

    		}	
    		IshtMDB i = new IshtMDB();
    		i.setTrnList(listIshtMDB);
    		logger.info("list size " + listIshtMDB.size());
    		response.setObject1(i);

    		response.setSuccess(true);

    	}
    	catch(Exception ex) {
    		ex.printStackTrace();
    		logger.info("Exception in getIshtTranAdmin  :"+ex);
    	}
    	
    	return response;
    }
	
	
public IshtMDB getIshtTranByTran(ResultObject response) {


		IshtMDB ishtMdb = null;
		try {	
			 ishtMdb = fetchIshtDocument(response.getObject1().toString());}
		catch(Exception ex) {
			ex.printStackTrace();
			logger.info("Exception in getIshtTranAdmin  :"+ex);
		}
		
		return ishtMdb;

	}	
	
}
