package edu.cust.course.Course.platform.service;

import edu.cust.course.Course.common.model.Meetting;

public interface MeettingService {
	/**首页显示当前最新会议信息*/
	public String load(String limit);
	/**根据显示单个会议具体信息，显示正文内容*/
	public Meetting selectOne(Integer id);
}
