package com.olsa.bo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.olsa.pojo.FamilyIDSequence;
import com.olsa.pojo.IshtMDB;
import com.olsa.pojo.IshtSequence;
import com.olsa.pojo.RitvikMDB;
import com.olsa.pojo.RootMDB;
import com.olsa.pojo.RootSequence;
import com.olsa.utility.MongoConstants;
import com.olsa.pojo.IshtLineMDB;
import com.olsa.utility.OnlineSAConstants;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.include;


public class MongoBaseDao extends BaseDao {
	static final Logger logger = Logger.getLogger(MongoBaseDao.class);
	private MongoClient mongoClient;
	public MongoClient getMongoClient() {
		return mongoClient;
	}


	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}


	public String getMongoDbName() {
		return mongoDbName;
	}


	public void setMongoDbName(String mongoDbName) {
		this.mongoDbName = mongoDbName;
	}

	private String mongoDbName;
	
	public void doBackendLogging(String detailsId, Class<?> className, String taskName, Map<String, ?> input,
			Map output, long timeElapsed) {
	}
	
	public BigDecimal getRootSequenceIdForSeqName(String sequenceName)  {
		RootSequence seqObj = fetchRootSequenceCollection().findOneAndUpdate(Filters.eq(MongoConstants.MONGO_ID, sequenceName),
				Updates.inc(MongoConstants.SEQ_VALUE, 1),
				new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER).upsert(true));

		if (seqObj == null || seqObj.getSeqValue() == null) {
			
		}

		return seqObj.getSeqValue();
	}

	public MongoCollection<RootSequence> fetchRootSequenceCollection() {
		return getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.ROOT_SEQ, RootSequence.class);
	}


	/**
	 * function to get seq id for family
	 * @param sequenceName
	 * @return
	 */
	public BigDecimal getFamilyIDSequenceForSeqName(String sequenceName)  {
		FamilyIDSequence seqObj = fetchFamilyIDSequenceCollection().findOneAndUpdate(Filters.eq(MongoConstants.MONGO_ID, sequenceName),
				Updates.inc(MongoConstants.SEQ_VALUE, 1),
				new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER).upsert(true));

		if (seqObj == null || seqObj.getSeqValue() == null) {
			
		}

		return seqObj.getSeqValue();
	}
	

	public MongoCollection<FamilyIDSequence> fetchFamilyIDSequenceCollection() {
		return getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.FAMILY_SEQ, FamilyIDSequence.class);
	}
	
	
	/***
	 * 	This method is used to fetch Isht Collection
	 * @return Isht
	 */	
	public MongoCollection<IshtMDB> fetchIshtCollection(){
		return getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.ISHT_COLLECTION,IshtMDB.class);	
	}

	/***
	 * 	This method is used to fetch Isht Document
	 * @return Root Document
	 */

	public IshtMDB fetchIshtDocument(String receiptNo){
		logger.info("fetchIshttDocument " + receiptNo);
		return fetchIshtCollection().find(Filters.eq("receiptNo", receiptNo), IshtMDB.class).first();
	}
	

	
	/***
	 * 	This method is used to fetch Root Collection
	 * @return RootCollection
	 */	
	public MongoCollection<RootMDB> fetchRootCollection(){
		return getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.ROOT_COLLECTION,RootMDB.class);	
	}
	
	/***
	 * 	This method is used to fetch Root Collection
	 * @return RootCollection
	 */	
	public MongoCollection<RootMDB> fetchRootListCollection(){
		
		return getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.ROOT_COLLECTION,RootMDB.class);	
	}
	/***
	 * 	This method is used to fetch Root Document
	 * @return Root Document
	 */

	public RootMDB fetchRootDocument(String phoneNo){
		logger.info("fetchRootDocument " + phoneNo);
		return fetchRootCollection().find(Filters.eq("phoneNo", phoneNo), RootMDB.class).first();
	}
	
	/***
	*This method implemetns like operator and returns the mulitple Documents.
	*/
	public FindIterable<RootMDB> fetchRootListDocument(String inputValue,String inputParam){
		BasicDBObject regexQuery = new BasicDBObject();
		
		logger.info("fetchRootListDocument " + inputParam);
		
		if(inputParam.equalsIgnoreCase("phoneNo")){
			regexQuery.put("phoneNo", new BasicDBObject("$regex", inputValue).append("$options", "i"));
			//DBCursor cursor = fetchRootCollection().find(regexQuery,)
		}
		else if(inputParam.equalsIgnoreCase("familyCode")) {
			regexQuery.put("familyID", new BasicDBObject("$regex", inputValue).append("$options", "i"));
		}
		else if(inputParam.equalsIgnoreCase("firstName")) {
			regexQuery.put("firstName", new BasicDBObject("$regex", inputValue).append("$options", "i"));
		}
		else if(inputParam.equalsIgnoreCase("lastName")) {
			regexQuery.put("lastName", new BasicDBObject("$regex", inputValue).append("$options", "i"));
		}
		
		logger.info("regexQuery :"+regexQuery.toString());
		//return  fetchRootCollection().find(Filters.eq("phoneNo", inputValue), RootMDB.class).first();
		return fetchRootListCollection().find(regexQuery);
	}
	
	
	public RootMDB validateUserDetails(String userName, String password){
		return fetchRootCollection().find(Filters.eq("phoneNo", userName), RootMDB.class).first();
	}
	
	/***
	 * 	This method is used to fetch Root Collection
	 * @return RootCollection
	 */	
	public MongoCollection<RitvikMDB> fetchRitvikCollection(){
		return getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.RITVIK_COLLECTION,RitvikMDB.class);	
	}

	/**
	 * seq id for Isht receipt no
	 * @param sequenceName
	 * @return
	 */
	public BigDecimal getIshtSequenceIdForSeqName(String sequenceName)  {
		IshtSequence seqObj = fetchIshtSequenceCollection().findOneAndUpdate(Filters.eq(MongoConstants.MONGO_ID, sequenceName),
				Updates.inc(MongoConstants.SEQ_VALUE, 1),
				new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER).upsert(true));

		if (seqObj == null || seqObj.getSeqValue() == null) {
			
		}

		return seqObj.getSeqValue();
	}

	public MongoCollection<IshtSequence> fetchIshtSequenceCollection() {
		return getMongoClient().getDatabase(getMongoDbName()).getCollection(MongoConstants.ISHT_SEQ, IshtSequence.class);
	}

	
 public List<IshtLineMDB> fetchIshtLines(final String phoneNo) {

            if (phoneNo != null) {
		                final Document doc = this.getMongoClient().getDatabase(this.getMongoDbName())
		                        .getCollection(OnlineSAConstants.ISHT_COLLECTION, IshtMDB.class)
		                        .find(eq("phoneNo", phoneNo), Document.class)
		                        .projection(include("line")).sort(Sorts.descending("_id")).first();
		                if (doc != null) {
		                    final List<IshtLineMDB> lines = com.olsa.utility.MongoUtil.documentListToObjectList((List<Document>) doc
		                            .get("line"), IshtLineMDB.class);
		                        return lines;
		                    }
		                else  {
		                   	 logger.info("Inside Else condition there is no transaction lines.");
		                	return null;
		                }
		                
		                }
		            return null;
       }

}
