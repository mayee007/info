package com.mine.info.test.service;

import static org.junit.Assert.assertEquals;

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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import org.mockito.Mock;
import org.mockito.Mockito;

import com.mine.info.dao.TechnologyRepository;
import com.mine.info.model.Technology;
import com.mine.info.resource.TechnologyResource;
import com.mine.info.service.TechnologyService;
import com.mine.info.service.TechnologyServiceImpl;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders; 
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TechnologyService.class)
public class TechnologyServiceTest {

	@MockBean
	private TechnologyRepository repo; 
	
	@InjectMocks
	private TechnologyServiceImpl service; 
	
	@Autowired
    WebApplicationContext context;
	
	Technology expectedTechnology; 
	
	List<Technology> allTechsExpected = new ArrayList<Technology>();
	Optional<Technology> techExpected; 
	
	@Before
	public void setUp() throws Exception {   					
		MockitoAnnotations.initMocks(this);
		
		// test data for Technology object 
		Technology tech = new Technology(1, "aws", "iam"); 
        techExpected = Optional.of(tech);  
		Mockito.when(repo.findById(1)).thenReturn(techExpected); 
		
	}
	
	@Test 
	public void findByTechnologyIdTest() throws Exception {
		Technology techActual = service.findTechnologyById(1);  
		assertEquals(techActual.getTechnologyId(), techExpected.get().getTechnologyId());
		assertEquals(techActual.getCategory(), techExpected.get().getCategory());
		assertEquals(techActual.getTechnologyType(), techExpected.get().getTechnologyType());
    }
	
}
