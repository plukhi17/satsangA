package com.olsa.pojo;
/*
 * @author :dbhishik
 */
public class AddressMDB
{
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
   
	private long zipCode;
    private String state;
    private String city;
    private String country;

    public void setAddressLine1(String addressLine1){
        this.addressLine1 = addressLine1;
    }
    public String getAddressLine1(){
        return this.addressLine1;
    }
    public void setAddressLine2(String addressLine2){
        this.addressLine2 = addressLine2;
    }
    public String getAddressLine2(){
        return this.addressLine2;
    }
    public void setZipCode(long zipCode){
        this.zipCode = zipCode;
    }
    public long getZipCode(){
        return this.zipCode;
    }
    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return this.city;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public String getCountry(){
        return this.country;
    }
    public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
}

