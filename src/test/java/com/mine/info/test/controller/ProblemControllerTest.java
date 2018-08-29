package com.mine.info.test.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
		expectedProblem = new Problem(7, "not able to connect to wireless", "dont know", "dont know", date, date, tech); 
        
		Mockito.when(service.findProblemById(7)).thenReturn(expectedProblem);
		
		Mockito.when(service.findProblemById(1)).thenReturn(null);
		
		idUrlPath = "/problem/7";
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
        mvc.perform(get("/problem/{id}", 7).accept(MediaType.APPLICATION_JSON_UTF8_VALUE) )
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
               .andExpect(jsonPath("$.id", is(7)))
               .andExpect(jsonPath("$.problem", is("not able to connect to wireless")));
        
        verify(service, times(1)).findProblemById(7);
    }
	
	@Test 
	public void findByIdProblemResourceAbsentTest() throws Exception {
		Mockito.when(service.findProblemById(100)).thenReturn(null);
		
		mvc.perform(get("/problem/{id}", 100)) 
        	.andExpect(status().isNotFound());
        
        verify(service, times(1)).findProblemById(100);
    }
}
