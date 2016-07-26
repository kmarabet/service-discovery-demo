package com.altoros;

import com.altoros.web.WebMicroController;
import com.altoros.web.WebMicroService;
import com.altoros.web.HomeController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * The web-server works as a microservice client, fetching data from the Micro-Service.
 * Uses the Discovery Server (Eureka) to find the microservice.
 * 
 * @author kmarabet
 */
@SpringBootApplication
@EnableDiscoveryClient
// Disable component scanner in order to instantiate
// the annotated @Controller WebMicroController and @Service WebMicroService manually...
@ComponentScan(useDefaultFilters = false)
public class WebServerApp {

	/**
	 * URL uses the logical name of micro-service - upper or lower case, doesn't matter.
	 */
	//public static final String MICRO_SERVICE_URL = "http://MICRO-SERVICE";
	//public static final String MICRO_SERVICE_URL = "MICRO-SERVICE";
	@Value("${micro_service_url}")
	private String MICRO_SERVICE_URL;

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for web-server.properties or web-server.yml
		//System.setProperty("spring.config.name", "web-server");
		//MICRO_SERVICE_URL = System.getProperty("micro.service.url");
		//System.err.println("MICRO_SERVICE_URL: "+MICRO_SERVICE_URL);
		SpringApplication.run(WebServerApp.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * The WebMicroService encapsulates the interaction with the micro-service.
	 * 
	 * @return A new service instance.
	 */
	@Bean
	public WebMicroService microService() {
		System.err.println("MICRO_SERVICE_URL: "+ MICRO_SERVICE_URL);
		return new WebMicroService(MICRO_SERVICE_URL);
	}

	/**
	 * Create the controller, passing it the {@link WebMicroService} to use.
	 * 
	 * @return
	 */
	@Bean
	public WebMicroController microController() {
		return new WebMicroController(microService());
	}

	@Bean
	public HomeController homeController() {
		return new HomeController();
	}

}
