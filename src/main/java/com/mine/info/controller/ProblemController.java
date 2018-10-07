package com.mine.info.controller;

import java.util.Date;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.mine.info.model.Problem;
import com.mine.info.model.Technology;
import com.mine.info.service.ProblemService;
import com.mine.info.service.TechnologyService;

@Controller
public class ProblemController {
	
	private Logger logger = LoggerFactory.getLogger(ProblemController.class);
	
	@Autowired
	ProblemService service; 
	
	@Autowired 
	TechnologyService techService; 
	
	@PostMapping("/addProblem.htm")
	String  addInfo(@RequestParam("category") String category, 
			@RequestParam("technologyType") String technologyType, 
			@RequestParam("problem") String problem, 
			@RequestParam("reasonForProblem") String reasonForProblem,
			@RequestParam("solution") String solution, Map<String, Object> model) {
		
		logger.info("inside add Problem(), type = \"" + technologyType + "\" , category \""+category+ "\""); 
		logger.info("problem " + problem); 
		logger.info("reason " + reasonForProblem);
		logger.info("solution " + solution);
		logger.info("tech type now" + technologyType.split(":")[1]); 
	  	
		Technology tech = techService.findTechnology(technologyType.split(":")[1], category.split(":")[1]); 
		 
		if (tech != null) {
			logger.info("Technology found with ID " + tech.getTechnologyId());
		} else {
			Technology newTech = new Technology(); 
			newTech.setCategory(category);
			newTech.setTechnologyType(technologyType);
			techService.addTechnology(newTech); 
			
			tech = newTech; 
		}
		
		logger.info("found tech "+ tech.getTechnologyId());
		Problem prob = new Problem();
		prob.setProblem(problem); 
		prob.setSolution(solution);
		prob.setReasonForProblem(reasonForProblem);
		prob.setTechnology(tech);
		prob.setSubmitDate(new Date());
		prob.setModifiedDate(new Date());
		service.addProblem(prob); 
		
	    return "problem" ;
	}
}
