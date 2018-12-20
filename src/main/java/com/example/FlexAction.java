package com.example;

import java.io.IOException;
import java.text.ParseException;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import com.olsa.action.BaseAction;
import com.olsa.pojo.ResultObject;
import com.olsa.services.UserService;


public class FlexAction extends BaseAction {
	private static final long serialVersionUID = -1321735510969119510L;
	static final Logger logger = Logger.getLogger(FlexAction.class);
	private UserService userService;
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
 
	
	public void getFlexSchedule() throws IOException{

		logger.info(getRequest().getParameter("invoiceAmount"));
		
		
		int invoiceAmount = Integer.parseInt(getRequest().getParameter("invoiceAmount"));
		int userOptions = Integer.parseInt(getRequest().getParameter("userOptions"));
		int serviceDurationMonths =Integer.parseInt(getRequest().getParameter("serviceDurationMonths"));
		
		logger.info(invoiceAmount+"---"+userOptions +"---"+ serviceDurationMonths);
		FlexInvoiceManager flex = new FlexInvoiceManager();
		try {
			ResultObject result = flex.generateFlexInvoiceSchedule(invoiceAmount, serviceDurationMonths, userOptions);
			if (result.getObject1()!=null){
				getResponse().setContentType("text/json;charset=utf-8");
								
				JSONObject responseObject = new JSONObject((FlexInvoice)result.getObject1());
				logger.info(responseObject);
				responseObject.put("returnCode", "success");
				
				try {
					responseObject.write(getResponse().getWriter());
					logger.info(responseObject.toString());
				} catch (JSONException e) {
					logger.error("Exception occure:"+e.getMessage() );
				} catch (IOException e) {
					logger.error("Exception occure:"+e.getMessage() );
				}
				
			}
		} catch (ParseException e) {
			logger.error("Exception occure:"+e.getMessage() );
		}
		
		/*
		if ((orderSearchParam!=null) && (orderSearchType!=null)){
			System.out.println("Inside the block");
			Order order = getOrderService().getOrderList(orderSearchType, orderSearchParam);
			
			
			getResponse().setContentType("text/json;charset=utf-8");
			// create JSON order list object
			JSONObject responseOrderObject = new JSONObject();
			responseOrderObject.put("webOrderId", order.getWebOrderID());
			responseOrderObject.put("orderNumber", order.getOrderNumber());
			responseOrderObject.put("orderStatus", order.getOrderStatus());
			responseOrderObject.put("purchaseOrderNumber", order.getPurchaseOrderNumber());
			responseOrderObject.put("orderSourceName", order.getOrderSource());
			responseOrderObject.put("orderCategoryCode", order.getOrderCategory());
			responseOrderObject.put("erpEnv", order.getOrderDestination());

			JSONObject responseObject = new JSONObject();
			responseObject.put("orderObject", responseOrderObject);
			responseObject.put("returnCode", "success");
			
			JSONObject responseObjectLine = new JSONObject();
			responseObjectLine.put("orderObject", responseOrderObject);
			
			responseObject.put("responseObjectLine", responseObjectLine);
			
			
			try {
				responseObject.write(getResponse().getWriter());
				System.out.println(responseObject.toString());
				
				HashMap<String,Object> results = null;
				results = (HashMap<String,Object>)orderService.getValidOrderShareeContacts
						("imdisti", 3929763, "", "", "", "mariar", "CCOID", "ORDER_SHARE", "");
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//return "success";
		return null;
*/			
	}
	
}
