package edu.cust.course.Course.common.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.cust.course.Course.common.model.Meetting;

@Repository
public interface MeettingMapper {
   
	public List<Meetting> load(String limit);
	
	public Meetting selectOne(Integer id);
	
}