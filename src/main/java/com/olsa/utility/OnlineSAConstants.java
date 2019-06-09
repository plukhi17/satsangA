package com.olsa.utility;
import java.util.HashMap;

import org.json.JSONObject;

public interface OnlineSAConstants {
	String RETURN_CODE = "returnCode";
	String USER_JSON = "userJSON";
	String USER_JSON_OBJECT = "userJSONObject";
	String DPST_SMRY_OBJECT = "depositSmryJSONObject";
	String ISHT_REF_OBJECT = "ishtRefValObject";
	String DPST_SMRY_BAL = "depositSmryBal";
	String INC_BAL_WRAPPER = "incomeBalWrapper";
	String EXP_BAL_WRAPPER = "expenseBalWrapper";
	String RITVIK_JSON_OBJECT = "ritvikJSONObject";
	String USER_NAME = "userName";
	String ISHT_TRAN_OBJECT = "ishtTranObject";
	String RETURN_ERROR_MSG_CODE = "errMsgCode";
	String RETURN_MESSAGE = "returnMessage";
	String ERROR_FLAG = "error";
	String SUCCESS_FLAG = "success";
	String ROOT_COLLECTION= "Root";
	String ISHT_COLLECTION= "Isht";
	String ISHT_REF_VAL= "IshtRefVal";
	String RITVIK_COLLECTION= "Ritvik";
	String DATE_TIME_FORMAT_MONGO = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	String DATE_FORMAT_MONGO = "MM/dd/yyyy";
	String DATE_FORMAT_MONGO1 = "MM-dd-yyyy";
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
	String INC_CODE_SEQ = "INCCODE";
	String INC_SUB_CODE_SEQ = "INCSUBCODE";
	String INCOME_CODE_SEQ_NAME = "IncomeCode";
	String INCOME_SUB_CODE_SEQ_NAME = "IncomeSubCode";
	
	String EXPN_CODE_SEQ = "EXPNCODE";
	String EXPN_SUB_CODE_SEQ = "EXPNSUBCODE";
	String EXPN_CODE_SEQ_NAME = "ExpenseCode";
	String EXPN_SUB_CODE_SEQ_NAME = "ExpenseSubCode";
	
	//Email creds
	final String EMAIL_ID="satsangamericatest@gmail.com";
    final String CREDENTIALS="SatsangAmerica123$";	
    final String SENDER_EMAIL_ID2="Satsang America Test <satsangamericatest@gmail.com>";
    final String SENDER_EMAIL_ID1="Satsang America Test <satsangamericatest@gmail.com>";
    final String IMAGE_URL="https://www.satsangamerica.com/images/";
    
    final String SQL_DB_LOCAL = "jdbc:mysql://127.0.0.1:3306/istavrity";
	final String SQL_DB_LOCAL_USER = "root";
	final String SQL_DB_LOCAL_PASS = "!stavrity$";
	
	final String ISHT_PROP="ishtProp";

	
	public static final HashMap<String, JSONObject> ritvikHashMapCache = new HashMap<String, JSONObject>();
}
