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

import in.jit.model.OrderMethod;

public class OrderMethodPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		HeaderFooter header = new HeaderFooter(new Phrase("ORDER METHOD  REPORT"), false);
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
		Paragraph p = new Paragraph("MAJHI WareHouse ORDER METHOD", fontTitle);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);

		@SuppressWarnings("unchecked")
		List<OrderMethod> list = (List<OrderMethod>) model.get("list");

		PdfPTable table = new PdfPTable(6);
		table.setSpacingBefore(10.0f);
		table.setTotalWidth(new float[] { 1.0f, 1.0f, 1.5f, 1.0f, 2.0f, 2.0f });

		Font fontHead = new Font(Font.HELVETICA, 12, Font.BOLD, Color.BLUE);
		Font fontBody = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, Color.DARK_GRAY);

		table.addCell(new Phrase("ID", fontHead));
		table.addCell(new Phrase("MODE", fontHead));
		table.addCell(new Phrase("CODE", fontHead));
		table.addCell(new Phrase("TYPE", fontHead));
		table.addCell(new Phrase("ACCEPT", fontHead));
		table.addCell(new Phrase("DESCRIPTION", fontHead));

		for (OrderMethod om : list) {
			table.addCell(new Phrase(om.getId().toString(), fontBody));
			table.addCell(om.getOrderMode());
			table.addCell(om.getOrderCode());
			table.addCell(om.getOrderType());
			table.addCell(om.getOrderAcpt().toString());
			table.addCell(om.getDescription());
		}

		document.add(table);

		document.add(new Phrase());

	}

}
