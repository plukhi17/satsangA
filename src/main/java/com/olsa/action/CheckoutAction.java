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
import com.olsa.utility.ACHDetailsDTO;
import com.olsa.utility.CardDetailsDTO;
import com.olsa.utility.ManualPaymentUtils;
import com.olsa.utility.PaymentACHUtils;
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

	
	public void getClientToken() throws IOException {
		logger.info("Enter in  getACCTOken() CheckoutAction class");
		PrintWriter writer = getResponse().getWriter();
		ObjectMapper mapper = new ObjectMapper();

		String token = paymentServic.transactionGetToken();
		
		
		writer.append(token);
	}
	
	public void achTransactions() throws IOException {
		logger.info("Enter in  transaction() CheckoutAction class");
		PrintWriter writer ;
		ObjectMapper mapper = new ObjectMapper();
		String inputParam=getRequest().getReader().readLine();
		try {
			PaymentACHUtils paymentUtils = mapper.readValue(inputParam, PaymentACHUtils.class);
			
			PaymentResponseUtils paymentResponseUtils=null;
			paymentResponseUtils=paymentServic.transaction(paymentUtils);
			writer= getResponse().getWriter();
			writer.append(mapper.writeValueAsString(paymentResponseUtils));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
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
	public void removeCard() throws JsonParseException, JsonMappingException, IOException {
		PrintWriter writer = getResponse().getWriter();
		ObjectMapper mapper = new ObjectMapper();
		PaymentUtils paymentUtils = mapper.readValue(getRequest().getReader().readLine(), PaymentUtils.class);
		Map<String,String> response=new HashMap<String,String>();
		response.put("responseMsg", paymentServic.removeCard(paymentUtils));
		writer.append(mapper.writeValueAsString(response));
	}
	
	public void addACH() throws JsonParseException, JsonMappingException, IOException {
		PrintWriter writer ;
		ObjectMapper mapper = new ObjectMapper();
		try {
			PaymentACHUtils paymentUtils = mapper.readValue(getRequest().getReader().readLine(), PaymentACHUtils.class);
			Map<String,String> response=new HashMap<String,String>();
			response.put("responseMsg", paymentServic.addACH(paymentUtils));
			writer= getResponse().getWriter();
			writer.append(mapper.writeValueAsString(response));
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public void viewCard() throws IOException {
		PrintWriter writer = getResponse().getWriter();
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("rawtypes")
		HashMap map=mapper.readValue(getRequest().getReader().readLine(),HashMap.class);
		List<CardDetailsDTO> card=paymentServic.viewAllCard(map.get("contact").toString());
		writer.append(mapper.writeValueAsString(card));
		
	}
	public void viewACH() throws IOException {
		PrintWriter writer = getResponse().getWriter();
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("rawtypes")
		HashMap map=mapper.readValue(getRequest().getReader().readLine(),HashMap.class);
		List<ACHDetailsDTO> card=paymentServic.viewAllACH(map.get("contact").toString());
		writer.append(mapper.writeValueAsString(card));
		
	}
	
	
	public void transactionsuccess() {
		//System.out.println(getRequest().getParameter("transactionId"));
		//System.out.println("===============================");
	}
}
