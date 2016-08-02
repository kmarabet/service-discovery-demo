package com.altoros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home page controller.
 * 
 * @author kmarabet
 */
@Controller
public class HomeController {

	@Autowired
	ApplicationInfo appInfo;

	@RequestMapping("/")
	public String home(Model model) {

		model.addAttribute("appInfo", appInfo);
		return "index";
	}

	@RequestMapping("/test")
	public String test(Model model) {

		model.addAttribute("appInfo", appInfo);
//		return "Micro-service (Account-service) is available on port: "+port;
		return "test";
	}

}
