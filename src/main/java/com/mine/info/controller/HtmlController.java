package com.mine.info.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class HtmlController {
	
	private Logger logger = LoggerFactory.getLogger(HtmlController.class);
	
	@GetMapping("/technology.html")
	String listAllTechnology(Map<String, Object> model) {
		logger.info("inside TechnologyController::listAllTechnology()");
			
		return "technology";
	}
	
	@GetMapping("/problem.html")
	String listAllProblem(Map<String, Object> model) {
		logger.info("inside ProblemController::listAllProblem()");
			
		return "problem";
	}
	
}
