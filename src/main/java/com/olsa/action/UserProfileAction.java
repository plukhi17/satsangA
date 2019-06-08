package com.olsa.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mongodb.client.FindIterable;
import com.olsa.pojo.ResultObject;
import com.olsa.pojo.RitvikMDB;
import com.olsa.pojo.RootMDB;
import com.olsa.services.RitvikService;
import com.olsa.services.UserService;
import com.olsa.utility.ForgotPasswordResponse;
import com.olsa.utility.OTPResponse;
import com.olsa.utility.OnlineSAConstants;
import com.olsa.utility.SQLUtility;

public class UserProfileAction extends BaseAction {

	static final Logger logger = Logger.getLogger(UserProfileAction.class);
	private static final long serialVersionUID = -5357706454746642860L;
	private UserService userService;
	private RitvikService ritvikService;
	private JdbcTemplate jdbcTemplate;
	
	

	
	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RitvikService getRitvikService() {
		return ritvikService;
	}

	public void setRitvikService(RitvikService ritvikService) {
		this.ritvikService = ritvikService;
	}

	/**
	 * This method will add the primary user to the . validation will validate if
	 * user exists or not based on the mobile # user has provided on sucess update
	 * user will be stored in session to reuse the user during is session. the user
	 * will be navigated to dashbord changes made : dbhishik
	 */

	public void addPrimaryUser() throws IOException {
		String primaryUserDetails = getRequest().getParameter(("primaryUserDetails"));
		String applicationFlow = getRequest().getParameter(("applicationFlow"));
		getResponse().setContentType("text/json;charset=utf-8");
		JSONObject responseObject = new JSONObject();

		try {
			logger.info("inside  addPrimaryUserDetails");
			ResultObject resultObject = getUserService().addPrimaryUserDetails(primaryUserDetails);
			RootMDB userSession = (RootMDB) getRequest().getSession().getAttribute("userBean");
			HashMap errorMap = null;
			if (resultObject.isSuccess()) {
				logger.info("inside sucess");
				RootMDB root = (RootMDB) resultObject.getObject1();
				JSONObject obj = new JSONObject(primaryUserDetails);
				logger.info(obj);
				// do not set user session if admin is adding user
				if (applicationFlow.equalsIgnoreCase(ADMIN_FLOW)) {
					responseObject.put(APPLICATION_FLOW, ADMIN_FLOW);
				} else {
					logger.info("what is the flow " + applicationFlow);
					getRequest().getSession().setAttribute("userBean", root);
					responseObject.put(APPLICATION_FLOW, PORTAL_USER);
				}
				
				SQLUtility sqUtil= new SQLUtility();
				sqUtil.executeSQL(root,jdbcTemplate);
				
				JSONObject userJSONObject = new JSONObject(root);
				responseObject.put(USER_JSON_OBJECT, userJSONObject);
				responseObject.put(RETURN_MESSAGE, CONFIRMATION_MESSAGE);
				logger.info(responseObject.get(RETURN_MESSAGE));
			} else {
				errorMap = resultObject.getErrors();
				logger.info("inside error block for user" + errorMap.get("errorMessage"));
				responseObject.put(RETURN_CODE, ERROR_FLAG);
				responseObject.put(RETURN_MESSAGE, errorMap.get("errorMessage"));
			}
			try {
				responseObject.write(getResponse().getWriter());
			} catch (Exception e) {
				logger.error("Exception occure:"+ e.getMessage());
				responseObject.put(RETURN_CODE, ERROR_FLAG);
				responseObject.put(RETURN_MESSAGE, errorMap.get("errorMessage"));
			}
		} catch (Exception e) {
			logger.error("Exception occure:"+ e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * this method will add family member to the primary member account.
	 * 
	 */

	public void addFamilyUser() throws IOException {

		String familyUserDetails = getRequest().getParameter(("familyUserDetails"));

		if (getRequest().getParameter("applicationFlow") != null)
			;
		String applicationFlow = getRequest().getParameter(("applicationFlow"));
		logger.info("inside addFamilyUser applicationFlow : " + applicationFlow);
		RootMDB userSession;
		if (!OnlineSAConstants.ADMIN_FLOW.equalsIgnoreCase(applicationFlow)) {
			userSession = (RootMDB) getRequest().getSession().getAttribute("userBean");
			logger.info("Session in user phone no " + userSession.getPhoneNo());
		} else {
			userSession = (RootMDB) getRequest().getSession().getAttribute(OnlineSAConstants.USER_ROLE_SUPER_USER);
			logger.info("Session else user phone no " + userSession.getPhoneNo());
		}

		getResponse().setContentType("text/json;charset=utf-8");
		JSONObject responseObject = new JSONObject();

		try {
			logger.info("inside  addfamilyUser");
			ResultObject resultObject = getUserService().addFamilyUserDetails(familyUserDetails, userSession);

			if (resultObject.isSuccess()) {
				logger.info("inside sucess");
				RootMDB root = (RootMDB) resultObject.getObject1();
				getRequest().getSession().setAttribute("userBean", root);
			} else {
				HashMap errorMap = resultObject.getErrors();
				logger.info("inside error block for user" + errorMap.get("errorMessage"));
				responseObject.put(RETURN_CODE, ERROR_FLAG);
				responseObject.put(RETURN_MESSAGE, errorMap.get("errorMessage"));
				try {
					responseObject.write(getResponse().getWriter());
				} catch (Exception e1) {
					e1.printStackTrace();
					responseObject.put(RETURN_CODE, ERROR_FLAG);
					responseObject.put(RETURN_MESSAGE, errorMap.get("errorMessage"));
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * This method will with validate the user. validation will validate if user
	 * exists or not if user exists, it will store the user in session to reuse the
	 * user during is session.
	 */
	public void validateUser() {
		String loginUserDetails = getRequest().getParameter(("userDetails"));
		boolean result = true;
		RootMDB root = null;
		HashMap<String, String> error = null;
		try {
			getRitvikService().getAllRitvik();
			logger.info("inside  validateUser");
			ResultObject resultObject = getUserService().validateLogin(loginUserDetails);
			if (resultObject.getObject1() != null) {
				root = (RootMDB) resultObject.getObject1();

			} else {
				error = resultObject.getErrors();
				logger.info(error.size());
				result = false;
			}

			try {
				getResponse().setContentType("text/json;charset=utf-8");
				JSONObject responseObject = new JSONObject();
				if (result) {
					JSONObject userJSONObject = new JSONObject(root);
					responseObject.put("returnCode", "success");
					responseObject.put("userObject", root);
					responseObject.put(USER_JSON_OBJECT, userJSONObject);
					logger.info(userJSONObject);
					getRequest().getSession().setAttribute("userBean", root);

				} else {
					responseObject.put(RETURN_CODE, ERROR_FLAG);
					if (error.get("password") != null) {
						responseObject.put(RETURN_MESSAGE, error.get("password"));
						logger.info(responseObject);
					} else {
						responseObject.put(RETURN_MESSAGE, error.get("userName"));
					}
				}
				responseObject.write(getResponse().getWriter());
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void getUserJSONObject() {
		try {

			String phoneNo = getRequest().getParameter(("phoneNo"));
			String familyCode = getRequest().getParameter(("familyCode"));
			String firstName = getRequest().getParameter(("firstName"));
			String lastName = getRequest().getParameter(("lastName"));

			logger.info("inside getUserJSONObject");
			getResponse().setContentType("text/json;charset=utf-8");
			JSONObject responseObject = new JSONObject();
			ResultObject result = new ResultObject();
			if (phoneNo != null && phoneNo.length() > 0) {
				result = getUserService().getUserObjectJSON(phoneNo);
				logger.info("Phone No :" + phoneNo);
			} else if (familyCode != null && familyCode.length() > 0) {
				logger.info("familyCode :" + familyCode);
			} else if (firstName != null && firstName.length() > 0) {
				logger.info("firstName :" + firstName);
			} else if (lastName != null && lastName.length() > 0) {
				logger.info("lastName :" + lastName);
			}
			logger.info("getUserJSONObject Action sucess flag" + result.isSuccess());
			if (result.isSuccess()) {
				logger.info("inside sucess");
				responseObject.put("returnCode", "success");
				RootMDB root = (RootMDB) result.getObject1();
				List<RootMDB> rootList = new ArrayList<RootMDB>();
				rootList.add(root);
				JSONObject userJSONObject = new JSONObject(rootList);
				responseObject.put(USER_JSON_OBJECT, rootList);
				getRequest().getSession().removeAttribute(OnlineSAConstants.PORTAL_USER);
				getRequest().getSession().setAttribute(OnlineSAConstants.PORTAL_USER, root);
				logger.info(responseObject);
			} else {
				logger.info("inside failuer");
				responseObject.put(RETURN_CODE, ERROR_FLAG);
			}
			responseObject.write(getResponse().getWriter());
		} catch (JSONException e) {
			logger.info("Exeption in getUserJSONObject " + e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("Exeption in getUserJSONObject " + e);
			e.printStackTrace();
		}

	}

	public void getSearchUserObject() {

		try {

			String phoneNo = getRequest().getParameter(("phoneNo"));
			String familyCode = getRequest().getParameter(("familyCode"));
			String firstName = getRequest().getParameter(("firstName"));
			String lastName = getRequest().getParameter(("lastName"));

			logger.info("inside getSearchUserObject");
			getResponse().setContentType("text/json;charset=utf-8");
			JSONObject responseObject = new JSONObject();
			ResultObject result = new ResultObject();

			if (phoneNo != null && phoneNo.length() > 0) {
				result = getUserService().getSearchUserObject(phoneNo, "phoneNo");
				logger.info("Phone No :" + phoneNo);
			} else if (familyCode != null && familyCode.length() > 0) {
				result = getUserService().getSearchUserObject(familyCode, "familyCode");
				logger.info("familyCode :" + familyCode);
			} else if (firstName != null && firstName.length() > 0) {
				logger.info("firstName :" + firstName);
				result = getUserService().getSearchUserObject(firstName, "firstName");
			} else if (lastName != null && lastName.length() > 0) {
				logger.info("lastName :" + lastName);
				result = getUserService().getSearchUserObject(lastName, "lastName");
			}
			logger.info("getSearchUserObject Action sucess flag" + result.isSuccess());
			if (result.isSuccess()) {
				logger.info("inside sucess");
				responseObject.put("returnCode", "success");
				// RootMDB root = (RootMDB)result.getObject1();
				List<RootMDB> rootList = new ArrayList<RootMDB>();
				FindIterable<RootMDB> iterableRootMDB = (FindIterable<RootMDB>) result.getObject1();
				iterableRootMDB.first();
				for (RootMDB rootMDB : iterableRootMDB) {
					rootList.add(rootMDB);
				}
				// rootList.add(root);
				JSONObject userJSONObject = new JSONObject(rootList);
				responseObject.put(USER_JSON_OBJECT, rootList);
				// getRequest().getSession().removeAttribute(OnlineSAConstants.PORTAL_USER);
				getRequest().getSession().setAttribute(OnlineSAConstants.USER_ROLE_SUPER_USER, rootList);
				logger.info(responseObject);
			} else {
				logger.info("inside failuer");
				responseObject.put(RETURN_CODE, ERROR_FLAG);
			}
			responseObject.write(getResponse().getWriter());
		} catch (JSONException e) {
			logger.info("Exeption in getSearchUserObject " + e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("Exeption in getSearchUserObject " + e);
			e.printStackTrace();
		}

	}

	public void grantAdminAccess() {
		String phoneNo = getRequest().getParameter(("phoneNo"));
		logger.info("inside getUserJSONObject");
		getResponse().setContentType("text/json;charset=utf-8");
		JSONObject responseObject = new JSONObject();
		try {
			ResultObject result = new ResultObject();
			result = getUserService().getUserObjectJSON(phoneNo);
			logger.info("getUserJSONObject Action sucess flag" + result.isSuccess());
			if (result.isSuccess()) {
				logger.info("inside sucess");
				responseObject.put("returnCode", "success");
				responseObject.put(RETURN_MESSAGE, "Admin Access Granted");
				RootMDB root = (RootMDB) result.getObject1();
				JSONObject userJSONObject = new JSONObject(root);
				responseObject.put(USER_JSON_OBJECT, userJSONObject);
				logger.info(responseObject);
			} else {
				logger.info("inside failuer");
				responseObject.put(RETURN_CODE, ERROR_FLAG);
			}
			responseObject.write(getResponse().getWriter());
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void removeAdminAccess() {
		String phoneNo = getRequest().getParameter(("phoneNo"));
		logger.info("inside getUserJSONObject");
		getResponse().setContentType("text/json;charset=utf-8");
		JSONObject responseObject = new JSONObject();
		try {
			ResultObject result = new ResultObject();
			result = getUserService().getUserObjectJSON(phoneNo);
			logger.info("getUserJSONObject Action sucess flag" + result.isSuccess());
			if (result.isSuccess()) {
				logger.info("inside sucess");
				responseObject.put("returnCode", "success");
				RootMDB root = (RootMDB) result.getObject1();
				JSONObject userJSONObject = new JSONObject(root);
				responseObject.put(USER_JSON_OBJECT, userJSONObject);
				logger.info(responseObject);
			} else {
				logger.info("inside failuer");
				responseObject.put(RETURN_CODE, ERROR_FLAG);
			}
			responseObject.write(getResponse().getWriter());
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void getAllRitvik() {

		JSONObject responseObject = new JSONObject();
		try {
			logger.info("Before call" + OnlineSAConstants.ritvikHashMapCache.get("ritvikJSONObject"));
			if (OnlineSAConstants.ritvikHashMapCache.get("ritvikJSONObject") == null) {
				ResultObject result = new ResultObject();
				result = getRitvikService().getAllRitvik();
				logger.info("getAllRitvik Action sucess flag " + result.isSuccess());
				if (result.isSuccess()) {
					responseObject.put("returnCode", "success");
					RitvikMDB ritvik = (RitvikMDB) result.getObject1();
					JSONObject ritvikJSONObject = new JSONObject(ritvik);
					responseObject.put(RITVIK_JSON_OBJECT, ritvikJSONObject);
					logger.info(responseObject);
				} else {
					logger.info("inside failuer");
					responseObject.put(RETURN_CODE, ERROR_FLAG);
				}
			} else {
				logger.info("Getting from cache" + OnlineSAConstants.ritvikHashMapCache.get("ritvikJSONObject"));
				responseObject.put(RITVIK_JSON_OBJECT, OnlineSAConstants.ritvikHashMapCache.get("ritvikJSONObject"));
			}
			responseObject.write(getResponse().getWriter());
		} catch (JSONException e) {
			logger.error("Exception occure:"+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Exception occure:"+ e.getMessage());
			e.printStackTrace();
		}
	}

	public void reportMechanism() throws IOException {
		// setting response
		getResponse().setContentType("text/json;charset=utf-8");
		String reportReq = getRequest().getParameter(("primaryReportDetails"));
		String familyCode = null;
		String receiptno = null;
		String toDate = null;
		String fromDate = null;
		JSONObject reqObject = new JSONObject(reportReq);
		try {
			familyCode = (String) reqObject.get("familyCode");
			receiptno = (String) reqObject.get("receiptno");
			toDate = (String) reqObject.get("toDate");
			fromDate = (String) reqObject.get("fromDate");
		} catch (Exception e) {
			logger.error("Exception occure:"+ e.getMessage());
		}
		ResultObject result = null;
		try {
			if (familyCode != null && familyCode.length() > 0) {
				result = getUserService().getSearchUserObject(familyCode, "familyCode");
			} else if (receiptno != null && receiptno.length() == 0) {

			} else if (toDate != null && toDate.length() > 0 && fromDate != null && fromDate.length() > 0) {

			}
			JSONObject responseObject = new JSONObject(reportReq);
			if (result.isSuccess()) {
				List<JSONObject> rootList = new ArrayList<JSONObject>();
				@SuppressWarnings("unchecked")
				FindIterable<RootMDB> iterableRootMDB = (FindIterable<RootMDB>) result.getObject1();
				iterableRootMDB.first();
				for (RootMDB rootMDB : iterableRootMDB) {
					JSONObject userJSONObject = new JSONObject(rootMDB);
					rootList.add(userJSONObject);
				}
				responseObject.put("returnCode", "success");
				responseObject.put(USER_JSON_OBJECT, rootList);
				getRequest().getSession().setAttribute(OnlineSAConstants.USER_ROLE_SUPER_USER, rootList);
				logger.info("report details  " + rootList);
			} else {
				responseObject.put(RETURN_CODE, ERROR_FLAG);
			}
			responseObject.write(getResponse().getWriter());
		} catch (RuntimeException e) {
			logger.error("Exception occure while getting report datails " + e.getMessage());
		}
	}

	public void forgotPassword() throws IOException {
		PrintWriter writer = getResponse().getWriter();
		ObjectMapper mapper = new ObjectMapper();
		String email=getRequest().getParameter("email");
		ForgotPasswordResponse respons=userService.verifyEmailId(email);
		writer.append(mapper.writeValueAsString(respons));
		
	}
	
	public void verifyOTP() throws IOException {
		PrintWriter writer = getResponse().getWriter();
		ObjectMapper mapper = new ObjectMapper();
		String email=getRequest().getParameter("email");
		String otpNum=getRequest().getParameter("OTPNum");
		String currentDate=getRequest().getParameter("date");
		//System.out.println(email+"  "+otpNum+" "+currentDate);
		OTPResponse res=userService.verifyOTP(email,otpNum,currentDate);
		writer.append(mapper.writeValueAsString(res));
	}
	public void reSendOTP() throws IOException {
		String email=getRequest().getParameter("email");
		String date=getRequest().getParameter("date");
		PrintWriter writer = getResponse().getWriter();
		ObjectMapper mapper = new ObjectMapper();
		ForgotPasswordResponse respons=userService.reSendOTP(email,date);
		writer.append(mapper.writeValueAsString(respons));
	}
	
	
	public void newPassword() throws IOException {
		String mobileNumber=getRequest().getParameter("mobileNumber");
		String newPassword=getRequest().getParameter("newPassword");
		ForgotPasswordResponse respons=userService.updateNewPassword(mobileNumber,newPassword);
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter writer = getResponse().getWriter();
		writer.append(mapper.writeValueAsString(respons));
	}
	
	public void checkEmailExists() throws JsonParseException, JsonMappingException, IOException {
		PrintWriter writer = getResponse().getWriter();
		ObjectMapper mapper = new ObjectMapper();
		String email = getRequest().getParameter("email");
		Map<String,String> response=new HashMap<String,String>();
		response.put("responseMsg", userService.checkEmailExists(email));
		writer.append(mapper.writeValueAsString(response));
	}
	
}
