package com.mine.info.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mine.info.controller.HelloController;
import com.mine.info.dao.TechnologyRepository;
import com.mine.info.model.Info;
import com.mine.info.model.Problem;
import com.mine.info.model.Technology;
import com.mine.info.resource.TechnologyResource;


@RunWith(SpringRunner.class)
@WebMvcTest(TechnologyResource.class)
public class TechnologyResourceTests {
	
	@TestConfiguration
    static class TechnologyServiceTestContextConfiguration {
  
        @Bean
        public TechnologyResource technologyResource() {
            return new TechnologyResource();
        }
    }
	
	//@Autowired 
	// private MockMvc mock; 

	@MockBean
	private TechnologyRepository repo; 
	
	@Autowired 
	private TechnologyResource resource; 
	
	Technology tech2; 
	Iterable<Technology> allTechsExpected; 
	@Before
	public void setUp() {   
	    
		//mock object 
		repo = Mockito.mock(TechnologyRepository.class);
		
		//create resource and set mock repo 
		resource = new TechnologyResource(); 
		resource.setTechnologyRepository(repo);
	    
		Optional<Technology> tech = null; 
		tech2 = new Technology(1,"java","aws");
	    
		allTechsExpected = null; 
		Mockito.when(repo.findById(1)).thenReturn(tech);
		//Mockito.when(repo.deleteById(1)).thenReturn(null);
	}
	
	@Test 
	public void simpleTest()  { 
		// Technology found = resource.getTechnologyById(1); 
		  resource.deleteTechnology(1);
		  //Iterable<Technology> techs = resource.getAllTechnology(); 
		  //assertThat(allTechsExpected).isEqualTo(resource.getAllTechnology()); 
		  resource.addTechnology(tech2); 
		  resource.getAllTechnology();
		  
	     //assertThat(found.getCategory()).isEqualTo("java"); 
	     //assertThat(found.getTechnologyType()).isEqualTo("aws"); 
	     //assertThat(found.getTechnologyId()).isEqualTo(1); 
	}
}
