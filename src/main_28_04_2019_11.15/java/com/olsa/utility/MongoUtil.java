package com.olsa.utility;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class MongoUtil {
	private MongoUtil() {}
	
	static JsonDeserializer<BigDecimal> bigDecimalDeserializer = new JsonDeserializer<BigDecimal>() {
		  public BigDecimal deserialize(JsonElement json, Type typeOfT,
		       JsonDeserializationContext context) throws JsonParseException {
		    if(json == null)
		     return null;
		    if(json instanceof JsonPrimitive){
			     if(json.getAsBigDecimal()!=null && json.getAsBigDecimal().remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO)==0){
			    	 return (json.getAsBigDecimal()).setScale(0, BigDecimal.ROUND_HALF_UP);
			     }else{
			    	 return json.getAsBigDecimal();
			     }      
		    }else
		     if(json instanceof JsonObject){
			     JsonElement value = ((JsonObject)json).get("$numberLong");
			     if(value != null){
			    	 if(value.getAsBigDecimal()!=null && value.getAsBigDecimal().remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO)==0){
				    	 return (value.getAsBigDecimal()).setScale(0, BigDecimal.ROUND_HALF_UP);
				     }else{
					    return value.getAsBigDecimal();
				     }					   
			     }else{
				     return null;
				 }
		     }
		    
		    return null;
		  }
		};
	
	static JsonDeserializer<Date> dateDeserializer = new JsonDeserializer<Date>() {
		  public Date deserialize(JsonElement json, Type typeOfT,
		       JsonDeserializationContext context) throws JsonParseException {
		    return json == null ? null : new Date(((JsonObject)json).get("$date").getAsLong());
		  }
	};
	static JsonSerializer<Date> dateSerializer = new JsonSerializer<Date>() {
		public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject object = new JsonObject();
			object.addProperty("$date", src.getTime());
			return object;
		}
	};
	
	static Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, dateSerializer)
			 .registerTypeAdapter(Date.class, dateDeserializer)
			 .registerTypeAdapter(BigDecimal.class, bigDecimalDeserializer).create();
	
	
	
	public final static Document objectToDocument(Object o){
		if(o == null)
			return null;
		return Document.parse(gson.toJson(o));
	}
	
	public final static <T> T documentToObject(Document d,Class<T> typeClass){
		if(d == null)
			return null;
		return gson.fromJson(d.toJson(),typeClass);
	}
	
	public final static <T> List<T> documentListToObjectList(List<Document> list,Class<T> typeClass){
		if(list == null)
			return null;
		List<T> objList = new ArrayList<T>();
		for (Document document : list) {
			objList.add(documentToObject(document,typeClass));
		}
		return objList;
	}
	
	public final static <T> List<T> mongoListToObjectList(MongoCollection<Document> mongoCol,Class<T> typeClass){
		if(mongoCol == null)
			return null;
		List<T> objList = new ArrayList<T>();
		MongoCursor<Document> cursor = mongoCol.find().iterator();
		if(cursor!=null){					
			while(cursor.hasNext()){
				Document document = cursor.next();
				objList.add(documentToObject(document,typeClass));
			}
		}
		return objList;
	}
}
