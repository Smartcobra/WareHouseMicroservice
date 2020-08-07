package in.jit.view;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;

import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.jit.model.ShipmentType;

public class ShipmentTypePDFView extends AbstractPdfView {

	@Override
	protected void buildPdfMetadata(Map<String, Object> model, 
			Document document, HttpServletRequest request)  {
		
		HeaderFooter header = new HeaderFooter(new Phrase("SHIPMENT TYPE REPORT"), false);
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
		//image.setBorderWidth(5.0f);
		//image.enableBorderSide(Rectangle.BOTTOM + Rectangle.TOP + Rectangle.LEFT + Rectangle.RIGHT);

		document.add(image);
		 response.addHeader("Content-Disposition","attachment;filename=shipments.pdf");

		//Font font = new Font(Font.HELVETICA, 20, Font.BOLD, Color.DARK_GRAY);

		///Paragraph p = new Paragraph("J Company", font);
		//p.setAlignment(Element.ALIGN_LEFT);

		//document.add(p);

		@SuppressWarnings("unchecked")
		List<ShipmentType> list = (List<ShipmentType>) model.get("list");

		PdfPTable table = new PdfPTable(6);
		table.setSpacingBefore(4.0f);
		table.setTotalWidth(new float[] { 1.0f, 1.0f, 1.5f, 1.0f, 1.0f, 1.5f });

		table.addCell("ID");
		table.addCell("MODE");
		table.addCell("CODE");
		table.addCell("ENABLE");
		table.addCell("GRADE");
		table.addCell("DESCRIPTION");

		for (ShipmentType st : list) {
			table.addCell(st.getId().toString());
			table.addCell(st.getShipmentMode());
			table.addCell(st.getShipmentCode());
			table.addCell(st.getEnableShipment());
			table.addCell(st.getShipmentGrade());
			table.addCell(st.getDescription());
		}

		document.add(table);
	}

}
