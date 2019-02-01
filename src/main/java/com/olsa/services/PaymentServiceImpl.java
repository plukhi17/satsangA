package com.olsa.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.CreditCard;
import com.braintreegateway.Customer;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.braintreegateway.ValidationError;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.olsa.bo.PaymentCardDao;
import com.olsa.utility.ACHDetailsDTO;
import com.olsa.utility.CardDetailsDTO;
import com.olsa.utility.ErrorValidation;
import com.olsa.utility.ManualPaymentUtils;
import com.olsa.utility.PaymentACHUtils;
import com.olsa.utility.PaymentResponseUtils;
import com.olsa.utility.PaymentUtils;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	static final Logger logger = Logger.getLogger(PaymentServiceImpl.class);
	
	private BraintreeGateway gateway;
	
	private PaymentCardDao paymentCard;
	
	public PaymentCardDao getPaymentCard() {
		return paymentCard;
	}

	public void setPaymentCard(PaymentCardDao paymentCard) {
		this.paymentCard = paymentCard;
	}

	public BraintreeGateway getGateway() {
		return gateway;
	}

	public void setGateway(BraintreeGateway gateway) {
		this.gateway = gateway;
	}
	
	
public String transactionGetToken() {
		String token=null;
		
		try {
			token= gateway.clientToken().generate();
			logger.error("generated Client Token : "+token);
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return token;
		

	}
	
	
	
	public PaymentResponseUtils transaction(PaymentUtils paymentUtils) {
		
		PaymentResponseUtils paymentResponseUtils=new PaymentResponseUtils();
		
		if (amount(paymentUtils.getAmount()) == false) {
			paymentResponseUtils.setResMessage("Amount Not valid Try again");
			paymentResponseUtils.setStatus(false);
			logger.error("Payment Transaction Respponse Failed: FamilyCode: "+paymentUtils.getFamilyCode()+", contact number: "+paymentUtils.getContact());
			logger.error("paymentResponseUtils : "+paymentResponseUtils);
			
			return paymentResponseUtils;
		} else {
			TransactionRequest request = new TransactionRequest().creditCard().number(paymentUtils.getCardNumber())
					//.expirationDate(paymentUtils.getExpirationDate())
					.expirationMonth(paymentUtils.getExpirationDate().split("/")[0])
				    .expirationYear(paymentUtils.getExpirationDate().split("/")[1])
					.cvv(paymentUtils.getCvv()).done()
					.amount(new BigDecimal(paymentUtils.getAmount())).paymentMethodNonce("fake-valid-nonce").options()
					.submitForSettlement(true).done();
		       	Result<Transaction> result = gateway.transaction().sale(request);
			
			if (result.isSuccess()) {
				Transaction transaction = result.getTarget();
				Date trasnDate=new Date();
				paymentResponseUtils.setResMessage("Success");
				paymentResponseUtils.setStatus(true);
				paymentResponseUtils.setTrasactionId(transaction.getId());
				paymentResponseUtils.setTransactionDate(trasnDate);
				paymentUtils.setTransDate(trasnDate);
				paymentCard.transactionDetail(paymentUtils, transaction.getId());
			
				logger.info("Transaction Successfully went through !! paymentResponseUtils Response :  "+paymentResponseUtils);
				logger.info("FamilyCode: "+paymentUtils.getFamilyCode()+", contact number: "+paymentUtils.getContact()+", transaction id: "+transaction.getId());
				return paymentResponseUtils;
				
			} else if (result.getTransaction() != null) {
				Transaction transaction = result.getTransaction();
				Date trasnDate=new Date();
				paymentResponseUtils.setResMessage(result.getMessage());
				paymentResponseUtils.setStatus(false);
				paymentResponseUtils.setTrasactionId(transaction.getId());
				paymentResponseUtils.setTransactionDate(trasnDate);
				paymentUtils.setTransDate(trasnDate);
				paymentCard.transactionDetail(paymentUtils, transaction.getId());
	
		           
				logger.error("Payment Transaction Respponse Fail: FamilyCode: "+paymentUtils.getFamilyCode()+", contact number: "+paymentUtils.getContact()+", transaction id: "+transaction.getId());
			} else {			
				List<ErrorValidation> list=new ArrayList<ErrorValidation>();
				int i=1;
				String errorString = "";
				for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
					ErrorValidation errorValidation=new ErrorValidation();
					errorValidation.setCode(error.getCode().code);
					errorValidation.setError(error.getMessage().replace('_', ' '));
					list.add(errorValidation);
				}
				 for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
		               errorString += "Error: " + error.getCode() + ": " + error.getMessage() + "\n";
		            }
				paymentResponseUtils.setResMessage("Try again valid details");
				paymentResponseUtils.setStatus(false);
				paymentResponseUtils.setErrorValidations(list);
				logger.error("Payment Transaction Respponse Fail: FamilyCode: "+paymentUtils.getFamilyCode()+", contact number: "+paymentUtils.getContact()+", Error: "+errorString);
			}
			return paymentResponseUtils;
		}

	}
	
	
	public PaymentResponseUtils transaction(PaymentACHUtils paymentUtils) {
		
		PaymentResponseUtils paymentResponseUtils=new PaymentResponseUtils();
		
		if (amount(paymentUtils.getAmount()) == false) {
			paymentResponseUtils.setResMessage("Amount Not valid Try again");
			paymentResponseUtils.setStatus(false);
			logger.error("Payment Transaction Respponse Failed: FamilyCode: "+paymentUtils.getFamilyCode()+", contact number: "+paymentUtils.getContact());
			logger.error("paymentResponseUtils : "+paymentResponseUtils);
			
			return paymentResponseUtils;
		} else {
			TransactionRequest request = new TransactionRequest()
					.amount(new BigDecimal(paymentUtils.getAmount())).paymentMethodNonce(paymentUtils.getNonce()).options()
					.submitForSettlement(true).done();
		       	Result<Transaction> result = gateway.transaction().sale(request);
			
			if (result.isSuccess()) {
				Transaction transaction = result.getTarget();
				Date transDate= new Date();
				paymentResponseUtils.setResMessage("Success");
				paymentResponseUtils.setStatus(true);
				paymentResponseUtils.setTrasactionId(transaction.getId());
				paymentResponseUtils.setTransactionDate(transDate);
				paymentUtils.setTransDate(transDate);
				paymentCard.transactionDetail(paymentUtils, transaction.getId());
				
				logger.info("Transaction Successfully went through !! paymentResponseUtils Response :  "+paymentResponseUtils);
				logger.info("FamilyCode: "+paymentUtils.getFamilyCode()+", contact number: "+paymentUtils.getContact()+", transaction id: "+transaction.getId());
				return paymentResponseUtils;
				
			} else if (result.getTransaction() != null) {
				Transaction transaction = result.getTransaction();
				Date transDate= new Date();
				paymentResponseUtils.setResMessage(result.getMessage());
				paymentResponseUtils.setStatus(false);
				paymentResponseUtils.setTrasactionId(transaction.getId());
				paymentResponseUtils.setTransactionDate(transDate);
				paymentUtils.setTransDate(transDate);
				paymentCard.transactionDetail(paymentUtils, transaction.getId());
				
		           
				logger.error("Payment Transaction Respponse Fail: FamilyCode: "+paymentUtils.getFamilyCode()+", contact number: "+paymentUtils.getContact()+", transaction id: "+transaction.getId());
			} else {			
				List<ErrorValidation> list=new ArrayList<ErrorValidation>();
				int i=1;
				String errorString = "";
				for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
					ErrorValidation errorValidation=new ErrorValidation();
					errorValidation.setCode(error.getCode().code);
					errorValidation.setError(error.getMessage().replace('_', ' '));
					list.add(errorValidation);
				}
				 for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
		               errorString += "Error: " + error.getCode() + ": " + error.getMessage() + "\n";
		            }
				paymentResponseUtils.setResMessage("Try again valid details");
				paymentResponseUtils.setStatus(false);
				paymentResponseUtils.setErrorValidations(list);
				logger.error("Payment Transaction Respponse Fail: FamilyCode: "+paymentUtils.getFamilyCode()+", contact number: "+paymentUtils.getContact()+", Error: "+errorString);
			}
			return paymentResponseUtils;
		}

	}
	

	
/*
 * 	(non-Javadoc)
 * @see com.olsa.services.PaymentService#transaction(com.olsa.utility.ManualPaymentUtils)
 * This method is used for manual payment method
 */
public PaymentResponseUtils transaction(ManualPaymentUtils paymentUtils) {
		
		PaymentResponseUtils paymentResponseUtils=new PaymentResponseUtils();
		
		if (amount(paymentUtils.getAmount()) == false) {
			paymentResponseUtils.setResMessage("Amount Not valid Try again");
			paymentResponseUtils.setStatus(false);
			logger.error("Payment Transaction Respponse Failed: FamilyCode: "+paymentUtils.getFamilyCode()+", contact number: "+paymentUtils.getContact());
			logger.error("paymentResponseUtils : "+paymentResponseUtils);
			
			return paymentResponseUtils;
		} else {
		
			
	
				paymentResponseUtils.setResMessage("Success");
				paymentResponseUtils.setStatus(true);
				String uniqueID = UUID.randomUUID().toString();
				paymentResponseUtils.setTrasactionId(uniqueID);
				paymentCard.transactionDetail(paymentUtils, paymentResponseUtils.getTrasactionId());
				
				logger.info("Transaction Successfully went through !! paymentResponseUtils Response :  "+paymentResponseUtils);
				logger.info("FamilyCode: "+paymentUtils.getFamilyCode()+", contact number: "+paymentUtils.getContact()+", transaction id: "+paymentResponseUtils.getTrasactionId());
				return paymentResponseUtils;
				
			
			}
			
		

	}

	public boolean amount(String amount) {
		BigDecimal decimalAmount;
		decimalAmount = new BigDecimal(amount);

		TransactionRequest request = new TransactionRequest().amount(decimalAmount)
				.paymentMethodNonce("fake-valid-nonce").options().submitForSettlement(true).done();

		Result<Transaction> result = gateway.transaction().sale(request);

		if (result.isSuccess()) {
			return true;
		} else if (result.getTransaction() != null) {
			return true;
		} else {
			return true;
		}
	}

	public String getTransaction() {
		Map<String, Object> map = new HashMap<String, Object>();
		String transactionId = null;

		Transaction transaction;
		CreditCard creditCard;
		Customer customer;
		try {
			transaction = gateway.transaction().find(transactionId);
			creditCard = transaction.getCreditCard();
			customer = transaction.getCustomer();
		} catch (Exception e) {
			logger.error("Exception: " + e);
			return "redirect:/checkouts";
		}

		map.put("isSuccess", transaction.getStatus());
		map.put("transaction", transaction);
		map.put("creditCard", creditCard);
		map.put("customer", customer);

		return "checkouts/show";
	}

	
	@Override
	public String addCard(PaymentUtils paymentUtils) {
		return paymentCard.saveCadeDetails(paymentUtils);
	}
	@Override
	public String removeCard(PaymentUtils paymentUtils) {
		return paymentCard.removeCardDetails(paymentUtils);
	}
	@Override
	public String removeACH(PaymentACHUtils paymentUtils) {
		return paymentCard.removeACHDetails(paymentUtils);
	}
	
	
	@Override
	public String addACH(PaymentACHUtils paymentUtils) {
		return paymentCard.saveACHDetails(paymentUtils);
	}
	
	@Override
	public List<CardDetailsDTO> viewAllCard(String contact) {
		
		return paymentCard.viewAllCard(contact);
	}
	
	@Override
	public List<ACHDetailsDTO> viewAllACH(String contact) {
		
		return paymentCard.viewAllACH(contact);
	}

}
