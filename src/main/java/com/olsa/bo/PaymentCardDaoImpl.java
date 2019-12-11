package com.olsa.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.braintreegateway.CreditCard;
import com.braintreegateway.Customer;
import com.braintreegateway.PaymentMethod;
import com.braintreegateway.Result;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.olsa.pojo.RootMDB;
import com.olsa.utility.ACHDetailsDTO;
import com.olsa.utility.BraintreeUtil;
import com.olsa.utility.CardDetailsDTO;
import com.olsa.utility.ManualPaymentUtils;
import com.olsa.utility.MongoConstants;
import com.olsa.utility.PaymentACHUtils;
import com.olsa.utility.PaymentUtils;

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
	public String saveCadeDetails(PaymentUtils paymentUtils, RootMDB root) {
		String response = null;
		Customer customer=BraintreeUtil.findCustomer(paymentUtils.getFamilyCode());
		try {
	
				/*MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName())
						.getCollection(MongoConstants.CARD_DETAILS);*/
						//.getCollection("CardDetails");
			/*	Document document = new Document().append("userId", paymentUtils.getContact())
						.append("familyCode", paymentUtils.getFamilyCode())
						.append("cardNumber", paymentUtils.getCardNumber())
						.append("expiDate", paymentUtils.getExpirationDate());*/
				//db.insertOne(document);
				//Check whether customer exists or not
		
				
				Result<? extends PaymentMethod> result;
				if(customer==null) {
				
					if(BraintreeUtil.createCustomer(root).isSuccess()) {
						logger.info("Customer Created Successfully ");
					}
					else {
						throw new Exception("Error while saving card==> Saving customer details");
					}
				}
	
					result= BraintreeUtil.createCreditCard(paymentUtils);
					if(result.isSuccess()) {
						if(!ifExistCardNumber(((CreditCard)result.getTarget()).getUniqueNumberIdentifier(),customer)) {
							response ="Card Added Succesfully";
						}else {
							removeCardDetails(((CreditCard)result.getTarget()).getToken());
							response ="Card already exists";
						}
							
						
					}else {
						
						response = "Error while saving the card details :"+result.getMessage();
					}
				
			
		
	
			
		} catch (Exception e) {
			logger.error("Exception occure while saving card details: " + e.getMessage());
			response = "Try later";
		}
		return response;
		
		

	}
	
	

	@Override
	public String removeCardDetails(String cToken) {
		String response = null;
		try {
		
				MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName())
						.getCollection(MongoConstants.CARD_DETAILS);
						//.getCollection("CardDetails");
				//	db.deleteOne(new Document("cardNumber",paymentUtils.getCardNumber()));
				//paymentUtils.getNonce()  is creditocken for this time.
				boolean paymentMethod =BraintreeUtil.deleteCreditCard(cToken);
				if(paymentMethod) {
					response = "Successfully removed card details";
				}else {
					response = "Error removed card details";
				}
				
			
		} catch (Exception e) {
			logger.error("Exception occure while removeCardDetails card details: " + e.getMessage());
			response = "Try later";
		}
		return response;

	}
	
	
	@Override
	public String saveACHDetails(PaymentACHUtils paymentUtils) {
		String response = null;
		try {
			if (ifExistACHNumber(paymentUtils.getChAccNo()) != true) {
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
	@Override
	public String removeACHDetails(PaymentACHUtils paymentUtils) {
		String response = null;
		try {
		
			MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName())
						.getCollection(MongoConstants.ACH_DETAILS);
						//.getCollection("CardDetails");
			db.deleteOne(new Document("chAccNo",paymentUtils.getChAccNo()));
			response = "Successfully removed ACH details";
			
		} catch (Exception e) {
			logger.error("Exception occure while removing ACH details: " + e.getMessage());
			response = "Try later";
		}
		return response;

	}
	

	boolean ifExistCardNumber(PaymentUtils paymentUtils) {
		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.CARD_DETAILS);
				//.getCollection("CardDetails");
		boolean flag = false;
		Document document = new Document();
		document.put("cardNumber", paymentUtils.getCardNumber());
		document.put("familyCode", paymentUtils.getFamilyCode());
		FindIterable<Document> result = db.find(document);
		for (Document doc : result) {
			if (doc.get("cardNumber") != null) {
				return true;
			}
		}
		return flag;
	}
	
	private boolean ifExistCardNumber(String unique, Customer customer) {
			for(CreditCard card : customer.getCreditCards()) {
				if(card.getUniqueNumberIdentifier().equals(unique)) {
					return true;
				}
			}
			return false;
				

			
			
			
		}
	
	
	@Override
	public boolean ifExistTrNo(String trNo) {
		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.ISHT_COLLECTION);
				//.getCollection("CardDetails");
		boolean flag = false;
		Document document = new Document();
		document.put("trnDetails",trNo.toString());
	
		FindIterable<Document> result = db.find(document);
		for (Document doc : result) {
			if (doc.get("trnDetails") != null) {
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
	
	public void transactionDetail(PaymentACHUtils paymentUtils, String transationId) {
		try {
			MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName())
					.getCollection(MongoConstants.TRANSACTION_DETAILS);
					//.getCollection("TransactionDetails");
			
			Document document = new Document().append("userId", paymentUtils.getContact())
					.append("familyCode", paymentUtils.getFamilyCode())
					.append("accNumber", paymentUtils.getChAccNo())
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
	public List<CardDetailsDTO> viewAllCard(String familyId) {
		List<CardDetailsDTO> cardDetailsDTOs=new ArrayList<CardDetailsDTO>();
		
		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.CARD_DETAILS);  
		//.getCollection("CardDetails");
		Document document = new Document();
		document.put("userId", familyId);
		FindIterable<Document> result = db.find(document);
		List<CreditCard> cards=null;
		try {
			cards= BraintreeUtil.getCrediCards(familyId);
			if(cards!=null) {
				for (CreditCard card : cards) {
					CardDetailsDTO dto=new CardDetailsDTO();
					dto.setCardNumber(card.getLast4());
					dto.setCardType(card.getCardType());
					dto.setcToken(card.getToken());
					dto.setExpirationDate(card.getExpirationDate());
					cardDetailsDTOs.add(dto);
				}
			}
		
		}catch(Exception e) {
			e.printStackTrace();
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
