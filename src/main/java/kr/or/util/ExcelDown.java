package kr.or.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExcelDown {
	private String fileName;
	private List<String> fileList;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public List<String> getFileList() {
		return fileList;
	}
	public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}
	
	public void excelDown(HttpServletResponse response, Map<String, Object> fileMap, String fileType) throws IOException {
		Workbook workbook = null; // 새로운 엑셀파일 만들기
		
		FileOutputStream fileOutputStream = null;
		
		if(fileType.equals("xls")) {
			workbook = new HSSFWorkbook();
			fileOutputStream = new FileOutputStream("c:/shop/"+fileMap.get("fileName")+".xls");
		} else if(fileType.equals("xlsx")) {
			workbook = new XSSFWorkbook();
			fileOutputStream = new FileOutputStream("c:/shop/"+fileMap.get("fileName")+".xlsx");
		}
		
		Sheet sheet = workbook.createSheet(); // 엑셀에서 새 시트 만들기
		Row row; // 엑셀의 행은 0부터 시작
		Cell cell; // 엑셀의 셀은 0부터 시작

		row = sheet.createRow(0);
		cell = row.createCell(0);
		cell.setCellValue("주문번호");
		cell = row.createCell(1);
		cell.setCellValue("고객번호");
		cell = row.createCell(2);
		cell.setCellValue("고객명");
		cell = row.createCell(3);
		cell.setCellValue("상품번호");
		cell = row.createCell(4);
		cell.setCellValue("상품명");
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<Object> totalList = objectMapper.convertValue(fileMap.get("totalArray"), new TypeReference<List<Object>>() {});
		
		int rowNum = 1;
		int cellNum = 0;
		row = sheet.createRow(rowNum);
		for(int i=0; i<totalList.size(); i++) {
			cell = row.createCell(cellNum++);
			cell.setCellValue(totalList.get(i).toString());
			
			if(cellNum == 5) {
				++rowNum;
				row = sheet.createRow(rowNum);
				cellNum = 0;
			}
		}
		workbook.write(fileOutputStream);
		fileOutputStream.close();
	}
}
