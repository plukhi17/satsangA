package com.olsa.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.olsa.bo.BaseDao;
import com.olsa.bo.TransReportDTO;
import com.olsa.pojo.IshtLineMDB;
import com.olsa.pojo.IshtMDB;
import com.olsa.pojo.ResultObject;
import com.olsa.pojo.RootMDB;
import com.olsa.services.IshtService;
import com.olsa.services.UserService;
import com.olsa.utility.DateUtility;
import com.olsa.utility.MailThread;
import com.olsa.utility.OnlineSAConstants;
import com.olsa.utility.ReportDTO;
import com.olsa.utility.SQLUtility;
import com.olsa.utility.SendEmail;
import com.olsa.utility.TaskThread;
import com.olsa.utility.WriteToMySQL;

public class IshtAction extends BaseAction {
	private static final long serialVersionUID = 5230385675531302191L;
	static final Logger logger = Logger.getLogger(IshtAction.class);
	private UserService userService;
	private IshtService ishtService;
	private BaseDao baseDao;
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

	/**
	 * @return the baseDao
	 */
	public BaseDao getBaseDao() {
		return baseDao;
	}

	/**
	 * @param baseDao the baseDao to set
	 */
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public IshtService getIshtService() {
		return ishtService;
	}

	public void setIshtService(IshtService ishtService) {
		this.ishtService = ishtService;
	}

	public void getIshtJSONObject() {

		try {

			String familyId = getRequest().getParameter(("familyID"));

			if (getRequest().getParameter("applicationFlow") != null)
				;
			String applicationFlow = getRequest().getParameter(("applicationFlow"));

			logger.info(
					"inside getIshtJSONObject 1 : familyId no  :" + familyId + "applicationFlow : " + applicationFlow);

			if (!OnlineSAConstants.ADMIN_FLOW.equalsIgnoreCase(applicationFlow)) {
				RootMDB rootMdb = (RootMDB) getRequest().getSession().getAttribute("userBean");
				familyId = rootMdb.getFamilyID();
				logger.info("Getting Root Object  :" + familyId + "applicationFlow : " + applicationFlow);
			}

			// logger.info("inside getIshtJSONObject 2: Phone no :" + phoneNo
			// +"applicationFlow : "+applicationFlow);
			getResponse().setContentType("text/json;charset=utf-8");
			JSONObject responseObject = new JSONObject();

			ResultObject result = new ResultObject();
			result = getIshtService().getUserIshtObjectJSON(familyId);
			logger.info("getIshtJSONObject Action sucess flag " + result.isSuccess());
			if (result.isSuccess()) {
				RootMDB root = (RootMDB) result.getObject2();
				getRequest().getSession().removeAttribute(OnlineSAConstants.PORTAL_USER);
				getRequest().getSession().setAttribute(OnlineSAConstants.PORTAL_USER, root);
				responseObject.put("returnCode", "success");

				/*
				 * getting the Grand Total ResultObject rslt = new ResultObject(); rslt =
				 * getIshtService().getIshtTran(phoneNo); IshtMDB isthHdr =null;
				 * if(rslt.isSuccess()){ logger.info("inside sucess");
				 * //responseObject.put("returnCode", "success"); isthHdr=
				 * (IshtMDB)rslt.getObject1(); } getting the Grand Total
				 */

				IshtMDB isht = (IshtMDB) result.getObject1();
				JSONObject userJSONObject = new JSONObject(isht);
				// logger.info("Grand Total Amount :"+isthHdr.getTotal());
				// isht.setTotal(isthHdr.getTotal());
				responseObject.put(USER_JSON_OBJECT, userJSONObject);
				logger.info("responseObject : "+responseObject);
			} else {
				logger.info("inside failuer");
				responseObject.put(RETURN_CODE, ERROR_FLAG);
			}
			responseObject.write(getResponse().getWriter());
		} catch (JSONException e) {
			logger.info("Exception inside getIshtJSONObject() :" + e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("Exception inside getIshtJSONObject() :" + e);
			e.printStackTrace();
		}

	}

	/***
	 * this method will load tran details for individual user
	 */
	public void getIshtTran() {

		String familyId = getRequest().getParameter(("familyId"));
		logger.info("Inside getIshtTran() Action");
		try {
			ResultObject result = new ResultObject();
			result = getIshtService().getIshtTran(familyId);
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
	
	

	/***
	 * this method will load tran details for individual user
	 */
	public void loadIshtProp() {

	
		logger.info("Inside loadIshtProp() Action");
		try {
			ResultObject result = new ResultObject();
			result = getIshtService().loadIshtProp(OnlineSAConstants.ISHT_PROP);
			getResponse().setContentType("text/json;charset=utf-8");
			JSONObject responseObject = new JSONObject();
			if (result.isSuccess()) {
				logger.info("inside sucess");
				responseObject.put("returnCode", "success");
			
				responseObject.put(ISHT_REF_OBJECT, result.getObject1());
			} else {
				logger.info("inside failuer");
				responseObject.put(RETURN_CODE, ERROR_FLAG);
			}
			responseObject.write(getResponse().getWriter());
		} catch (Exception e) {

		}
	}

	/***
	 * this method will load tran details for admin user to approve reject tran.
	 */
	public void getIshtTranAdmin() {
		String familyId = getRequest().getParameter(("familyId"));
		logger.info("Inside getIshtTran() Action");
		try {
			ResultObject result = new ResultObject();
			result = getIshtService().getIshtTranAdmin(familyId);
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
				logger.info("inside failure");
				responseObject.put(RETURN_CODE, ERROR_FLAG);
			}
			responseObject.write(getResponse().getWriter());

		} catch (Exception e) {
			logger.info("Exception getIshtTranAdmin() :" + e);
		}

	}

	public void saveIshtJSONData() {
		logger.info("Inside saveIshtJSONData() Action");
		ObjectMapper mapper = new ObjectMapper();

		//mapper.getDeserializationConfig().setDateFormat(new SimpleDateFormat(OnlineSAConstants.DATE_TIME_FORMAT_MONGO));
		//mapper.getSerializationConfig().setDateFormat(new SimpleDateFormat(OnlineSAConstants.DATE_TIME_FORMAT_MONGO));
		String ishtLineData = getRequest().getParameter(("ishtLineData"));
		String ishtHeaderData = getRequest().getParameter(("ishtHeaderData"));
		String applicationFlow = getRequest().getParameter(("applicationFlow"));
		logger.info("applicationFlow" + applicationFlow);
		JSONObject responseObject = null;
		try {
			logger.info("ishtLineData :" + ishtLineData);
			// get header details
			IshtMDB ishtMDBObj = mapper.readValue(ishtHeaderData, IshtMDB.class);

			// get line details to make line and header total
			IshtLineMDB[] ishtLineObj = mapper.readValue(ishtLineData, IshtLineMDB[].class);
			RootMDB rootMdb = (RootMDB) getRequest().getSession().getAttribute("userBean");
			logger.info(ishtLineObj.length);
			List<IshtLineMDB> ishtLineMDBList = new ArrayList<IshtLineMDB>();
			double total = 0;
			for (int i = 0; i < ishtLineObj.length - 1; i++) {
				
				
				ishtLineMDBList.add(ishtLineObj[i + 1]);
				ishtLineObj[i + 1].setTotal(ishtLineObj[i + 1].lineTotal());
				total = total + ishtLineObj[i + 1].getTotal();
				logger.info(
						("Line total for" + ishtLineObj[i + 1].getName() + " --- " + ishtLineObj[i + 1].lineTotal()));
			}
			ishtMDBObj.setTotal(total);
			ishtMDBObj.setLine(ishtLineMDBList);
			RootMDB portalUser = (RootMDB) getRequest().getSession().getAttribute(OnlineSAConstants.PORTAL_USER);
			ishtMDBObj.setName(portalUser.getFirstName() + " " + portalUser.getLastName());
			ishtMDBObj.setFamilyID(portalUser.getFamilyID());

			String MonthYear ;
			DateUtility dtl = new DateUtility();
			if(ishtMDBObj.getChecqDate()!=null) {

				logger.info("Extract :" + (ishtMDBObj.getChecqDate().toString()));
				MonthYear= dtl.getMonthYear((ishtMDBObj.getChecqDate().toString()));
			}else {
				MonthYear= dtl.getMonthYear(dtl.getCurrentDate1().toString());
			}


			logger.info("MonthYear : " + MonthYear);
			ishtMDBObj.setMonthYear(MonthYear);
			ishtMDBObj.setCollectedBy("SHYAM GIRI");
			//ishtMDBObj.setSubmittedOn(dtl.getCurrentDate());
			ishtMDBObj.setSubmittedOn(dtl.getCurrentDateInDate());
			ishtMDBObj.setReceiptDate(dtl.getCurrentDate4().toString());
			ishtMDBObj.setApprovedBy("SYSTEM");
			ishtMDBObj.setApprovedOn(dtl.getCurrentDate4().toString());


		
			logger.info("Payment method of AUTO  "+ishtMDBObj.getPaymentMethod()+"HHHH");
			logger.info("Receipt No : "+ishtMDBObj.getReceiptNo());

			ResultObject result = new ResultObject();
			result = getIshtService().saveIshtObjectJSON(ishtMDBObj);
			getResponse().setContentType("text/json;charset=utf-8");
			responseObject = new JSONObject();

			if (result.isSuccess()) {
				logger.info("inside sucess");
				responseObject.put("returnCode", "success");
				responseObject.put(OnlineSAConstants.RETURN_MESSAGE,
						"Ishtavrity Transaction processed successful, please click on Admin Menu for next transaction.");
				if (applicationFlow.equalsIgnoreCase(ADMIN_FLOW)) {
					responseObject.put(APPLICATION_FLOW, ADMIN_FLOW);
				} else {
					logger.info("what is the flow " + applicationFlow);
					responseObject.put(APPLICATION_FLOW, PORTAL_USER);
				}
				IshtMDB isht = (IshtMDB) result.getObject1();
				JSONObject userJSONObject = new JSONObject(isht);
				responseObject.put(USER_JSON_OBJECT, userJSONObject);
				logger.info("responseObject:" + responseObject);
				getRequest().getSession().removeAttribute("ishtPay");
				getRequest().getSession().setAttribute("ishtPay", isht);

				// Save the same data to MySQL Database as well

				
				try {

					logger.info("Going to insert into My SQL");

					WriteToMySQL writeToSQL = new WriteToMySQL();

					new SQLUtility().executeSQL(isht,getJdbcTemplate());
					String sql = "";
					Connection con = writeToSQL.executeSelectSQL(sql);
					logger.info("Successfully data inserted to My SQL");
				} catch (Exception ex) {

					logger.info("Execption while inserting data to MySQL " + ex);
				}
			
				// Save the same data to MySQL Database as well

				// send email notification to Admin and User about the transaction.
				ResultObject resultObj = getUserService().getUserObjectJSON(isht.getPhoneNo());
			    
				if (resultObj.isSuccess()) {
					RootMDB root = (RootMDB) resultObj.getObject1();
					// sendUserNotification(root, isht);
					// sendApproverNotification(root, isht);
					// Start // calling the Thread to execute the Task of Creating Receipt and
					// sending to the users.
					logger.info("Calling Thread Creating the Receipt  and Send Email");
					Thread t1 = new MailThread(isht, root);
					logger.info("Starting MailThread");
					t1.start();
					logger.info("MailThread has been started");
					// End // calling the Thread to execute the Task of Creating Receipt and sending
					// to the users.
				}
			} else {
				logger.info("inside failure");
				responseObject.put(RETURN_CODE, ERROR_FLAG);
			}

			// creating the PDF Receipt ---
			
			IshtMDB isht1 = (IshtMDB) result.getObject1();
			String receiptNo = isht1.getReceiptNo();
			logger.info("Inside approveIshtTran() Action - receiptNo :" + receiptNo);

			ResultObject resultObj = getUserService().getUserObjectJSON(isht1.getPhoneNo());
			//ResultObject resultObj = getUserService().getUserObjectJSON(isht.getPhoneNo());
			if (resultObj.isSuccess()) {
				RootMDB root = (RootMDB) resultObj.getObject1();
				// CreateNSendArghyaPraswasti createAP = new CreateNSendArghyaPraswasti();
				// Start // calling the Thread to execute the Task of Creating Receipt and
				// sending to the users.
				logger.info("Calling Thread Creating the Receipt  and Send Email");
				Thread t3 = new TaskThread(receiptNo, isht1, root);
				logger.info("Starting TaskThread");
				t3.start();
				logger.info("TaskThread has been started");
				// End // calling the Thread to execute the Task of Creating Receipt and sending
				// to the users.
				// working fine
				// createAP.sendEmailPdfAttachment(receiptNo, ishtMdb, root);
				// createAP.sendArghyaPraswastiPDFAttachmentInEmail(receiptNo, ishtMdb, root);
				// throw new Exception ("DataObject retrival failed to create
				// ArghyaPraswasti.");
			} else {
				responseObject.put(RETURN_CODE, ERROR_FLAG);
			}
			// creating the PDF Receipt ---

			responseObject.write(getResponse().getWriter());

		} catch (Exception e) {
			e.printStackTrace();
			responseObject.put(RETURN_CODE, ERROR_FLAG);
			logger.error("Exception occure:"+ e.getMessage());
		}
	}

	private boolean sendNotification(RootMDB root, IshtMDB isht) {
		logger.info("Email to sent" + root.getEmail());
		SendEmail sendEmail = new SendEmail();
		StringBuffer content_html = new StringBuffer();
		content_html.append(
				"Thank you for your recent payment. Your payment has been successfully applied to your account. <br> <br>")
				.append("Date of Payment :" + getIshtService().formatDate(isht.getCollectedOn())).append("<br> <br>")
				.append("Ishtavrity Receipt :" + isht.getReceiptNo()).append("<br> <br>")
				.append("Ishtavrity Amount :$" + isht.getTotal()).append("<br> <br>").append("Jayguru and Regards <br>")
				.append("Ever servant of the Lord <br>").append("Ghana Shyam Giri (SPR) <br>")
				.append("On Behalf of Satsang America <br>");
		String GuestName = root.getFirstName() + " " + root.getLastName(), GuestEmailId = root.getEmail();
		sendEmail.setRecipient(root.getEmail());
		sendEmail.setSender("satsangbayareausa@gmail.com");
		sendEmail.setSubject("Receipt of Ishtavrity Payment");
		String MailbodyContent = "<p style=\"font-family: Verdana, sans-serif;  color:blue; font-weight: bold; font-size: 12px;\">"
				+ GuestName + ",<br><br>" + content_html.toString();
		sendEmail.setContent(MailbodyContent); // setting the boy contennt.
		try {
			sendEmail.emailHtml();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // sendEmailInviation(); //Caling program to send the Email
		logger.error("Email Sent to : " + GuestEmailId + " Guest Name :   " + GuestName);

		return true;
	}

	private boolean sendUserNotification(RootMDB root, IshtMDB isht) {

		logger.info("Email to sent :" + root.getEmail());
		SendEmail sendEmail = new SendEmail();

		StringBuffer content_html = new StringBuffer();
		content_html.append(
				"Hope this will find you in good health and spirited mind by the divine grace of our Love Lord Sree Sree Thakur !!.<br> <br>")

				.append("Thank you for your recent payment. Your payment has been successfully submitted to the istabhrity account. <br> <br>")
				.append("Date of Payment :" + getIshtService().formatDate(isht.getCollectedOn())).append("<br> <br>")
				.append("Ishtavrity Receipt :" + isht.getReceiptNo()).append("<br> <br>")
				.append("Ishtavrity Amount :$" + isht.getTotal())

				.append("<br> <br>").append("Jayguru and Regards <br>").append("Istavrity Team <br>")
				.append("</p> <p style=\"font-family:sans-serif;  color: grey; font-weight:normal; font-size: 12px;\">")
				.append("Satsang America, Inc.<br>").append("111-17 Sutphin Blvd<br>").append("Jamaica, NY 11435<br>")
				.append("EIN:46-5341000<br>").append("Ph: 317-480-3184<br>")
				.append("Visit Us www.SatsangAmerica.org <br>").append("Email - "+OnlineSAConstants.EMAIL_ID+"<br>");

		/*
		 * Satsang America, Inc. 111-17 Sutphin Blvd Jamaica, NY 11435 EIN:46-5341000
		 * Phone: 317-480-3184 Visit Us www.SatsangAmerica.org Email -
		 * istabhrity@gmail.com
		 */

		SendEmail SendingProgram = new SendEmail();
		SendingProgram.setRecipient(root.getEmail());
		//SendingProgram.setSender("istabhrity@gmail.com");
		SendingProgram.setSender(OnlineSAConstants.EMAIL_ID);
		SendingProgram.setSubject("Submission of Ishtabhrity Notification");
		String MailbodyContent = "<p style=\"font-family:sans-serif;  color:blue; font-weight: bold; font-size: 14px;\"> Ishtapraneshu Admin Jayguru, <br><br>"
				+ content_html.toString();
		SendingProgram.setContent(MailbodyContent); // setting the boy contennt.

		try {
			SendingProgram.sendHtmlEmail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // sendEmailInviation(); //Caling program to send the Email
		logger.info("Approval Email Sent to ");

		return true;
	}

	private boolean sendApproverNotification(RootMDB root, IshtMDB isht) {

		// logger.info("Email to sent" +OnlineSAConstants.APPROVER_1);

		StringBuffer content_html = new StringBuffer();
		content_html.append(
				"Hope this will find you in good health and spirited mind by the divine grace of our Love Lord Sree Sree Thakur !!.<br> <br>")
				.append("There are few istabhrity transactions have been submitted and waiting for Admin approval. Please approve the submmitted transactions to get Arghya Praswasti created and sent to the user submitted Arghya. <br> <br>")
				.append("Please click").append("<a href='http://www.satsangamerica.com/ishtTrnDet.jsp'> Here </a>.")
				.append("Jayguru and Regards <br>").append("Istavrity Team <br>")
				.append("</p> <p style=\"font-family:sans-serif;  color: grey; font-weight:normal; font-size: 12px;\">")
				.append("Satsang America, Inc.<br>").append("111-17 Sutphin Blvd<br>").append("Jamaica, NY 11435<br>")
				.append("EIN:46-5341000<br>").append("Ph: 317-480-3184<br>")
				.append("Visit Us www.SatsangAmerica.org <br>").append("Email - "+OnlineSAConstants.EMAIL_ID+"<br>");

		/*
		 * Satsang America, Inc. 111-17 Sutphin Blvd Jamaica, NY 11435 EIN:46-5341000
		 * Phone: 317-480-3184 Visit Us www.SatsangAmerica.org Email -
		 * istabhrity@gmail.com
		 */

		SendEmail SendingProgram = new SendEmail();
		SendingProgram.setRecipient(OnlineSAConstants.APPROVER_1);
		// SendingProgram.setRecipient(OnlineSAConstants.APPROVER_1
		// +","+OnlineSAConstants.APPROVER_2+","+OnlineSAConstants.APPROVER_3);
		SendingProgram.setSender(OnlineSAConstants.EMAIL_ID);
		SendingProgram.setSubject("Istabhrity Transactons Approval Required.");
		String MailbodyContent = "<p style=\"font-family:sans-serif;  color:blue; font-weight: bold; font-size: 14px;\"> Ishtapraneshu Admin Jayguru, <br><br>"
				+ content_html.toString();
		SendingProgram.setContent(MailbodyContent); // setting the boy contennt.

		try {
			SendingProgram.sendHtmlEmail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // sendEmailInviation(); //Caling program to send the Email
		logger.info("Approval Email Sent to ");

		return true;
	}

	/***
	 * function to approve tran by admin
	 */
	public void approveIshtTran() {

		String receiptNo = getRequest().getParameter(("receiptNo"));
		logger.info("Inside approveIshtTran() Action - receiptNo :" + receiptNo);
		String phoneNo = getRequest().getParameter(("phoneNo"));
		String collectedOn = getRequest().getParameter(("collectedOn"));

		logger.info("Inside approveIshtTran() Action collectedOn :" + collectedOn);

		try {

			// Added by shyam on June 5th
			// String phoneNo = getRequest().getParameter(("phoneNo"));
			logger.info("phoneNo : " + phoneNo);
			ResultObject resObj = new ResultObject();
			ResultObject resObj1 = new ResultObject();
			resObj = getIshtService().getUserIshtObjectJSON(phoneNo);
			logger.info("getIshtJSONObject Action sucess flag " + resObj.isSuccess());
			RootMDB rootMDB = new RootMDB();
			if (resObj.isSuccess()) {
				rootMDB = (RootMDB) resObj.getObject2();
			}
			// resObj1 = getIshtService().getRootMDBObject(resObj);

			// Added by Shyam on June 5th
			ResultObject result = new ResultObject();
			result.setObject1(receiptNo);
			result.setObject2(rootMDB);
			result.setObject3(collectedOn);
			result = getIshtService().approveIshtTran(result);

			getResponse().setContentType("text/json;charset=utf-8");
			JSONObject responseObject = new JSONObject();
			if (result.isSuccess()) {
				logger.info("inside sucess");
				responseObject.put(RETURN_CODE, SUCCESS_FLAG);
				responseObject.put(RETURN_MESSAGE, "Transaction approved for " + receiptNo);
				logger.info("Response Object :" + responseObject);
				IshtMDB ishtMdb = (IshtMDB) result.getObject1();
				JSONObject userJSONObject = new JSONObject(ishtMdb);
				logger.info("userJSONObject :" + userJSONObject);
				responseObject.put(USER_JSON_OBJECT, userJSONObject);
				logger.info("Response Object 1 : " + responseObject);

				// creating the PDF Receipt ---
				ResultObject resultObj = getUserService().getUserObjectJSON(ishtMdb.getPhoneNo());
				if (resultObj.isSuccess()) {
					RootMDB root = (RootMDB) resultObj.getObject1();
					// CreateNSendArghyaPraswasti createAP = new CreateNSendArghyaPraswasti();
					// Start // calling the Thread to execute the Task of Creating Receipt and
					// sending to the users.
					logger.info("Calling Thread Creating the Receipt  and Send Email");
					Thread t3 = new TaskThread(receiptNo, ishtMdb, root);
					logger.info("Starting TaskThread");
					t3.start();
					logger.info("TaskThread has been started");
					// End // calling the Thread to execute the Task of Creating Receipt and sending
					// to the users.
					// working fine
					// createAP.sendEmailPdfAttachment(receiptNo, ishtMdb, root);
					// createAP.sendArghyaPraswastiPDFAttachmentInEmail(receiptNo, ishtMdb, root);
					// throw new Exception ("DataObject retrival failed to create
					// ArghyaPraswasti.");
				} else {
					responseObject.put(RETURN_CODE, ERROR_FLAG);
				}
				// creating the PDF Receipt ---
			} else {
				logger.info("inside failure");
				responseObject.put(RETURN_CODE, ERROR_FLAG);
			}

			responseObject.write(getResponse().getWriter());
		} catch (Exception e) {
			logger.error("Exception in approveIshtTran()  :" + e);

		}
	}

	/***
	 * function to reject tran by admin
	 */
	public void rejectIshtTran() {

		String receiptNo = getRequest().getParameter(("receiptNo"));
		logger.info("Inside rejectIshtTran() Action");
		try {
			ResultObject result = new ResultObject();
			result = getIshtService().getIshtTran(receiptNo);
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

			logger.error("Exception : " + e.getMessage());

		}
	}



	public void report() throws IOException {
		PrintWriter writer = getResponse().getWriter();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		ReportDTO reportDTO = mapper.readValue(getRequest().getReader().readLine(), ReportDTO.class);
		List<IshtMDB> lists = ishtService.findReport(reportDTO);
		try {
		writer.append(mapper.writeValueAsString(lists));
		}catch(IOException io) {
			io.printStackTrace();
			throw io;
		}
	}
	
	public void  downloadReceipt() throws IOException {
		//PrintWriter writer = getResponse().getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		TransReportDTO  reportDTO= new TransReportDTO();
		RootMDB rootMdb = (RootMDB) getRequest().getSession().getAttribute("userBean");
		String phoneNo = rootMdb.getPhoneNo();
		try {
			//reportDTO = mapper.readValue(getRequest().getReader().readLine(), TransReportDTO.class);
			String receiptNo = getRequest().getParameter(("receiptNo"));
			reportDTO.setPhoneNo(phoneNo);
			reportDTO.setReceiptNo(receiptNo);
			ResultObject result = ishtService.downLoadReceipt(reportDTO,rootMdb);
			
			//writer.println(new String((byte[]) result.getObject1(), Charset.defaultCharset()));
			//writer.flush();
			getResponse().setContentType("application/pdf");
			OutputStream out = getResponse().getOutputStream();
			out.write((byte[])result.getObject1());
			out.flush();
		
		
			}catch(IOException io) {
				io.printStackTrace();
				throw io;
			}catch(Exception e) {
				e.printStackTrace();
			}
		
	}
	
}
