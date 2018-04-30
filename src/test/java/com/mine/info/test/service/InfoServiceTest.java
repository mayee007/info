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

import com.mine.info.dao.InfoRepository;
import com.mine.info.model.Info;
import com.mine.info.model.Technology;
import com.mine.info.service.InfoServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InfoServiceImpl.class)
public class InfoServiceTest {

	@MockBean
	private InfoRepository repo; 
	
	@InjectMocks
	private InfoServiceImpl service; 
	
	@Autowired
    WebApplicationContext context;
	
	List<Info> allInfosExpected = new ArrayList<Info>();
	Optional<Info> InfoExpected; 
	
	@Before
	public void setUp() throws Exception {   					
		MockitoAnnotations.initMocks(this);
		
		Technology tech = new Technology(1, "aws", "iam"); 
		// test data for Info object 
		final Date date = Mockito.mock(Date.class); 
		Info Info = new Info(2, "how to mock test", "dont know", date, date, tech); 
        InfoExpected = Optional.of(Info);  
		Mockito.when(repo.findById(1)).thenReturn(InfoExpected); 
		
		// when trying to save tech, return the same object 
		// **** now working for some reason 
		//Mockito.when(repo.save(tech)).thenReturn(tech);
		
		// when trying to save tech, return the same object 
		//Mockito.when(service.addTechnology(expectedTechnology)).thenReturn(expectedTechnology);	
		
		// test for for ALL technology 
		Info Info1 = new Info(2, "mock test basics", "dont know", date, date, tech); 
		Info Info2 = new Info(2, "another Info", "dont know", date, date, tech); 
		Info Info3 = new Info(2, "Infos keep coming", "dont know", date, date, tech);   
		allInfosExpected.add(Info1);
		allInfosExpected.add(Info2);
		allInfosExpected.add(Info3);
		Mockito.when(repo.findAll()).thenReturn(allInfosExpected);	
	}
	
	@Test 
	public void findByInfoIdPresentTest() throws Exception {
		Info InfoActual = service.findInfoById(1);  
		assertThat(InfoActual.getId())
			.isEqualTo(repo.findById(1).get().getId()); 
    }
	
	@Test 
	public void findByInfoIdAbsentTest() throws Exception {
		Info InfoActual = service.findInfoById(2);  
		assertThat(InfoActual).isEqualTo(null); 
    }
	
	@Test
	public void deleteTechnology() throws Exception {
		Technology tech = new Technology(1, "aws", "iam");
		final Date date = Mockito.mock(Date.class); 
		Info Info = new Info(1, "mocking basics", "dont know", date, date, tech); 
        InfoExpected = Optional.of(Info);  
        Mockito.when(repo.findById(1)).thenReturn(InfoExpected); 
        
        
		service.deleteInfo(Info.getId());
		//service.deleteInfo(repo.findById(1).get().getId());
		verify(repo, times(1)).delete(Info); 
	}
	
	@Test 
	public void addTechnology() throws Exception {
		Technology tech = new Technology(1, "aws", "iam");
		final Date date = Mockito.mock(Date.class); 
		Info Info = new Info(2, "mock test", "dont know", date, date, tech); 
		
		// when trying to save tech, return the same object 
		Mockito.when(repo.save(Info)).thenReturn(Info);
		
		Info result = service.addInfo(Info); 
		assertEquals(result.getDescription(), Info.getDescription());
		assertEquals(result.getTechnology(), Info.getTechnology());

	}
	
	@Test 
	public void getAllTechnology() throws Exception {
		List<Info> allInfos = service.getAllInfo(); 
		assertEquals(3, allInfos.size()); 
		
		Info Info = allInfos.get(0); 
		assertEquals("mock test basics", Info.getSubject()); 

	}
}
