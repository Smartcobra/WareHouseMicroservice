package in.jit.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import in.jit.model.WhUserType;


public class WhUserTypeExcelView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.addHeader("Content-Disposition", "attachment;filename=WHUSERTYPE.xls");

		@SuppressWarnings("unchecked")
		List<WhUserType> list = (List<WhUserType>) model.get("obs");

		Sheet sheet = workbook.createSheet("WHUSERTYPE");

		sethead(sheet);
		setBody(sheet, list);

	}

	private void sethead(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("TYPE");
		row.createCell(2).setCellValue("CODE");
		row.createCell(3).setCellValue("USER FOR");
		row.createCell(4).setCellValue("EMAIL");
		row.createCell(5).setCellValue("CONTACT");
		row.createCell(6).setCellValue("ID TYPE");
		row.createCell(7).setCellValue("IF OTHER");
		row.createCell(8).setCellValue("ID NUBER");

	}

	private void setBody(Sheet sheet, List<WhUserType> list) {
		
		int rowNum=1;
		for(WhUserType user:list) {
			Row row=sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(user.getId());
			row.createCell(1).setCellValue(user.getUserType());
			row.createCell(2).setCellValue(user.getUserCode());
			row.createCell(3).setCellValue(user.getUserFor());
			row.createCell(4).setCellValue(user.getUserMail());
			row.createCell(5).setCellValue(user.getUserContact());
			row.createCell(6).setCellValue(user.getUserIdType());
			row.createCell(7).setCellValue(user.getIfOther());
			row.createCell(8).setCellValue(user.getIdNumber());
		}

	}


	

}
