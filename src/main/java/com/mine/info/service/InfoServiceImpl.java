package com.mine.info.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mine.info.dao.InfoRepository;
import com.mine.info.model.Info;

@Component
public class InfoServiceImpl implements InfoService {
	
	@Autowired
	InfoRepository repo;  
	
	@Override
	public Info findInfoById(Integer id) {
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
			infos.add(info); 
		}
		return infos; 
	}

	@Override
	public Info addInfo(Info info) {
		return repo.save(info); 
	}

	@Override
	public void deleteInfo(Integer id) {
		Info info = repo.findById(id).get(); 
		repo.delete(info);
	}

}
