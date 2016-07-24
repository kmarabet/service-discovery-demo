package com.altoros.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home page controller.
 * 
 * @author kmarabet
 */
@Controller
@EnableDiscoveryClient
public class HomeController {

	@Value("${eureka.client.serviceUrl.defaultZone}")
	String eurekaUrl;

	@RequestMapping("/")
	public String home(Model model) {

		model.addAttribute("eurekaUrl", eurekaUrl);

		return "index";
	}
}
