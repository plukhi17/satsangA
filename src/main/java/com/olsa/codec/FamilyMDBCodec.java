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

import com.olsa.pojo.FamilyMDB;
import com.olsa.pojo.RootMDB;
import com.olsa.utility.MongoUtil;

	public class FamilyMDBCodec implements CollectibleCodec<FamilyMDB> {
			private Codec<Document> documentCodec;

			public FamilyMDBCodec(Codec<Document> documentCodec) {
				this.documentCodec = documentCodec;
			}

			public void encode(BsonWriter writer, FamilyMDB family, EncoderContext context) {
				documentCodec.encode(writer, MongoUtil.objectToDocument(family), context);
			}

			public Class<FamilyMDB> getEncoderClass() {
				return FamilyMDB.class;
			}

			public FamilyMDB decode(BsonReader reader, DecoderContext decoderContext) {
				Document document = documentCodec.decode(reader, decoderContext);
				return (FamilyMDB) MongoUtil.documentToObject(document, FamilyMDB.class);
			}

			
			public FamilyMDB generateIdIfAbsentFromDocument(FamilyMDB family) {
				if(!documentHasId(family)){
					//order.setOrdId(new ObjectId("1"));
				}
				return family;
			}

			public boolean documentHasId(FamilyMDB document) {
				// TODO Auto-generated method stub
				return false;
			}

			public BsonValue getDocumentId(FamilyMDB document) {
				// TODO Auto-generated method stub
				return null;
			}

	}
			
		
