package com.olsa.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.soap.SAAJMetaFactory;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.olsa.pojo.IshtMDB;
import com.olsa.pojo.ResultObject;
import com.olsa.pojo.RootMDB;
import com.olsa.pojo.SAArghyaDpsitSmmaryMDB;
import com.olsa.services.LedgerService;
import com.olsa.services.PaymentService;
import com.olsa.utility.ACHDetailsDTO;
import com.olsa.utility.CardDetailsDTO;
import com.olsa.utility.Code;
import com.olsa.utility.ManualPaymentUtils;
import com.olsa.utility.OnlineSAConstants;
import com.olsa.utility.PaymentACHUtils;
import com.olsa.utility.PaymentResponseUtils;
import com.olsa.utility.PaymentUtils;

public class LedgerAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7891022077086320943L;
	static final Logger logger = Logger.getLogger(LedgerAction.class);
	
	private LedgerService ledgerServic;
	
	
	static {
		logger.info("Enter in static block");
	}




	public LedgerAction() {
		//
	}


	
	

	public void addCode() throws JsonParseException, JsonMappingException, IOException {
		PrintWriter writer = getResponse().getWriter();
		ObjectMapper mapper = new ObjectMapper();
		try{
			Code code = mapper.readValue(getRequest().getReader().readLine(), Code.class);
			Map<String,String> response=new HashMap<String,String>();
			response.put("responseMsg", ledgerServic.addCode(code));
			writer.append(mapper.writeValueAsString(response));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void addLedger() throws JsonParseException, JsonMappingException, IOException {
		PrintWriter writer = getResponse().getWriter();
		ObjectMapper mapper = new ObjectMapper();
		try{
			SAArghyaDpsitSmmaryMDB ledger = mapper.readValue(getRequest().getReader().readLine(), SAArghyaDpsitSmmaryMDB.class );
			Map<String,String> response=new HashMap<String,String>();
			RootMDB portalUser = (RootMDB) getRequest().getSession().getAttribute(OnlineSAConstants.ADMIN_USER);
			//ledger.setCreatedBy(portalUser.getFirstName() + " " + portalUser.getLastName());
			//ledger.setUpdatedBy(portalUser.getFirstName() + " " + portalUser.getLastName());
			response.put("responseMsg", ledgerServic.saveLedger(ledger));
			writer.append(mapper.writeValueAsString(response));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void getCodes() throws IOException {
		PrintWriter writer = getResponse().getWriter();
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("rawtypes")
		HashMap map=mapper.readValue(getRequest().getReader().readLine(),HashMap.class);
		List<Code> card=ledgerServic.getAllCode();
		writer.append(mapper.writeValueAsString(card));
		
	}
	public void getLedgerEntries() throws IOException {

	
		logger.info("Inside getLedgerEntries() Action");
		try {
			ResultObject result = new ResultObject();
			result = ledgerServic.getLedgerEntries();
			getResponse().setContentType("text/json;charset=utf-8");
			JSONObject responseObject = new JSONObject();
			if (result.isSuccess()) {
				logger.info("inside sucess");
				responseObject.put("returnCode", "success");
				IshtMDB i = (IshtMDB) result.getObject1();
				JSONObject userJSONObject = new JSONObject(i);
				logger.info(userJSONObject);
				responseObject.put(USER_JSON_OBJECT, userJSONObject);
			} else {
				logger.info("inside failuer");
				responseObject.put(RETURN_CODE, ERROR_FLAG);
			}
			responseObject.write(getResponse().getWriter());
		} catch (Exception e) {

		}
	}

	



	/**
	 * @return the ledgerServic
	 */
	public LedgerService getLedgerServic() {
		return ledgerServic;
	}





	/**
	 * @param ledgerServic the ledgerServic to set
	 */
	public void setLedgerServic(LedgerService ledgerServic) {
		this.ledgerServic = ledgerServic;
	}
	
	
	
	
}
