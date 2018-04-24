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
	public List<Technology> getAllTechnology() {
		List<Technology> techs = new ArrayList<Technology>(); 
		for (Technology tech: repo.findAll()) { 
			techs.add(tech); 
		}
		return techs; 
	}

	@Override
	public void addTechnology(Technology tech) {
		repo.save(tech); 

	}

	@Override
	public void deleteTechnology(Integer id) {
		Technology tech = repo.findById(id).get(); 
		repo.delete(tech);
	}

}
