package com.olsa.utility;

import org.apache.log4j.Logger;

import com.olsa.pojo.IshtMDB;
import com.olsa.pojo.RootMDB;

public class TaskThread extends Thread {
	static final Logger logger = Logger.getLogger(TaskThread.class);
	String receiptNo;
	IshtMDB ishtMdb;
	RootMDB root;

	public TaskThread(String name) {
		super(name);
	}

	public TaskThread(String receiptNo, IshtMDB ishtMdb, RootMDB root) {
		this.receiptNo=receiptNo;
		this.ishtMdb=ishtMdb;
		this.root=root;
	}

	@Override
	public void run() {
		logger.info("MyThread - START "+Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
			//Get database connection, delete unused data from DB
			receiptProcessing();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("MyThread - END "+Thread.currentThread().getName());
	}

	private void receiptProcessing() throws InterruptedException {

		//Thread.sleep(5000);
		CreateNSendArghyaPraswasti createAP = new CreateNSendArghyaPraswasti();
		//working fine
		//createAP.sendEmailPdfAttachment(receiptNo, ishtMdb, root);  
		try  {
			createAP.sendArghyaPraswastiPDFAttachmentInEmail(this.receiptNo, this.ishtMdb, this.root);
			// throw new Exception ("DataObject retrival failed to create ArghyaPraswasti.");
		}
		catch (Exception ex) {
			logger.error("Exception while receiptProcessing "+ex);
		}
	}

}


