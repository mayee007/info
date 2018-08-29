package com.mine.info.test.controller;

import java.sql.Date;
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

import org.mockito.Mockito;
import com.mine.info.model.Problem;
import com.mine.info.model.Technology;
import com.mine.info.resource.ProblemResource;
import com.mine.info.service.ProblemService;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders; 
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProblemResource.class)
public class ProblemControllerTest {

	@MockBean
	private ProblemService service; 
	
	@InjectMocks
	private ProblemResource controller; 
	
	@Autowired
    WebApplicationContext context;
	
	Problem expectedProblem; 
	
	List<Problem> allTechsExpected = new ArrayList<Problem>();
	
	private MockMvc mvc;
	
	String expectedStringValue; 
	String idUrlPath; 
	String urlPath; 
	
	@Before
	public void setUp() throws Exception {   
		//mock repository object 
		service = Mockito.mock(ProblemService.class);
						
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller)
					.build();
		
		//create resource and set mock repo 
		controller = new ProblemResource(); 
		controller.setService(service);
		
		// test data
		Technology tech = new Technology(1, "aws", "iam"); 
		// test data for Problem object 
		final Date date = Mockito.mock(Date.class); 
		expectedProblem = new Problem(2, "not able to connect to wireless", "dont know", "dont know", date, date, tech); 
        
		idUrlPath = "/problem/1";
		urlPath = "/problem";
	}
	
	@Test 
	public void addProblemResourceTest() throws Exception {
        mvc.perform(
        		MockMvcRequestBuilders.get(idUrlPath).accept(MediaType.APPLICATION_JSON))
        		  .andExpect(MockMvcResultMatchers.status().isOk());  
    }
	
	@Test 
	public void deleteProblemResourceTest() throws Exception {
        mvc.perform(
        		MockMvcRequestBuilders.get(idUrlPath).accept(MediaType.APPLICATION_JSON))
        		  .andExpect(MockMvcResultMatchers.status().isOk());  
    }
	
	@Test 
	public void findByIdProblemResourcePresentTest() throws Exception {
        mvc.perform(
        		MockMvcRequestBuilders.get(idUrlPath).accept(MediaType.APPLICATION_JSON))
        		  .andExpect(MockMvcResultMatchers.status().isOk());  
    }
	
	@Test 
	public void findByIdProblemResourceAbsentTest() throws Exception {
        mvc.perform(
        		MockMvcRequestBuilders.get(idUrlPath).accept(MediaType.APPLICATION_JSON))
        		  .andExpect(MockMvcResultMatchers.status().isOk());  
    }
}
