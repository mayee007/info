package com.mine.info.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mine.info.model.Technology;

@Repository
public interface TechnologyRepository extends CrudRepository<Technology, Integer> {
	//Technology findTechnologyById(int id); 
}
