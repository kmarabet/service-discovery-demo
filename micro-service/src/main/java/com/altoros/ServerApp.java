package com.altoros;

import com.altoros.config.CommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
//@Import(CommonConfig.class)
public class ServerApp {

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for accounts-server.properties or
		// accounts-server.yml
		//System.setProperty("spring.config.name", "accounts-server");

		SpringApplication.run(ServerApp.class, args);
		/*new SpringApplicationBuilder(ServerApp.class)
				.web(false)
				.run(args);*/
	}
}

@RestController
class HomeController {

	@Autowired
	ApplicationInfo appInfo;

	@RequestMapping("/")
	public String index() {
		//todo provide more detailed output using appInfo bean
		return "Greetings from Spring Boot! Running on port: "+appInfo.getPort();
	}

	@RequestMapping("/test")
	public String test() {

		return appInfo.getMessage();
	}

	@ResponseBody
	@RequestMapping(value = "/test-sd", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public TextModel text() {

		return new TextModel(appInfo.getMessage());
	}

	@Bean
	public ApplicationInfo applicationInfo(){
		ApplicationInfo appInfo = new ApplicationInfo();
		appInfo.setArtifactId("Simple-micro-service-1.0-SNAPSHOT");
		return appInfo;
	}

}
