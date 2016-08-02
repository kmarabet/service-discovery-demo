package com.altoros;

import com.altoros.WebServerApp;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Spring Integration/System test - by using @SpringApplicationConfiguration,
 * it picks up the same configuration that Spring Boot would use.
 * 
 * @author kmarabet
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebServerApp.class)
@WebIntegrationTest({"server.port=0", "spring.cloud.discovery.enabled=false", "spring.cloud.consul.enabled=false"})
public class WebServerIntegrationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testServiceAvailability() throws Exception {

		mockMvc.perform(get("/")).
				andExpect(status().isOk()).
				andReturn();
	}

	@Test
	public void testHealthCheckEndpoint() throws Exception {

		mockMvc.perform(get("/test")).
				andExpect(status().isOk()).
				andReturn();
	}

	@Test
	@Ignore //Requires integration testing
	public void testWebServerInteractionWithAccountsService() throws Exception {

		this.mockMvc.perform(get("/test-sd"))
		//this.mockMvc.perform(get("/accounts/123456789"))
				.andExpect(status().isOk());
		//.andExpect(jsonPath("number", equalTo("123456789")));

	}

}
