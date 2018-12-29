/**
 * 
 */
package com.olsa.utility;

/**
 * @author parthl
 *
 */
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
 
public class CustomDateSerializer extends JsonSerializer<Date>{
 
    public void serialize(Date dt, JsonGenerator jsonGen, SerializerProvider serProv) 
                                            throws IOException, JsonProcessingException {
        DateFormat sdf = new SimpleDateFormat(OnlineSAConstants.DATE_TIME_FORMAT_MONGO);
        String formattedDate = sdf.format(dt);
        jsonGen.writeString(formattedDate);
    }
}

