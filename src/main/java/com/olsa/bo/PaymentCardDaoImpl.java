package com.olsa.bo;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.olsa.utility.ACHDetailsDTO;
import com.olsa.utility.CardDetailsDTO;
import com.olsa.utility.ManualPaymentUtils;
import com.olsa.utility.MongoConstants;
import com.olsa.utility.PaymentACHUtils;
import com.olsa.utility.PaymentUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;

public class PaymentCardDaoImpl extends MongoBaseDao implements PaymentCardDao {
	static final Logger logger = Logger.getLogger(PaymentCardDaoImpl.class);
	private MongoClient mongoClient;

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

	@Override
	public String saveCadeDetails(PaymentUtils paymentUtils) {
		String response = null;
		try {
			if (ifExistCardNumber(paymentUtils.getCardNumber()) != true) {
				MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName())
						.getCollection(MongoConstants.CARD_DETAILS);
						//.getCollection("CardDetails");
				Document document = new Document().append("userId", paymentUtils.getContact())
						.append("familyCode", paymentUtils.getFamilyCode())
						.append("cardNumber", paymentUtils.getCardNumber())
						.append("expiDate", paymentUtils.getExpirationDate());
				db.insertOne(document);
				response = "Successfully saved card details";
			} else {
				response = "All ready exist card details";
			}
		} catch (Exception e) {
			logger.error("Exception occure while saving card details: " + e.getMessage());
			response = "Try later";
		}
		return response;

	}
	
	@Override
	public String removeCardDetails(PaymentUtils paymentUtils) {
		String response = null;
		try {
		
				MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName())
						.getCollection(MongoConstants.CARD_DETAILS);
						//.getCollection("CardDetails");
			db.deleteOne(new Document("cardNumber",paymentUtils.getCardNumber()));
				response = "Successfully removed card details";
			
		} catch (Exception e) {
			logger.error("Exception occure while saving card details: " + e.getMessage());
			response = "Try later";
		}
		return response;

	}
	
	@Override
	public String saveACHDetails(PaymentACHUtils paymentUtils) {
		String response = null;
		try {
			if (ifExistCardNumber(paymentUtils.getChAccNo()) != true) {
				MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName())
						.getCollection(MongoConstants.ACH_DETAILS);
						//.getCollection("CardDetails");
				Document document = new Document().append("userId", paymentUtils.getContact())
						.append("familyCode", paymentUtils.getFamilyCode())
						.append("accName", paymentUtils.getAccName())
						.append("routingNo", paymentUtils.getBankRoutingNo())
						.append("chAccNo", paymentUtils.getChAccNo())
						.append("dlNo", paymentUtils.getDlNo());
				db.insertOne(document);
				response = "Successfully saved card details";
			} else {
				response = "All ready exist card details";
			}
		} catch (Exception e) {
			logger.error("Exception occure while saving card details: " + e.getMessage());
			response = "Try later";
		}
		return response;

	}


	boolean ifExistCardNumber(String cardNumber) {
		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.CARD_DETAILS);
				//.getCollection("CardDetails");
		boolean flag = false;
		Document document = new Document();
		document.put("cardNumber", cardNumber);
		FindIterable<Document> result = db.find(document);
		for (Document doc : result) {
			if (doc.get("cardNumber") != null) {
				return true;
			}
		}
		return flag;
	}
	
	boolean ifExistACHNumber(String accNo) {
		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.ACH_DETAILS);
				//.getCollection("CardDetails");
		boolean flag = false;
		Document document = new Document();
		document.put("chAccNo", accNo);
		FindIterable<Document> result = db.find(document);
		for (Document doc : result) {
			if (doc.get("chAccNo") != null) {
				return true;
			}
		}
		return flag;
	}

	public void transactionDetail(PaymentUtils paymentUtils, String transationId) {
		try {
			MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName())
					.getCollection(MongoConstants.TRANSACTION_DETAILS);
					//.getCollection("TransactionDetails");
			
			Document document = new Document().append("userId", paymentUtils.getContact())
					.append("familyCode", paymentUtils.getFamilyCode())
					.append("cardNumber", paymentUtils.getCardNumber())
					.append("Amount", paymentUtils.getAmount())
					.append("transactionId", transationId)
					.append("transactionDate", new Date());
			db.insertOne(document);
			logger.info("successfully save trasaction details in DB "+paymentUtils);
		} catch (Exception e) {
			logger.error("Exception occure while saving transaction details: " + e.getMessage());
		}
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
	public List<CardDetailsDTO> viewAllCard(String contact) {
		List<CardDetailsDTO> cardDetailsDTOs=new ArrayList<CardDetailsDTO>();
		
		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.CARD_DETAILS);  
		//.getCollection("CardDetails");
		Document document = new Document();
		document.put("userId", contact);
		FindIterable<Document> result = db.find(document);
		for (Document doc : result) {
			CardDetailsDTO dto=new CardDetailsDTO();
			dto.setCardNumber((String)doc.get("cardNumber"));
			dto.setExpirationDate((String)doc.get("expiDate"));
			cardDetailsDTOs.add(dto);
		}
		return cardDetailsDTOs;
	}
	
	@Override
	public List<ACHDetailsDTO> viewAllACH(String contact) {
		List<ACHDetailsDTO> cardDetailsDTOs=new ArrayList<ACHDetailsDTO>();
		
		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.ACH_DETAILS);  
		//.getCollection("CardDetails");
		Document document = new Document();
		document.put("userId", contact);
		FindIterable<Document> result = db.find(document);
		for (Document doc : result) {
			ACHDetailsDTO dto=new ACHDetailsDTO();
			dto.setAccName((String)doc.get("accName"));
			dto.setRoutingNo((String)doc.get("routingNo"));
			dto.setChAccNo((String)doc.get("chAccNo"));
			dto.setDlNo((String)doc.get("dlNo"));
			cardDetailsDTOs.add(dto);
		}
		return cardDetailsDTOs;
	}
}
