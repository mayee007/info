package com.mine.info.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.mine.info.InfoApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
	
	@Test
	public void contextLoads() {
	}
	
	@Test 
	public void applicationStart() { 
		InfoApplication.main(new String[] {});
	}
}
