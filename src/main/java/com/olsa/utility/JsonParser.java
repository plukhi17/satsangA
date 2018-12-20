package com.olsa.utility;

import java.net.URLEncoder;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.json.JSONObject;

public class JsonParser {
	static final Logger logger = Logger.getLogger(JsonParser.class);

	public static void geocoding(String addr) throws Exception
	{
	    // build a URL
	    String s = "http://maps.google.com/maps/api/geocode/json?" +
	                    "sensor=false&address=";
	    s += URLEncoder.encode(addr, "UTF-8");
	    java.net.URL url = new java.net.URL(s);
	 
	    // read from the URL
	    Scanner scan = new Scanner(url.openStream());
	    String str = new String();
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	    logger.info(str);
	    // build a JSON object
	    JSONObject obj = new JSONObject(str);
	    if (!obj.getString("status").equals("OK"))
	        return;
	 
	    // get the first result
	    JSONObject res = obj.getJSONArray("results").getJSONObject(0);
	    logger.info(res.getString("formatted_address"));
	    JSONObject loc =
	        res.getJSONObject("geometry").getJSONObject("location");
	    logger.info("lat: " + loc.getDouble("lat") +
	                        ", lng: " + loc.getDouble("lng"));
	}

	public static void main(String[] args) throws Exception{
		JsonParser jp = new JsonParser();
		jp.geocoding("nagpur");
		
	}
}
