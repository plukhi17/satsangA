package com.olsa.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.olsa.utility.OnlineSAConstants;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,OnlineSAConstants{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9056764223478946465L;
	static final Logger logger = Logger.getLogger(BaseAction.class);
	private HttpServletRequest request;
	private HttpServletResponse response;
	protected String tabCode="";
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}

	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}
	
	public void insideBaseAction(){
		logger.info("Load on Start Up----------");	
	}
	


}
