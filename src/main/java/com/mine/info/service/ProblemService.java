package com.mine.info.service;

import java.util.List;

import com.mine.info.model.Problem;

public interface ProblemService {
	Problem findProblemById(Integer id); 
	List<Problem> getAllProblem(); 
	Problem addProblem(Problem tech); 
	void deleteProblem(Integer id); 
}
