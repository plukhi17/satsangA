package com.olsa.utility;

import java.util.List;

import org.apache.log4j.Logger;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.CreditCard;
import com.braintreegateway.CreditCardRequest;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.PaymentMethod;
import com.braintreegateway.PaymentMethodNonce;
import com.braintreegateway.PaymentMethodRequest;
import com.braintreegateway.Result;
import com.braintreegateway.exceptions.NotFoundException;

import com.olsa.pojo.RootMDB;


public class BraintreeUtil {
	static final Logger logger = Logger.getLogger(BraintreeUtil.class);

	
	private static BraintreeGateway gateway;
	public static Result<Customer> createCustomer(RootMDB root) {
		CustomerRequest request = new CustomerRequest()
				  .id(root.getFamilyID())
				  .email(root.getEmail())
				  .firstName(root.getFirstName())
				  .lastName(root.getLastName());

		
				return gateway.customer().create(request);
	}
	

	
	public static Result<? extends PaymentMethod> createCreditCard(PaymentUtils paymentUtils){
		
		 try {
			
			 
			 PaymentMethodRequest request1 = new PaymentMethodRequest()
					  .customerId(paymentUtils.getFamilyCode())
					  .paymentMethodNonce(paymentUtils.getNonce());
			 
			 Result<? extends PaymentMethod> result = gateway.paymentMethod().create(request1);
			 return  result;
		 }catch(Exception nf){
			 	nf.printStackTrace();
				logger.error("Exception occure while saving customer Card details: " + nf.getMessage());
		 }
		return null;
		
		
	}
	
	public static List<CreditCard> getCrediCards(String customerId){
		 List<CreditCard> result=null;
		 try {
			 gateway = BeanUtil.getBean(BraintreeGateway.class);
			 result =gateway.customer().find(customerId).getCreditCards();
		 }catch(Exception nf){
			 	nf.printStackTrace();
				logger.error("Exception occure while retrevig Customer Card details: " + nf.getMessage());
		 }
		return  result;
		
	}


	public static Customer findCustomer(String familyCode) {
		try {
			return gateway.customer().find(familyCode);
		}catch(NotFoundException ne) {
			logger.error("Customer not found for  " +familyCode +" from braintree >> "+ne.getMessage());
		}
		return null;
			
		
	}

	
	public static String getClientToken(String familyId) {
		ClientTokenRequest clientTokenRequest = new ClientTokenRequest()
				  .customerId(familyId);
		return gateway.clientToken().generate(clientTokenRequest);
	}


	public static CreditCard findCreditCard(String token) {
		CreditCard creditCard=null;
		try{
		
			Result<PaymentMethodNonce> result = gateway.paymentMethodNonce().create("A_PAYMENT_METHOD_TOKEN");
			String nonce = result.getTarget().getNonce();
			 creditCard = gateway.creditCard().find(nonce);
			return creditCard;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean deleteCreditCard(String token) {
		Result<CreditCard> result=null;
		try{
		

	
			result = gateway.creditCard().delete(token);
			return result.isSuccess();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}




	public static PaymentMethod getPaymentMethods(String clientToken) {
		PaymentMethod paymentMethod=null;
		try{
			
			paymentMethod = gateway.paymentMethod().find(clientToken);
		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return paymentMethod;
	}
}
