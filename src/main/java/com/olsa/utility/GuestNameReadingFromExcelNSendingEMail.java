package com.olsa.utility;

import org.apache.log4j.Logger;
 
/**
 * A dirty simple program that reads an Excel file.
 * @author Shyam Giri
 *
 */
public class GuestNameReadingFromExcelNSendingEMail {
	static final Logger logger = Logger.getLogger(GuestNameReadingFromExcelNSendingEMail.class);
	public static void main(String[] args) throws Exception {

		try {

			StringBuffer  content_html= new StringBuffer();
			
			 content_html.append("With joy and love we cordially invite you to to be part of the Holy 130th birth Anniversary celebration of our Supreme Beloved Sree Sree Thakur with your family, friends and relatives.  <br> <br>")
			.append("Your gracious presence on this auspicious day will be highly solicited.<br> <br>")
			.append(" Please find the attached invitation with event schedule. <br> <br>") 
			.append("</p> <p style=\"font-family: Arial, sans-serif;  color: black; font-weight: bold; font-size: 14px;\">")
			.append("When   :  Sept 16 2017 , Saturday , Starting at 6.00 AM till 8.30 PM. <br> <br>") 
			.append("Where   : Satya Narayan Mandir <br>")
			.append("75-15 Woodside Ave, Jackson Heights, NY 11373 <br> ")
			.append("</p><p style=\"font-family: Verdana, sans-serif;  color: blue; font-weight: bold; font-size: 12px;\">")
			//.append("Jayguru and Regards <br>")
			.append("Ever servant of the Lord <br>")
			.append("Ghana Shyam Giri (SPR) <br>")
			.append("On Behalf of Satsang America <br>")
			.append("Utsav Committee</p> <br>");

			 	SendEmail GuestEmail = new SendEmail();
			
				String GuestName="Dhananjay Bhishikar", GuestEmailId="dbhishikar@gmail.com";

				GuestEmail.setRecipient(GuestEmailId);
				GuestEmail.setSender("dbhishikar@gmail.com");
				GuestEmail.setSubject("Gentle Reminder, Annual Utsav 2017 Invitation");
				String MailbodyContent="<p style=\"font-family: Verdana, sans-serif;  color:blue; font-weight: bold; font-size: 12px;\">"+ GuestName +",<br><br>"+content_html.toString();

				GuestEmail.setContent(MailbodyContent); //setting the boy contennt.

				GuestEmail.emailHtml();//sendEmailInviation(); //Caling program to send the Email 

				logger.info("Email Sent to : "+GuestEmailId+" Guest Name :   "+GuestName);
			
			}
		catch(Exception e) {
			logger.info("Exception Occurred : "+e);
		}
		logger.info("All Email Sent Successfully");	
	}
 
}
