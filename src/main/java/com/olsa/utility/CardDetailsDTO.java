package com.olsa.utility;

import java.io.Serializable;

/**
 * @author parthl
 *
 */
public class CardDetailsDTO implements Serializable {
	private static final long serialVersionUID = -3745684431500938685L;
	private String cardNumber;
	private String expirationDate;
	private String cToken;
	private String cardType;
	

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


	/**
	 * @return the cToken
	 */
	public String getcToken() {
		return cToken;
	}

	/**
	 * @param cToken the cToken to set
	 */
	public void setcToken(String cToken) {
		this.cToken = cToken;
	}

	
	/**
	 * @return the cardType
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@Override
	public String toString() {
		return "CardDetailsDTO [cardNumber=" + cardNumber + ", expirationDate=" + expirationDate + "]";
	}
	
	

}
