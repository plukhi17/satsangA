package com.olsa.services;

import java.util.List;

import com.olsa.utility.CardDetailsDTO;
import com.olsa.utility.ManualPaymentUtils;
import com.olsa.utility.PaymentResponseUtils;
import com.olsa.utility.PaymentUtils;

public interface PaymentService {

	PaymentResponseUtils transaction(PaymentUtils paymentUtils);
	
	PaymentResponseUtils transaction(ManualPaymentUtils paymentUtils);
	

	String addCard(PaymentUtils paymentUtils);

	List<CardDetailsDTO> viewAllCard(String string);

}
