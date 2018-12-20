package com.olsa.utility;

import org.apache.log4j.Logger;

import com.olsa.pojo.IshtMDB;
import com.olsa.pojo.RootMDB;

public class MailThread extends Thread {
	static final Logger logger = Logger.getLogger(MailThread.class);
	String receiptNo;
	IshtMDB ishtMdb;
	RootMDB root;

	public MailThread(String name) {
		super(name);
	}

	public MailThread(IshtMDB ishtMdb, RootMDB root) {
		this.receiptNo=receiptNo;
		this.ishtMdb=ishtMdb;
		this.root=root;
	}

	@Override
	public void run() {
		logger.info("MailThread - START "+Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
			//Get database connection, delete unused data from DB
			mailProcessing();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("MailThread - END "+Thread.currentThread().getName());
	}

	private void mailProcessing() throws InterruptedException {
		
		try  {
			sendUserNotification(this.root, this.ishtMdb);
			sendApproverNotification(this.root, this.ishtMdb);
			
		}
		catch (Exception ex) {
			logger.error("Exception while mailProcessing "+ex);
		}

		//creating the PDF Receipt ---

	}
	
	
	  private boolean sendUserNotification(RootMDB root, IshtMDB isht ){
			
		  logger.info("Email to sent :" +root.getEmail());
			SendEmail sendEmail = new SendEmail();
			
			StringBuffer  content_html= new StringBuffer();
			content_html.append("Hope this will find you in good health and spirited mind by the divine grace of our Love Lord Sree Sree Thakur !!.<br> <br>")
			
			 .append("Thank you for your istarghy submisssion. Your istarghya has been successfully submitted to Satsang America Istarghay Deposit System (SAIDS) with following details . <br> <br>")
			//.append("Date of Payment :"+ getIshtService().formatDate(isht.getCollectedOn()))
			.append("Date of Submission :"+isht.getCollectedOn().substring(0,10))
			.append("<br> <br>")
			.append("Ishtavrity Submission Ref# :"+ isht.getReceiptNo()) 
			.append("<br> <br>")
			.append("Ishtavrity Amount :$"+ isht.getTotal()) 
			
			.append("<br> <br>")
			.append("Jayguru and Regards <br>")
			.append("Istavrity Team <br>")
			.append("</p> <p style=\"font-family:sans-serif;  color: grey; font-weight:normal; font-size: 12px;\">")
			.append("Satsang America, Inc.<br>") 
			.append("14 W District Rd<br>") 
			.append("Unionville, CT 06085<br>") 
			.append("Ph: 317-480-3184<br>") 
			.append("Visit Us www.SatsangAmerica.org <br>") ;
			
			SendEmail SendingProgram = new SendEmail();						
			SendingProgram.setRecipient(root.getEmail());
			SendingProgram.setSender("istabhrity@gmail.com");
			SendingProgram.setSubject("SA-TEST: Submission of Ishtabhrity Notification");
			String MailbodyContent="<p style=\"font-family:sans-serif;  color:blue; font-weight: bold; font-size: 14px;\"> Ishtapraneshu Admin Jayguru, <br><br>"+content_html.toString();
			SendingProgram.setContent(MailbodyContent); //setting the boy contennt.
		    
			try {
				SendingProgram.sendHtmlEmail();
			} catch (Exception e) {
				logger.error("Exception occure while sending User Notification"+e.getMessage());
			}//sendEmailInviation(); //Caling program to send the Email 
			logger.info("Approval Email Sent to ");
			
			return true;
		}
		
		
		private boolean sendApproverNotification(RootMDB root, IshtMDB isht ){
			
			
			StringBuffer  content_html= new StringBuffer();
			content_html.append("Hope this will find you in good health and spirited mind by the divine grace of our Love Lord Sree Sree Thakur !!.<br> <br>")
			//.append("There are few istabhrity transactions have been submitted and waiting for Admin approval. Please approve the submmitted transactions to get created and sent Arghya Praswasti and . <br> <br>")
			.append("There are few istabhrity transactions have been submitted and waiting for Admin approval. Please approve the submmitted transactions to get Arghya Praswasti created and sent to the user submitted Arghya. <br> <br>")
		    .append("Please click ") 
		    .append(" <a href='http://www.satsangamerica.com/ishtTrnDet.jsp'> Here </a>. <br> <br>")
			.append("Jayguru and Regards <br>")
			.append("Istavrity Team <br>")
			.append("</p> <p style=\"font-family:sans-serif;  color: grey; font-weight:normal; font-size: 12px;\">")
			.append("Satsang America, Inc.<br>") 
			.append("14 W District Rd<br>") 
			.append("Unionville, CT 06085<br>") 
			.append("Ph: 317-480-3184<br>") 
			.append("Visit Us www.SatsangAmerica.org <br>") ;
			
			SendEmail SendingProgram = new SendEmail();						
			SendingProgram.setRecipient(OnlineSAConstants.APPROVER_1);
			//SendingProgram.setRecipient(OnlineSAConstants.APPROVER_1 +","+OnlineSAConstants.APPROVER_2+","+OnlineSAConstants.APPROVER_3);
			SendingProgram.setSender("istabhrity@gmail.com");
			SendingProgram.setSubject("SA-TEST: Istabhrity Transactons Approval Required.");
			String MailbodyContent="<p style=\"font-family:sans-serif;  color:blue; font-weight: bold; font-size: 14px;\"> Ishtapraneshu Admin Jayguru, <br><br>"+content_html.toString();
			SendingProgram.setContent(MailbodyContent); //setting the boy contennt.
		    
			try {
				SendingProgram.sendHtmlEmail();
			} catch (Exception e) {
				logger.error("exception occure while Approval Email Sent to "+ e.getMessage());
			}//sendEmailInviation(); 
			logger.info("Approval Email Sent to ");
			return true;
		}
		

}


