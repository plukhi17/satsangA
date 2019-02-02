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
import com.olsa.utility.SubCode;

public class SubCodeCodec implements CollectibleCodec<SubCode> {
	private Codec<Document> documentCodec;

	public SubCodeCodec(Codec<Document> documentCodec) {
		this.documentCodec = documentCodec;
	}

	public void encode(BsonWriter writer, SubCode cntSeq, EncoderContext context) {
		documentCodec.encode(writer, MongoUtil.objectToDocument(cntSeq), context);
	}

	public Class<SubCode> getEncoderClass() {
		return SubCode.class;
	}

	
	public SubCode decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);
		return (SubCode) MongoUtil.documentToObject(document, SubCode.class);
	}

	

	
	public SubCode generateIdIfAbsentFromDocument(SubCode ishtSequence) {
		if(!documentHasId(ishtSequence)){
			//errors.setOrdId(new ObjectId("1"));
		}
		return ishtSequence;
	}




	@Override
	public boolean documentHasId(SubCode document) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BsonValue getDocumentId(SubCode document) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

