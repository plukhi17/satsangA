package com.olsa.bo;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.olsa.pojo.IshtMDB;
import com.olsa.pojo.ResultObject;
import com.olsa.pojo.RootMDB;
import com.olsa.pojo.SAArghyaDpsitSmmaryMDB;
import com.olsa.utility.ACHDetailsDTO;
import com.olsa.utility.CardDetailsDTO;
import com.olsa.utility.Code;
import com.olsa.utility.ManualPaymentUtils;
import com.olsa.utility.MongoConstants;
import com.olsa.utility.OnlineSAConstants;
import com.olsa.utility.PaymentACHUtils;
import com.olsa.utility.PaymentUtils;
import com.olsa.utility.SubCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;

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
						.append("codeDescription", code.getCodeDesc());
						
				db.insertOne(document);
				response = "Successfully saved code details";
			} else {
				response = "All ready exist code details";
			}
		} catch (Exception e) {
			logger.error("Exception occure while saving card details: " + e.getMessage());
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
						.append("createdDt",createdDate)
						.append("updatedDt",createdDate)
						.append("createdBy",createdDate)
						.append("updatedBy",createdDate)
						.append("amountDesc", ledger.getAmountDesc());
						
				db.insertOne(document);
				response = "Leedger Entry Created Successfully";
			
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
    		List<SAArghyaDpsitSmmaryMDB> listIshtMDB = new ArrayList<SAArghyaDpsitSmmaryMDB>();

    		cursor = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.SA_ARGHYA_DEPPOSIT_SUMMARY).find().iterator();
    		if(cursor!=null){
    			while(cursor.hasNext()){
    				Document result = cursor.next();
    				IshtMDB ishtMDB = new IshtMDB();
    				if(result.get("collectedOn")!=null) {
    					logger.info("Collected On : "+result.get("collectedOn").toString());
        				//ishtMDB.setCollectedOn(formatDate((result.get("collectedOn").toString())));
        				
    				}
    				if(result.get("trnDetails")!=null) {
        				ishtMDB.setTrnDetails(result.get("trnDetails").toString());
        				ishtMDB.setChequeIssueBank(result.get("chequeIssueBank").toString().toUpperCase());
        				
    				}
    				ishtMDB.setFamilyID(result.get("familyID").toString());

    				//ishtMDB.setChecqNo(result.get("checqNo").toString());
    				ishtMDB.setTotal((Double) result.get("total"));
    				ishtMDB.setIssuedFlag(result.get("issuedFlag").toString());
    				ishtMDB.setReceiptNo(result.get("receiptNo").toString());
    				//listIshtMDB.add(ishtMDB);
    			}

    		}	
    		IshtMDB i = new IshtMDB();
    		//i.setTrnList(listIshtMDB);
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
			dto.setCodeDesc((String)doc.get("codeDescrition"));
			dto.setSubCodes((List<SubCode>)doc.get("subCodes"));
			cardDTOs.add(dto);
		}
		return cardDTOs;
	}
	

}