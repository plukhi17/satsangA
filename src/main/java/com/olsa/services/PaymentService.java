package com.olsa.services;

import java.util.List;

import com.olsa.utility.ACHDetailsDTO;
import com.olsa.utility.CardDetailsDTO;
import com.olsa.utility.ManualPaymentUtils;
import com.olsa.utility.PaymentACHUtils;
import com.olsa.utility.PaymentResponseUtils;
import com.olsa.utility.PaymentUtils;

public interface PaymentService {

	PaymentResponseUtils transaction(PaymentUtils paymentUtils);
	
	PaymentResponseUtils transaction(ManualPaymentUtils paymentUtils);
	

	String addCard(PaymentUtils paymentUtils);

	List<CardDetailsDTO> viewAllCard(String string);

	String addACH(PaymentACHUtils paymentUtils);

	List<ACHDetailsDTO> viewAllACH(String string);

	String removeCard(PaymentUtils paymentUtils);

	PaymentResponseUtils transaction(PaymentACHUtils paymentUtils);

	String transactionGetToken();

}
