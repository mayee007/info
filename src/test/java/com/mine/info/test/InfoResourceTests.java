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

import com.mine.info.model.Info;
import com.mine.info.model.Technology;
import com.mine.info.resource.InfoResource;
import com.mine.info.service.InfoService;
import static org.mockito.Mockito.times;


@RunWith(SpringRunner.class)
@WebMvcTest(InfoResource.class)
public class InfoResourceTests {
		
	@MockBean
	private InfoService service; 
	
	@Autowired 
	private InfoResource resource; 
	
	Info expectedInfo; 
	
	List<Info> allInfosExpected = new ArrayList<Info>(); 
	final Date date = Mockito.mock(Date.class); 
	
	@Before
	public void setUp() {   
	    
		//mock repository object 
		service = Mockito.mock(InfoService.class);
		
		//create resource and set mock repo 
		resource = new InfoResource(); 
		resource.setInfoService(service);
	    
		// Optional<Technology> tech = null; 
		Technology tech = new Technology(1,"java","aws");
		expectedInfo = new Info(2, "aws kms", "how to enable kms for an app", date, date, tech);
				
		//allTechsExpected.add(expectedTechnology); 
		//allTechsExpected = null; 
		
		// while trying to find tech with id "1" return Technology pre-created object 
		Mockito.when(service.findInfoById(1)).thenReturn(expectedInfo);
		
		// when trying to save tech, return the same object 
		Mockito.when(service.addInfo(expectedInfo)).thenReturn(expectedInfo);
		
		//Mockito.when(repo.deleteById(1)).thenReturn(null);
	}
	
	@Test 
	public void deleteInfoTest()  { 
		  resource.deleteInfo(1);
	}
	
	@Test 
	public void addInfoTest()  { 
		resource.addInfo(expectedInfo); 
	}
	
	@Test 
	public void getAllInfoTest()  { 
		assertThat(resource.getInfo()).isEqualTo(allInfosExpected);
	}
	
	@Test 
	public void getInfoById()  { 
		assertThat(resource.getInfoById(new Integer(1))).isEqualTo(expectedInfo); 
	}	
}
