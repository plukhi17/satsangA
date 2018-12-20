package com.olsa.utility;

import java.io.Serializable;

public class CardDetailsDTO implements Serializable {
	private static final long serialVersionUID = -3745684431500938685L;
	private String cardNumber;
	private String expirationDate;
	private String cvv;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	@Override
	public String toString() {
		return "CardDetailsDTO [cardNumber=" + cardNumber + ", expirationDate=" + expirationDate + ", cvv=" + cvv + "]";
	}

}
