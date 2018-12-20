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

import com.olsa.pojo.IshtMDB;
import com.olsa.pojo.RootMDB;
import com.olsa.utility.MongoUtil;

	public class IshtMDBCodec implements CollectibleCodec<IshtMDB> {
			private Codec<Document> documentCodec;

			public IshtMDBCodec(Codec<Document> documentCodec) {
				this.documentCodec = documentCodec;
			}

			public void encode(BsonWriter writer, IshtMDB root, EncoderContext context) {
				documentCodec.encode(writer, MongoUtil.objectToDocument(root), context);
			}

			public Class<IshtMDB> getEncoderClass() {
				return IshtMDB.class;
			}

			public IshtMDB decode(BsonReader reader, DecoderContext decoderContext) {
				Document document = documentCodec.decode(reader, decoderContext);
				return (IshtMDB) MongoUtil.documentToObject(document, IshtMDB.class);
			}

			public IshtMDB generateIdIfAbsentFromDocument(IshtMDB document) {
				if(!documentHasId(document)){
					//order.setOrdId(new ObjectId("1"));
				}
				return document;
			}

			public boolean documentHasId(IshtMDB document) {
				return document != null ;

			}

			public BsonValue getDocumentId(IshtMDB document) {
				if(documentHasId(document)){
					return new BsonString(document.get_id().toString());
				}
				return null;
			}

			
			
	}
			
		
