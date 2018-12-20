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

import com.olsa.pojo.FamilyIDSequence;
import com.olsa.utility.MongoUtil;

public class FamilyIdSequenceCodec implements CollectibleCodec<FamilyIDSequence> {
	private Codec<Document> documentCodec;

	public FamilyIdSequenceCodec(Codec<Document> documentCodec) {
		this.documentCodec = documentCodec;
	}

	public void encode(BsonWriter writer, FamilyIDSequence familyIdSequence, EncoderContext context) {
		documentCodec.encode(writer, MongoUtil.objectToDocument(familyIdSequence), context);
	}

	public Class<FamilyIDSequence> getEncoderClass() {
		return FamilyIDSequence.class;
	}

	
	public FamilyIDSequence decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);
		return (FamilyIDSequence) MongoUtil.documentToObject(document, FamilyIDSequence.class);
	}

	
	public boolean documentHasId(FamilyIDSequence familyIdSequence) {
		return familyIdSequence!=null && familyIdSequence.getSeqName()!=null;
	}

	public FamilyIDSequence generateIdIfAbsentFromDocument(
			FamilyIDSequence document) {
		return null;
	}

	public BsonValue getDocumentId(FamilyIDSequence document) {
		return null;
	}

	
}

