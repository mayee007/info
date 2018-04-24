package com.mine.info.service;

import java.util.List;

import com.mine.info.model.Info;

public interface InfoService {
	Info findInfoById(Integer id); 
	List<Info> getAllInfo(); 
	void addInfo(Info tech); 
	void deleteInfo(Integer id); 
}
