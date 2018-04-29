package com.mine.info.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

import com.mine.info.dao.TechnologyRepository;
import com.mine.info.model.Technology;
import com.mine.info.service.TechnologyService;
import com.mine.info.service.TechnologyServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TechnologyService.class)
public class TechnologyServiceTest {

	@MockBean
	private TechnologyRepository repo; 
	
	@InjectMocks
	private TechnologyServiceImpl service; 
	
	@Autowired
    WebApplicationContext context;
	
	List<Technology> allTechsExpected = new ArrayList<Technology>();
	Optional<Technology> techExpected; 
	
	@Before
	public void setUp() throws Exception {   					
		MockitoAnnotations.initMocks(this);
		
		// test data for Technology object 
		Technology tech = new Technology(1, "aws", "iam"); 
        techExpected = Optional.of(tech);  
		Mockito.when(repo.findById(1)).thenReturn(techExpected); 
		
		// when trying to save tech, return the same object 
		// **** now working for some reason 
		//Mockito.when(repo.save(tech)).thenReturn(tech);
		
		// when trying to save tech, return the same object 
		//Mockito.when(service.addTechnology(expectedTechnology)).thenReturn(expectedTechnology);	
		
		// test for for ALL technology 
		Technology tech1 = new Technology(1, "aws", "iam"); 
		Technology tech2 = new Technology(1, "aws", "s3"); 
		Technology tech3 = new Technology(1, "java", "spring"); 
		allTechsExpected.add(tech1);
		allTechsExpected.add(tech2);
		allTechsExpected.add(tech3);
		Mockito.when(repo.findAll()).thenReturn(allTechsExpected);	
	}
	
	@Test 
	public void findByTechnologyIdTest() throws Exception {
		Technology techActual = service.findTechnologyById(1);  
		assertEquals(techActual.getTechnologyId(), techExpected.get().getTechnologyId());
		assertEquals(techActual.getCategory(), techExpected.get().getCategory());
		assertEquals(techActual.getTechnologyType(), techExpected.get().getTechnologyType());
    }
	
	@Test
	public void deleteTechnology() throws Exception {
		service.deleteTechnology(techExpected.get().getTechnologyId());
		verify(repo, times(1)).delete(techExpected.get()); 
	}
	
	@Test 
	public void addTechnology() throws Exception {
		Technology tech = new Technology(1, "aws", "iam");
		//tech.setTechnologyId(1);
		//tech.setTechnologyType("aws");
		//tech.setCategory("iam");
		
		// when trying to save tech, return the same object 
		Mockito.when(repo.save(tech)).thenReturn(tech);
		
		Technology result = service.addTechnology(tech); 
		assertEquals(tech.getCategory(), 
				result.getCategory());
		assertEquals(tech.getTechnologyType(), result.getTechnologyType());
	}
	
	@Test 
	public void getAllTechnologyCountTest() throws Exception {
		List<Technology> allTechs = service.getAllTechnology(); 
		assertEquals(3, allTechs.size()); 
	}
	
	@Test 
	public void getAllTechnologyValueTest() throws Exception {
		List<Technology> allTechs = service.getAllTechnology(); 
		
		Technology tech3 = allTechs.get(2); 
		assertEquals("spring", tech3.getCategory()); 
		assertEquals("java", tech3.getTechnologyType()); 
	}
}
