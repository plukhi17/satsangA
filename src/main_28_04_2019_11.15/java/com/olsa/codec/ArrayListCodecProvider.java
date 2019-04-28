package com.olsa.codec;

import java.util.ArrayList;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class ArrayListCodecProvider implements CodecProvider {

	public <T> Codec<T> get(Class<T> type, CodecRegistry cr) {
		if (type == ArrayList.class) {
            return (Codec<T>) new ArrayListCodec(cr);
        }
        return null;
	}

}
