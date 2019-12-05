package kr.or.controller;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ShopController {
	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String shop(Model model) throws IOException {
		logger.info("shop controller");

		String id = "testId";
		String pw = "testPw";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/orders?id="+id+"&pw="+pw, String.class);
		String str = responseEntity.getBody();
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<Object> totalList = objectMapper.readValue(str, new TypeReference<List<Object>>(){});
		
		model.addAttribute("totalList", totalList);
		
		return "shop/orders";
	}
	
}