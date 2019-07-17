package com.olsa.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.mongodb.client.FindIterable;
import com.olsa.bo.MongoBaseDao;
import com.olsa.bo.UserDao;
import com.olsa.bo.UserProfileMDBDao;
import com.olsa.pojo.IshtMDB;
import com.olsa.pojo.OTPResponseBO;
import com.olsa.pojo.ResultObject;
import com.olsa.pojo.RootMDB;
import com.olsa.pojo.User;
import com.olsa.utility.ForgotPasswordResponse;
import com.olsa.utility.MongoConstants;
import com.olsa.utility.OTPResponse;
import com.olsa.utility.OnlineSAConstants;
import com.olsa.utility.RandomNumber;
import com.olsa.utility.SendEmail;

public class UserServiceImpl implements UserService {

	static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	private User user;
	private UserDao userDao;
	private MongoBaseDao mongoBaseDao;
	private UserProfileMDBDao userProfileMDBDao;

	public UserProfileMDBDao getUserProfileMDBDao() {
		return userProfileMDBDao;
	}

	public void setUserProfileMDBDao(UserProfileMDBDao userProfileMDBDao) {
		this.userProfileMDBDao = userProfileMDBDao;
	}

	public MongoBaseDao getmongoBaseDao() {
		return mongoBaseDao;
	}

	public void setMongoBaseDao(MongoBaseDao mongoBaseDao) {
		this.mongoBaseDao = mongoBaseDao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User getUserData(String userId) {
		return null;
	}

	public ResultObject saveUserData(User user) {
		return null;
	}

	public ResultObject removeUserData(String userId) {
		return null;
	}

	public boolean validateUser(String userId, String password) throws Exception {
		boolean object = getUserDao().validateUser(userId, password);
		return object;
	}

	public String addUser() {
		BigDecimal d = getmongoBaseDao().getRootSequenceIdForSeqName(MongoConstants.ROOT_SEQ);
		logger.info("seq id" + d);
		return null;
	}

	/***
	 * this method will add primary user details into Mongo
	 */
	public ResultObject addPrimaryUserDetails(String primaryUserDetails) {
		logger.info("inide addPrimaryUserDetails");
		ResultObject resultObject = getUserProfileMDBDao().addPrimaryUserDetails(primaryUserDetails);
		return resultObject;
	}

	public ResultObject validateLogin(String loginDetails) {
		logger.info("inide validateLogin");
		String primaryUserDetailsJSON = loginDetails;
		ResultObject resultObject = null;
		JSONObject obj = new JSONObject(loginDetails);
		if (obj.getString("userName").equalsIgnoreCase("admin")
				&& obj.getString("password").equalsIgnoreCase("admin")) {
			resultObject = new ResultObject();
			RootMDB root = new RootMDB();
			root.setFirstName("Admin");
			root.setLastName("User");
			root.setUserType(OnlineSAConstants.ADMIN_USER);
			resultObject.setObject1(root);
		} else {
			resultObject = getUserProfileMDBDao().validateLogin(obj.getString("userName"), obj.getString("password"));
		}
		return resultObject;
	}
	
	public ResultObject validateUserEmail(String loginDetails) {
		logger.info("inide validateLogin");
		String primaryUserDetailsJSON = loginDetails;
		ResultObject resultObject = null;
		JSONObject obj = new JSONObject(loginDetails);
		if (obj.getString("userName").equalsIgnoreCase("admin")
				&& obj.getString("password").equalsIgnoreCase("admin")) {
			resultObject = new ResultObject();
			RootMDB root = new RootMDB();
			root.setFirstName("Admin");
			root.setLastName("User");
			root.setUserType(OnlineSAConstants.ADMIN_USER);
			resultObject.setObject1(root);
		} else {
			resultObject = getUserProfileMDBDao().validateForgetUser(obj.getString("userName"));
		}
		return resultObject;
	}
	
	

	public ResultObject addFamilyUserDetails(String familyUserDetails, RootMDB userSession) {
		logger.info("inside addFamilyUserDetails");
		ResultObject resultObject = getUserProfileMDBDao().addFamilyUserDetails(familyUserDetails, userSession);
		return resultObject;
	}
	public ResultObject modifyFamilyUserDetails(String familyUserDetails, RootMDB userSession) {
		logger.info("inside modifyFamilyUserDetails");
		ResultObject resultObject = getUserProfileMDBDao().modifyFamilyUserDetails(familyUserDetails, userSession);
		return resultObject;
	}
	public ResultObject getUserObjectJSON(String phoneNo) {
		ResultObject resultObject = new ResultObject();
		RootMDB userJSONObject = getUserProfileMDBDao().fetchRootDocument(phoneNo);
		logger.info("getUserObjectJSON");
		if (userJSONObject != null) {
			logger.info("pass");
			resultObject.setSuccess(true);
			resultObject.setObject1(userJSONObject);
		} else {
			logger.info("fail");
			resultObject.setSuccess(false);
		}
		return resultObject;
	}

	
	@Override
	public String checkEmailExists(String email) {
		return String.valueOf(getUserProfileMDBDao().ifExistEmail(email));
	}
	
	public ResultObject getSearchUserObject(String inputValue, String inputParam) {
		ResultObject resultObject = new ResultObject();
		// RootMDB userJSONObject =
		// getUserProfileMDBDao().fetchRootListDocument(inputValue,inputParam);
		FindIterable<RootMDB> iterableJSONObject = getUserProfileMDBDao().fetchRootListDocument(inputValue, inputParam);
		logger.info("getUserObjectJSON");
		if (iterableJSONObject != null) {
			logger.info("pass");
			resultObject.setSuccess(true);
			resultObject.setObject1(iterableJSONObject);
		} else {
			logger.info("fail");
			resultObject.setSuccess(false);
		}
		return resultObject;
	}

	public ResultObject grantAdminAccess(String loginDetails) {
		ResultObject resultObject = new ResultObject();
		resultObject.setObject1(loginDetails);
		resultObject = getUserProfileMDBDao().grantAdminAccess(resultObject);
		return resultObject;
	}

	public ResultObject removeAdminAccess(String loginDetails) {
		ResultObject resultObject = new ResultObject();
		return resultObject;
	}

	@Override
	public ForgotPasswordResponse verifyEmailId(String email) {

		ForgotPasswordResponse response = new ForgotPasswordResponse();
		Integer OTPnum = RandomNumber.getNumber();
		String date = String.valueOf(new Date().getTime());
		boolean flag = userProfileMDBDao.ifExistEmailId(email);
		if (flag == true) {
			userProfileMDBDao.saveOTP(OTPnum, email, date, false);
			response.setResponseMsg("success");
			response.setStatus("true");
			response.setDate(date);
			response.setEmail(email);
			logger.info("forgot password: " + response + "   OTP:" + OTPnum);
			boolean retvalue = sendOTPToEmailId(response,OTPnum); //calling to send Email and SMS
			return response;
		} else {
			response.setResponseMsg("Not match this email id");
			response.setStatus("false");
			response.setEmail(email);
			
			return response;
		}
		// verifyOTP(email, OTPnum.toString(), date);
	}

	@Override
	public OTPResponse verifyOTP(String email, String otpNum, String currentDate) {
		OTPResponse otpResponse = new OTPResponse();
		OTPResponseBO bo = userProfileMDBDao.findUserOTPValue(email, currentDate);
		if (bo == null) {
			otpResponse.setOtp(otpNum);
			otpResponse.setResponseMsg("Not valied email id please try latter");
			otpResponse.setStatus("false");
			return otpResponse;
		} else {
			long tenMinute = 10 * 60000;
			long tempMili = new Date().getTime() - Long.parseLong(bo.getDate());
			if (tenMinute > tempMili) {
				if (otpNum.equals(bo.getOtp())) {
					if (bo.getStatus().equalsIgnoreCase("false")) {
						otpResponse.setResponseMsg("Success");
						otpResponse.setStatus("true");

						// userProfileMDBDao.updateOTPStatusTrue(currentDate);
						// update status
						return otpResponse;
					} else {
						otpResponse.setResponseMsg("Not valied OTP, Please enter valied OTP");
						otpResponse.setStatus("false");
						logger.error("Session expire try different OTP: " + otpNum);
						return otpResponse;
					}
				} else {
					otpResponse.setResponseMsg("Not valied OTP, Please try valied OTP");
					otpResponse.setStatus("false");
					logger.error("Not valied OTP number user OTP number =" + otpNum + " DB OTP number=" + bo.getOtp());
					return otpResponse;
				}
			} else {
				otpResponse.setResponseMsg("Session expire please try latter");
				otpResponse.setStatus("false");
				return otpResponse;
			}
		}
	}

	@Override
	public ForgotPasswordResponse reSendOTP(String email, String date) {
		ForgotPasswordResponse response = new ForgotPasswordResponse();
		boolean flag = userProfileMDBDao.ifExistEmailId(email);
		if (flag == true) {
			// userProfileMDBDao.saveOTP(OTPnum, email, date, false);
			OTPResponseBO bo = userProfileMDBDao.findUserOTPValue(email, date);
			long tenMinute = 10 * 60000;
			long tempMili = new Date().getTime() - Long.parseLong(bo.getDate());
			if (tenMinute > tempMili) {
				// here resend mail integration
				response.setResponseMsg("Successfully OTP resend your email id: " + email);
				response.setStatus("true");
				response.setDate(date);
				response.setEmail(email);
				return response;
			} else {
				return verifyEmailId(email);
			}
		} else {
			response.setResponseMsg("Not match this email id");
			response.setStatus("false");
			response.setEmail(email);
			return response;
		}
	}
	@Override
	public ForgotPasswordResponse updateNewPassword(String familId, String newPassword) {
		ForgotPasswordResponse response=new ForgotPasswordResponse();
		//boolean flag = userProfileMDBDao.ifExistMobileNumber(mobileNumber);
	
			userProfileMDBDao.updatePassword(familId,newPassword);
			response.setResponseMsg("Successfully updated password");
			response.setStatus("true");
			logger.info("Successfully updated new password");
			return response;
		
	}
	
	
	
	
	 private boolean sendOTPToEmailId(ForgotPasswordResponse resObj, Integer OtPnum){
			
		    logger.info("Email to sent :" +resObj.getEmail());
			SendEmail sendEmail = new SendEmail();
			
			StringBuffer  content_html= new StringBuffer();
			content_html.append("Your Satsang America account password verification code is :"+OtPnum+".  This code is valid for 10 Minutes Please do not share this code with anyone.")
			
			.append("<br> <br>")
			.append("Jayguru and Regards <br>")
			.append("Istavrity Team <br>")
			.append("</p> <p style=\"font-family:sans-serif;  color: grey; font-weight:normal; font-size: 12px;\">")
			.append("Satsang America, Inc.<br>") 
			.append("14 W District Rd<br>") 
			.append("Unionville, CT 06085<br>") 
			.append("Ph: 317-480-3184<br>") 
			.append("Visit Us www.SatsangAmerica.org <br>") ;
			
			SendEmail SendingProgram = new SendEmail();						
			SendingProgram.setRecipient(resObj.getEmail());
			//SendingProgram.setSender("istabhrity@gmail.com");
			SendingProgram.setSender(OnlineSAConstants.EMAIL_ID);
			SendingProgram.setSubject("Password Verification Code");
			String MailbodyContent="<p style=\"font-family:sans-serif;  color:blue; font-weight: bold; font-size: 14px;\"> Ishtapraneshu  Jayguru, <br><br>"+content_html.toString();
			SendingProgram.setContent(MailbodyContent); //setting the boy contennt.
			try {
				SendingProgram.sendHtmlEmail();
			} catch (Exception e) {
				logger.error("Exception occure while sending User Notification"+e.getMessage());
			}//sendEmailInviation(); //Caling program to send the Email 
			logger.info("Approval Email Sent to ");
			
			return true;
		}
		
	

}
