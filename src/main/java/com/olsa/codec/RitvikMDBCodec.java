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

import com.olsa.pojo.RitvikMDB;
import com.olsa.utility.MongoUtil;

	public class RitvikMDBCodec implements CollectibleCodec<RitvikMDB> {
			private Codec<Document> documentCodec;

			public RitvikMDBCodec(Codec<Document> documentCodec) {
				this.documentCodec = documentCodec;
			}

			public void encode(BsonWriter writer, RitvikMDB root, EncoderContext context) {
				documentCodec.encode(writer, MongoUtil.objectToDocument(root), context);
			}

			public Class<RitvikMDB> getEncoderClass() {
				return RitvikMDB.class;
			}

			public RitvikMDB decode(BsonReader reader, DecoderContext decoderContext) {
				Document document = documentCodec.decode(reader, decoderContext);
				return (RitvikMDB) MongoUtil.documentToObject(document, RitvikMDB.class);
			}

			public RitvikMDB generateIdIfAbsentFromDocument(RitvikMDB document) {
				if(!documentHasId(document)){
					//order.setOrdId(new ObjectId("1"));
				}
				return document;
			}

			public boolean documentHasId(RitvikMDB document) {
				return document != null ;

			}

			public BsonValue getDocumentId(RitvikMDB document) {
				if(documentHasId(document)){
					return new BsonString(document.get_id().toString());
				}
				return null;
			}

			
			
	}
			
		
