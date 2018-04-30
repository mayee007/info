package com.mine.info.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;
import org.mockito.Mockito;

import com.mine.info.dao.ProblemRepository;
import com.mine.info.dao.TechnologyRepository;
import com.mine.info.model.Problem;
import com.mine.info.model.Technology;
import com.mine.info.service.ProblemServiceImpl;
import com.mine.info.service.TechnologyService;
import com.mine.info.service.TechnologyServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TechnologyService.class)
public class ProblemServiceTest {

	@MockBean
	private ProblemRepository repo; 
	
	@InjectMocks
	private ProblemServiceImpl service; 
	
	@Autowired
    WebApplicationContext context;
	
	List<Problem> allProblemsExpected = new ArrayList<Problem>();
	Optional<Problem> problemExpected; 
	
	@Before
	public void setUp() throws Exception {   					
		MockitoAnnotations.initMocks(this);
		
		Technology tech = new Technology(1, "aws", "iam"); 
		// test data for Problem object 
		final Date date = Mockito.mock(Date.class); 
		Problem problem = new Problem(2, "not able to connect to wireless", "dont know", "dont know", date, date, tech); 
        problemExpected = Optional.of(problem);  
		Mockito.when(repo.findById(1)).thenReturn(problemExpected); 
		
		// when trying to save tech, return the same object 
		// **** now working for some reason 
		//Mockito.when(repo.save(tech)).thenReturn(tech);
		
		// when trying to save tech, return the same object 
		//Mockito.when(service.addTechnology(expectedTechnology)).thenReturn(expectedTechnology);	
		
		// test for for ALL technology 
		Problem problem1 = new Problem(2, "not able to connect to wireless", "dont know", "dont know", date, date, tech); 
		Problem problem2 = new Problem(2, "another problem", "dont know", "dont know", date, date, tech); 
		Problem problem3 = new Problem(2, "problems keep coming", "dont know", "dont know", date, date, tech);   
		allProblemsExpected.add(problem1);
		allProblemsExpected.add(problem2);
		allProblemsExpected.add(problem3);
		Mockito.when(repo.findAll()).thenReturn(allProblemsExpected);	
	}
	
	@Test 
	public void findByProblemIdPresentTest() throws Exception {
		Problem problemActual = service.findProblemById(1);  
		assertThat(problemActual.getId())
			.isEqualTo(repo.findById(1).get().getId()); 
    }
	
	@Test 
	public void findByProblemIdAbsentTest() throws Exception {
		Problem problemActual = service.findProblemById(2);  
		assertThat(problemActual).isEqualTo(null); 
    }
	
	@Test
	public void deleteTechnology() throws Exception {
		Technology tech = new Technology(1, "aws", "iam");
		final Date date = Mockito.mock(Date.class); 
		Problem problem = new Problem(1, "not able to connect to wireless", "dont know", "dont know", date, date, tech); 
        problemExpected = Optional.of(problem);  
        Mockito.when(repo.findById(1)).thenReturn(problemExpected); 
        
        
		service.deleteProblem(problem.getId());
		//service.deleteProblem(repo.findById(1).get().getId());
		verify(repo, times(1)).delete(problem); 
	}
	
	@Test 
	public void addTechnology() throws Exception {
		Technology tech = new Technology(1, "aws", "iam");
		final Date date = Mockito.mock(Date.class); 
		Problem problem = new Problem(2, "not able to connect to wireless", "dont know", "dont know", date, date, tech); 
		
		// when trying to save tech, return the same object 
		Mockito.when(repo.save(problem)).thenReturn(problem);
		
		Problem result = service.addProblem(problem); 
		assertEquals(result.getProblem(), problem.getProblem());
		assertEquals(result.getSolution(), problem.getSolution());

	}
	
	@Test 
	public void getAllTechnology() throws Exception {
		List<Problem> allproblems = service.getAllProblem(); 
		assertEquals(3, allproblems.size()); 
		
		Problem problem = allproblems.get(0); 
		assertEquals("dont know", problem.getSolution()); 

	}
}
