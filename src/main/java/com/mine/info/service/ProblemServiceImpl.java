package com.mine.info.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.mine.info.dao.ProblemRepository;
import com.mine.info.model.Problem;
import com.mine.info.model.Technology;

@Component
public class ProblemServiceImpl implements ProblemService {
	
	@Autowired
	ProblemRepository repo;  
	
	private RedisTemplate<String, Problem> redisTemplate; 
	private HashOperations redisOps; 
	private String KEY = "PROBLEM";
	
	public ProblemServiceImpl(RedisTemplate<String, Problem> redisTemplate) {
		this.redisTemplate = redisTemplate;
		redisOps = redisTemplate.opsForHash();
	}
	
	@Override
	public Problem findProblemById(Integer id) {
		System.out.println("inside ProblemServiceImpl::findProblemById(), ID is "+id);
		if (redisOps.hasKey(KEY, id.intValue())) { 
			System.out.println("key exists in redis"); 
			return (Problem) redisOps.get(KEY, id.intValue()); 
		}
		
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
			redisOps.put(KEY, problem.getId(), problem);
			problems.add(problem); 
		}
		return problems; 
	}

	@Override
	public Problem addProblem(Problem problem) {
		Problem newProblem = repo.save(problem); 
		System.out.println("inside addProblem server:");
		System.out.println("before adding, tech :"+problem); 
		System.out.println("after  adding, tech :"+newProblem); 
		redisOps.put(KEY, newProblem.getId(), newProblem);
		return newProblem;
	}

	@Override
	public void deleteProblem(Integer id) {
		Problem problem = repo.findById(id).get(); 
		redisOps.delete(KEY, problem.getId());
		repo.delete(problem);
	}

}
