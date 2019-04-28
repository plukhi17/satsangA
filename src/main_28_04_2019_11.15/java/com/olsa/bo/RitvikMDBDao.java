package com.olsa.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.client.MongoCursor;
import com.olsa.pojo.ResultObject;
import com.olsa.pojo.RitvikMDB;
import com.olsa.utility.OnlineSAConstants;

public class RitvikMDBDao extends MongoBaseDao{
	static final Logger logger = Logger.getLogger(RitvikMDBDao.class);
	private List<RitvikMDB> ritvikList = new ArrayList<RitvikMDB>();
	public static boolean isRitvikListCreated = false;
	
	public ResultObject getAllRitVik(){
		logger.info("Inside the  getAllRitVik ");
		ResultObject result = new ResultObject();
			RitvikMDB ritvik = new RitvikMDB();	
	    	MongoCursor<Document> cursor= null;
	    		cursor = getMongoClient().getDatabase(getMongoDbName()).getCollection(OnlineSAConstants.RITVIK_COLLECTION).find().iterator();
	    		//System.out.println("got the cursor ");
		    	if(cursor!=null){
		    		while(cursor.hasNext()){
		    			Document result1 = cursor.next();
		    			//System.out.println("cusrsor size" + result1.size());
		    			RitvikMDB r = new RitvikMDB();
		    			//System.out.println("after the cursor ");
	                      r.setSaID(result1.get("SA_SPR_ID").toString());
	                      if ((result1.get("LIVING_STATUS").toString().equalsIgnoreCase("E"))) {
	                    	  r.setFirstName("LATE "+result1.get("FIRST_NAME").toString());
	                      }else {
	                    	  r.setFirstName(result1.get("FIRST_NAME").toString());
	                      }
		    			  r.setLastName(result1.get("LAST_NAME").toString());
		    		      r.setAddress(result1.get("ADDRESS").toString());
		    			  ritvikList.add(r);
		    			 // System.out.println("Ritvik Name " + result1.get("FIRST_NAME").toString() +" "+result1.get("LAST_NAME").toString());
		    		}
		    	}	
		    	ritvik.setRitvikList(ritvikList);
		    	JSONObject ritvikJSONObject = new JSONObject(ritvik);
		    	
		    	OnlineSAConstants.ritvikHashMapCache.put("ritvikJSONObject", ritvikJSONObject);
		    	
		    	result.setObject1(ritvik);
		  return result;
	    }
	
}