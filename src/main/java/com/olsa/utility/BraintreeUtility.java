package com.olsa.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.CreditCard;
import com.braintreegateway.CreditCardRequest;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Result;
import com.olsa.pojo.RootMDB;

public class BraintreeUtility {
	
	@Autowired
	private static BraintreeGateway gateway;
	
	public static Result<Customer> createCustomer(RootMDB root) {
		CustomerRequest request = new CustomerRequest()
				  .id(root.getFamilyID())
				  .email(root.getEmail())
				  .firstName(root.getFirstName())
				  .lastName(root.getLastName());

		
				return gateway.customer().create(request);
	}
	
	
	public static Result<CreditCard> createCreditCard(PaymentUtils paymentUtils){
		
		
		CreditCardRequest request = new CreditCardRequest()
				  .customerId(paymentUtils.getFamilyCode())
				  .cvv(paymentUtils.getCvv())
				  .number(paymentUtils.getCardNumber())
				  .expirationDate(paymentUtils.getExpirationDate());
		return  gateway.creditCard().create(request);
	}
	
	public static List<CreditCard> getCrediCards(String customerId){
		return gateway.customer().find(customerId).getCreditCards();
		
	}

}
