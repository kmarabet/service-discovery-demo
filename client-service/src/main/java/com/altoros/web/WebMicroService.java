package com.altoros.web;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Hide the access to the micro-service inside this local service.
 * 
 * @author kmarabet
 */
@Service
public class WebMicroService {

	protected Logger logger = Logger.getLogger(WebMicroService.class.getName());

	@Autowired
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	public WebMicroService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}

	/**
	 * The RestTemplate works because it uses a custom request-factory that uses
	 * Ribbon to look-up the service to use. This method simply exists to show
	 * this.
	 */
	@PostConstruct
	public void demoOnly() {
		// Can't do this in the constructor because the RestTemplate injection
		// happens afterwards.
		logger.warning("The RestTemplate request factory is "
				+ restTemplate.getRequestFactory());
	}

	public String testServiceAvailability() {

		logger.info("Test service availability...");
		return restTemplate.getForObject(serviceUrl+ "/test", String.class);
	}

}
