package com.mine.info.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mine.info.model.Info;

@Repository
public interface InfoRepository extends CrudRepository<Info, Integer> {

}
