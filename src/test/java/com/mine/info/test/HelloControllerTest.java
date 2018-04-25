package com.mine.info.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mine.info.controller.HelloController;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders; 
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HelloController.class)
public class HelloControllerTest {

	@InjectMocks
	private HelloController controller; 
	
	@Autowired
    WebApplicationContext context;
	
	private MockMvc mvc;
	
	String expectedStringValue; 
	String urlPath; 
	
	@Before
	public void setUp() throws Exception {   
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller)
					.build(); 
		
		// test data 
		expectedStringValue = "hello"; 
		urlPath = "/hello";
	}
	
	@Test 
	public void helloControllerStringTest() throws Exception {
        mvc.perform(
        		MockMvcRequestBuilders.get(urlPath))
        		  .andExpect(MockMvcResultMatchers.status().isOk())
        		  .andExpect(MockMvcResultMatchers.content().string(expectedStringValue));        	
		
    }
	
}
