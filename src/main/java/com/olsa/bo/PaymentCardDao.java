package com.olsa.bo;

import java.util.List;

import com.olsa.utility.CardDetailsDTO;
import com.olsa.utility.PaymentUtils;

public interface PaymentCardDao {

	public String saveCadeDetails(PaymentUtils paymentUtils);
	public void transactionDetail(PaymentUtils paymentUtils,String transationId);
	public List<CardDetailsDTO> viewAllCard(String contact);
}
