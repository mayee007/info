package com.mine.info;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mine.info.model.Technology;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityTests {
	//@Autowired 
	Technology tech = new Technology(1, "java", "aws"); 
	
	@Test
	public void idTest( ) {
		assertThat(tech.getTechnologyId()).isEqualTo(1); 
		assertThat(tech.getCategory()).isEqualTo("aws"); 
		assertThat(tech.getTechnologyType()).isEqualTo("java");
	}

}
