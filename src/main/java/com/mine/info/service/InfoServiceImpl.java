package com.mine.info.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.mine.info.dao.InfoRepository;
import com.mine.info.model.Info;
import com.mine.info.model.Problem;

@Component
public class InfoServiceImpl implements InfoService {
	
	@Autowired
	InfoRepository repo;  
	private HashOperations redisOps; 
	private String KEY = "INFO";
	private RedisTemplate<String, Info> redisTemplate; 
	
	public InfoServiceImpl(RedisTemplate<String, Info> redisTemplate) {
		this.redisTemplate = redisTemplate;
		redisOps = redisTemplate.opsForHash();
	}
	
	@Override
	public Info findInfoById(Integer id) {
		System.out.println("inside InfoServiceImpl::findInfoById(), ID is "+id);
		if (redisOps.hasKey(KEY, id.intValue())) { 
			System.out.println("key exists in redis"); 
			return (Info) redisOps.get(KEY, id.intValue()); 
		}
				
		Info info = null; 
		if (repo.findById(id).isPresent()) { 
			info = repo.findById(id).get();
		}
		return info;
	}

	@Override
	public List<Info> getAllInfo() {
		List<Info> infos = new ArrayList<Info>(); 
		for (Info info: repo.findAll()) { 
			redisOps.put(KEY, info.getId(), info);
			infos.add(info); 
		}
		return infos; 
	}

	@Override
	public Info addInfo(Info info) {
		Info newInfo = repo.save(info); 
		redisOps.put(KEY, newInfo.getId(), newInfo);
		return newInfo; 
	}

	@Override
	public void deleteInfo(Integer id) {
		Info info = repo.findById(id).get(); 
		redisOps.delete(KEY, info.getId());
		repo.delete(info);
	}

}
