package com.olsa.utility;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.text.Document;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.olsa.pojo.IshtMDB;
import com.olsa.pojo.RootMDB;

public class SendEmail {
	
	    private Session session;
	    private Transport trans;
	    private MimeMessage msg;
		
	    private boolean IsMultiRecipient=false;
		
	    public boolean isIsMultiRecipient() {
			return IsMultiRecipient;
		}

		public void setIsMultiRecipient(boolean isMultiRecipient) {
			IsMultiRecipient = isMultiRecipient;
		}
	    public String getSender() {
			return sender;
		}

		public void setSender(String sender) {
			this.sender = sender;
		}

		public String getRecipient() {
			return recipient;
		}

		public void setRecipient(String recipient) {
			this.recipient = recipient;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getContenct_html() {
			return contenct_html;
		}

		public void setContenct_html(String contenct_html) {
			this.contenct_html = contenct_html;
		}

		private String sender, recipient, content, contenct_html,subject ;
		
		private String[] multipleRecipients = new String[4]; 
		
	    
	    public String[] getMultipleRecipients() {
			return multipleRecipients;
		}

		public void setMultipleRecipients(String[] multipleRecipients) {
			this.multipleRecipients = multipleRecipients;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}
				
		/**
	     * Sends an email to user when user will donate Isht. .
	     */
	    public void emailHtml() throws Exception {
	    	 
	    	 String smtpHost = "javamail";
	         String protocol = "smtp";

	         Properties props = System.getProperties();
	         props.put("mail.smtp.host", smtpHost);
	         props.put("mail.store.protocol",protocol);
	         
	         session = Session.getDefaultInstance(props, null);
	         trans = session.getTransport(protocol);
	         msg = new MimeMessage(session);
	         msg.setSentDate(new Date());
	         
	        ByteArrayOutputStream outputStream = null;
	         
	        try {      
	        	
	            //construct the text body part
	            MimeBodyPart textBodyPart = new MimeBodyPart();
	            //construct the mime multi part
	            MimeMultipart mimeMultipart = new MimeMultipart();
	            //create the sender/recipient addresses
	            InternetAddress iaSender = new InternetAddress(sender);
	           
	            //construct the mime message
			    msg.setFrom(new InternetAddress("SatsangBayArea USA <satsangbayareausa@gmail.com>"));
				msg.setSender(iaSender);
				msg.setSubject(subject);
				
				if(this.multipleRecipients.length>1) {
		            InternetAddress[] sendTo = new InternetAddress[multipleRecipients.length];
		            	for (int i = 0; i <multipleRecipients.length; i++) {
		    				//System.out.println("Send to:" + to[i]);
		    				sendTo[i] = new InternetAddress(multipleRecipients[i]);
		    			}
		            	msg.addRecipients(Message.RecipientType.TO, sendTo);
		    		}else {
		    			 InternetAddress iaRecipient = new InternetAddress(recipient);
		    			msg.setRecipient(Message.RecipientType.TO, iaRecipient);		
		    		}
		    	String MailbodyContent=this.getContent();
				textBodyPart.setContent("<html><body><h1> </h1>\n" + MailbodyContent.toString() + "</body></html>", "text/html");
				mimeMultipart.addBodyPart(textBodyPart);
			    msg.setContent(mimeMultipart,"text/html" ); 
	            //send off the email
	            trans.send(msg);
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            //clean off
	            if(null != outputStream) {
	                try { outputStream.close(); outputStream = null; }
	                catch(Exception ex) {
	                	System.out.println("Exception in Email "+ex.getMessage());
	                }
	            }
	        }
	    }
	     
	    
	    /**
	     * Writes the content of a PDF file (using iText API)
	     * to the {@link OutputStream}.
	     * @param outputStream {@link OutputStream}.
	     * @throws Exception
	     */
	    public void writePdf(OutputStream outputStream) throws Exception {
	        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
	        PdfWriter.getInstance(document, outputStream);
	        document.open();
	        document.addTitle("Test PDF");
	        document.addSubject("Testing email PDF");
	        document.addKeywords("iText, email");
	        Paragraph paragraph = new Paragraph();
	        paragraph.add(new Chunk("hello!"));
	        document.add(paragraph);
	        document.close();
	    }

		public String convertDate(Date d, String newFormat) throws Exception {
			SimpleDateFormat sdf = new SimpleDateFormat(newFormat);
			return sdf.format(d);
		}
		
		/**
	     * Sends an email to user when user will donate Isht. .
	     */
	    public void emailReceipt() throws Exception {
	    	 
	    	 String smtpHost = "javamail";
	         String protocol = "smtp";

	         Properties props = System.getProperties();
	         props.put("mail.smtp.host", smtpHost);
	         props.put("mail.store.protocol",protocol);
	         
	         session = Session.getDefaultInstance(props, null);
	         trans = session.getTransport(protocol);
	         msg = new MimeMessage(session);
	         msg.setSentDate(new Date());
	         
	        ByteArrayOutputStream outputStream = null;
	         
	        try {      
	        	
	        	//now write the PDF content to the output stream
	            outputStream = new ByteArrayOutputStream();
	            writePdf(outputStream);
	            byte[] bytes = outputStream.toByteArray();
	            
	          //construct the pdf body part
	            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
	            MimeBodyPart pdfBodyPart = new MimeBodyPart();
	            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
	            pdfBodyPart.setFileName("recipt.pdf");
	            
	            //construct the text body part 
	            MimeBodyPart textBodyPart = new MimeBodyPart();
	            
	            //construct the mime multi part
	            MimeMultipart mimeMultipart = new MimeMultipart();
	             
	            //create the sender/recipient addresses
	            InternetAddress iaSender = new InternetAddress(sender);
	            InternetAddress iaRecipient = new InternetAddress(recipient);
	                      
	            //construct the mime message
			    msg.setFrom(new InternetAddress("SatsangBayArea USA <satsangbayareausa@gmail.com>"));
				msg.setSender(iaSender);
				msg.setSubject(subject);
				msg.setRecipient(Message.RecipientType.TO, iaRecipient);
				String MailbodyContent=this.getContent();
				textBodyPart.setContent("<html><body><h1> </h1>\n" + MailbodyContent.toString() + "</body></html>", "text/html");
				mimeMultipart.addBodyPart(textBodyPart);
				mimeMultipart.addBodyPart(pdfBodyPart);
	            msg.setContent(mimeMultipart,"text/html" ); 
	            //send off the email
	            trans.send(msg);
	             
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            //clean off
	            if(null != outputStream) {
	                try { outputStream.close(); outputStream = null; }
	                catch(Exception ex) {
	                	System.out.println("Exception in Email "+ex.getMessage());
	                }
	            }
	        }
	    }
	    
	    
		/*
		 * Sends and Html format email with PDF attachement.
		 * 
		 */
		
		/**
	     * Sends an email with a PDF attachment.
	     */
	    public void sendHtmlEmailWithPDFAttachment(String input_receipt_no , IshtMDB ishtMDB , RootMDB rootMdb) 
	    		throws Exception {
	    	
	    	  Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.smtp.starttls.enable", "true"); 
		      props.put("mail.smtp.host", "smtp.gmail.com");
		      props.put("mail.smtp.port", "587");
		      props.put("mail.debug", "false");
		      
		      Session session = Session.getInstance(props,
		    		  new Authenticator() {
		  			protected PasswordAuthentication getPasswordAuthentication() {
		  				return new PasswordAuthentication( OnlineSAConstants.EMAIL_ID,OnlineSAConstants.CREDENTIALS );
		  			}
		  		  });
		  
	        ByteArrayOutputStream outputStream = null;
	         
	        try {           
	        	
	        	MimeMessage msg = new MimeMessage(session);
	        	//construct the text body part
	        	MimeBodyPart textBodyPart = new MimeBodyPart();
	        	//added for attachement
	        	MimeBodyPart attachBodyPart = new MimeBodyPart();  
	        	
	        	String input_family_code=rootMdb.getFamilyID() ,input_month_year=ishtMDB.getMonthYear();
	        	String filename="ARGHYA_PRASWASTI_"+input_family_code+"_"+""+input_month_year+".pdf"; 
	        	       	
	        	 //now write the PDF content to the output stream
	             outputStream = new ByteArrayOutputStream();
	            CreateNSendArghyaPraswasti createAP = new CreateNSendArghyaPraswasti();
	            createAP.buildArghyaPraswasti(outputStream,input_receipt_no,ishtMDB,rootMdb);
	            byte[] bytes = outputStream.toByteArray();
	             
	            //construct the pdf body part
	            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
	            MimeBodyPart pdfBodyPart = new MimeBodyPart();
	            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
	            pdfBodyPart.setFileName(filename);

	           //construct the mime multi part
	        	MimeMultipart mimeMultipart = new MimeMultipart();

	        	//create the sender/recipient addresses
	        	InternetAddress iaSender = new InternetAddress(sender);
	        	InternetAddress iaRecipient = new InternetAddress(recipient);

	        	//construct the mime message
	        	//msg mimeMessage = new MimeMessage(session);
	        	//msg.setFrom(new InternetAddress(OnlineSAConstants.SENDER_EMAIL_ID2));
	        	msg.setFrom(new InternetAddress(OnlineSAConstants.SENDER_EMAIL_ID2));
	        	//msg.setFrom(new InternetAddress(sender));
	        	msg.setSender(iaSender);
	        	msg.setSubject(subject);
	        	msg.setRecipient(Message.RecipientType.TO, iaRecipient);
	        	msg.setSentDate(new Date());
	        	//msg.setContent(mimeMultipart);

	        	//String MailbodyContent="<p style=\"font-family: Verdana, sans-serif;  color: blue; font-weight: bold; font-size: 12px;\"> Hello Shyam Bhai <br>  Your content here.  </p>";
	        	String MailbodyContent=this.getContent();

	           	//added for attachement
	        	textBodyPart.setContent("<html><body><h1> </h1>\n" + MailbodyContent.toString() + "</body></html>", "text/html");
	        	mimeMultipart.addBodyPart(textBodyPart);
	        	//mimeMultipart.addBodyPart(attachBodyPart);
	        	mimeMultipart.addBodyPart(pdfBodyPart);

	        	msg.setContent(mimeMultipart,"text/html" ); 

	        	//send off the email
	        	trans.send(msg);

	        	System.out.println("Successfully !!! Sent from " + sender + ", to " + recipient +""); 
	        
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            //clean off
	            if(null != outputStream) {
	                try { outputStream.close(); outputStream = null; }
	                catch(Exception ex) {
	                	System.out.println("Exception in Email "+ex.getMessage());
	                }
	            }
	        }
	    }
	    
	    
	  
	    public void sendHtmlEmail() 
	    		throws Exception {
	    	
	    	  Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.smtp.starttls.enable", "true"); 
		      props.put("mail.smtp.host", "smtp.gmail.com");
		      props.put("mail.smtp.port", "587");
		      props.put("mail.debug", "false");
		 	     
		      Session session = Session.getInstance(props,
		    		  new Authenticator() {
		  			protected PasswordAuthentication getPasswordAuthentication() {
		  				return new PasswordAuthentication(OnlineSAConstants.EMAIL_ID,OnlineSAConstants.CREDENTIALS);
		  			}
		  		  });
		  
	        ByteArrayOutputStream outputStream = null;
	         
	        try {           
	        	
	        	MimeMessage msg = new MimeMessage(session);
	        	//construct the text body part
	        	MimeBodyPart textBodyPart = new MimeBodyPart();
	        	//added for attachement
	        	MimeBodyPart attachBodyPart = new MimeBodyPart();  
	        
	           //construct the mime multi part
	        	MimeMultipart mimeMultipart = new MimeMultipart();

	        	//create the sender/recipient addresses
	        	InternetAddress iaSender = new InternetAddress(sender);
	        	//InternetAddress iaRecipient = new InternetAddress(recipient);
	        	
	        	msg.setFrom(new InternetAddress(OnlineSAConstants.SENDER_EMAIL_ID2));
	        	//msg.setFrom(new InternetAddress(sender));
	        	msg.setSender(iaSender);
	        	msg.setSubject(subject);
	        	//msg.setRecipient(Message.RecipientType.TO, iaRecipient);
	        	//msg.setRecipient(Message.RecipientType.CC, iaRecipient);
	        	msg.setSentDate(new Date());
	        	//msg.setContent(mimeMultipart);
	        	String MailbodyContent=this.getContent();
	           	//added for attachement
	        	textBodyPart.setContent("<html><body><h1> </h1>\n" + MailbodyContent.toString() + "</body></html>", "text/html");
	        	mimeMultipart.addBodyPart(textBodyPart);
	        	//mimeMultipart.addBodyPart(attachBodyPart);
	        	//mimeMultipart.addBodyPart(pdfBodyPart);
	        	msg.setContent(mimeMultipart,"text/html" ); 
	        	//send off the email
	        	
	        	if(this.IsMultiRecipient) {	        		        	
		        	if(this.multipleRecipients.length>1) {
		        	    InternetAddress[] sendTo = new InternetAddress[multipleRecipients.length];
			            	for (int i = 0; i <multipleRecipients.length; i++) {
			    					sendTo[i] = new InternetAddress(multipleRecipients[i]);	 
			    			}
			            	msg.addRecipients(Message.RecipientType.TO, sendTo);
			            	trans.send(msg);
			           }else {
			    			 InternetAddress iaRecipient = new InternetAddress(recipient);
			    			 msg.setRecipient(Message.RecipientType.TO, iaRecipient);
			    			trans.sendMessage(msg, msg.getAllRecipients());
			    			System.out.println("Successfully !!! Sent to Approver " + sender + ", to " + Arrays.toString(multipleRecipients)+"");
			    		}
		        	}
		        	else {
		        		InternetAddress iaRecipient = new InternetAddress(recipient);
		        		msg.setRecipient(Message.RecipientType.TO, iaRecipient);
		        		trans.send(msg);
		        		System.out.println("Successfully !!! Sent to Approver " + sender + ", to " + recipient +"");
		        	}
	        		
	        	//trans.sendMessage(msg, msg.getAllRecipients());

	        	
	        	//System.out.println("Successfully !!! Sent to Approver " + sender + ", to " + multipleRecipients +"");
	        
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            //clean off
	            if(null != outputStream) {
	                try { outputStream.close(); outputStream = null; }
	                catch(Exception ex) {
	                	System.out.println("Exception in sendHtmlEmail "+ex.getMessage());
	                }
	            }
	        }
	    }
	    
	    
}
