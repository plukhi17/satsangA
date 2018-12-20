package com.olsa.services;

import com.olsa.pojo.ResultObject;
import com.olsa.pojo.RootMDB;
import com.olsa.pojo.User;
import com.olsa.utility.ForgotPasswordResponse;
import com.olsa.utility.OTPResponse;

public interface UserService {
	
	User getUserData(String userId);
	ResultObject saveUserData(User user); 
	ResultObject removeUserData(String userId);
	boolean validateUser(String userId, String password) throws Exception;
	String addUser();
	ResultObject addPrimaryUserDetails(String primaryUserDetails);
	ResultObject addFamilyUserDetails(String familyUserDetails, RootMDB userSession);
	ResultObject validateLogin(String loginDetails);
	ResultObject getUserObjectJSON(String loginDetails);
	ResultObject grantAdminAccess(String loginDetails);
	ResultObject removeAdminAccess(String loginDetails);
	ResultObject getSearchUserObject(String inputValue, String inputParam);
	ForgotPasswordResponse verifyEmailId(String email);
	OTPResponse verifyOTP(String email, String otpNum,String currentDate);
	ForgotPasswordResponse reSendOTP(String email, String date);
	ForgotPasswordResponse updateNewPassword(String mobileNumber, String newPassword);
}
