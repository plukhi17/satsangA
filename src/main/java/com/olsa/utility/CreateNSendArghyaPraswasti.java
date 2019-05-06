package com.olsa.utility;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

import org.apache.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.olsa.pojo.AddressMDB;
import com.olsa.pojo.IshtLineMDB;
import com.olsa.pojo.IshtMDB;
import com.olsa.pojo.ResultObject;
import com.olsa.pojo.RootMDB;
import com.olsa.services.UserServiceImpl;
import com.olsa.utility.OnlineSAConstants;

import java.net.URL;

public class CreateNSendArghyaPraswasti {
	static final Logger logger = Logger.getLogger(CreateNSendArghyaPraswasti.class);
	private Session session;
	private Transport trans;
	private MimeMessage msg;

	public void buildArghyaPraswasti(ByteArrayOutputStream outputStream, String input_receipt_no, IshtMDB ishtMDB,
			RootMDB root) throws Exception, DocumentException {

		float llx = 16;
		float lly = 580;
		float urx = 580;
		float ury = 826;
		String donorName = "GHANA SHYAM GIRI";
		String MethodOfPayment = "Check";
		String monthYear = "", fileName = "", cheque_Date = "";
		float donorAmount = 000.00f;

		DecimalFormat df2 = new DecimalFormat(".##");

		try {

			monthYear = "FEB_2018"; // its hard coded
			fileName = "ARGHYA_PRASWASTI_" + ishtMDB.getFamilyID() + "_" + "" + monthYear + ".pdf";
			String str_SA_FAMILY_CODE = ishtMDB.getFamilyID();

			String str_FIRST_NAME = root.getFirstName();
			String str_LAST_NAME = root.getLastName();
			String str_MIDDLE_NAME = root.getMiddleName();
			String str_EMAIL_ID = root.getEmail();

			AddressMDB address = root.getAddress();

			String str_ADDRESS1 = address.getAddressLine1();
			String str_ADDRESS2 = address.getAddressLine2();
			String str_CITY = address.getCity();
			String str_STATE = address.getState();
			// String str_PROVINCE = null;
			String str_COUNTRY = address.getCountry();
			Long str_ZIP_CODE = address.getZipCode();
			String str_PRIMARY_PHONE = root.getPhoneNo();

			String str_COLLECTED_ON = ishtMDB.getCollectedOn();
			String str_CHEQUE_DATE = ishtMDB.getChecqDate().toString();

			// Dynamic PDF creation
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, outputStream);
			writer.setPageEvent(new WaterMarkPageEvent());
			document.open();

			// Static PDF File creation
			// Document document = new Document();
			// PdfWriter writer = PdfWriter.getInstance(document, new
			// FileOutputStream(fileName));

			LineSeparator line = new LineSeparator();
			document.open();
			PdfContentByte cb = writer.getDirectContent();
			Rectangle rect = new Rectangle(25, 200, 580, 830); // perfect working
			rect.setBorder(Rectangle.BOX);
			rect.setBorderWidth(1);
			cb.rectangle(rect);
			PdfPTable table = new PdfPTable(6); // 6
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.setWidthPercentage(100);
			table.setTotalWidth(540); // working fine
			// table.setTotalWidth(780);
			table.setLockedWidth(true);
			table.setWidths(new float[] { 0.5f, 1, 1, 1, 1, 1 }); // 6

			// table.setWidths(new float[]{1.5f,1,1,1,1,1,1}); //7

			// 0th Row

			Phrase first_line = new Phrase(new Chunk("ARGHYA-PRASWASTI",
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Font.NORMAL, BaseColor.BLUE)));
			PdfPCell newcell = new PdfPCell();
			newcell.setBorder(PdfPCell.NO_BORDER);
			newcell.setVerticalAlignment(Element.ALIGN_TOP);
			newcell.setNoWrap(true);
			newcell.addElement(first_line);
			table.addCell(newcell); // 1

			newcell = null;
			newcell = new PdfPCell();
			newcell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(newcell); // 2

			newcell = new PdfPCell();
			newcell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(newcell); // 3

			Phrase ph = new Phrase(new Chunk("Your spontaneous, volitional, adherence-inducing, ardent unexpectant",
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Font.NORMAL, BaseColor.BLUE)));
			Phrase ph1 = new Phrase(new Chunk("devotional offerings delight Sree Sree Thakur, the Supreme Love.",
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Font.NORMAL, BaseColor.BLUE)));
			newcell = new PdfPCell();
			newcell.setBorder(PdfPCell.NO_BORDER);
			newcell.setVerticalAlignment(Element.ALIGN_TOP);
			newcell.setNoWrap(true);
			newcell.addElement(ph);
			newcell.addElement(ph1);
			table.addCell(newcell); // 5

			newcell = new PdfPCell();
			newcell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(newcell); // 6

			newcell = new PdfPCell();
			newcell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(newcell); // 4

			// 1st Row

			ph = null;
			ph1 = null;
			ph = new Phrase(
					new Chunk("   ", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Font.NORMAL, BaseColor.BLUE)));
			// ph1 = new Phrase(new Chunk(" ",
			// FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Font.NORMAL,
			// BaseColor.BLUE)));
			newcell = new PdfPCell();
			newcell.setBorder(PdfPCell.NO_BORDER);
			newcell.setVerticalAlignment(Element.ALIGN_TOP);
			newcell.setNoWrap(true);
			newcell.addElement(ph);
			// newcell.addElement(ph1);
			table.addCell(newcell); // 5

			newcell = new PdfPCell();
			newcell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(newcell); // 1

			newcell = new PdfPCell();
			newcell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(newcell); // 1

			newcell = new PdfPCell();
			newcell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(newcell); // 1

			newcell = new PdfPCell();
			newcell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(newcell); // 1

			newcell = new PdfPCell();
			newcell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(newcell); // 1

			// second row Row

			ph = null;
			ph1 = null;
			ph = new Phrase(
					new Chunk("   ", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Font.NORMAL, BaseColor.BLUE)));
			newcell = new PdfPCell();
			newcell.setBorder(PdfPCell.NO_BORDER);
			newcell.setVerticalAlignment(Element.ALIGN_TOP);
			newcell.setNoWrap(true);
			newcell.addElement(ph);
			table.addCell(newcell); // 1

			newcell = new PdfPCell();
			newcell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(newcell); // 2

			newcell = new PdfPCell();
			newcell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(newcell); // 3

			newcell = new PdfPCell();
			newcell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(newcell); // 4

			newcell = new PdfPCell();
			newcell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(newcell); // 5

			Paragraph para = new Paragraph(input_receipt_no);
			para.setFont(FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, BaseColor.DARK_GRAY));
			newcell = getCell(para, Element.ALIGN_TOP);
			// cell.setColspan(2);
			newcell.setNoWrap(true);
			newcell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(newcell); // 6

			// third Row

			String imageUrl =OnlineSAConstants.IMAGE_URL+"image.jpeg";
			Image image = Image.getInstance(new URL(imageUrl));
			imageUrl = "";

			// Image image = Image.getInstance("images/image.jpeg"); ///images/image.jpeg
			// Image image = Image.getInstance("./WEB-INF/images/image.jpeg");
			// Image image = Image.getInstance("src/main/webapp/images/image.jpeg"); //this
			// works in local
			
		
			PdfPCell cell = new PdfPCell(Image.getInstance(image), true);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell); // 1

			// FontSelector selector1 = new FontSelector();
			// Font f1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD , 9);
			// f1.setColor(BaseColor.DARK_GRAY);
			// selector1.addFont(f1);
			// Phrase ph = selector1.process("SATSANG AMERICA");

			ph = null;
			ph1 = null; // ph2=null; ph3=null; newcell=null;

			ph = new Phrase(new Chunk("SATSANG AMERICA",
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, Font.NORMAL, BaseColor.DARK_GRAY)));
			ph1 = new Phrase(new Chunk("111-17 Sutphin Blvd",
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6, Font.NORMAL, BaseColor.DARK_GRAY)));
			Phrase ph2 = new Phrase(new Chunk("Jamaica, NY 11435",
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6, Font.NORMAL, BaseColor.DARK_GRAY)));
			Phrase ph3 = new Phrase(new Chunk("EIN:46-5341000",
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6, Font.NORMAL, BaseColor.DARK_GRAY)));
			// Phrase ph4 = new Phrase(new Chunk(" ",
			// FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Font.NORMAL,
			// BaseColor.DARK_GRAY)));
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setNoWrap(true);
			cell.addElement(ph);
			// cell.addElement(ph4); //Space
			cell.addElement(ph1);
			cell.addElement(ph2);
			cell.addElement(ph3);
			table.addCell(cell); // 2

			cell = null;
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell); // 3

			ph = null;
			ph1 = null;
			ph2 = null;
			ph3 = null;
			cell = null;

			ph = new Phrase(new Chunk(str_FIRST_NAME + " " + str_MIDDLE_NAME + " " + str_LAST_NAME,
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Font.NORMAL, BaseColor.DARK_GRAY)));
			ph1 = new Phrase(new Chunk(str_ADDRESS1 + " " + str_ADDRESS2 + ",",
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7, Font.NORMAL, BaseColor.DARK_GRAY)));
			ph2 = new Phrase(new Chunk(str_CITY + " , " + str_STATE + " " + str_ZIP_CODE + " , " + str_COUNTRY,
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7, Font.NORMAL, BaseColor.DARK_GRAY)));
			ph3 = new Phrase(new Chunk("Email: " + str_EMAIL_ID,
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7, Font.NORMAL, BaseColor.DARK_GRAY)));
			Phrase ph4 = new Phrase(new Chunk("Cell : " + str_PRIMARY_PHONE,
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7, Font.NORMAL, BaseColor.DARK_GRAY)));

			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setNoWrap(true);
			cell.addElement(ph);
			cell.addElement(ph1);
			cell.addElement(ph2);
			cell.addElement(ph3);
			cell.addElement(ph4);
			table.addCell(cell); // 4

			ph = null;
			ph1 = null;
			ph2 = null;
			ph3 = null;
			cell = null;
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell); // 5

			ph = null;
			ph1 = null;
			ph2 = null;
			ph3 = null;
			cell = null;
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell); // 6

			/*
			 * 
			 * //Paragraph para = new Paragraph("Receipt No : SA/2016/0000001"); Paragraph
			 * para = new Paragraph(input_receipt_no);
			 * para.setFont(FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL,
			 * BaseColor.DARK_GRAY)); cell=getCell(para, Element.ALIGN_TOP);
			 * //cell.setColspan(2); cell.setNoWrap(true);
			 * cell.setBorder(PdfPCell.NO_BORDER); table.addCell(cell); //6
			 */
			/*****/

			// 2nd Row Blank Row

			cell = null;
			para = null;
			line = null;
			Chunk chunk0 = new Chunk("  ");
			chunk0.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, BaseColor.DARK_GRAY));
			// chunk1.setUnderline(0.3f, -3.5f);
			// chunk1.setUnderline(1.5f, 3.5f);
			line = new LineSeparator();
			line.setPercentage(100);
			line.setLineWidth(0.5f);
			line.setOffset(-18);
			cell = new PdfPCell();
			// cell.addElement(line);
			cell.addElement(chunk0);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setColspan(7);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table.addCell(cell); // 1

			// 3rd Row Blank Row

			cell = null;
			para = null;
			line = null;
			chunk0 = new Chunk("  ");
			chunk0.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, BaseColor.DARK_GRAY));
			// chunk1.setUnderline(0.3f, -3.5f);
			// chunk1.setUnderline(1.5f, 3.5f);
			line = new LineSeparator();
			line.setPercentage(100);
			line.setLineWidth(0.5f);
			line.setOffset(-18);
			cell = new PdfPCell();
			// cell.addElement(line);
			cell.addElement(chunk0);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setColspan(7);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table.addCell(cell); // 1

			/*****/

			// Second Row

			/*
			 * ph=null; ph1=null; ph2=null; ph3=null; cell=null;para=null;
			 * 
			 * para = new Paragraph(" ");
			 * para.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL,
			 * BaseColor.DARK_GRAY)); cell=getCell(para, Element.ALIGN_BOTTOM);
			 * //cell.setColspan(4); cell.setBorder(PdfPCell.NO_BORDER);
			 * cell.setNoWrap(true); table.addCell(cell); // 1
			 */

			ph = null;
			ph1 = null;
			ph2 = null;
			ph3 = null;
			cell = null;

			ph = new Phrase(new Chunk("www.SatsangAmerica.org",
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Font.NORMAL, BaseColor.DARK_GRAY)));
			ph1 = new Phrase(new Chunk("Phone: 317 480 3184",
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6, Font.NORMAL, BaseColor.DARK_GRAY)));
			ph2 = new Phrase(new Chunk("Email: istabhrity@gmail.com",
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6, Font.NORMAL, BaseColor.DARK_GRAY)));
			// ph3 = new Phrase(new Chunk("EIN:46-5341000",
			// FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL,
			// BaseColor.DARK_GRAY)));
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setNoWrap(true);
			cell.addElement(ph);
			cell.addElement(ph1);
			cell.addElement(ph2);
			table.addCell(cell); // 4

			cell = null;
			para = null;

			para = new Paragraph(" ");
			para.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, BaseColor.DARK_GRAY));
			cell = getCell(para, Element.ALIGN_BOTTOM);
			cell.setBorder(PdfPCell.NO_BORDER);
			cell.setNoWrap(true);
			table.addCell(cell); // 2

			cell = null;
			para = null;

			para = new Paragraph(" ");
			para.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, BaseColor.DARK_GRAY));
			cell = getCell(para, Element.ALIGN_BOTTOM);
			cell.setBorder(PdfPCell.NO_BORDER);
			cell.setNoWrap(true);
			table.addCell(cell); // 3

			cell = null;
			para = null;

			para = new Paragraph(" ");
			para.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, BaseColor.DARK_GRAY));
			cell = getCell(para, Element.ALIGN_BOTTOM);
			cell.setBorder(PdfPCell.NO_BORDER);
			cell.setNoWrap(true);
			table.addCell(cell); // 4

			cell = null;
			para = null;

			para = new Paragraph(" ");
			para.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, BaseColor.DARK_GRAY));
			cell = getCell(para, Element.ALIGN_BOTTOM);
			cell.setBorder(PdfPCell.NO_BORDER);
			cell.setNoWrap(true);
			table.addCell(cell); // 5

			cell = null;
			para = null;

			Date date = new Date();
			Chunk chunk1 = new Chunk("Date : " + convertDate(date, "MMM dd, yyyy"));
			chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, BaseColor.DARK_GRAY));
			chunk1.setUnderline(0.3f, -3.5f);
			// chunk1.setUnderline(1.5f, 3.5f);
			cell = new PdfPCell();
			cell.addElement(chunk1);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table.addCell(cell); // 6

			/*
			 * //Thrid Row
			 * 
			 * ph=null; ph1=null; ph2=null; ph3=null; cell=null; para=null; chunk1=null;
			 * line=null;
			 * 
			 * para = new Paragraph("Received from");
			 * para.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL,
			 * BaseColor.DARK_GRAY)); line = new LineSeparator(); //line.setPercentage(100);
			 * line.setLineWidth(0.5f); line.setOffset(-5); cell=getCell(para,
			 * Element.ALIGN_BOTTOM); cell.addElement(line); cell.setColspan(2);
			 * cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			 * cell.setBorder(PdfPCell.NO_BORDER); //cell.setNoWrap(true);
			 * table.addCell(cell); // 1
			 * 
			 * 
			 * chunk1 = new Chunk("      "
			 * +donorName+"                                                     Amount US $ "
			 * +df2.format(donorAmount));
			 * chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,
			 * BaseColor.DARK_GRAY)); //chunk1.setUnderline(0.3f, -3.5f);
			 * //chunk1.setUnderline(1.5f, 3.5f); line = new LineSeparator();
			 * line.setPercentage(100); line.setLineWidth(0.5f); line.setOffset(-18); cell =
			 * new PdfPCell(); cell.addElement(line); cell.addElement(chunk1);
			 * cell.setVerticalAlignment(Element.ALIGN_TOP); cell.setColspan(4);
			 * cell.setBorder(PdfPCell.NO_BORDER); //cell.setNoWrap(true);
			 * table.addCell(cell); // 1
			 * 
			 * cell=null;para=null;line=null;
			 * 
			 * 
			 * //Thrid Row System.out.println("Amount passed to Convert : "+donorAmount);
			 * System.out.println("Amount passed by  formating : "+df2.format(donorAmount));
			 */

			/*
			 * cell=null;para=null;line=null;
			 * 
			 * chunk1 = new Chunk("                                              "
			 * +wordAmount+"               Towards Satsang Centre.        ");
			 * chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL,
			 * BaseColor.DARK_GRAY)); //chunk1.setUnderline(0.3f, -3.5f);
			 * //chunk1.setUnderline(1.5f, 3.5f); line = new LineSeparator();
			 * line.setPercentage(100); line.setLineWidth(0.5f); line.setOffset(-18); cell =
			 * new PdfPCell(); cell.addElement(line); cell.addElement(chunk1);
			 * cell.setVerticalAlignment(Element.ALIGN_TOP); cell.setColspan(7);
			 * cell.setBorder(PdfPCell.NO_BORDER); //cell.setNoWrap(true);
			 * table.addCell(cell); // 1
			 * 
			 * //Fourth Row
			 * 
			 * cell=null;para=null;line=null;
			 * 
			 * chunk1 = new
			 * Chunk("                        Method of Payment : "+MethodOfPayment);
			 * chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL,
			 * BaseColor.DARK_GRAY)); //chunk1.setUnderline(0.3f, -3.5f);
			 * //chunk1.setUnderline(1.5f, 3.5f); line = new LineSeparator();
			 * line.setPercentage(100); line.setLineWidth(0.5f); line.setOffset(-18); cell =
			 * new PdfPCell(); cell.addElement(line); cell.addElement(chunk1);
			 * cell.setVerticalAlignment(Element.ALIGN_TOP); cell.setColspan(7);
			 * cell.setBorder(PdfPCell.NO_BORDER); //cell.setNoWrap(true);
			 * table.addCell(cell); // 1
			 * 
			 * 
			 * //5th FRow
			 * 
			 * cell=null;para=null;line=null;
			 * 
			 * chunk1 = new Chunk("    ");
			 * chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL,
			 * BaseColor.DARK_GRAY)); //chunk1.setUnderline(0.3f, -3.5f);
			 * //chunk1.setUnderline(1.5f, 3.5f); line = new LineSeparator();
			 * line.setPercentage(100); line.setLineWidth(0.5f); line.setOffset(-18); cell =
			 * new PdfPCell(); //cell.addElement(line); cell.addElement(chunk1);
			 * cell.setVerticalAlignment(Element.ALIGN_TOP); cell.setColspan(7);
			 * cell.setBorder(PdfPCell.NO_BORDER); //cell.setNoWrap(true);
			 * table.addCell(cell); // 1
			 * 
			 */

			// 6th Row Blank Row

			cell = null;
			para = null;
			line = null;

			chunk1 = new Chunk("  ");
			chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, BaseColor.DARK_GRAY));
			// chunk1.setUnderline(0.3f, -3.5f);
			// chunk1.setUnderline(1.5f, 3.5f);
			line = new LineSeparator();
			line.setPercentage(100);
			line.setLineWidth(0.5f);
			line.setOffset(-18);
			cell = new PdfPCell();
			// cell.addElement(line);
			cell.addElement(chunk1);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setColspan(7);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table.addCell(cell); // 1

			/*
			 * Istavrity Details table will go here ...
			 */

			PdfPTable table1 = new PdfPTable(13);

			table1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.setWidthPercentage(100);
			table1.setTotalWidth(540);
			table1.setLockedWidth(true);
			// table1.setWidths(new float[]{0.5f,2,2,1,1,1,0.7f,0.8f,0.7f,1,0.7f,1,1});

			table1.setWidths(new float[] { 0.5f, 2, 2, 1, 1, 1, 0.7f, 0.9f, 0.7f, 1, 0.7f, 0.6f, 1 });

			// Swastyayani Istavrity Acharyavrity Dakshina Sangathani Pranami "Swastyayani
			// Surplus Amt" Retwiki TOTAL

			// 1st row
			ph = null;
			cell = null;
			ph = new Phrase(new Chunk("SL No",
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7, Font.NORMAL, BaseColor.BLACK)));
			cell = new PdfPCell();
			// cell.setBorder(PdfPCell.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setNoWrap(true);
			cell.addElement(ph);
			table1.addCell(cell); // 1

			ph = null;
			ph1 = null;
			cell = null;
			ph = new Phrase(new Chunk("Name/*Ritwik",
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7, Font.NORMAL, BaseColor.BLACK)));
			// ph1 = new Phrase(new Chunk("Ritwik's Name",
			// FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7, Font.NORMAL,
			// BaseColor.BLACK)));
			cell = new PdfPCell();
			// cell.setBorder(PdfPCell.NO_BORDER);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setNoWrap(true);
			cell.addElement(ph);
			table1.addCell(cell); // 2

			/*
			 * ph=null; cell=null; ph = new Phrase(new Chunk("Ritwik's Name",
			 * FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7, Font.NORMAL,
			 * BaseColor.BLACK))); cell = new PdfPCell();
			 * //cell.setBorder(PdfPCell.NO_BORDER);
			 * cell.setVerticalAlignment(Element.ALIGN_TOP); cell.setNoWrap(true);
			 * cell.addElement(ph); table1.addCell(cell); // 3
			 */

			ph = null;
			cell = null;
			ph = new Phrase(new Chunk("Swastyayani",
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
			cell = new PdfPCell();
			// cell.setBorder(PdfPCell.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setNoWrap(true);
			cell.addElement(ph);
			table1.addCell(cell); // 4

			ph = null;
			cell = null;
			ph = new Phrase(new Chunk("Istavrity",
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
			cell = new PdfPCell();
			// cell.setBorder(PdfPCell.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setNoWrap(true);
			cell.addElement(ph);
			table1.addCell(cell); // 5

			ph = null;
			cell = null;
			ph = new Phrase(new Chunk("Acharyavrity",
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
			cell = new PdfPCell();
			// cell.setBorder(PdfPCell.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setNoWrap(true);
			cell.addElement(ph);
			table1.addCell(cell); // 6

			ph = null;
			cell = null;
			ph = new Phrase(new Chunk("Dakshina",
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
			cell = new PdfPCell();
			// cell.setBorder(PdfPCell.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setNoWrap(true);
			cell.addElement(ph);
			table1.addCell(cell); // 7

			ph = null;
			cell = null;
			ph = new Phrase(new Chunk("Sangathani",
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
			cell = new PdfPCell();
			// cell.setBorder(PdfPCell.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setNoWrap(true);
			cell.addElement(ph);
			table1.addCell(cell); // 8

			ph = null;
			cell = null;
			ph = new Phrase(new Chunk("Pranami",
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
			cell = new PdfPCell();
			// cell.setBorder(PdfPCell.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setNoWrap(true);
			cell.addElement(ph);
			table1.addCell(cell); // 9

			ph = null;
			cell = null;
			ph = new Phrase(new Chunk("Swastyayani Surplus Amt",
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
			cell = new PdfPCell();
			// cell.setBorder(PdfPCell.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			// cell.setNoWrap(true);
			cell.addElement(ph);
			table1.addCell(cell); // 10

			ph = null;
			cell = null;
			ph = new Phrase(
					new Chunk("Parivrity", FontFactory.getFont(FontFactory.TIMES, 7, Font.NORMAL, BaseColor.BLACK)));
			cell = new PdfPCell();
			// cell.setBorder(PdfPCell.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setNoWrap(true);
			cell.addElement(ph);
			table1.addCell(cell); // 11

			ph = null;
			cell = null;
			ph = new Phrase(
					new Chunk("Ritwiki", FontFactory.getFont(FontFactory.TIMES, 7, Font.NORMAL, BaseColor.BLACK)));
			cell = new PdfPCell();
			// cell.setBorder(PdfPCell.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setNoWrap(true);
			cell.addElement(ph);
			table1.addCell(cell); // 12

			ph = null;
			cell = null;
			ph = new Phrase(
					new Chunk("TOTAL", FontFactory.getFont(FontFactory.TIMES, 7, Font.NORMAL, BaseColor.BLACK)));
			cell = new PdfPCell();
			// cell.setBorder(PdfPCell.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setNoWrap(true);
			cell.addElement(ph);
			table1.addCell(cell); // 13

			double ROW_TOTAL = 0, ROWISE_GRAND_TOTAL = 0;
			double GRAND_TOTAL = 0;
			int counter = 0;

			List<IshtLineMDB> ishtlineMdblist = ishtMDB.getLine();

			logger.info("Size of the ishtlineMdblist.size() " + ishtlineMdblist.size());

			for (int i = 0; i < ishtlineMdblist.size(); i++) {

				IshtLineMDB lineMdb = ishtlineMdblist.get(i);

				double SWASTYAYANI_AMT = lineMdb.getSwastyayani();
				double ISTAVRITY_AMT = lineMdb.getIstavrity();
				double ACHARYAVRITY_AMT = lineMdb.getAcharyavrity();
				double DAKSHINA_AMT = lineMdb.getDakshina();
				double SANGATHANI_AMT = lineMdb.getSangathani();
				double PRONAMI_AMT = lineMdb.getPronami();
				double SWASTYAYANI_SURPLUS_AMT = lineMdb.getSurplus();
				double RITWIKI_AMT = lineMdb.getRitwiki();
				double PARIVRITY_AMT = lineMdb.getParivrity();
				double TOTAL_AMT = lineMdb.getTotal();

				String FIRST_NAME = lineMdb.getName();
				String RITWIK_NAME = root.getRitvikName();
				// String MIDDLE_NAME = root.getMiddleName();
				String EMAIL_ID = root.getEmail();

				str_COLLECTED_ON = ishtMDB.getCollectedOn();
				String COLLECTED_BY = ishtMDB.getCollectedBy();
				String PAYMENT_METHOD = ishtMDB.getPaymentMethod();
				String CHEQUE_ISSUE_BANK = ishtMDB.getChequeIssueBank();
				String CHEQUE_NO = ishtMDB.getChecqNo();

				String CHEQUE_DATE = ishtMDB.getChecqDate().toString();
				cheque_Date = ishtMDB.getChecqDate().toString();

				String ARGHYA_PRASWASTI_ISSUE = ishtMDB.getIssuedFlag();
				String ARGHYA_PRASWASTI_NO = ishtMDB.getReceiptNo();
				String ARGHYA_PRASWASTI_ISSUE_DATE = ishtMDB.getReceiptDate();

				// Details Transaction Table goes here
				counter = counter + 1;

				// SL No
				ph = null;
				ph1 = null;
				cell = null;
				ph = new Phrase(new Chunk("" + counter,
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
				// ph1 = new Phrase(new Chunk("*Ritwik's Name"+i,
				// FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7, Font.NORMAL,
				// BaseColor.BLACK)));
				cell = new PdfPCell();
				// cell.setBorder(PdfPCell.NO_BORDER);
				cell.setVerticalAlignment(Element.ALIGN_TOP);
				// cell.setColspan(2);
				cell.setNoWrap(true);
				cell.addElement(ph);
				cell.addElement(ph1);
				table1.addCell(cell); // 1

				ph = null;
				ph1 = null;
				cell = null;
				String Member_Name = "Not Available";
				if ((null == lineMdb.getName()) || (lineMdb.getName().isEmpty())) {

				} else {
					Member_Name = lineMdb.getName(); // FIRST_NAME +" "+ MIDDLE_NAME+" "+LAST_NAME;
				}

				String Ritwik_Name = "Not Available";
				String Complete_Ritwik_Name = "";
				if ((null == lineMdb.getRitwik()) || (lineMdb.getRitwik().isEmpty())) {

				} else {
					Ritwik_Name = lineMdb.getRitwik();// ""+ RITWIK_STATUS+" "+ RITWIK_FIRST_NAME+"
														// "+RITWIK_MIDDLE_NAME+" "+RITWIK_LAST_NAME;
					logger.info("Ritwik Available " + Ritwik_Name);
					Complete_Ritwik_Name = "*" + Ritwik_Name;
				}

				ph = new Phrase(new Chunk(Member_Name,
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
				ph1 = new Phrase(new Chunk(Complete_Ritwik_Name,
						FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7, Font.NORMAL, BaseColor.BLACK)));
				cell = new PdfPCell();
				// cell.setBorder(PdfPCell.NO_BORDER);
				cell.setVerticalAlignment(Element.ALIGN_TOP);
				cell.setColspan(2);
				cell.setNoWrap(true);
				cell.addElement(ph);
				cell.addElement(ph1);
				table1.addCell(cell); // 2

				if (String.valueOf(SWASTYAYANI_AMT).equals("0.0")) {

					ph = new Phrase("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 4
				} else {
					ph = null;
					cell = null;
					// String strAmt = String.format("%.2f", GRAND_TOTAL);
					ph = new Phrase(new Chunk("" + String.format("%.2f", SWASTYAYANI_AMT),
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 4
				}

				if (String.valueOf(ISTAVRITY_AMT).equals("0.0")) {

					ph = null;
					cell = null;
					ph = new Phrase(new Chunk("",
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 5
				} else {
					ph = null;
					cell = null;
					ph = new Phrase(new Chunk("" + String.format("%.2f", ISTAVRITY_AMT),
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 5

				}

				if (String.valueOf(ACHARYAVRITY_AMT).equals("0.0")) {
					ph = null;
					cell = null;
					ph = new Phrase(new Chunk("",
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 6
				} else {
					ph = null;
					cell = null;
					ph = new Phrase(new Chunk("" + String.format("%.2f", ACHARYAVRITY_AMT),
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 6
				}

				if (String.valueOf(DAKSHINA_AMT).equals("0.0")) {
					ph = null;
					cell = null;
					ph = new Phrase(new Chunk("",
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 7
				} else {
					ph = null;
					cell = null;
					ph = new Phrase(new Chunk("" + String.format("%.2f", DAKSHINA_AMT),
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 7
				}

				if (String.valueOf(SANGATHANI_AMT).equals("0.0")) {

					ph = null;
					cell = null;
					ph = new Phrase(new Chunk("",
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 8
				} else {
					ph = null;
					cell = null;
					ph = new Phrase(new Chunk("" + String.format("%.2f", SANGATHANI_AMT),
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 8
				}
				if (String.valueOf(PRONAMI_AMT).equals("0.0")) {
					ph = null;
					cell = null;
					ph = new Phrase(new Chunk("",
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 9
				} else {
					ph = null;
					cell = null;
					ph = new Phrase(new Chunk("" + String.format("%.2f", PRONAMI_AMT),
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 9
				}
				if (String.valueOf(SWASTYAYANI_SURPLUS_AMT).equals("0.0")) {
					ph = null;
					cell = null;
					ph = new Phrase(new Chunk("",
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 10
				} else {
					ph = null;
					cell = null;
					ph = new Phrase(new Chunk("" + String.format("%.2f", SWASTYAYANI_SURPLUS_AMT),
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 10
				}

				if (String.valueOf(PARIVRITY_AMT).equals("0.0")) {
					ph = null;
					cell = null;
					ph = new Phrase(new Chunk("",
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 10
				} else {
					ph = null;
					cell = null;
					ph = new Phrase(new Chunk("" + String.format("%.2f", PARIVRITY_AMT),
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 11
				}

				if (String.valueOf(RITWIKI_AMT).equals("0.0")) {
					ph = null;
					cell = null;
					ph = new Phrase(new Chunk("",
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 11
				} else {
					ph = null;
					cell = null;
					ph = new Phrase(new Chunk("" + String.format("%.2f", RITWIKI_AMT),
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 12
				}

				ROW_TOTAL = ISTAVRITY_AMT + SWASTYAYANI_AMT + ACHARYAVRITY_AMT + DAKSHINA_AMT + SANGATHANI_AMT
						+ PRONAMI_AMT + SWASTYAYANI_SURPLUS_AMT + PARIVRITY_AMT + RITWIKI_AMT;

				if (String.valueOf(ROW_TOTAL).equals("0.0")) {
					ph = null;
					cell = null;
					ph = new Phrase(new Chunk("",
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 13
				} else {
					ph = null;
					cell = null;
					ph = new Phrase(new Chunk("" + String.format("%.2f", ROW_TOTAL),
							FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK)));
					cell = new PdfPCell();
					// cell.setBorder(PdfPCell.NO_BORDER);
					cell.setVerticalAlignment(Element.ALIGN_TOP);
					cell.setNoWrap(true);
					cell.addElement(ph);
					table1.addCell(cell); // 13
				}

				// Details Transaction Table goes here

				// Display values
				/*
				 * System.out.println("ISTAVRITY_AMT: " + ISTAVRITY_AMT);
				 * System.out.println("SWASTYAYANI_AMT: " + SWASTYAYANI_AMT);
				 * System.out.println("ACHARYAVRITY_AMT: " + ACHARYAVRITY_AMT);
				 * System.out.println(" DAKSHINA_AMT: " + DAKSHINA_AMT);
				 * 
				 * System.out.println("COLLECTED_ON: " + COLLECTED_ON);
				 * System.out.println("COLLECTED_BY: " + COLLECTED_BY);
				 * System.out.println("ARGHYA_PRASWASTI_ISSUE_DATE: " +
				 * ARGHYA_PRASWASTI_ISSUE_DATE); System.out.println(" DAKSHINA_AMT: " +
				 * DAKSHINA_AMT);
				 */

				GRAND_TOTAL = GRAND_TOTAL + TOTAL_AMT;

				ROWISE_GRAND_TOTAL = ROWISE_GRAND_TOTAL + ROW_TOTAL;

			} // for loop

			// STEP 6: Clean-up environment

			logger.info(" ROWISE_GRAND_TOTAL: " + ROWISE_GRAND_TOTAL);

			logger.info(" GRAND_TOTAL: " + GRAND_TOTAL);

			// donorAmount =(float) GRAND_TOTAL;

			// String wordAmount = ConvertMoneyToWords(df2.format(GRAND_TOTAL));

			String wordAmount = ConvertMoneyToWords(String.format("%.2f", ROWISE_GRAND_TOTAL));

			// String wordAmount = ConvertMoneyToWords(String.format("%.2f",GRAND_TOTAL));

			logger.info("GRAND_TOTAL : " + GRAND_TOTAL);

			/*
			 * Istavrity Details table will go here ...
			 */

			/*
			 * Again Upper table Goes here ...
			 * 
			 */

			PdfPTable table2 = new PdfPTable(6); // 6
			table2.setHorizontalAlignment(Element.ALIGN_LEFT);
			table2.setWidthPercentage(100);
			table2.setTotalWidth(540); // working fine
			table2.setLockedWidth(true);
			table2.setWidths(new float[] { 0.5f, 1, 1, 1, 1, 1 }); // 6

			// Last Row
			cell = null;
			para = null;
			line = null;
			chunk1 = null;

			chunk1 = new Chunk("");
			chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, BaseColor.DARK_GRAY));
			// chunk1.setUnderline(0.3f, -3.5f);
			// chunk1.setUnderline(1.5f, 3.5f);
			// line = new LineSeparator();
			// line.setPercentage(100);
			// line.setLineWidth(0.5f);
			// line.setOffset(-18);
			cell = new PdfPCell();
			// cell.addElement(line);
			cell.addElement(chunk1);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setColspan(4);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table2.addCell(cell); // 1

			cell = null;
			para = null;
			line = null;
			chunk1 = null;

			para = new Paragraph("Received By      ");
			para.setFont(FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, BaseColor.DARK_GRAY));
			// image = Image.getInstance("src/main/webapp/images/MySignature.jpg"); this
			// works fine in local

			imageUrl = OnlineSAConstants.IMAGE_URL+"MySignature.jpg";
			image = Image.getInstance(new URL(imageUrl));
			imageUrl = "";

			// image = Image.getInstance("images/MySignature.jpg");
			image.setWidthPercentage(10);
			image.scalePercent(25);
			// image.scaleAbsolute(70f, 70f);
			para.add(new Chunk(image, 0, 0, true));
			// chunk1 = new Chunk("Received By ");
			// chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL,
			// BaseColor.DARK_GRAY));
			// chunk1.setUnderline(0.3f, -3.5f);
			// chunk1.setUnderline(1.5f, 3.5f);
			line = new LineSeparator();
			line.setPercentage(100);
			line.setLineWidth(0.5f);
			line.setOffset(-3);
			cell = new PdfPCell();
			// cell = new PdfPCell(Image.getInstance(image), true);
			cell.addElement(para);
			// cell.addElement(image);
			cell.addElement(line);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setColspan(2);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table2.addCell(cell); // 1

			cell = null;
			para = null;
			line = null;

			/*
			 * image = Image.getInstance("MySignature.jpg"); image.scaleAbsolute(20f, 20f);
			 * line = new LineSeparator(); line.setPercentage(100); line.setLineWidth(0.5f);
			 * line.setOffset(-28); cell = new PdfPCell(Image.getInstance(image), true);
			 * cell.addElement(line); cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			 * cell.setVerticalAlignment(Element.ALIGN_TOP);
			 * cell.setBorder(PdfPCell.NO_BORDER); table.addCell(cell); //1
			 */

			// Blank row
			/*
			 * cell=null;para=null;line=null;
			 * 
			 * chunk1 = new Chunk("  ");
			 * chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL,
			 * BaseColor.DARK_GRAY)); //chunk1.setUnderline(0.3f, -3.5f);
			 * //chunk1.setUnderline(1.5f, 3.5f); cell = new PdfPCell();
			 * //cell.addElement(line); cell.addElement(chunk1);
			 * cell.setVerticalAlignment(Element.ALIGN_TOP); cell.setColspan(7);
			 * cell.setBorder(PdfPCell.NO_BORDER); //cell.setNoWrap(true);
			 * table.addCell(cell); // 1
			 */

			// Thank you Row

			// Last Row

			cell = null;
			para = null;
			line = null;
			chunk1 = null;

			chunk1 = new Chunk("Family Code - " + str_SA_FAMILY_CODE);
			chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Font.NORMAL, BaseColor.DARK_GRAY));
			// chunk1.setUnderline(0.3f, -3.5f);
			// chunk1.setUnderline(1.5f, 3.5f);
			// line = new LineSeparator();
			// line.setPercentage(100);
			// line.setLineWidth(0.5f);
			// line.setOffset(-18);
			cell = new PdfPCell();
			// cell.addElement(line);
			cell.addElement(chunk1);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setColspan(2);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table2.addCell(cell); // 2

			cell = null;
			para = null;
			line = null;
			chunk1 = null;

			chunk1 = new Chunk(
					"Total Amount US$ " + String.format("%.2f", ROWISE_GRAND_TOTAL) + "  ( " + wordAmount + " ) ");
			chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6, Font.NORMAL, BaseColor.DARK_GRAY));
			// chunk1.setUnderline(0.3f, -3.5f);
			// chunk1.setUnderline(1.5f, 3.5f);
			// line = new LineSeparator();
			// line.setPercentage(100);
			// line.setLineWidth(0.5f);
			// line.setOffset(-18);
			cell = new PdfPCell();
			// cell.addElement(line);
			cell.addElement(chunk1);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setColspan(2);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table2.addCell(cell); // 2

			cell = null;
			para = null;
			line = null;
			chunk1 = null;
			// DateFormat formatter = null;
			// Date convertedDate = null;
			// 2016-11-10 11:21:57.0
			// .String date = "15-09-2011:23:30:45";
			// formatter =new SimpleDateFormat("yyyy-mm-dd:HH:mm:SS");
			// str_COLLECTED_ON
			// 2017-10-10T07:00:00.000Z

			// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddThh:mm:sssZ");
			logger.info("Before Coversion str_COLLECTED_ON : " + str_COLLECTED_ON);
			str_COLLECTED_ON = convertMongoDate(str_COLLECTED_ON);
			logger.info(" After conversion str_COLLECTED_ON : " + str_COLLECTED_ON);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date convertedDate = formatter.parse(str_COLLECTED_ON);
			logger.info("convertedDate String in Java : " + convertedDate.toString());

			str_CHEQUE_DATE = cheque_Date;

			if ((null == str_CHEQUE_DATE) || ("".equalsIgnoreCase(str_CHEQUE_DATE))) {
				str_CHEQUE_DATE = str_COLLECTED_ON;
			}
			
			Date dte= new Date();
			try {
				dte = new Date(str_CHEQUE_DATE);
			}catch(IllegalArgumentException i) {
				
				DateFormat frmt = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
				dte = (Date)frmt.parse(str_CHEQUE_DATE);
			}
			
			String formatteddate = formatter.format(dte);
			logger.info(" CHEQUE_DATE : " + str_CHEQUE_DATE);
			Date cvtd_cheque_Date = formatter.parse(formatteddate);
			logger.info("convertedDate cvtd_cheque_Date in Java : " + cvtd_cheque_Date.toString());

			// chunk1 = new Chunk("Date : "+convertDate(date, "MMM dd, yyyy"));
			chunk1 = new Chunk("Date : " + convertDate(cvtd_cheque_Date, "MMM dd, yyyy"));
			chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6, Font.NORMAL, BaseColor.DARK_GRAY));
			// chunk1.setUnderline(0.3f, -3.5f);
			// chunk1.setUnderline(1.5f, 3.5f);
			// line = new LineSeparator();
			// line.setPercentage(100);
			// line.setLineWidth(0.5f);
			// line.setOffset(-18);
			cell = new PdfPCell();
			// cell.addElement(line);
			cell.addElement(chunk1);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			// cell.setColspan(2);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table2.addCell(cell); // 2

			chunk1 = new Chunk("THANK YOU ");
			chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.DARK_GRAY));
			// chunk1.setUnderline(0.3f, -3.5f);
			// chunk1.setUnderline(1.5f, 3.5f);
			line = new LineSeparator();
			line.setPercentage(100);
			line.setLineWidth(0.5f);
			line.setOffset(-18);
			cell = new PdfPCell();
			// cell.addElement(line);
			cell.addElement(chunk1);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			// cell.setColspan(2);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table2.addCell(cell); // 1

			// SAPCE ROW

			cell = null;
			para = null;
			line = null;

			chunk1 = new Chunk(" ");
			chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.DARK_GRAY));
			// chunk1.setUnderline(0.3f, -3.5f);
			// chunk1.setUnderline(1.5f, 3.5f);
			line = new LineSeparator();
			line.setPercentage(100);
			line.setLineWidth(0.5f);
			line.setOffset(-25);
			cell = new PdfPCell();
			// cell.addElement(line);
			cell.addElement(chunk1);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setColspan(5);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table2.addCell(cell); // 1

			chunk1 = new Chunk(" ");
			chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.DARK_GRAY));
			// chunk1.setUnderline(0.3f, -3.5f);
			// chunk1.setUnderline(1.5f, 3.5f);
			line = new LineSeparator();
			line.setPercentage(100);
			line.setLineWidth(0.5f);
			line.setOffset(-18);
			cell = new PdfPCell();
			// cell.addElement(line);
			cell.addElement(chunk1);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			// cell.setColspan(2);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table2.addCell(cell); // 1

			// Las of last Row

			cell = null;
			para = null;
			line = null;

			chunk1 = new Chunk("");
			chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Font.NORMAL, BaseColor.DARK_GRAY));
			// chunk1.setUnderline(0.3f, -3.5f);
			// chunk1.setUnderline(1.5f, 3.5f);
			line = new LineSeparator();
			line.setPercentage(100);
			line.setLineWidth(0.5f);
			line.setOffset(-25);
			cell = new PdfPCell();
			// cell.addElement(line);
			cell.addElement(chunk1);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setColspan(5);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table2.addCell(cell); // 1

			chunk1 = new Chunk(" ");
			chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.DARK_GRAY));
			// chunk1.setUnderline(0.3f, -3.5f);
			// chunk1.setUnderline(1.5f, 3.5f);
			line = new LineSeparator();
			line.setPercentage(100);
			line.setLineWidth(0.5f);
			line.setOffset(-18);
			cell = new PdfPCell();
			// cell.addElement(line);
			cell.addElement(chunk1);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			// cell.setColspan(2);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table2.addCell(cell); // 1

			// Last Row

			cell = null;
			para = null;
			line = null;

			chunk1 = new Chunk(
					"N.B. In every remittance clearly mention your name, address,family code-number and all other names with details of offerings and Ritwik's name.  ");
			chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Font.NORMAL, BaseColor.DARK_GRAY));
			// chunk1.setUnderline(0.3f, -3.5f);
			// chunk1.setUnderline(1.5f, 3.5f);
			line = new LineSeparator();
			line.setPercentage(100);
			line.setLineWidth(0.5f);
			line.setOffset(-25);
			cell = new PdfPCell();
			// cell.addElement(line);
			cell.addElement(chunk1);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setColspan(6);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table2.addCell(cell); // 1

			// Scretary Signaure Row

			cell = null;
			para = null;
			line = null;
			chunk1 = null;

			chunk1 = new Chunk(" **Notice: No goods or services were provided in return for this gift.");
			chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Font.NORMAL, BaseColor.DARK_GRAY));
			cell = new PdfPCell();
			// cell.addElement(line);
			cell.addElement(chunk1);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setColspan(4);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table2.addCell(cell); // 1

			chunk1 = new Chunk("");
			chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Font.NORMAL, BaseColor.DARK_GRAY));
			cell = new PdfPCell();
			// cell.addElement(line);
			cell.addElement(chunk1);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			// cell.setColspan(4);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table2.addCell(cell); // 1

			cell = null;
			para = null;
			line = null;
			chunk1 = null;

			para = new Paragraph("   ");
			para.setFont(FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, BaseColor.DARK_GRAY));
			// image = Image.getInstance("src/main/webapp/images/Richi_Daa_Signature.jpg");

			imageUrl = OnlineSAConstants.IMAGE_URL+"Richi_Daa_Signature.jpg";
			image = Image.getInstance(new URL(imageUrl));
			imageUrl = "";
			// image = Image.getInstance("images/Richi_Daa_Signature.jpg");
			image.setWidthPercentage(30);
			image.scalePercent(45);
			// image.scaleAbsolute(70f, 70f);
			para.add(new Chunk(image, 0, 0, true));
			// chunk1 = new Chunk("Received By ");
			// chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL,
			// BaseColor.DARK_GRAY));
			// chunk1.setUnderline(0.3f, -3.5f);
			// chunk1.setUnderline(1.5f, 3.5f);
			line = new LineSeparator();
			line.setPercentage(100);
			line.setLineWidth(0.5f);
			line.setOffset(-3);
			cell = new PdfPCell();
			// cell = new PdfPCell(Image.getInstance(image), true);
			cell.addElement(para);
			// cell.addElement(image);
			cell.addElement(line);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			// cell.setColspan(2);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table2.addCell(cell); // 1

			// Scretary Row

			cell = null;
			para = null;
			line = null;
			chunk1 = null;

			chunk1 = new Chunk("");
			chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Font.NORMAL, BaseColor.DARK_GRAY));
			cell = new PdfPCell();
			// cell.addElement(line);
			cell.addElement(chunk1);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setColspan(4);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table2.addCell(cell); // 1

			chunk1 = new Chunk("");
			chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Font.NORMAL, BaseColor.DARK_GRAY));
			cell = new PdfPCell();
			// cell.addElement(line);
			cell.addElement(chunk1);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			// cell.setColspan(4);
			cell.setBorder(PdfPCell.NO_BORDER);
			// cell.setNoWrap(true);
			table2.addCell(cell); // 1

			chunk1 = new Chunk("Secretary, Satsang America");
			chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7, Font.NORMAL, BaseColor.DARK_GRAY));
			cell = new PdfPCell();
			// cell.addElement(line);
			cell.addElement(chunk1);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			// cell.setColspan(2);
			cell.setBorder(PdfPCell.NO_BORDER);
			cell.setNoWrap(true);
			table2.addCell(cell); // 1

			cell = null;
			para = null;
			line = null;
			chunk1 = null;

			/*
			 * chunk1 = new Chunk("Amount US $ "+df2.format(donorAmount));
			 * chunk1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL,
			 * BaseColor.DARK_GRAY)); //chunk1.setUnderline(0.3f, -3.5f);
			 * //chunk1.setUnderline(1.5f, 3.5f); line = new LineSeparator();
			 * line.setPercentage(100); line.setLineWidth(0.5f); line.setOffset(-18); cell =
			 * new PdfPCell(); cell.addElement(line); cell.addElement(chunk1);
			 * cell.setVerticalAlignment(Element.ALIGN_BOTTOM); cell.setColspan(2);
			 * cell.setBorder(PdfPCell.NO_BORDER); //cell.setNoWrap(true);
			 * table.addCell(cell); // 1
			 */

			document.add(table);

			document.add(table1);

			document.add(table2);

			cell = null;
			para = null;

			document.close();

			logger.info("Successfully  PDF Got Created !!!!");

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			logger.info("Exception while creating the  PDF :  " + se);
			throw new Exception("Exception while creating the  PDF ");
		} catch (Exception e) {
			// Handle errors for Class.forNameb
			e.printStackTrace();
			logger.info("Exception Occurred while  PDF being Got Created :" + e);
			throw new Exception("Exception Occurred while  PDF being Got Created");
		}

	}

	public void sendEmailPdfAttachment(String input_receipt_no, IshtMDB ishtMDB, RootMDB rootMdb) throws Exception {

		/****************** working fine *******************/

		String smtpHost = "javamail";
		String protocol = "smtp";

		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.store.protocol", protocol);

		session = Session.getDefaultInstance(props, null);
		trans = session.getTransport(protocol);
		msg = new MimeMessage(session);
		msg.setSentDate(new Date());

		String sender = "satsangbayareausa@gmail.com"; // "gs.gsgiri@gmail.com"; //replace this with a valid sender
														// email address
		// String recipient ="rprosad@gmail.com"; //"gs.gsgiri@gmail.com";
		// //"rprosad@gmail.com"; // //replace this with a valid recipient email address
		String recipient = "parthl@meditab.com";
		String GuestEmailId = recipient;
		String content = "Please find your attached Arghya Receipt "; // this will be the text of the email
		String subject = "Your Arghya Receipt"; // this will be the subject of the email

		/****************** working fine *******************/

		ByteArrayOutputStream outputStream = null;

		try {

			/****************** working fine *******************/

			// construct the text body part
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(content);

			// now write the PDF content to the output stream
			outputStream = new ByteArrayOutputStream();
			// writePdf(outputStream);
			buildArghyaPraswasti(outputStream, input_receipt_no, ishtMDB, rootMdb);
			byte[] bytes = outputStream.toByteArray();

			// construct the pdf body part
			DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
			MimeBodyPart pdfBodyPart = new MimeBodyPart();
			pdfBodyPart.setDataHandler(new DataHandler(dataSource));
			pdfBodyPart.setFileName("Arghya_Reciept.pdf");

			// construct the mime multi part
			MimeMultipart mimeMultipart = new MimeMultipart();
			mimeMultipart.addBodyPart(textBodyPart);
			mimeMultipart.addBodyPart(pdfBodyPart);

			// create the sender/recipient addresses
			InternetAddress iaSender = new InternetAddress(sender);
			InternetAddress iaRecipient = new InternetAddress(recipient);

			// construct the mime message
			// msg mimeMessage = new MimeMessage(session);
			msg.setFrom(new InternetAddress(sender));
			msg.setSender(iaSender);
			msg.setSubject(subject);
			msg.setRecipient(Message.RecipientType.TO, iaRecipient);
			msg.setContent(mimeMultipart);

			// send off the email
			trans.send(msg);

			logger.info("Successfully PDF !!! Sent from " + sender + ", to " + recipient + "; server = " + smtpHost
					+ ", port = " + protocol);

			/**************** working fine **********************/

		} catch (Exception e) {
			// Handle errors
			e.printStackTrace();
			logger.error(" Exception Occurred inside method send_Email_Arghya_Praswasti_PDF_Attachment : " + e);
		} finally {
			// clean off
			if (null != outputStream) {
				try {
					outputStream.close();
					outputStream = null;
				} catch (Exception ex) {
					logger.error("Exception in Email " + ex.getMessage());
				}
			}
		}
	}

	/*
	 * Sends and Html format email with PDF attachement.
	 * 
	 */

	public static void sendArghyaPraswastiPDFAttachmentInEmail(String input_receipt_no, IshtMDB ishtMDB,
			RootMDB rootMdb) throws Exception {

		try {

			StringBuffer content_html = new StringBuffer();
			// hard corded value need to replace
			String GuestEmailId = rootMdb.getEmail(), GuestName = rootMdb.getPseronalize(),
					month_year = ishtMDB.getMonthYear();

			// content_html.append("<p style=\"font-family:sans-serif; color: blue;
			// font-weight: bold; font-size: 12px;\"> Hello Shyam Bhai <br> ")
			// content_html.append("Please ignore today's earlier Arghya Praswasti as it was
			// sent with wrong data. Please find the corrected one, ! Apologized for the
			// inconvenience caused !!.<br> <br>");
			content_html.append(
					"Hope this will find you in good health and spirited mind by the divine grace of our Love Lord Sree Sree Thakur !!.<br> <br>")
					.append("Please find your attached Arghya Praswasti. <br> <br>").append("Jayguru and Regards <br>")
					.append("Istavrity Team <br>")
					.append("</p> <p style=\"font-family:sans-serif;  color: grey; font-weight:normal; font-size: 12px;\">")
					.append("Satsang America, Inc.<br>").append("111-17 Sutphin Blvd<br>")
					.append("Jamaica, NY 11435<br>").append("EIN:46-5341000<br>").append("Ph: 317-480-3184<br>")
					.append("Visit Us www.SatsangAmerica.org <br>").append("Email - "+OnlineSAConstants.EMAIL_ID +" <br>");

			/*
			 * Satsang America, Inc. 111-17 Sutphin Blvd Jamaica, NY 11435 EIN:46-5341000
			 * Phone: 317-480-3184 Visit Us www.SatsangAmerica.org Email -
			 * istabhrity@gmail.com
			 */

			if (null == GuestEmailId || GuestEmailId.equalsIgnoreCase("")) {

				logger.info("Email id Null or Empty !!");
			} else {

				SendEmail SendingProgram = new SendEmail();

				SendingProgram.setRecipient(GuestEmailId);
				//SendingProgram.setSender("istabhrity@gmail.com");
				SendingProgram.setSender(OnlineSAConstants.EMAIL_ID);
				SendingProgram.setSubject("SA-TEST: Arghya Praswasti " + month_year);

				String MailbodyContent = "<p style=\"font-family:sans-serif;  color:blue; font-weight: bold; font-size: 14px;\"> Ishtapraneshu "
						+ GuestName + " Jayguru, <br><br>" + content_html.toString();

				// String MailbodyContent=" <p style=\"font-family:sans-serif; color:blue;
				// font-weight: bold; font-size: 16px;\"> BANDE PURUSHOTTAM <br> <br> </p> <p
				// style=\"font-family:sans-serif; color:blue; font-weight: bold; font-size:
				// 14px;\">"+ GuestName +",<br><br>"+content_html.toString();
				SendingProgram.setContent(MailbodyContent); // setting the boy contennt.

				// Caling program to send the Email
				SendingProgram.sendHtmlEmailWithPDFAttachment(input_receipt_no, ishtMDB, rootMdb);

			}
		} catch (Exception e) {
			// Handle errors
			e.printStackTrace();
			logger.error(" Exception Occurred inside method sendArghyaPraswastiPDFAttachmentInEmail : " + e);
		}

	}

	public PdfPCell getCell(Paragraph p, int verticalAlignment) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(verticalAlignment);
		cell.setUseAscender(true);
		cell.setUseDescender(true);
		cell.addElement(p);
		return cell;
	}

	public String convertDate(Date d, String newFormat) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(newFormat);
		return sdf.format(d);
	}

	private static String convertMongoDate(String val) {
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String finalStr = outputFormat.format(inputFormat.parse(val));
			logger.info(finalStr);

			return finalStr;

		} catch (Exception e) {
			e.printStackTrace();

		}

		return "";
	}

	public String ConvertMoneyToWords(String amount) throws IOException, DocumentException {

		NumToWords numToWords = new NumToWords();
		String wordMoeny = "";
		String amt = amount;
		String str2 = "";
		String str1 = "";

		int rupees = Integer.parseInt(amt.split("\\.")[0]);
		str1 = numToWords.convert(rupees);
		str1 += " Dollars ";
		int paise = Integer.parseInt(amt.split("\\.")[1]);
		if (paise != 0) {
			str2 += " and ";
			str2 += numToWords.convert(paise);
			str2 += " Cents";
		}

		// wordMoeny =str1 + str2 + " Only";
		wordMoeny = str1 + str2 + "";

		logger.info("String 1 :" + str1);
		logger.info("String 2 :" + str2);
		logger.info(str1 + str2 + " Only");

		return wordMoeny;

	}

}
