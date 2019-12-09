package kr.or.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.or.util.ExcelDown;

@Controller
public class ShopController {
	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String shop(Model model, HttpServletRequest request) throws IOException {
		logger.info("shop controller");

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/orders?auth=" + request.getParameter("auth"), String.class);
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
	public String excelDown(Model model, HttpServletRequest request, @RequestBody Map<String, Object> fileMap) throws IOException {
		logger.info("excelDown controller");
		
		ExcelDown excelDown = new ExcelDown();
		excelDown.xlsExcelDown(fileMap);
		
		return "shop/orders";
	}
	
	@RequestMapping(value = "/excelDown2", method = RequestMethod.POST)
	public String excelDown2(Model model, HttpServletRequest request, @RequestBody Map<String, Object> fileMap) throws IOException {
		logger.info("excelDown2 controller");
		
		ExcelDown excelDown = new ExcelDown();
		excelDown.xlsxExcelDown(fileMap);

		return "shop/orders";
	}
}