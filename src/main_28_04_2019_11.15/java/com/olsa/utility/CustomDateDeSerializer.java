/**
 * 
 */
package com.olsa.utility;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;


/**
 * @author parthl
 *
 */
public class CustomDateDeSerializer extends JsonDeserializer<Date>{

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		 SimpleDateFormat formatter;
		    final String dateAsString = jp.getText();
		    Date date = null;
		
		        formatter = new SimpleDateFormat(OnlineSAConstants.DATE_TIME_FORMAT_MONGO);
		       // formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		        try {
					date = formatter.parse(dateAsString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return date;
	}

	
}
