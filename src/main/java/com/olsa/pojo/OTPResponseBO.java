package com.olsa.pojo;

import java.util.Date;

public class OTPResponseBO {
	private String date;
	private String otp;
	private String status;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OTPResponseBO [date=" + date + ", otp=" + otp + ", status=" + status + "]";
	}

}
