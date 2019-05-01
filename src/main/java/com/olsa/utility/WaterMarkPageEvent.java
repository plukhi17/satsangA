/**
 * 
 */
package com.olsa.utility;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author parthl
 *
 */
public class WaterMarkPageEvent implements PdfPageEvent  {

	/* (non-Javadoc)
	 * @see com.itextpdf.text.pdf.PdfPageEvent#onOpenDocument(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
	 */
	@Override
	public void onOpenDocument(PdfWriter writer, Document document) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.itextpdf.text.pdf.PdfPageEvent#onStartPage(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
	 */
	@Override
	public void onStartPage(PdfWriter writer, Document document) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.itextpdf.text.pdf.PdfPageEvent#onEndPage(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
	 */
	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		ColumnText.showTextAligned(writer.getDirectContentUnder(),
                Element.ALIGN_CENTER, new Phrase("Memorynotfound.com"),
                297.5f, 421, writer.getPageNumber() % 2 == 1 ? 45 : -45);

	}

	/* (non-Javadoc)
	 * @see com.itextpdf.text.pdf.PdfPageEvent#onCloseDocument(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
	 */
	@Override
	public void onCloseDocument(PdfWriter writer, Document document) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.itextpdf.text.pdf.PdfPageEvent#onParagraph(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document, float)
	 */
	@Override
	public void onParagraph(PdfWriter writer, Document document, float paragraphPosition) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.itextpdf.text.pdf.PdfPageEvent#onParagraphEnd(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document, float)
	 */
	@Override
	public void onParagraphEnd(PdfWriter writer, Document document, float paragraphPosition) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.itextpdf.text.pdf.PdfPageEvent#onChapter(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document, float, com.itextpdf.text.Paragraph)
	 */
	@Override
	public void onChapter(PdfWriter writer, Document document, float paragraphPosition, Paragraph title) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.itextpdf.text.pdf.PdfPageEvent#onChapterEnd(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document, float)
	 */
	@Override
	public void onChapterEnd(PdfWriter writer, Document document, float paragraphPosition) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.itextpdf.text.pdf.PdfPageEvent#onSection(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document, float, int, com.itextpdf.text.Paragraph)
	 */
	@Override
	public void onSection(PdfWriter writer, Document document, float paragraphPosition, int depth, Paragraph title) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.itextpdf.text.pdf.PdfPageEvent#onSectionEnd(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document, float)
	 */
	@Override
	public void onSectionEnd(PdfWriter writer, Document document, float paragraphPosition) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.itextpdf.text.pdf.PdfPageEvent#onGenericTag(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document, com.itextpdf.text.Rectangle, java.lang.String)
	 */
	@Override
	public void onGenericTag(PdfWriter writer, Document document, Rectangle rect, String text) {
		// TODO Auto-generated method stub

	}

}
