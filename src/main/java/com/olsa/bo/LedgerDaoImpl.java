package com.olsa.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.operation.OrderBy;
import com.olsa.pojo.ResultObject;
import com.olsa.pojo.SAArghyaDpsitSmmaryMDB;
import com.olsa.utility.Code;
import com.olsa.utility.Counter;
import com.olsa.utility.DateUtility;
import com.olsa.utility.ManualPaymentUtils;
import com.olsa.utility.MongoConstants;
import com.olsa.utility.OnlineSAConstants;
import com.olsa.utility.SubCode;

public class LedgerDaoImpl extends MongoBaseDao implements LedgerDao {
	static final Logger logger = Logger.getLogger(LedgerDaoImpl.class);
	
	private MongoClient mongoClient;

	

	/**
	 * @return the mongoClient
	 */
	public MongoClient getMongoClient() {
		return mongoClient;
	}




	/**
	 * @param mongoClient the mongoClient to set
	 */
	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}




	@Override
	public String saveCodeDetails(Code code) {
		String response = null;
		try {
			if (ifExistCode(code.getCodeName()) != true) {
				MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName())
						.getCollection(MongoConstants.CODE_DETAILS);
						//.getCollection("CardDetails");
				Document document = new Document().append("codeName", code.getCodeName())
						.append("codeType", code.getCodeType())
						.append("codeDesc", code.getCodeDesc());
						
				db.insertOne(document);
				
				MongoCollection<Counter> countDb = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.COUNTER,Counter.class); 
				String codeSeqType="";
				if(code.getCodeType().equalsIgnoreCase("income")) {
					codeSeqType=OnlineSAConstants.INCOME_CODE_SEQ_NAME;
				}else {
					codeSeqType=OnlineSAConstants.EXPN_CODE_SEQ_NAME;
				}
				Counter seqObj = countDb.findOneAndUpdate(Filters.eq(MongoConstants.CNT_SEQ_NAME,codeSeqType),
						Updates.inc(MongoConstants.CNT_SEQ_COUNTER, 1),
						new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER).upsert(true));
				
				response = "Successfully saved code details.";
			} else {
				response = "All ready exist code details.";
			}
		} catch (Exception e) {
			logger.error("Exception occure while saving card details: " + e.getMessage());
			response = "Try later";
		}
		return response;

	}
	@Override
	public String saveSubCodeDetails(SubCode code) {
		String response = null;
		try {
			if (ifExistSubCode(code.getSubCodeName()) == false) {
				MongoCollection<Code> db = getMongoClient().getDatabase(getMongoDbName())
						.getCollection(MongoConstants.CODE_DETAILS, Code.class);
						//.getCollection("CardDetails");
			

				BasicDBObject searchQuery = new BasicDBObject().append("codeName", code.getCodeName());
//				//do the update
//			    db.update(Filters.eq("codeName", code.getCodeName(), new BasicDBObject("$push", new BasicDBObject(code.getSubCodeName(), code.getSubCodeDesc())));
//				Document document = new Document().append("codeName", code.getCodeName())
//						.append("codeDesc", code.getSubCodeDesc());
						
				
				
				BasicDBObject subCodeDoc = new BasicDBObject();
				subCodeDoc.put("subCodeName", code.getSubCodeName());
				subCodeDoc.put("subCodeDesc", code.getSubCodeDesc());
				
				BasicDBObject update = new BasicDBObject();
				update.put("$push", new BasicDBObject("subCodes",subCodeDoc));
				
				
				
				db.updateOne(searchQuery,update);
				
				MongoCollection<Counter> countDb = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.COUNTER,Counter.class);
				Counter seqObj =null;
						
				if(code.getCodeName().toUpperCase().startsWith("INC")) {
					 countDb.findOneAndUpdate(Filters.eq(MongoConstants.CNT_SEQ_NAME, OnlineSAConstants.INCOME_SUB_CODE_SEQ_NAME),
							Updates.inc(MongoConstants.CNT_SEQ_COUNTER, 1),
							new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER).upsert(true));
					
				}else if(code.getCodeName().toUpperCase().startsWith("EXP")) {
					countDb.findOneAndUpdate(Filters.eq(MongoConstants.CNT_SEQ_NAME, OnlineSAConstants.EXPN_SUB_CODE_SEQ_NAME),
							Updates.inc(MongoConstants.CNT_SEQ_COUNTER, 1),
							new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER).upsert(true));
				}
				
				
				response = "Successfully saved Subcode details.";
			} else {
				response = "SubCode already existed.";
			}
		} catch (Exception e) {
			logger.error("Exception occure while saving Subcode details: " + e.getMessage());
			e.printStackTrace();
			response = "Try later";
		}
		return response;

	}
	
	@Override
	public String saveLedger(SAArghyaDpsitSmmaryMDB ledger) {
		String response = null;
		try {
			Date createdDate= new Date();
			
	
				MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName())
						.getCollection(MongoConstants.SA_ARGHYA_DEPPOSIT_SUMMARY);
						//.getCollection("CardDetails");
				
				Document document = new Document().append("amount", ledger.getAmount())
						.append("headType", ledger.getHeadType())
						.append("headCodeName", ledger.getHeadCode())	
						.append("balance", ledger.getBalance())
						.append("headCodeDesc", ledger.getHeadCodeDesc())
						.append("headSubCodeName", ledger.getHeadSubCode())
						.append("headSubCodeDesc", ledger.getHeadSubCodeDesc())
						.append("createdDt",createdDate)
						.append("updatedDt",createdDate)
						.append("createdBy",createdDate)
						.append("updatedBy",createdDate)
						.append("amountDesc", ledger.getAmountDesc());
						
				db.insertOne(document);
				response = "Ledger Entry Created Successfully";
			
		} catch (Exception e) {
			logger.error("Exception occure while saving Leedger Entry details: " + e.getMessage());
			response = "Try later";
		}
		return response;

	}
	
	public ResultObject getLedgerEntries(ResultObject response)
	{

    	
    	try {
    		MongoCursor<Document> cursor= null;
    		List<SAArghyaDpsitSmmaryMDB> listDpstSmry = new ArrayList<SAArghyaDpsitSmmaryMDB>();

    		cursor = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.SA_ARGHYA_DEPPOSIT_SUMMARY).find().iterator();
    		if(cursor!=null){
    			while(cursor.hasNext()){
    				Document result = cursor.next();
    				SAArghyaDpsitSmmaryMDB dpstSmry = new SAArghyaDpsitSmmaryMDB();
    				if(result.get("amount")!=null) {
    					logger.info("Amount : "+result.get("amount").toString());
        				dpstSmry.setAmount((Double) result.get("amount"));
        			}
    			
    				dpstSmry.setAmountDesc(result.get("amountDesc")!=null?result.get("amountDesc").toString():"");

    				//ishtMDB.setChecqNo(result.get("checqNo").toString());
    				dpstSmry.setHeadType((String) result.get("headType"));
    				dpstSmry.setHeadCode(result.get("headCodeName").toString());
    				dpstSmry.setBalance(Double.valueOf(result.get("balance").toString()));
    				dpstSmry.setHeadCodeDesc(result.get("headCodeDesc").toString());
    				dpstSmry.setHeadSubCode(result.get("headSubCodeName").toString());
    				dpstSmry.setHeadSubCodeDesc(result.get("headSubCodeDesc").toString());
    				dpstSmry.setSubmittedOn(DateUtility.formateDate1((Date)result.get("createdDt")));
    				response.setObject2(dpstSmry.getBalance());
    				listDpstSmry.add(dpstSmry);
    			}

    		}	
    		response.setObject1(listDpstSmry);
    		
    		response.setSuccess(true);
    	}
    	catch(Exception ex) {
    		ex.printStackTrace();
    		logger.info("Exception in getIshtTranAdmin   :"+ex);
    	}
    	
    	return response;
    
	}
	
	public ResultObject getBalanceSummary(Date summaryDate)
	{
		ResultObject response = new ResultObject();
    	try {
    		MongoCursor<Document> cursor= null;
    		
    		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName())
    				.getCollection(MongoConstants.SA_ARGHYA_DEPPOSIT_SUMMARY);
    		summaryDate=DateUtility.atStartOfDay(summaryDate);
    		 BasicDBObject getQuery = new BasicDBObject();
		    getQuery.put("createdDt", new BasicDBObject("$lt", summaryDate));
    		
    		List<SAArghyaDpsitSmmaryMDB> listDpstSmry = new ArrayList<SAArghyaDpsitSmmaryMDB>();
    		cursor = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.SA_ARGHYA_DEPPOSIT_SUMMARY).find(getQuery).
    				sort(new BasicDBObject("createdDt", OrderBy.DESC.getIntRepresentation())).limit(1).iterator();
    		if(cursor!=null){
    			while(cursor.hasNext()){
    				Document result = cursor.next();
    				SAArghyaDpsitSmmaryMDB dpstSmry = new SAArghyaDpsitSmmaryMDB();
    				dpstSmry.setBalance(Double.valueOf(result.get("balance").toString()));
    				response.setObject3(dpstSmry.getBalance());
    			}
    		}	
    		if(response.getObject3()==null) {
    			response.setObject3(0.0);
    		}
    		cursor= null;
    		Date startDate= summaryDate;
    		Date endDate= DateUtility.atEndOfDay(summaryDate);
    		getQuery = new BasicDBObject();
		    getQuery.put("createdDt", new BasicDBObject("$lt", endDate).append("$gte",startDate));
    		
    		cursor = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.SA_ARGHYA_DEPPOSIT_SUMMARY).find(getQuery).
    				iterator();
    		
    		double incomeAmt=0,expenseAmt=0;
    		int incomeCnt = 0,expenseCnt=0;
    	
    		if(cursor!=null){
    			while(cursor.hasNext()){
    				Document result = cursor.next();
    				SAArghyaDpsitSmmaryMDB dpstSmry = new SAArghyaDpsitSmmaryMDB();
    				if(result.get("headType").toString().equals("income")) {
    					incomeAmt=incomeAmt+(double) result.get("amount");
    					incomeCnt++;
    				}else if(result.get("headType").toString().equals("expense")) {
    					expenseAmt=expenseAmt+(double) result.get("amount");
    					expenseCnt++;
    				}
    		
    				listDpstSmry.add(dpstSmry);
    			 }
    			}	
    		
    		BalanceSummaryWrapper incomeWrapper= new BalanceSummaryWrapper();
    		incomeWrapper.setAmount(incomeAmt);
    		incomeWrapper.setHeadType("income");
    		incomeWrapper.setCount(incomeCnt);
    		response.setObject4(incomeWrapper);
    		
    		BalanceSummaryWrapper expenseWrapper= new BalanceSummaryWrapper();
    		expenseWrapper.setAmount(expenseAmt);
    		expenseWrapper.setHeadType("expense");
    		expenseWrapper.setCount(expenseCnt);
    		response.setObject5(expenseWrapper);
    		
    		response.setSuccess(true);

    	}
    	catch(Exception ex) {
    		ex.printStackTrace();
    		logger.info("Exception in getIshtTranAdmin   :"+ex);
    	}
    	
    	return response;
    
	}
	
	
	boolean ifExistSubCode(String subCode) {

		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.CODE_DETAILS);
				//.getCollection("CardDetails");
		boolean flag = false;
		Document document = new Document();
		document.put("subCodes.subCodeName", subCode);
		FindIterable<Document> result = db.find(document);
		for (Document doc : result) {
			//if (doc.get("subCodes.subCodeName") != null) {
				return true;
			//}
		}
		return flag;
	
	}
	
	boolean ifExistCode(String code) {
		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.CODE_DETAILS);
				//.getCollection("CardDetails");
		boolean flag = false;
		Document document = new Document();
		document.put("codeName", code);
		FindIterable<Document> result = db.find(document);
		for (Document doc : result) {
			if (doc.get("codeName") != null) {
				return true;
			}
		}
		return flag;
	}

	
	public void transactionDetail(ManualPaymentUtils paymentUtils, String transationId) {
		try {
			MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName())
					.getCollection(MongoConstants.TRANSACTION_DETAILS);
					//.getCollection("TransactionDetails");
			
			Document document = new Document().append("userId", paymentUtils.getContact())
					.append("familyCode", paymentUtils.getFamilyCode())
					
					.append("Amount", paymentUtils.getAmount())
					.append("transactionId", "PARTH-17-LUKHI")
					.append("transactionDate", paymentUtils.getDate());
			db.insertOne(document);
			logger.info("successfully save trasaction details in DB "+paymentUtils);
		} catch (Exception e) {
			logger.error("Exception occure while saving transaction details: " + e.getMessage());
		}
	}

	@Override
	public List<Code> viewAllCode() {
		List<Code> cardDTOs=new ArrayList<Code>();
		
		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.CODE_DETAILS);  
		//.getCollection("CardDetails");
		Document document = new Document();
		FindIterable<Document> result = db.find(document);
		for (Document doc : result) {
			Code dto=new Code();
			dto.setCodeName((String)doc.get("codeName"));
			dto.setCodeDesc((String)doc.get("codeDesc"));
			dto.setSubCodes((List<SubCode>)doc.get("subCodes"));
			cardDTOs.add(dto);
		}
		return cardDTOs;
	}
	
	@Override
	public List<Code> viewAllCode(String headType) {
		List<Code> cardDTOs=new ArrayList<Code>();
		
		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.CODE_DETAILS);  
		//.getCollection("CardDetails");
		Document document = new Document();
		document.put("codeType", headType);
		FindIterable<Document> result = db.find(document);

		for (Document doc : result) {
			Code dto=new Code();
			dto.setCodeName((String)doc.get("codeName"));
			dto.setCodeDesc((String)doc.get("codeDesc"));
			dto.setCodeType((String)doc.get("codeType"));
			dto.setSubCodes((List<SubCode>)doc.get("subCodes"));
			cardDTOs.add(dto);
		}
		return cardDTOs;
	}
	@Override
	public List<Code> getAllSubCodesByCode(String code) {
		List<Code> cardDTOs=new ArrayList<Code>();
		
		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.CODE_DETAILS);  
		Document document = new Document();
		document.put("codeName", code);
	
		FindIterable<Document> result = db.find(document);
		for (Document doc : result) {
			Code dto=new Code();
			dto.setCodeName((String)doc.get("codeName"));
			dto.setCodeDesc((String)doc.get("codeDesc"));
			dto.setSubCodes((List<SubCode>)doc.get("subCodes"));
			cardDTOs.add(dto);
		}
		return cardDTOs;
	}
	
	@Override
	public String getNextIncCode(String sequenceName) {
		String seqCode = null;
		
		
		//MongoCollection<Counter> db = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.COUNTER,Counter.class); 
		
		//Counter seqObj = db.findOneAndUpdate(Filters.eq(MongoConstants.CNT_SEQ_NAME, sequenceName),
				//Updates.inc(MongoConstants.CNT_SEQ_COUNTER, 1),
				//new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER).upsert(true));
		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.COUNTER);  
		FindIterable<Document> result = db.find(Filters.eq(MongoConstants.CNT_SEQ_NAME, sequenceName));
		int cnt =0;
		for (Document doc : result) {
			cnt=((Double) doc.get("counter")).intValue();
		}
		String formattedCounter="";
		switch(sequenceName){
		case OnlineSAConstants.INCOME_CODE_SEQ_NAME:
			formattedCounter = String.format("%03d",cnt+1); 
			seqCode = "INC"+formattedCounter;
			break;
			
		case OnlineSAConstants.INCOME_SUB_CODE_SEQ_NAME:
			formattedCounter = String.format("%03d",cnt+1); 
			seqCode = "INCSUB"+formattedCounter;
			break;
			
		case OnlineSAConstants.EXPN_CODE_SEQ_NAME:
			formattedCounter = String.format("%03d",cnt+1); 
			seqCode = "EXPN"+formattedCounter;
			break;
			
		case OnlineSAConstants.EXPN_SUB_CODE_SEQ_NAME:
			formattedCounter = String.format("%03d",cnt+1); 
			seqCode = "EXPNSUB"+formattedCounter;
			break;
			
		}
		
		
		
		
		return seqCode;
	}


	
	
}

