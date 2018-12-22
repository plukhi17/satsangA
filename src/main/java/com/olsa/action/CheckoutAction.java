package com.olsa.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.olsa.pojo.IshtMDB;
import com.olsa.services.PaymentService;
import com.olsa.utility.CardDetailsDTO;
import com.olsa.utility.ManualPaymentUtils;
import com.olsa.utility.PaymentResponseUtils;
import com.olsa.utility.PaymentUtils;

public class CheckoutAction extends BaseAction {
	static final Logger logger = Logger.getLogger(CheckoutAction.class);
	
	
	static {
		logger.info("Enter in static block");
	}
	private static final long serialVersionUID = 8066302412437876715L;


	private PaymentService paymentServic;

	public CheckoutAction() {
		//
	}

	public PaymentService getPaymentServic() {
		return paymentServic;
	}

	public void setPaymentServic(PaymentService paymentServic) {
		this.paymentServic = paymentServic;
	}

	public void transaction() throws IOException {
		logger.info("Enter in  transaction() CheckoutAction class");
		PrintWriter writer = getResponse().getWriter();
		ObjectMapper mapper = new ObjectMapper();
		PaymentUtils paymentUtils = mapper.readValue(getRequest().getReader().readLine(), PaymentUtils.class);
		
		PaymentResponseUtils paymentResponseUtils=null;
		paymentResponseUtils=paymentServic.transaction(paymentUtils);
		
		
		writer.append(mapper.writeValueAsString(paymentResponseUtils));
	}
	
	public void manualTransactions() throws IOException {
		logger.info("Enter in  transaction() CheckoutAction class");
		PrintWriter writer = getResponse().getWriter();
		ObjectMapper mapper = new ObjectMapper();
		String manualPayDetails = getRequest().getParameter("manualPayDetails");

		JSONObject obj = new JSONObject(manualPayDetails);
		ManualPaymentUtils manualPay =new ManualPaymentUtils();
		manualPay.setAmount(obj.getString("amount"));
		manualPay.setContact(obj.getString("contact"));
		manualPay.setFamilyCode(obj.getString("familyCode"));
		//PaymentUtils paymentUtils = mapper.readValue(getRequest().getReader().readLine(), PaymentUtils.class);
		PaymentResponseUtils paymentResponseUtils=paymentServic.transaction(manualPay);
		writer.append(mapper.writeValueAsString(paymentResponseUtils));
	}
	
	public void addCard() throws JsonParseException, JsonMappingException, IOException {
		PrintWriter writer = getResponse().getWriter();
		ObjectMapper mapper = new ObjectMapper();
		PaymentUtils paymentUtils = mapper.readValue(getRequest().getReader().readLine(), PaymentUtils.class);
		Map<String,String> response=new HashMap<String,String>();
		response.put("responseMsg", paymentServic.addCard(paymentUtils));
		writer.append(mapper.writeValueAsString(response));
	}
	
	public void viewCard() throws IOException {
		PrintWriter writer = getResponse().getWriter();
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("rawtypes")
		HashMap map=mapper.readValue(getRequest().getReader().readLine(),HashMap.class);
		List<CardDetailsDTO> card=paymentServic.viewAllCard(map.get("contact").toString());
		writer.append(mapper.writeValueAsString(card));
		
	}
	
	public void transactionsuccess() {
		//System.out.println(getRequest().getParameter("transactionId"));
		//System.out.println("===============================");
	}
}
