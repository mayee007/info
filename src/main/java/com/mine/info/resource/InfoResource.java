package com.mine.info.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional; 

import com.mine.info.dao.InfoRepository;
import com.mine.info.model.Info;
import com.mine.info.service.InfoService;
import com.mine.info.service.TechnologyService;

@RestController
public class InfoResource {
	@Autowired
	InfoService service;  
	
	@Autowired
	public void setInfoService(InfoService service) { 
		this.service = service; 
	}
	
	// GET ALL 
	@RequestMapping("/info")
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Iterable<Info> getInfo() {
		System.out.println("inside InfoService::getInfo()"); 
		return service.getAllInfo(); 
    }
	
	// POST 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	//@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/info", method = RequestMethod.POST)
    public @ResponseBody Info addInfo(@RequestBody Info info) {
		System.out.println("inside InfoResource.addInfo() "); 
		service.addInfo(info);
		return info; 
    }
	
	
	//GET by ID 
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public @ResponseBody Info getInfoById(@PathVariable Integer id) {
		System.out.println("inside InfoResource.getInfoById() "); 
		Info info = service.findInfoById(id); 
		return info; 		    
    }
	
	//DELETE 
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@RequestMapping(value = "/info/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void deleteInfo(@PathVariable Integer id) {
		System.out.println("inside InfoResource.deleteInfo() "); 
		service.deleteInfo(id);	    
    }
	
}
