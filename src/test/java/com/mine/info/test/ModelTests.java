package com.mine.info.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mine.info.model.Info;
import com.mine.info.model.Problem;
import com.mine.info.model.Technology;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelTests {
	//@Autowired 
	Technology tech1 = new Technology(1, "java", "aws"); 
	Technology tech2 = new Technology(); 
	
	Info info1 = new Info(); 
	Info info2 ; 
	
	Problem problem1 = new Problem();
	Problem problem2; 
	
	final Date date = Mockito.mock(Date.class); 
	
	@Before
	public void setup() { 
		tech2.setCategory("aws");
		tech2.setTechnologyType("java");
		tech2.setTechnologyId(2);
		
		info1.setId(1);
		info1.setTechnology(tech1); 
		info1.setSubject("something to think about");
		info1.setDescription("some test desc");
		info1.setSubmitDate(date);
		info1.setModifiedDate(date);
		
		info2 = new Info(2, "aws kms", "how to enable kms for an app", date, date, tech1);
		
		problem1.setId(1);
		problem1.setTechnology(tech1); 
		problem1.setProblem("something problem");
		problem1.setSolution("some solution");
		problem1.setReasonForProblem("dont know");
		problem1.setSubmitDate(date); 
		problem1.setModifiedDate(date); 
		
		problem2 = new Problem(2, "not able to connect to wireless", "dont know", "dont know", date, date, tech1);
	}
	
	@Test
	public void technologyModelTest1() {
		assertThat(tech1.getTechnologyId()).isEqualTo(1); 
		assertThat(tech1.getCategory()).isEqualTo("aws"); 
		assertThat(tech1.getTechnologyType()).isEqualTo("java");
	}

	@Test
	public void technologyModelTest2() {
		assertThat(tech2.getTechnologyId()).isEqualTo(2); 
		assertThat(tech2.getCategory()).isEqualTo("aws"); 
		assertThat(tech2.getTechnologyType()).isEqualTo("java");
	}
	
	@Test
	public void infoModelTest1() {
		assertThat(info1.getTechnology().getTechnologyId()).isEqualTo(1); 
		assertThat(info1.getTechnology().getCategory()).isEqualTo("aws"); 
		assertThat(info1.getTechnology().getTechnologyType()).isEqualTo("java");
	}
	
	@Test
	public void infoModelTest2() {
		assertThat(info1.getId()).isEqualTo(1); 
		assertThat(info1.getDescription()).isEqualTo("some test desc"); 
		assertThat(info1.getSubject()).isEqualTo("something to think about");
		assertThat(info1.getSubmitDate()).isEqualTo(date);
		assertThat(info1.getModifiedDate()).isEqualTo(date);
	}
	
	@Test
	public void infoModelTest3() {
		assertThat(info2.getId()).isEqualTo(2); 		
		assertThat(info2.getSubject()).isEqualTo("aws kms"); 
		assertThat(info2.getDescription()).isEqualTo("how to enable kms for an app"); 
	}
	
	@Test
	public void problemModelTest1() {
		assertThat(problem1.getTechnology().getTechnologyId()).isEqualTo(1); 
		assertThat(problem1.getTechnology().getCategory()).isEqualTo("aws"); 
		assertThat(problem1.getTechnology().getTechnologyType()).isEqualTo("java");
	}
	
	@Test
	public void problemModelTest2() {
		assertThat(problem1.getId()).isEqualTo(1); 
		assertThat(problem1.getProblem()).isEqualTo("something problem"); 
		assertThat(problem1.getSolution()).isEqualTo("some solution"); 
	}
	
	@Test
	public void problemModelTest3() {
		assertThat(problem2.getId()).isEqualTo(2); 
		assertThat(problem2.getProblem()).isEqualTo("not able to connect to wireless");
		assertThat(problem2.getSolution()).isEqualTo("dont know");
		assertThat(problem2.getReasonForProblem()).isEqualTo("dont know");
		assertThat(problem2.getSubmitDate()).isEqualTo(date);
		assertThat(problem2.getModifiedDate()).isEqualTo(date);
	}
	
}
