package com.olsa.bo;

import static com.mongodb.client.model.Filters.eq;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import com.olsa.mongo.OsMongoClient;
import com.olsa.pojo.AddressMDB;
import com.olsa.pojo.FamilyMDB;
import com.olsa.pojo.IshtMDB;
import com.olsa.pojo.OTPResponseBO;
import com.olsa.pojo.ResultObject;
import com.olsa.pojo.RitvikMDB;
import com.olsa.pojo.RootMDB;
import com.olsa.utility.CardDetailsDTO;
import com.olsa.utility.MongoConstants;
import com.olsa.utility.OnlineSAConstants;

import sun.misc.BASE64Encoder;

public class UserProfileMDBDao extends MongoBaseDao {
	static final Logger logger = Logger.getLogger(UserProfileMDBDao.class);
	private final ThreadLocal<Random> random = new ThreadLocal<Random>();
	private MongoBaseDao mongoBaseDao;
	private OsMongoClient osMongoClient;
	 
    public MongoBaseDao getMongoBaseDao() {
        return this.mongoBaseDao;
    }

    public void setMongoBaseDao(MongoBaseDao mongoBaseDao) {
        this.mongoBaseDao = mongoBaseDao;
    }

    public OsMongoClient getOoMongoClient() {
        return this.osMongoClient;
    }

    public void setOoMongoClient(OsMongoClient osMongoClient) {
        this.osMongoClient = osMongoClient;
    }

    public MongoDatabase getMongoDataBase() {
        if (null != this.osMongoClient) {
            return this.osMongoClient.getMongoClient().getDatabase(this.osMongoClient.getMongoDbName());
        } else {
            return null;
        }
    }     
  

    public ResultObject addPrimaryUserDetails(String primaryUserDetails){
    	logger.info("inside primaryUserDetails : "+primaryUserDetails);
    	ResultObject resultObject = new ResultObject();
    	HashMap<String , String> errorMap = new HashMap<String, String>();
    	String primaryUserDetailsJSON = primaryUserDetails;
    	RootMDB root = new RootMDB();

    	try{
	    	JSONObject obj = new JSONObject(primaryUserDetailsJSON);
	    	root.set_id(getSeqID()); 
			root.setFirstName(obj.getString("firstName").toUpperCase());
			
			if(obj.has(("middleName")))
				root.setMiddleName(obj.getString("middleName").toUpperCase());
			
			if(obj.has("lastName"))
				root.setLastName(obj.getString("lastName").toUpperCase());
			
			if(obj.has("familyCode"))
				root.setIndfamilyCode(obj.getString("familyCode").toUpperCase());
			
			if(obj.has("emailID"))
				root.setEmail(obj.getString("emailID").toUpperCase());
			
			if(obj.has("pwd")){
		        String passwordRequest = obj.getString("pwd");
				String passwordHash = makePasswordHash(passwordRequest, Integer.toString(getRandom().nextInt()));
				root.setPassword(passwordHash);
			}
			
			if(obj.has("phoneNo"))
				root.setPhoneNo(obj.get(("phoneNo")).toString());
			
			if(obj.has("rName"))
				root.setrName(obj.get(("rName")).toString().toUpperCase());
			
			if(obj.has("rName")){
				StringTokenizer st = new StringTokenizer(obj.get(("rName")).toString(),"|");
				while (st.hasMoreElements()) {
					String rName= st.nextElement().toString();
					String saID = st.nextElement().toString();
					root.setrName(rName.toUpperCase());
					root.setRitwikID(saID);
				}
			}
			
			
			AddressMDB address = new AddressMDB();
			
			if(obj.has("add1"))
				address.setAddressLine1(obj.getString("add1").toUpperCase());
			
			if(obj.has("add2"))
				address.setAddressLine2(obj.getString("add2").toUpperCase());
			
			if(obj.has("add3"))
				address.setAddressLine3(obj.getString("add3").toUpperCase());
			
			if(obj.has("city"))
				address.setCity(obj.getString("city").toUpperCase());
			
			
			if(obj.has("country")){
				JSONObject state  = new JSONObject(obj.get("country").toString());
				Map<String,String> map = new HashMap<String,String>();
				Iterator<?> iter = state.keys();
				while(iter.hasNext()){
					String key = (String)iter.next();
					String value = state.getString(key);
					map.put(key,value);
				}
				//address.setCountry(obj.get("country").toString());
				address.setCountry(map.get("CountryName"));
			}

			if(obj.has("state")){
			    JSONObject state  = new JSONObject(obj.get("state").toString());
			    Map<String,String> map = new HashMap<String,String>();
			    Iterator<?> iter = state.keys();
			    while(iter.hasNext()){
			        String key = (String)iter.next();
			        String value = state.getString(key);
			        map.put(key,value);
			    }
				address.setState(map.get("StateCode"));
			}
	
			if(obj.has("zipCode"))
				address.setZipCode(obj.getInt("zipCode"));
			
			root.setAddress(address);
			logger.info("First Name : " + root.getFirstName());
			
			root.setFamilyID(getFamilySeqID());
			root.setPersonalId(root.getFamilyID()+"-"+"01");
			root.setPprFlag(OnlineSAConstants.YES); // By Default it will be Yes amnd e receipt will be generated.
			root.setUserType(OnlineSAConstants.PORTAL_USER); // by default all users will have portal access only
			root.setPseronalize(root.getFirstName()+" DADA");   // by default all users will have portal access only
			
		
			RootMDB getData = fetchRootDocument(obj.get(("phoneNo")).toString());
			/* 
			 * here the logic is to validate if user already exist, if exist
			 * get the user object to show error message to user on registration page, so user can change
			 * the user id 
			*/
			if (getData==null){
				resultObject.setSuccess(true);
				MongoCollection<RootMDB> rootSubmit = getMongoClient().
						getDatabase(getMongoDbName()).getCollection("Root", RootMDB.class);
				rootSubmit.insertOne(root);
				logger.info("Record updated and total records in the system " + rootSubmit.count());
				//addFamilyofPrimaryUser(obj.get(("phoneNo")).toString());
				
				RootMDB getUserBean = fetchRootDocument(obj.get(("phoneNo")).toString());
				List<FamilyMDB> arryList = getUserBean.getFamily();
				if(arryList!=null){
					for(int i = 0 ; i< arryList.size();i++){
						logger.info(arryList.get(i).getFirstName());
					}
				}
				logger.info("Newly inserted User Data " +getUserBean.getFirstName());
				resultObject.setObject1(getUserBean);
			}
			else{
				logger.info("user exists cannot add primary user");
				resultObject.setSuccess(false);
				errorMap.put("errorCode", "userExist");
				errorMap.put("errorMessage", "This user is already exist, please change Contact Information");
				resultObject.setErrors(errorMap);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			resultObject.setSuccess(false);
			errorMap.put("errorCode", "unknown");
			errorMap.put("errorMessage", "Some error occured , Please contact site admin");
			resultObject.setErrors(errorMap);
		}
		return resultObject; 
    }		

    public String getFamilySeqID(){
    	BigDecimal seqID=  getFamilyIDSequenceForSeqName(MongoConstants.FAMILY_SEQ);  
    	String familyID=null ;
    	logger.info("seqID   " + seqID.toString());
    	if (Integer.valueOf(seqID.intValue())<=9){
    		familyID = "SA000000"+ seqID.toString();
    	}else if(Integer.valueOf(seqID.intValue())<=99){
    		familyID ="SA00000"+ seqID.toString();
    	}else if(Integer.valueOf(seqID.intValue())<=999){
    		familyID ="SA0000"+ seqID.toString();
    	}else if(Integer.valueOf(seqID.intValue())<=9999){
    		familyID = "SA000"+ seqID.toString();
    	}else if(Integer.valueOf(seqID.intValue())<=99999){
    		familyID ="SA00"+ seqID.toString();
	    }else if(Integer.valueOf(seqID.intValue())<=999999){
			familyID ="SA0"+ seqID.toString();
		}
	    else if(Integer.valueOf(seqID.intValue())<=999999){
			familyID = "SA"+ seqID.toString();
		}
    	return familyID;
    }

    
    public BigDecimal getSeqID(){
    	BigDecimal seqID=  getRootSequenceIdForSeqName(MongoConstants.ROOT_SEQ);    	
    	return seqID;
    }

    private String makePasswordHash(String password, String salt) {
        try {
            String saltedAndHashed = password + "," + salt;
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(saltedAndHashed.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            byte hashedBytes[] = (new String(digest.digest(), "UTF-8")).getBytes();
            return encoder.encode(hashedBytes) + "," + salt;
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 is not available", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 unavailable?  Not a chance", e);
        }
    }
   
    private Random getRandom() {
        Random result = random.get();
        if (result == null) {
            result = new Random();
            random.set(result);
        }
        return result;
    }    
    
    public ResultObject validateLogin(String username, String password) {
    	
    	ResultObject resultObject = new ResultObject();
        HashMap<String, String> error = new HashMap<String, String>();
        RootMDB getRoot = fetchRootDocument(username);
                
        if (getRoot == null) {
            error.put("userName", "User Not Exist");
            resultObject.setErrors(error);
            return resultObject; 
        }

        String hashedAndSalted = getRoot.getPassword();
        String salt = hashedAndSalted.split(",")[1];
        
    		   
        if (!hashedAndSalted.equals(makePasswordHash(password, salt))) {
            logger.info("Submitted password is not a match");
            error.put("password", "Invalid PassWord");
            resultObject.setErrors(error);
            
            return resultObject; 
        }
        
        resultObject.setObject1(getRoot);
        return resultObject;
        
    }
    
public ResultObject validateForgetUser(String username) {
    	
    	ResultObject resultObject = new ResultObject();
        HashMap<String, String> error = new HashMap<String, String>();
        RootMDB getRoot = fetchRootDocument(username);
                
        if (getRoot == null) {
            error.put("userName", "User Not Exist");
            resultObject.setErrors(error);
            return resultObject; 
        }

       
        
        resultObject.setObject1(getRoot);
        return resultObject;
        
    }
    
    public void getRitvikData(){
	    	List<RitvikMDB> ritvikList = fetchRitvikCollection()
				.find()
				.into(new ArrayList<RitvikMDB>());
    }

    public ResultObject addFamilyUserDetails(String familyUserDetails, RootMDB userSession ){
    	logger.info("inside familyUserDetails : "+familyUserDetails);
    	ResultObject resultObject = new ResultObject();
    	HashMap<String , String> errorMap = new HashMap<String, String>();
    	String familyUserDetailsJSON = familyUserDetails;
    	int familyID = 0;
    	RootMDB root = new RootMDB();

    	try{
	    	JSONObject obj = new JSONObject(familyUserDetailsJSON);
	    	root.set_id(getSeqID()); 
			root.setFirstName(obj.getString("firstName").toUpperCase());
			
			if(obj.has(("middleName")))
				root.setMiddleName(obj.getString("middleName").toUpperCase());
			
			if(obj.has("lastName"))
				root.setLastName(obj.getString("lastName").toUpperCase());
			 root.setFamilyID(userSession.getFamilyID());
			
			 if(obj.has("rName")){
					StringTokenizer st = new StringTokenizer(obj.get(("rName")).toString(),"|");
					while (st.hasMoreElements()) {
						String rName= st.nextElement().toString();
						String saID = st.nextElement().toString();
						root.setrName(rName.toUpperCase());
						root.setRitwikID(saID);
					}
				}
			 
			RootMDB getData = fetchRootDocument(userSession.getPhoneNo().toString());
			/* 
			 * here the logic is to validate if primary user already exist, if exist
			 * get the user object to show error message to user on registration page, so user can change
			 * the user id 
			 * Member ID logic :  since primary member family ID always 1, we need to add + 1 always
			*/
			if (getData!=null){
				
				List<FamilyMDB> countFamilyList = getData.getFamily();
				if(countFamilyList!=null){
					familyID = countFamilyList.size()+2; 
				}
				else{
					familyID = 2;
				}
					
				resultObject.setSuccess(true);
				
				Document familyObject = new Document("_id", familyID)
		        .append("personalID", root.getFamilyID()+"-0"+familyID)
		        .append("firstName", root.getFirstName().toUpperCase())
		        .append("middleName", root.getMiddleName()!=null?root.getMiddleName().toUpperCase():null)
		        .append("lastName", root.getLastName().toUpperCase())
				.append("rName", root.getrName()!=null?root.getrName().toUpperCase():null)
				.append("saID", root.getRitwikID());
				 
				 fetchRootCollection().findOneAndUpdate(eq("phoneNo", userSession.getPhoneNo().toString()),
				 new Document("$push", new Document("family", familyObject)));
								
				RootMDB getUserBean = fetchRootDocument(userSession.getPhoneNo().toString());
				
				List<FamilyMDB> arryList = getUserBean.getFamily();
				for(int i = 0 ; i< arryList.size();i++){
					logger.info(arryList.get(i).getFirstName());
				}
				
				logger.info("Newly inserted User Data " +getUserBean.getFirstName());
				resultObject.setObject1(getUserBean);
			}
			else{
				resultObject.setSuccess(false);
				errorMap.put("errorCode", "userNotExist");
				errorMap.put("errorMessage", "This user is not exist, please login with valid user");
				resultObject.setErrors(errorMap);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error("Exception occure in :"+ e.getMessage());
			resultObject.setSuccess(false);
			errorMap.put("errorCode", "unknown");
			errorMap.put("errorMessage", "Some error occured , Please contact site admin");
			resultObject.setErrors(errorMap);
		}
		return resultObject; 
    }		

    public ResultObject getIstavObject(String phoneNo){
    	ResultObject result = new ResultObject();
    	 RootMDB userJSONObject = fetchRootDocument(phoneNo);
    	 IshtMDB isth = new IshtMDB();
    	return result;
    }

    public ResultObject removeAdminAccess(String phoneNo){
    	ResultObject result = new ResultObject();
    	RootMDB userJSONObject = fetchRootDocument(phoneNo);
    	return result;
    }

    public ResultObject grantAdminAccess(ResultObject resultObject){
    	RootMDB userJSONObject = fetchRootDocument(resultObject.getObject1().toString());
    	fetchRootCollection().findOneAndUpdate(eq("phoneNo", resultObject.getObject1().toString()),
		new Document("$set", new Document("userType", OnlineSAConstants.ADMIN_USER)));
    	resultObject.setSuccess(true);
    	return resultObject;
    }

	public void saveOTP(Integer number, String email, String date, boolean flag) {
		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName())
				.getCollection("PasswordResetOTP");
				Document document = new Document()
				.append("emailId", email)
				.append("OTP",number)
				.append("status",flag)
				.append("date", date);
				db.insertOne(document);
		//response = "Successfully saved card details";
		
	}

	public OTPResponseBO findUserOTPValue(String email,String currentDate) {
		OTPResponseBO bo=null;
		
		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName())
				.getCollection("PasswordResetOTP");
		Document document = new Document();
		document.put("date", currentDate);
	
		FindIterable<Document> result = db.find(document);
		for (Document doc : result) {
			bo=new OTPResponseBO();
			bo.setDate((String)doc.get("date"));
			bo.setOtp(doc.get("OTP").toString());
			bo.setStatus(doc.get("status").toString());
		}
		return bo;
	}

	public boolean ifExistEmailId(String email) {
		logger.info("Inside ifExistEmail Id, DAO class");
		
		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName())
				.getCollection("Root");
		Document document = new Document();
		document.put("email", email.toUpperCase());
		FindIterable<Document> result = db.find(document);
		String newEmail=null;
		for (Document doc : result) {
			newEmail=(String) doc.get("email");
			
		}
		if(newEmail==null) {
			return false;
		}
		return true;
	}

	public void updateOTPStatusTrue(String currentDate) {
		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName())
				.getCollection(MongoConstants.PASS_RESET_OTP);
				//.getCollection("PasswordResetOTP");
		
		/*Document document = new Document();
		document.put("date", currentDate);
		
		Document document2 = new Document();
		document2.put("status", "true");
		db.updateOne(document, document2);*/
		
		 BasicDBObject newDocument = new BasicDBObject();
		  newDocument.put("status", "true");

		  BasicDBObject searchQuery = new BasicDBObject().append("date", currentDate);

		  db.updateOne(searchQuery, newDocument);
		
		logger.info("Update status success");
		
	}

	public boolean ifExistMobileNumber(String mobileNumber) {
		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName())
				.getCollection("Root");
		Document document = new Document();
		document.put("phoneNo", mobileNumber);
		FindIterable<Document> result = db.find(document);
		String phoneNo=null;
		for (Document doc : result) {
			phoneNo=(String) doc.get("phoneNo");
			
		}
		if(phoneNo==null) {
			return false;
		}
		return true;
	}

	public void updatePassword(String familyId, String newPassword) {
		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName())
				.getCollection("Root");
		  	BasicDBObject query = new BasicDBObject();
			query.put("familyID", familyId);
			String passwordHash = makePasswordHash(newPassword, Integer.toString(getRandom().nextInt()));
			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("password",passwordHash);
			BasicDBObject updateObj = new BasicDBObject();
			updateObj.put("$set", newDocument);
			UpdateResult up=db.updateOne(query, updateObj);
			if(up.getModifiedCount()>0){
				query=new BasicDBObject();
				query.put("familyID", familyId);
				 newDocument = new BasicDBObject();
				newDocument.put("firstLogin",false);
				 updateObj = new BasicDBObject();
				updateObj.put("$set", newDocument);
				up=db.updateOne(query, updateObj);
			}
	}

	public boolean ifExistEmail(String email) {
		MongoCollection<Document> db = getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.ROOT_COLLECTION);
		//.getCollection("CardDetails");
		boolean flag = false;
		Document document = new Document();
		document.put("email",email.toString());
		
		FindIterable<Document> result = db.find(document);
		for (Document doc : result) {
			if (doc.get("email") != null) {
				return true;
			}
		}
		return flag;
	}
}
    

