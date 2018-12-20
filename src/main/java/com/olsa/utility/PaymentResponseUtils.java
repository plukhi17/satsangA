package com.olsa.utility;

import java.util.List;

public class PaymentResponseUtils {

	private String resMessage;
	private String trasactionId;
	private Boolean status;
	private List<ErrorValidation> errorValidations;



	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getResMessage() {
		return resMessage;
	}

	public void setResMessage(String resMessage) {
		this.resMessage = resMessage;
	}

	public String getTrasactionId() {
		return trasactionId;
	}

	public void setTrasactionId(String trasactionId) {
		this.trasactionId = trasactionId;
	}

	public List<ErrorValidation> getErrorValidations() {
		return errorValidations;
	}

	public void setErrorValidations(List<ErrorValidation> errorValidations) {
		this.errorValidations = errorValidations;
	}

	@Override
	public String toString() {
		return "PaymentResponseUtils [resMessage=" + resMessage + ", trasactionId=" + trasactionId + ", status="
				+ status + ", errorValidations=" + errorValidations + "]";
	}

}
