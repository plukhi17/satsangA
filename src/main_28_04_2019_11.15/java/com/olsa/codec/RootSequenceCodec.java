package com.olsa.codec;


import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import com.olsa.pojo.RootSequence;
import com.olsa.utility.MongoUtil;

public class RootSequenceCodec implements CollectibleCodec<RootSequence> {
	private Codec<Document> documentCodec;

	public RootSequenceCodec(Codec<Document> documentCodec) {
		this.documentCodec = documentCodec;
	}

	public void encode(BsonWriter writer, RootSequence rootSequence, EncoderContext context) {
		documentCodec.encode(writer, MongoUtil.objectToDocument(rootSequence), context);
	}

	public Class<RootSequence> getEncoderClass() {
		return RootSequence.class;
	}

	
	public RootSequence decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);
		return (RootSequence) MongoUtil.documentToObject(document, RootSequence.class);
	}

	
	public boolean documentHasId(RootSequence rootSequence) {
		return rootSequence!=null && rootSequence.getSeqName()!=null;
	}

	
	public RootSequence generateIdIfAbsentFromDocument(RootSequence rootSequence) {
		if(!documentHasId(rootSequence)){
			//errors.setOrdId(new ObjectId("1"));
		}
		return rootSequence;
	}

	public BsonValue getDocumentId(RootSequence rootSequence) {
		if(documentHasId(rootSequence)){
			return new BsonString(rootSequence.getSeqName());
		}
		return null;
	}

	
}

