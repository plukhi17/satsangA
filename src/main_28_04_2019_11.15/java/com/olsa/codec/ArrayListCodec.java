package com.olsa.codec;

import java.util.ArrayList;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

public class ArrayListCodec implements Codec<ArrayList> {
	private final CodecRegistry codecRegistry;
	
	public ArrayListCodec(CodecRegistry codecRegistry) {
		this.codecRegistry = codecRegistry;
	}

	public void encode(BsonWriter writer, ArrayList list, EncoderContext ec) {
		if(list != null && list.size() > 0){
			writer.writeStartArray();
			Codec codec = codecRegistry.get(list.get(0).getClass());
			for (Object object : list) {
				ec.encodeWithChildContext(codec, writer, object);
			}
			writer.writeEndArray();
		}
		else{
			writer.writeNull();
		}
	}

	
	public Class<ArrayList> getEncoderClass() {
		return ArrayList.class;
	}


	public ArrayList decode(BsonReader reader, DecoderContext dc) {
		ArrayList list = new ArrayList();
		reader.readStartArray();
		BsonType type = null;
		while ((type = reader.readBsonType()) != BsonType.END_OF_DOCUMENT) {
			Codec codec = codecRegistry.get(type.getClass());
			list.add(codec.decode(reader, dc));
        }
		reader.readEndArray();
		return list;
	}
  
}
