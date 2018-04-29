package com.mine.info.service;

import java.util.List;

import com.mine.info.model.Technology;

public interface TechnologyService {
	Technology findTechnologyById(Integer id); 
	List<Technology> getAllTechnology(); 
	Technology addTechnology(Technology tech); 
	void deleteTechnology(Integer id); 
}
