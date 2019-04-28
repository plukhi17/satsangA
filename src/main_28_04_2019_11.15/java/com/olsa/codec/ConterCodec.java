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

import com.olsa.pojo.IshtSequence;
import com.olsa.utility.Counter;
import com.olsa.utility.MongoUtil;

public class ConterCodec implements CollectibleCodec<Counter> {
	private Codec<Document> documentCodec;

	public ConterCodec(Codec<Document> documentCodec) {
		this.documentCodec = documentCodec;
	}

	public void encode(BsonWriter writer, Counter cntSeq, EncoderContext context) {
		documentCodec.encode(writer, MongoUtil.objectToDocument(cntSeq), context);
	}

	public Class<Counter> getEncoderClass() {
		return Counter.class;
	}

	
	public Counter decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);
		return (Counter) MongoUtil.documentToObject(document, Counter.class);
	}

	
	public boolean documentHasId(IshtSequence ishtSequence) {
		return ishtSequence!=null && ishtSequence.getSeqName()!=null;
	}

	
	public Counter generateIdIfAbsentFromDocument(Counter ishtSequence) {
		if(!documentHasId(ishtSequence)){
			//errors.setOrdId(new ObjectId("1"));
		}
		return ishtSequence;
	}

	public BsonValue getDocumentId(Counter ishtSequence) {
		if(documentHasId(ishtSequence)){
			return new BsonString(ishtSequence.getSeqName());
		}
		return null;
	}

	@Override
	public boolean documentHasId(Counter document) {
		return document!=null && document.getSeqName()!=null;
	
	}

	
}

