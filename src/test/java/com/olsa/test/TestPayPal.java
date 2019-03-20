package com.olsa.test;

import java.math.BigDecimal;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.braintreegateway.ValidationError;

public class TestPayPal {

	private static BraintreeGateway gateway = new BraintreeGateway(
			  Environment.SANDBOX,
			  "ty5m9dd9k9cgwrrf",
			  "m8jx83zmf2d943pm",
			  "bdc8dfd06fbd183c304f45a1f2a00057"
			);
	
	
	public static void main(String[] args) {
		//TestPayPal.testPayMent();
		//TestPayPal.generate();
		//TestPayPal.test();
	}
	
	public static void generate() {
		String clientToken = gateway.clientToken().generate();
		System.out.println(clientToken);
	}
	
	public static void testPayMent()
	{
		
		 BigDecimal decimalAmount;
	        	  decimalAmount = new BigDecimal("-11");
	       

	        TransactionRequest request = new TransactionRequest()
	        		 .amount(decimalAmount)
	            .paymentMethodNonce("fake-valid-nonce")
	            .options()
	                .submitForSettlement(true)
	                .done();

	        Result<Transaction> result = gateway.transaction().sale(request);

	        if (result.isSuccess()) {
	            Transaction transaction = result.getTarget();
	            System.out.println( transaction.getId()+"  seccuss"+transaction.getAmount());
	        } else if (result.getTransaction() != null) {  
	            Transaction transaction = result.getTransaction();
	           System.out.println(transaction.getId()+"            Error");
	        } else {
	            String errorString = "";
	            for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
	               errorString += "Error: " + error.getCode() + ": " + error.getMessage() + "\n";
	            }
	            System.out.println(errorString);
	        }
	    }
	
	
		public static void test() {
			TransactionRequest request = new TransactionRequest()
					  .creditCard()
					    .number("4111111111111111")
					  //  .expirationDate("09/10")
					    .expirationMonth("10")
					    .expirationYear("2016")
					    .cvv("100")
					    .done()
					    
					  .amount(new BigDecimal("100.00"))
					  .paymentMethodNonce("fake-valid-nonce")
					  .options()
					    .submitForSettlement(true)
					    .done();

					Result<Transaction> result = gateway.transaction().sale(request);

					  if (result.isSuccess()) {
				            Transaction transaction = result.getTarget();
				            System.out.println( transaction.getId()+"  seccuss:  "+transaction.getAmount());
				        } else if (result.getTransaction() != null) {
				            Transaction transaction = result.getTransaction();
				           System.out.println(transaction.getId()+"            Error");
				        } else {
				            String errorString = "";
				            for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
				               errorString += "Error: " + error.getCode() + ": " + error.getMessage() + "\n";
				            }
				            System.out.println(errorString);
				        }
		}
	
	
	
	
}
