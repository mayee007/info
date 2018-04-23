package com.mine.info;

import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mine.info.model.Info;
import com.mine.info.resource.InfoResource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InfoApplicationTests {
	@Autowired 
	InfoResource info; 
	
	@Test
	public void contextLoads() {
		
	}

}
