package com.olsa.mongo;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.olsa.codec.ArrayListCodecProvider;
import com.olsa.codec.ConterCodec;
import com.olsa.codec.FamilyMDBCodec;
import com.olsa.codec.IshtMDBCodec;
import com.olsa.codec.IshtSequenceCodec;
import com.olsa.codec.RootMDBCodec;
import com.olsa.codec.RitvikMDBCodec;
import com.olsa.codec.RootSequenceCodec;
import com.olsa.codec.SubCodeCodec;
import com.olsa.codec.FamilyIdSequenceCodec;
import com.olsa.pojo.RootSequence;
import com.olsa.pojo.FamilyIDSequence;


public class OsMongoClient {
	
		
    private MongoClient mongoClient;
    private String mongoDbName;	
	
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

	
	public OsMongoClient(String mongoDbName,String mongoDbHosts ,String mongoDbUsers,  String mongoDbPwds, String mongoDbPorts) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
			
			String[] mongoDbHostList=mongoDbHosts.trim().split(",");
			String[] mongoUsernameList=mongoDbUsers.trim().split(","); 
			String[] mongoDbPasswordList=mongoDbPwds.trim().split(",");
			String[] mongoDbPortList=mongoDbPorts.trim().split(",");
		   
		
			Codec<Document> defaultDocumentCodec = MongoClient
	                .getDefaultCodecRegistry().get(Document.class);
	
		   CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
		                MongoClient.getDefaultCodecRegistry(),
		                CodecRegistries.fromCodecs(new RootMDBCodec(defaultDocumentCodec)),
		                CodecRegistries.fromCodecs(new RitvikMDBCodec(defaultDocumentCodec)),
		                CodecRegistries.fromCodecs(new FamilyMDBCodec(defaultDocumentCodec)),
		                CodecRegistries.fromCodecs(new RootSequenceCodec(defaultDocumentCodec)),
		                CodecRegistries.fromCodecs(new FamilyIdSequenceCodec(defaultDocumentCodec)),
		                CodecRegistries.fromCodecs(new IshtSequenceCodec(defaultDocumentCodec)),
		                CodecRegistries.fromCodecs(new IshtMDBCodec(defaultDocumentCodec)),
		                CodecRegistries.fromCodecs(new ConterCodec(defaultDocumentCodec)),
		               
		                CodecRegistries.fromProviders(new ArrayListCodecProvider()));
		   
		   
		   
		   MongoClientOptions options = MongoClientOptions.builder()
				   .codecRegistry(codecRegistry)
	               .connectionsPerHost(100)
	               .connectTimeout(5000)
	               .threadsAllowedToBlockForConnectionMultiplier(10)
	               .maxConnectionIdleTime(600000)
	               .build();	  
		   
		   List<ServerAddress>  serverList = new ArrayList<ServerAddress>();
	   	   	for(int i=0;i<mongoUsernameList.length;i++){
	   			 ServerAddress s1= new ServerAddress(mongoDbHostList[i], Integer.valueOf(mongoDbPortList[i]));
	   			 serverList.add(s1);
	   			 
	   		}
	   		
	   		List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
	   		for(int i=0;i<mongoUsernameList.length;i++){
	   			 //Encryptor aes = new Encryptor();
	   			 MongoCredential credentialS1 = MongoCredential.createCredential(mongoUsernameList[i], mongoDbName, mongoDbPasswordList[i].toCharArray());
	   			 credentialsList.add(credentialS1);
	   		}
		   
		  /*
		    * primaryPreferred : Always reads from primary , if primary is down then read from secondary 
		    * Write : setting primary +1 , 5 secs wait for write , no forced writing to disk and using journaling feature for the crash recovery.
		    */
	   		setMongoClient(new MongoClient(serverList, credentialsList,options));
	   		setMongoDbName(mongoDbName);
	   		getMongoClient().setReadPreference(ReadPreference.primaryPreferred());	   		  
	   		WriteConcern concern = new WriteConcern(2,5000).withJournal(true);
	   		getMongoClient().setWriteConcern(concern);
	   
	   		   
	   }  
	   
	}

