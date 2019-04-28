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

import com.olsa.pojo.RootMDB;
import com.olsa.utility.MongoUtil;

	public class RootMDBCodec implements CollectibleCodec<RootMDB> {
			private Codec<Document> documentCodec;

			public RootMDBCodec(Codec<Document> documentCodec) {
				this.documentCodec = documentCodec;
			}

			public void encode(BsonWriter writer, RootMDB root, EncoderContext context) {
				documentCodec.encode(writer, MongoUtil.objectToDocument(root), context);
			}

			public Class<RootMDB> getEncoderClass() {
				return RootMDB.class;
			}

			public RootMDB decode(BsonReader reader, DecoderContext decoderContext) {
				Document document = documentCodec.decode(reader, decoderContext);
				return (RootMDB) MongoUtil.documentToObject(document, RootMDB.class);
			}

			public RootMDB generateIdIfAbsentFromDocument(RootMDB document) {
				if(!documentHasId(document)){
					//order.setOrdId(new ObjectId("1"));
				}
				return document;
			}

			public boolean documentHasId(RootMDB document) {
				return document != null ;

			}

			public BsonValue getDocumentId(RootMDB document) {
				if(documentHasId(document)){
					return new BsonString(document.get_id().toString());
				}
				return null;
			}

			
			
	}
			
		
