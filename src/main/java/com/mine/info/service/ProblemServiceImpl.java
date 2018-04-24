package com.mine.info.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mine.info.dao.ProblemRepository;
import com.mine.info.model.Problem;

@Component
public class ProblemServiceImpl implements ProblemService {
	
	@Autowired
	ProblemRepository repo;  
	
	@Override
	public Problem findProblemById(Integer id) {
		Problem problem = null; 
		if (repo.findById(id).isPresent()) { 
			problem = repo.findById(id).get();
		}
		return problem; 
	}

	@Override
	public List<Problem> getAllProblem() {
		List<Problem> problems = new ArrayList<Problem>(); 
		for (Problem problem: repo.findAll()) { 
			problems.add(problem); 
		}
		return problems; 
	}

	@Override
	public void addProblem(Problem problem) {
		repo.save(problem); 
	}

	@Override
	public void deleteProblem(Integer id) {
		Problem problem = repo.findById(id).get(); 
		repo.delete(problem);
	}

}
