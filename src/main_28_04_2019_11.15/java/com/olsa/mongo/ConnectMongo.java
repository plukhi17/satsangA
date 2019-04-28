package com.olsa.mongo;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import static com.mongodb.client.model.Filters.*;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoSocketException;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.olsa.pojo.RootMDB;
import com.olsa.utility.MongoConstants;
import com.olsa.utility.OnlineSAConstants;
import com.olsa.bo.UserProfileMDBDao;


public class ConnectMongo {
	static final Logger logger = Logger.getLogger(ConnectMongo.class);
	private MongoDatabase conectar() {
		MongoClient mongoClient = null;
		MongoDatabase database =  null;
		DB db=null;
		
		
		
		try {
			
			org.bson.codecs.Codec<Document> defaultDocumentCodec = MongoClient
	                .getDefaultCodecRegistry().get(Document.class);
	
		   CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
		                MongoClient.getDefaultCodecRegistry(),
		                CodecRegistries.fromCodecs(new com.olsa.codec.RootMDBCodec(defaultDocumentCodec)));
			
			MongoClientOptions options = MongoClientOptions.builder()
					   .codecRegistry(codecRegistry)
		               .connectionsPerHost(100)
		               .connectTimeout(5000)
		               .threadsAllowedToBlockForConnectionMultiplier(10)
		               .maxConnectionIdleTime(600000)
		               .build();
			             
			

			logger.info("**********ConnectMongo**********");
			//mongoClient = new MongoClient(("localhost:27017"),options);
			mongoClient = new MongoClient((MongoConstants.mongoHost),options);
			database = mongoClient.getDatabase(MongoConstants.Database);
		    		
		//	boolean auth = database.authenticate("username", "pwd".toCharArray());
			
	        RootMDB rootSubmitMDB = new RootMDB();
			rootSubmitMDB.setFirstName("Neha");
			
			UserProfileMDBDao u = new UserProfileMDBDao();
			
			
			RootMDB root = database.getCollection(MongoConstants.ROOT_COLLECTION,RootMDB.class).
					find(Filters.eq("phoneNo", "9975217963"), RootMDB.class).first();
			
			database.getCollection(MongoConstants.ROOT_COLLECTION,RootMDB.class).findOneAndUpdate(eq("phoneNo", "9975217963"),
					new Document("$set", new Document("userType", OnlineSAConstants.PORTAL_USER)));	
			
			logger.info(root.getFirstName());
			
			} catch (MongoSocketException   e) {
				logger.info("**********Inside catch**********");
				e.printStackTrace();
		}
		return database;
	}
	
	
	public static void main (String[] arg){
		ConnectMongo con = new ConnectMongo();
		con.conectar();
	}
}
