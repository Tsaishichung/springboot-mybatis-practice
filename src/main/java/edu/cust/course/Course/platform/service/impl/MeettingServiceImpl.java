package edu.cust.course.Course.platform.service.impl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import edu.cust.course.Course.common.mapper.MeettingMapper;
import edu.cust.course.Course.common.model.Meetting;
import edu.cust.course.Course.platform.service.MeettingService;
@Service
public class MeettingServiceImpl implements MeettingService {
	@Autowired
	private MeettingMapper meettingMapper;
	@Override
	public String load(String limit) {
		List<Meetting> meettingList = meettingMapper.load(limit);
		return JSONObject.valueToString(meettingList);
	}
	@Override
	public Meetting selectOne(Integer id) {
		return meettingMapper.selectOne(id);
	}

}
