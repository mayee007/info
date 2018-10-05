package com.mine.info.resource;

import java.util.List;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mine.info.dao.TechnologyRepository;
import com.mine.info.model.Technology;
import com.mine.info.service.TechnologyService;

@RestController
public class TechnologyResource {

	@Autowired
	TechnologyService service; 
	
	@Autowired
	public void setTechnologyService(TechnologyService service) { 
		this.service = service; 
	}
	
	// GET all 
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@RequestMapping(value = "/technology", method = RequestMethod.GET)
    public @ResponseBody List<Technology> getAllTechnology() {
		System.out.println("inside TechnologyResource.getAllTechnology() "); 
		return service.getAllTechnology();
    }
	
	//POST 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	//@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/technology", method = RequestMethod.POST)
    public @ResponseBody Technology addTechnology(@RequestBody Technology tech) {
		System.out.println("inside TechnologyResource.addTechnology() "); 
		System.out.println("tech = " + tech); 
		return service.addTechnology(tech);
    }
	
	//PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	//@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/technology", method = RequestMethod.PUT)
	public @ResponseBody Technology updateTechnology(@RequestBody Technology tech) {
		System.out.println("inside TechnologyResource.updateTechnology() "); 
		System.out.println("tech = " + tech); 
		return service.addTechnology(tech);
	}
		
	// GET by ID 
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@RequestMapping(value = "/technology/{id}", method = RequestMethod.GET)
    public @ResponseBody Technology getTechnologyById(@PathVariable Integer id) {
		System.out.println("inside TechnologyResource.getTechnologyById() "); 
		//Optional<Technology> tech = repo.findTechnologyById(id); 
		return service.findTechnologyById(new Integer(id)); 
		//return tech.get(); 		    
    }

	//DELETE by ID 
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@RequestMapping(value = "/technology/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void deleteTechnology(@PathVariable Integer id) {
		service.deleteTechnology(id);
		System.out.println("inside TechnologyResource.deleteTechnology() "); 
	}
}