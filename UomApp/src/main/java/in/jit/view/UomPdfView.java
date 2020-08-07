package in.jit.view;

import java.awt.Color;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import in.jit.model.Uom;

public class UomPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		HeaderFooter header = new HeaderFooter(new Phrase("UOM REPORT"), false);
		HeaderFooter footer = new HeaderFooter(
				new Phrase("Copyrights @ JituIT | " + new Phrase(LocalDateTime.now().toString() + " |   PAGE NUMBER#")),
				new Phrase("."));

		header.setAlignment(1);
		header.setBorder(Rectangle.BOTTOM);

		footer.setAlignment(1);
		footer.setBorder(Rectangle.TOP);

		document.setHeader(header);
		document.setFooter(footer);

	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Image image = Image.getInstance(new ClassPathResource("/static/images/logo.png").getURL());
		image.setAlignment(Image.MIDDLE);
		image.scaleAbsolute(300.0f, 80.0f);
		image.setBorderWidth(5.0f);
		image.enableBorderSide(Rectangle.BOTTOM + Rectangle.TOP + Rectangle.LEFT + Rectangle.RIGHT);

		document.add(image);

		Font fontTitle = new Font(Font.HELVETICA, 25, Font.BOLD, new Color(222, 161, 20));
		Paragraph p = new Paragraph("MAJHI WareHouse UOM DETAILS", fontTitle);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);

		@SuppressWarnings("unchecked")
		List<Uom> list = (List<Uom>) model.get("list");
		
		response.setHeader("Content-Disposition", "attachment;filename=Uom.pdf");

		PdfPTable table = new PdfPTable(4);
		table.setSpacingBefore(10.0f);
		table.setTotalWidth(new float[] { 1.0f, 2.5f, 3.0f, 3.0f});
    
		Font fontHead = new Font(Font.HELVETICA, 12, Font.BOLD, Color.BLUE);
		Font fontBody = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, Color.DARK_GRAY);

		table.addCell(new Phrase("ID", fontHead));
		table.addCell(new Phrase("TYPE", fontHead));
		table.addCell(new Phrase("MODEL", fontHead));
		table.addCell(new Phrase("DESCRIPTION", fontHead));

		for (Uom uom : list) {
			table.addCell(new Phrase(uom.getId().toString(), fontBody));
			table.addCell(uom.getUomType());
			table.addCell(uom.getUomModel());
			table.addCell(uom.getDescription());
		}

		document.add(table);

		document.add(new Phrase());

	}

}
