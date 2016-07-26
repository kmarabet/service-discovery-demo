package com.altoros;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroServiceApp implements CommandLineRunner, EnvironmentAware {

	//protected Logger logger = Logger.getLogger(MicroServiceApp.class.getName());
	@Value("${spring.application.name}")
	String applicationName;

	private String cfApplicationName;
	private String cfApplicationUris;
	@Override
	public void setEnvironment(Environment environment) {
		this.cfApplicationName = environment.getProperty("vcap.application.application_name");
		this.cfApplicationUris = environment.getProperty("vcap.application.application_uris");
	}

	@Override
	public void run(String... args) throws Exception {
		//THE System.err is used to highlight the output (in red in CF)
		System.err.println("**************************************************************************");
		System.err.println("*** spring.application.name: "+ applicationName);
		if (cfApplicationName != null){
			System.err.println("*** vcap.application.application_name: "+ cfApplicationName);
			System.err.println("*** vcap.application.application_uris: "+ cfApplicationUris);
		}
		System.err.println("**************************************************************************");
	}

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for micro-server.properties or
		// micro-server.yml
		//System.setProperty("spring.config.name", "micro-server");

		SpringApplication.run(MicroServiceApp.class, args);
		/*new SpringApplicationBuilder(MicroServiceApp.class)
				.web(false)
				.run(args);*/
	}
}

@RestController
class HelloController {

	@Value("${server.port}")
	private int port = 0;

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}


	@RequestMapping("/test")
	public String test() {
		return "Simple-micro-service is available on port: "+port ;
	}

}
