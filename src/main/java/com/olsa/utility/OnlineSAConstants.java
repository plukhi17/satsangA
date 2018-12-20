package com.olsa.utility;
import java.util.HashMap;

import org.json.JSONObject;

public interface OnlineSAConstants {
	String RETURN_CODE = "returnCode";
	String USER_JSON = "userJSON";
	String USER_JSON_OBJECT = "userJSONObject";
	String RITVIK_JSON_OBJECT = "ritvikJSONObject";
	String USER_NAME = "userName";
	String ISHT_TRAN_OBJECT = "ishtTranObject";
	String RETURN_ERROR_MSG_CODE = "errMsgCode";
	String RETURN_MESSAGE = "returnMessage";
	String ERROR_FLAG = "error";
	String SUCCESS_FLAG = "success";
	String ROOT_COLLECTION= "Root";
	String ISHT_COLLECTION= "Isht";
	String RITVIK_COLLECTION= "Ritvik";
	String DATE_TIME_FORMAT_MONGO = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	String DATE_FORMAT_MONGO = "MM/dd/yyyy";
	String APPLICATION_FLOW = "applicationFlow";
	String USER_TYPE= "userType";
	String ADMIN_USER = "adminUser";
	String PORTAL_USER = "portalUser";
	String USER_ROLE_SUPER_USER = "superUserRole";
	String USER_ROLE_ADMIN_USER = "adminUserRole";
	String YES="Y";
	String NO="N";
	String ADMIN_FLOW= "adminFlow";
	String USER_FLOW= "userFlow";
	String CONFIRMATION_MESSAGE= "Member added successfully. Please click on the Add Primary user link to add new Member";
	String APPROVER_1="gs.gsgiri@gmail.com";
	String APPROVER_2="satsangbayareausa@gmail.com";
	String APPROVER_3="gsgiri143@yahoo";

	public static final HashMap<String, JSONObject> ritvikHashMapCache = new HashMap<String, JSONObject>();
}
