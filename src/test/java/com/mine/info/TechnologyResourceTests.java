package com.mine.info;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
	@Autowired 
	private MockMvc mock; 
	
	@MockBean
	private TechnologyRepository repo; 
	
	@Test 
	public void getTest()  { 
		 
	}
}
