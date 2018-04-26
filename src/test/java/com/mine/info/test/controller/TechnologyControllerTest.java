package com.mine.info.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import org.mockito.Mock;
import org.mockito.Mockito;

import com.mine.info.model.Technology;
import com.mine.info.resource.TechnologyResource;
import com.mine.info.service.TechnologyService;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders; 
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TechnologyResource.class)
public class TechnologyControllerTest {

	@MockBean
	private TechnologyService service; 
	
	@InjectMocks
	private TechnologyResource controller; 
	
	@Autowired
    WebApplicationContext context;
	
	Technology expectedTechnology; 
	
	List<Technology> allTechsExpected = new ArrayList<Technology>();
	
	private MockMvc mvc;
	
	String expectedStringValue; 
	String urlPath; 
	
	@Before
	public void setUp() throws Exception {   
		//mock repository object 
		service = Mockito.mock(TechnologyService.class);
						
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller)
					.build();
		
		//create resource and set mock repo 
		controller = new TechnologyResource(); 
		controller.setTechnologyService(service);
		
		// test data
		expectedTechnology = new Technology(1,"java","aws");
		urlPath = "/technology/1";
	}
	
	@Test 
	public void helloControllerStringTest() throws Exception {
        mvc.perform(
        		MockMvcRequestBuilders.get(urlPath).accept(MediaType.APPLICATION_JSON))
        		  .andExpect(MockMvcResultMatchers.status().isOk());  
        		  //.andExpect(MockMvcResultMatchers.content()).equals(expectedTechnology);
		
    }
	
}
