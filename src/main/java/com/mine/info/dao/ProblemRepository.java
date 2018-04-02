package com.mine.info.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mine.info.model.Problem;

@Repository
public interface ProblemRepository extends CrudRepository<Problem, Integer> {

}
