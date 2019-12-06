package kr.or.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ShopController {
	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String shop(Model model, HttpServletRequest request) throws IOException {
		logger.info("shop controller");

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate
				.getForEntity("http://localhost:8080/api/orders?auth=" + request.getParameter("auth"), String.class);
		String str = responseEntity.getBody();

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			List<Object> totalList = objectMapper.readValue(str, new TypeReference<List<Object>>() {});
			model.addAttribute("totalList", totalList);
		} catch (NullPointerException e) {
			System.out.println("주문정보가 없습니다.");
		}

		return "shop/orders";
	}

	@RequestMapping(value = "/excelDown", method = RequestMethod.POST)
	/* @RequestBody String[] totalArray */
	public String excelDown(Model model, HttpServletRequest request, @RequestBody String fileName) throws IOException {
		System.out.println(fileName);
		HSSFWorkbook workbook = new HSSFWorkbook(); // 새로운 엑셀파일 만들기
		HSSFSheet sheet = workbook.createSheet(); // 엑셀에서 새 시트 만들기
		HSSFRow row; // 엑셀의 행은 0부터 시작
		HSSFCell cell; // 엑셀의 셀은 0부터 시작

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
		
//		int rowNum = 1;
//		int cellNum = 0;
//		row = sheet.createRow(rowNum);
//		for(int i=0; i<totalArray.length; i++) {
//			cell = row.createCell(cellNum++);
//			cell.setCellValue(totalArray[i]);
//			
//			if(cellNum == 5) {
//				++rowNum;
//				row = sheet.createRow(rowNum);
//				cellNum = 0;
//			}
//		}
		
		FileOutputStream fileOutputStream = new FileOutputStream("c:/shop/orders.xls");
		workbook.write(fileOutputStream);
		fileOutputStream.close();

		return "shop/orders";
	}
}