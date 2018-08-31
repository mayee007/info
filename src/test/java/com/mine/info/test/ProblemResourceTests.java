package com.mine.info.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mine.info.model.Problem;
import com.mine.info.model.Technology;
import com.mine.info.resource.ProblemResource;
import com.mine.info.service.ProblemService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProblemResource.class)
public class ProblemResourceTests {
		
	@MockBean
	private ProblemService service; 
	
	@Autowired 
	private ProblemResource resource; 
	
	Problem expectedProblem; 
	
	List<Problem> allProblemsExpected = new ArrayList<Problem>(); 
	final Date date = Mockito.mock(Date.class); 
	
	@Before
	public void setUp() {   
	    
		//mock repository object 
		service = Mockito.mock(ProblemService.class);
		
		//create resource and set mock repo 
		resource = new ProblemResource(); 
		resource.setService(service);
	    
		// Optional<Technology> tech = null; 
		Technology tech = new Technology(1,"java","aws");
		expectedProblem = new Problem(2, "not able to connect to wireless", "dont know", "dont know", date, date, tech);
	
		//allTechsExpected.add(expectedTechnology); 
		//allTechsExpected = null; 
		
		// while trying to find tech with id "1" return Technology pre-created object 
		Mockito.when(service.findProblemById(1)).thenReturn(expectedProblem);
		
		// when trying to save tech, return the same object 
		Mockito.when(service.addProblem(expectedProblem)).thenReturn(expectedProblem);
		
		//Mockito.when(repo.deleteById(1)).thenReturn(null);
	}
	
	@Test 
	public void deleteProblemTest()  { 
		  resource.deleteProblem(1);
	}
	
	@Test 
	public void addProblemTest()  { 
		resource.addProblem(expectedProblem); 
	}
	
	@Test 
	public void getAllProblemTest()  { 
		assertThat(resource.getProblem()).isEqualTo(allProblemsExpected);
	}
	
	@Test 
	public void getProblemById()  { 
		assertThat(resource.getProblemById(new Integer(1))).isEqualTo(expectedProblem); 
	}	
}
