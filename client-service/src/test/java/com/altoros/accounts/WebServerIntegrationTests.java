package com.altoros.accounts;

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
@WebIntegrationTest({"server.port=0", "spring.cloud.discovery.enabled=false"})
public class WebServerIntegrationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testSpringBootAppRestInterAvailability() throws Exception {
		MockHttpServletRequestBuilder getReq = get("/");
		//post.contentType(MediaType.APPLICATION_JSON);
		//post.content(JsonMappingUtils.asJsonString(customerDto));
		MvcResult mvcResult = mockMvc.perform(getReq).
				andExpect(status().isOk()).
				andReturn();
	}

	@Test
	@Ignore //Requires integration testing
	public void testWebServerInteractionWithMicroService() throws Exception {

		this.mockMvc.perform(get("/test-sd"))
				.andExpect(status().isOk());
		//.andExpect(jsonPath("number", equalTo("123456789")));

	}

}
