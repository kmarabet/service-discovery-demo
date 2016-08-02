package com.altoros;

import com.altoros.ServerApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServerApp.class)
@WebIntegrationTest({"server.port=0", "spring.cloud.discovery.enabled=false", "spring.cloud.consul.enabled=false"})
public class MicroServiceTest {

    private MockMvc clientServiceMockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        clientServiceMockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testServiceAvailability() throws Exception {

        clientServiceMockMvc.perform(get("/")).
                andExpect(status().isOk()).
                andReturn();
    }

    @Test
    public void testHealthCheckEndpoint() throws Exception {

        clientServiceMockMvc.perform(get("/test")).
                andExpect(status().isOk()).
                andReturn();
    }

}
