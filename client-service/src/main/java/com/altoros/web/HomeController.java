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

	@Value("${eureka.client.enabled}")
	Boolean eurekaEnabled;
	@Value("${eureka.client.serviceUrl.defaultZone}")
	String eurekaUrl;

	@Value("${spring.cloud.consul.enabled}")
	Boolean consulEnabled;
	@Value("${spring.cloud.consul.host}")
	String consulHost;
	@Value("${spring.cloud.consul.port}")
	String consulPort;

	@RequestMapping("/")
	public String home(Model model) {

		model.addAttribute("eurekaEnabled", eurekaEnabled);
		model.addAttribute("eurekaUrl", eurekaUrl);
		model.addAttribute("eurekaUrlApps", eurekaUrl+"/eureka/apps");

		model.addAttribute("consulEnabled", consulEnabled);
		model.addAttribute("consulUrl", "http://"+consulHost+":"+consulPort);

		return "index";
	}

}
