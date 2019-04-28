package com.olsa.utility;

public class ForgotPasswordResponse {

	private String status;
	private String date;
	private String responseMsg;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	@Override
	public String toString() {
		return "ForgotPasswordResponse [status=" + status + ", date=" + date + ", responseMsg=" + responseMsg
				+ ", email=" + email + "]";
	}

}
