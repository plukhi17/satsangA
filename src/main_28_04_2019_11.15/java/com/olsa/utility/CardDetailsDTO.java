package com.olsa.utility;

import java.io.Serializable;

public class CardDetailsDTO implements Serializable {
	private static final long serialVersionUID = -3745684431500938685L;
	private String cardNumber;
	private String expirationDate;
	

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


	@Override
	public String toString() {
		return "CardDetailsDTO [cardNumber=" + cardNumber + ", expirationDate=" + expirationDate + "]";
	}

}
