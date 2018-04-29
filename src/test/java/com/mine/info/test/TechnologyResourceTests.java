package com.mine.info.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
import com.mine.info.service.TechnologyService;
import static org.mockito.Mockito.times;


@RunWith(SpringRunner.class)
@WebMvcTest(TechnologyResource.class)
public class TechnologyResourceTests {
		
	@MockBean
	private TechnologyService service; 
	
	@Autowired 
	private TechnologyResource resource; 
	
	Technology expectedTechnology; 
	
	List<Technology> allTechsExpected = new ArrayList<Technology>(); 
	
	@Before
	public void setUp() {   
	    
		//mock repository object 
		service = Mockito.mock(TechnologyService.class);
		
		//create resource and set mock repo 
		resource = new TechnologyResource(); 
		resource.setTechnologyService(service);
	    
		// Optional<Technology> tech = null; 
		expectedTechnology = new Technology(1,"java","aws");
		
		//allTechsExpected.add(expectedTechnology); 
		//allTechsExpected = null; 
		
		// while trying to find tech with id "1" return Technology pre-created object 
		Mockito.when(service.findTechnologyById(1)).thenReturn(expectedTechnology);
		
		// when trying to save tech, return the same object 
		Mockito.when(service.addTechnology(expectedTechnology)).thenReturn(expectedTechnology);
		
		//Mockito.when(repo.deleteById(1)).thenReturn(null);
	}
	
	@Test 
	public void deleteTechnologyTest()  { 
		  resource.deleteTechnology(1);
	}
	
	@Test 
	public void addTechnologyTest()  { 
		resource.addTechnology(expectedTechnology); 
	}
	
	@Test 
	public void getAllTechnologyTest()  { 
		assertThat(resource.getAllTechnology()).isEqualTo(allTechsExpected);
	}
	
	@Test 
	public void getTechnologyById()  { 
		assertThat(resource.getTechnologyById(new Integer(1))).isEqualTo(expectedTechnology); 
	}	
}
