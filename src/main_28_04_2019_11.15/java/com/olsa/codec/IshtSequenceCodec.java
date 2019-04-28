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
import com.olsa.utility.MongoUtil;

public class IshtSequenceCodec implements CollectibleCodec<IshtSequence> {
	private Codec<Document> documentCodec;

	public IshtSequenceCodec(Codec<Document> documentCodec) {
		this.documentCodec = documentCodec;
	}

	public void encode(BsonWriter writer, IshtSequence ishtSequence, EncoderContext context) {
		documentCodec.encode(writer, MongoUtil.objectToDocument(ishtSequence), context);
	}

	public Class<IshtSequence> getEncoderClass() {
		return IshtSequence.class;
	}

	
	public IshtSequence decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);
		return (IshtSequence) MongoUtil.documentToObject(document, IshtSequence.class);
	}

	
	public boolean documentHasId(IshtSequence ishtSequence) {
		return ishtSequence!=null && ishtSequence.getSeqName()!=null;
	}

	
	public IshtSequence generateIdIfAbsentFromDocument(IshtSequence ishtSequence) {
		if(!documentHasId(ishtSequence)){
			//errors.setOrdId(new ObjectId("1"));
		}
		return ishtSequence;
	}

	public BsonValue getDocumentId(IshtSequence ishtSequence) {
		if(documentHasId(ishtSequence)){
			return new BsonString(ishtSequence.getSeqName());
		}
		return null;
	}

	
}

