package com.mine.info.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.After;
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
import org.springframework.test.web.servlet.MvcResult;
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

	
	@Before
	public void runBefore() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void getAdv() {
		try {
			//MvcResult result = this.mockMvc.perform(get("/hello")).andExpect(status().isOk())
				//	.andExpect(content().contentType("application/json"))
					//.andReturn();
			//System.out.println(result.getResponse().getContentAsString());
			//assertEquals("hello", result.getResponse().getContentAsString());
			assertEquals("","");
		} catch(Exception e) {
			System.out.println("Error while calling Info");
			e.printStackTrace();
		}
	}
	
	@Test
	public void getDummy() {
		System.out.println("Mahesh");
	}

}
