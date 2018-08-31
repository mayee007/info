package com.mine.info.resource;

import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mine.info.dao.ProblemRepository;
import com.mine.info.model.Problem;
import com.mine.info.service.ProblemService;

@RestController
public class ProblemResource {
	
	@Autowired
	ProblemService service; 

	public void setService(ProblemService service) {
		this.service = service;
	}

	// GET ALL 
	@RequestMapping("/problem")
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Iterable<Problem> getProblem() {
		System.out.println("inside Problemervice::getProblem()"); 
		return service.getAllProblem(); 
    }
	
	// POST 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@RequestMapping(value = "/problem", method = RequestMethod.POST)
    public @ResponseBody Problem addProblem(@RequestBody Problem problem) {
		System.out.println("inside ProblemResource.addProblem() "); 
		service.addProblem(problem);
		return problem; 
    }
	
	
	//GET by ID 
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@RequestMapping(value = "/problem/{id}", method = RequestMethod.GET)
    public @ResponseBody Problem getProblemById(@PathVariable Integer id) {
		System.out.println("inside ProblemResource.getProblemById() "); 
		Problem problem = service.findProblemById(id); 
		return problem;  		    
    }
	
	//DELETE 
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@RequestMapping(value = "/problem/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void deleteProblem(@PathVariable Integer id) {
		System.out.println("inside ProblemResource.deleteProblem() "); 
		service.deleteProblem(id);	    
    }
}
