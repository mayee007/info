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
public class InfoController {
	
	private Logger logger = LoggerFactory.getLogger(InfoController.class);
	
	@Autowired
	InfoService service; 
	
	@Autowired 
	TechnologyService techService; 
	
	@PostMapping("/addInfo.htm")
	String  addInfo(@RequestParam("category") String category, 
			@RequestParam("technologyType") String technologyType, 
			@RequestParam("subject") String subject, 
			@RequestParam("description") String description,
			Map<String, Object> model) {
		
		logger.info("inside add Info(), type = \"" + technologyType + "\" , category \""+category+ "\""); 
		logger.info("subject " + subject); 
		logger.info("description " + description);
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
		
		Info info = new Info();
		info.setSubject(subject);
		info.setDescription(description);
		info.setTechnology(tech);
		info.setSubmitDate(new Date());
		info.setModifiedDate(new Date());
		service.addInfo(info); 
		
	    return "info" ;
	}
}
