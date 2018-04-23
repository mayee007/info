package com.mine.info;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mine.info.controller.HelloController;
import com.mine.info.model.Info;
import com.mine.info.model.Problem;
import com.mine.info.model.Technology;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTests {
	@Autowired 
	private HelloController hello; 
	
	@Test 
	public void helloControllerTest() throws Exception { 
		assertThat(hello).isNotNull(); 
	}
}
