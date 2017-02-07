package edu.cust.course.Course.common.quartz;

import lombok.extern.log4j.Log4j;

@Log4j
public class QuartzJob {
			
	
	public void doSimple() {
		log.info("======Quartz Simple定时任务！！==========");
	}
	
}
