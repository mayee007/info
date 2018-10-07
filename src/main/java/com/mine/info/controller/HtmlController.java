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
	
	@GetMapping("/addTechnology.html")
	String addTechnology(Map<String, Object> model) {
		logger.info("inside HtmlController::addTechnology()");
			
		return "addTechnology";
	}
	
	@GetMapping("/problem.html")
	String listAllProblem(Map<String, Object> model) {
		logger.info("inside ProblemController::listAllProblem()");
			
		return "problem";
	}
	
	@GetMapping("/addProblem.html")
	String addProblem(Map<String, Object> model) {
		logger.info("inside ProblemController::addProblem()");
			
		return "addProblem";
	}
	
	@GetMapping("/info.html")
	String listAllInfo(Map<String, Object> model) {
		logger.info("inside HtmlController::listAllInfo()");
			
		return "info";
	}
	
	@GetMapping("/addInfo.html")
	String addInfo(Map<String, Object> model) {
		logger.info("inside HtmlController::addInfo()");
			
		return "addInfo";
	}
}
