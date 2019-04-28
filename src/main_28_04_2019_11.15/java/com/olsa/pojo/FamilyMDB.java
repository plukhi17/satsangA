package com.olsa.pojo;

public class FamilyMDB {
	
	    private int _id;
	    private String personalID;
	    private String firstName;
	    private String middleName;
	    private String lastName;
	    private String fullName;
	    private String ritwikID;
	    private boolean ritwikStatus;
	    private String rName;
	    
	    
	    public String getRitwikID() {
			return ritwikID;
		}
		public void setRitwikID(String ritwikID) {
			this.ritwikID = ritwikID;
		}
		public boolean isRitwikStatus() {
			return ritwikStatus;
		}
		public void setRitwikStatus(boolean ritwikStatus) {
			this.ritwikStatus = ritwikStatus;
		}
		public String getrName() {
			return rName;
		}
		public void setrName(String rName) {
			this.rName = rName;
		}
		

	    public void set_id(int _id){
	        this._id = _id;
	    }
	    public int get_id(){
	        return this._id;
	    }
	    public void setPersonalID(String personalID){
	        this.personalID = personalID;
	    }
	    public String getPersonalID(){
	        return this.personalID;
	    }
	    public void setFirstName(String firstName){
	        this.firstName = firstName;
	    }
	    public String getFirstName(){
	        return this.firstName;
	    }
	    public void setMiddleName(String middleName){
	        this.middleName = middleName;
	    }
	    public String getMiddleName(){
	        return this.middleName;
	    }
	    public void setLastName(String lastName){
	        this.lastName = lastName;
	    }
	    public String getLastName(){
	        return this.lastName;
	    }
	    
	    public void setFullName(String fullName){
	        this.fullName = fullName;
	    }
	    public String getFullName(){
	        return this.firstName +" "+ this.getLastName();
	    }

}
