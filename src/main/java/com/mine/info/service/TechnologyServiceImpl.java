package com.mine.info.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mine.info.dao.TechnologyRepository;
import com.mine.info.model.Technology;

@Component
public class TechnologyServiceImpl implements TechnologyService{
	@Autowired
	private TechnologyRepository repo; 
	
	@Override
	public Technology findTechnologyById(Integer id) {
		Technology tech = null; 
		if (repo.findById(id).isPresent()) { 
			tech = repo.findById(id).get();
		}
		return tech; 
	}

	@Override
	public Technology findTechnology(String type, String category) {
		System.out.println("inside findTechnology() ");
		System.out.println(" type "+ type + "  , category " + category); 
		for (Technology tech: repo.findAll()) { 
			System.out.println(tech.toString()); 
			if (tech.getTechnologyType().equals(type) && tech.getCategory().equals(category)) { 
				System.out.println("found match "); 
				return tech; 
			}
		}
		return null; 
	}
	
	@Override
	public List<Technology> getAllTechnology() {
		List<Technology> techs = new ArrayList<Technology>(); 
		for (Technology tech: repo.findAll()) { 
			techs.add(tech); 
		}
		return techs; 
	}

	@Override
	public Technology addTechnology(Technology tech) {
		return repo.save(tech); 

	}

	@Override
	public void deleteTechnology(Integer id) {
		Technology tech = repo.findById(id).get(); 
		repo.delete(tech);
	}

	@Override
	public List<String> getAllTechnologyType() {
		List<String> types = new ArrayList<String>(); 
		for (Technology tech: repo.findAll()) { 
			types.add(tech.getTechnologyType()); 
		}
		return types; 
	}
	
	@Override
	public List<String> getAllTechnologyCategory() {
		List<String> categorys = new ArrayList<String>(); 
		for (Technology tech: repo.findAll()) { 
			categorys.add(tech.getCategory()); 
		}
		return categorys; 
	}
}
