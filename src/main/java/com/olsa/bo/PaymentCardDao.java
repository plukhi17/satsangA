package com.olsa.bo;

import java.util.List;

import com.olsa.utility.ACHDetailsDTO;
import com.olsa.utility.CardDetailsDTO;
import com.olsa.utility.ManualPaymentUtils;
import com.olsa.utility.PaymentACHUtils;
import com.olsa.utility.PaymentUtils;

public interface PaymentCardDao {

	public String saveCadeDetails(PaymentUtils paymentUtils);
	public void transactionDetail(PaymentUtils paymentUtils,String transationId);
	public void transactionDetail(ManualPaymentUtils paymentUtils,String transationId);
	public List<CardDetailsDTO> viewAllCard(String contact);
	public String saveACHDetails(PaymentACHUtils paymentUtils);
	public List<ACHDetailsDTO> viewAllACH(String contact);
}
