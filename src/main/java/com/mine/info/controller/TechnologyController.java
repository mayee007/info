package com.mine.info.controller;

import java.util.Date;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.mine.info.model.Info;
import com.mine.info.model.Technology;
import com.mine.info.service.InfoService;
import com.mine.info.service.TechnologyService;

@Controller
public class TechnologyController {
	
	private Logger logger = LoggerFactory.getLogger(TechnologyController.class);
		
	@Autowired 
	TechnologyService techService; 
	
	@PostMapping("/addTechnology.htm")
	String  addTechnology(@RequestParam("category") String category, 
			@RequestParam("technologyType") String technologyType,
			Map<String, Object> model) {
		
		logger.info("inside add Info(), type = \"" + technologyType + "\" , category \""+category+ "\""); 
		
		Technology tech = techService.findTechnology(technologyType, category); 
		 
		if (tech != null) {
			logger.info("Technology already exists with ID " + tech.getTechnologyId() + ", not adding again");
			return "technology" ;
		}
		logger.info("technology not found, need to be added");
		
		Technology newTech = new Technology();
		newTech.setCategory(category);
		newTech.setTechnologyType(technologyType);
		techService.addTechnology(newTech); 
		
	    return "technology" ;
	}
}
