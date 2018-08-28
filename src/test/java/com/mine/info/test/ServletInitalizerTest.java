package com.mine.info.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mine.info.ServletInitializer;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.mine.info.test")
@ContextHierarchy({
	  @ContextConfiguration(classes={ServletInitializer.class})
})
@WebAppConfiguration
@SpringBootTest
public class ServletInitalizerTest {
	
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	
	private ObjectMapper om;
	
	@Before
	public void runBefore() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		om = new ObjectMapper();
	}

}
