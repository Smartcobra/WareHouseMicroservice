package in.jit.view;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.jit.model.WhUserType;

public class WhUserTypePDFView extends AbstractPdfView {

	@Override
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {

		HeaderFooter header = new HeaderFooter(new Phrase("WH User TYPE REPORT"), false);
		HeaderFooter footer = new HeaderFooter(
				new Phrase("Copyrights @ JituIT | " + new Phrase(LocalDateTime.now().toString() + " |   PAGE NUMBER#")),
				new Phrase("."));

		header.setAlignment(1); // 0 Left,1 Middle, 2Right
		header.setBorder(Rectangle.BOTTOM);

		footer.setAlignment(2);
		footer.setBorder(Rectangle.TOP);

		document.setHeader(header);
		document.setFooter(footer);
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Image image = Image.getInstance(new ClassPathResource("/static/images/logo.png").getURL());
		image.setAlignment(Image.RIGHT);
		image.scaleAbsolute(40.0f, 40.0f);
		// image.setBorderWidth(5.0f);
		// image.enableBorderSide(Rectangle.BOTTOM + Rectangle.TOP + Rectangle.LEFT +
		// Rectangle.RIGHT);

		document.add(image);
		response.addHeader("Content-Disposition", "attachment;filename=WhUserType.pdf");

		Font fonth = new Font(Font.HELVETICA,8);
		//fonth.setColor(BaseColor.WHITE);
		Font fontb = new Font(Font.NORMAL,7);

		/// Paragraph p = new Paragraph("J Company", font);
		// p.setAlignment(Element.ALIGN_LEFT);

		// document.add(p);

		@SuppressWarnings("unchecked")
		List<WhUserType> list = (List<WhUserType>) model.get("list");

		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100.0f);
		table.setSpacingBefore(6.0f);
		table.setTotalWidth(new float[] { 1.0f, 2.0f, 1.5f, 2.5f, 3.5f, 2.5f, 2.5f, 2.5f, 2.0f });

		 PdfPCell cell = new PdfPCell();
	        cell.setPadding(5);
		
		cell.setPhrase(new Phrase("ID", fonth));
		table.addCell(cell);
		cell.setPhrase(new Phrase("TYPE", fonth));
		table.addCell(cell);
		cell.setPhrase(new Phrase("CODE", fonth));
		table.addCell(cell);
		cell.setPhrase(new Phrase("USER FOR", fonth));
		table.addCell(cell);
		cell.setPhrase(new Phrase("EMAIL", fonth));
		table.addCell(cell);
		cell.setPhrase(new Phrase("CONTACT", fonth));
		table.addCell(cell);
		cell.setPhrase(new Phrase("ID TYPE", fonth));
		table.addCell(cell);
		cell.setPhrase(new Phrase("IF OTHER", fonth));
		table.addCell(cell);
		cell.setPhrase(new Phrase("ID NUMBER", fonth));
		table.addCell(cell);

		for (WhUserType user : list) {
			table.addCell(new Phrase(user.getId().toString(),fontb));
			table.addCell(new Phrase(user.getUserType(),fontb));
			table.addCell(new Phrase(user.getUserCode(),fontb));
			table.addCell(new Phrase(user.getUserFor(),fontb));
			table.addCell(new Phrase(user.getUserMail(),fontb));
			table.addCell(new Phrase(user.getUserContact(),fontb));
			table.addCell(new Phrase(user.getUserIdType(),fontb));
			table.addCell(new Phrase(user.getUserContact(),fontb));
			table.addCell(new Phrase(user.getIdNumber(),fontb));
		}

		document.add(table);
	}

}
