package com.altoros.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.logging.Logger;

/**
 * @author kmarabet
 */
@Controller
public class WebMicroController {

	protected Logger logger = Logger.getLogger(WebMicroController.class.getName());

	@Autowired
	protected WebMicroService microService;

	public WebMicroController(WebMicroService microService) {
		this.microService = microService;
	}

	/*@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("accountNumber", "searchText");
	}*/

	@RequestMapping("/test-sd")
	public String testServiceAvailability(Model model) {

		model.addAttribute("message", microService.testServiceAvailability());

		return "text";
	}

}
