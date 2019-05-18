package com.olsa.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.mongodb.util.JSON;
import com.olsa.pojo.ResultObject;
import com.olsa.pojo.RootMDB;
import com.olsa.pojo.SAArghyaDpsitSmmaryMDB;
import com.olsa.services.LedgerService;
import com.olsa.utility.Code;
import com.olsa.utility.OnlineSAConstants;
import com.olsa.utility.SubCode;

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
	
	public void addSubCode() throws JsonParseException, JsonMappingException, IOException {
		PrintWriter writer = getResponse().getWriter();
		ObjectMapper mapper = new ObjectMapper();
		try{
			SubCode code = mapper.readValue(getRequest().getReader().readLine(), SubCode.class);
			Map<String,String> response=new HashMap<String,String>();
			response.put("responseMsg", ledgerServic.addSubCode(code));
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
	
		
		List<Code> card = null;
		try {
			String headType = getRequest().getParameter("headType");
			card=ledgerServic.getAllCode(headType);
		}catch(Exception e){
			e.printStackTrace();
		}
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
				responseObject.put(DPST_SMRY_BAL,  result.getObject2());
				responseObject.put(DPST_SMRY_OBJECT,  result.getObject1());
			} else {
				logger.info("inside failuer");
				responseObject.put(RETURN_CODE, ERROR_FLAG);
			}
			responseObject.write(getResponse().getWriter());
		} catch (Exception e) {

		}
	}
	
	public void getBalanceSummary() throws IOException {

		
		logger.info("Inside getBalanceSummary() Action");
		try {
			ResultObject result = new ResultObject();
			String summaryDate = getRequest().getParameter("summaryDate");
			result = ledgerServic.getBalanceSummary(new Date());
			getResponse().setContentType("text/json;charset=utf-8");
			JSONObject responseObject = new JSONObject();
			if (result.isSuccess()) {
				logger.info("inside sucess");
				responseObject.put("returnCode", "success");
				responseObject.put(DPST_SMRY_BAL,  result.getObject3());
				responseObject.put(INC_BAL_WRAPPER,new Gson().toJson(result.getObject4()));
				responseObject.put(EXP_BAL_WRAPPER,  new Gson().toJson(result.getObject5()));
			} else {
				logger.info("inside failuer");
				responseObject.put(RETURN_CODE, ERROR_FLAG);
			}
			responseObject.write(getResponse().getWriter());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	public void getNextIncCode() throws IOException {

		
		logger.info("Inside getNextIncCode() Action");
		try {
			String seqName=OnlineSAConstants.INCOME_CODE_SEQ_NAME;
			String result = ledgerServic.getNextIncCode(seqName);
			getResponse().setContentType("text/json;charset=utf-8");
			JSONObject responseObject = new JSONObject();
			if (!result.isEmpty()) {
				responseObject.put(RETURN_CODE, SUCCESS_FLAG);
				logger.info("inside success");
				logger.info(result);
				responseObject.put(INC_CODE_SEQ, result);
			} else {
				logger.info("inside failuer");
				responseObject.put(RETURN_CODE, ERROR_FLAG);
			}
			responseObject.write(getResponse().getWriter());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
public void getNextExpCode() throws IOException {

		
		logger.info("Inside getNextIncCode() Action");
		try {
			String seqName=OnlineSAConstants.EXPN_CODE_SEQ_NAME;
			String result = ledgerServic.getNextIncCode(seqName);
			getResponse().setContentType("text/json;charset=utf-8");
			JSONObject responseObject = new JSONObject();
			if (!result.isEmpty()) {
				responseObject.put(RETURN_CODE, SUCCESS_FLAG);
				logger.info("inside success");
				logger.info(result);
				responseObject.put(EXPN_CODE_SEQ, result);
			} else {
				logger.info("inside failuer");
				responseObject.put(RETURN_CODE, ERROR_FLAG);
			}
			responseObject.write(getResponse().getWriter());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void getSubCodeByCode() throws IOException {

		
		logger.info("Inside getSubCodeByCode() Action");
		List<Code> subCodes = null;
		try {
			String codeName = getRequest().getParameter(("codeName"));
			String codeType = getRequest().getParameter("codeType");
			String seqName=OnlineSAConstants.INCOME_CODE_SEQ_NAME;
			if(codeType.equalsIgnoreCase("income")){
				seqName=OnlineSAConstants.INCOME_CODE_SEQ_NAME;
			}else if(codeType.equalsIgnoreCase("expense")) {
				seqName=OnlineSAConstants.EXPN_CODE_SEQ_NAME;
			}
			
			subCodes = ledgerServic.getAllSubCodesByCode(codeName);
			getResponse().setContentType("text/json;charset=utf-8");
			JSONObject responseObject = new JSONObject();
			if (!subCodes.isEmpty()) {
				responseObject.put(RETURN_CODE, SUCCESS_FLAG);
				logger.info("inside success");
				logger.info(subCodes);
				responseObject.put("subCodes", subCodes);
			} else {
				logger.info("inside failuer");
				responseObject.put(RETURN_CODE, ERROR_FLAG);
			}
			responseObject.write(getResponse().getWriter());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
public void getNextSubCode() throws IOException {

	
		logger.info("Inside getNextSubCode() Action");
		try {
		
			PrintWriter writer = getResponse().getWriter();
			ObjectMapper mapper = new ObjectMapper();
			String codeType = getRequest().getParameter("codeType");
			

			String seqName=OnlineSAConstants.INCOME_SUB_CODE_SEQ_NAME;
			
			if(codeType.equalsIgnoreCase("income")) {
				seqName=OnlineSAConstants.INCOME_SUB_CODE_SEQ_NAME;
			}else if(codeType.equalsIgnoreCase("expense")) {
				seqName=OnlineSAConstants.EXPN_SUB_CODE_SEQ_NAME;
			}
			String result = ledgerServic.getNextIncCode(seqName);
			getResponse().setContentType("text/json;charset=utf-8");
			JSONObject responseObject = new JSONObject();
			if (!result.isEmpty()) {
				responseObject.put(RETURN_CODE, SUCCESS_FLAG);
				logger.info("inside success");
				logger.info(result);
				responseObject.put(INC_SUB_CODE_SEQ, result);
			} else {
				logger.info("inside failuer");
				responseObject.put(RETURN_CODE, ERROR_FLAG);
			}
			responseObject.write(getResponse().getWriter());
		} catch (Exception e) {
			e.printStackTrace();
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
