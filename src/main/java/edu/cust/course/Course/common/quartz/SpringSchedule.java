package edu.cust.course.Course.common.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;
@Log4j
@Service
public class SpringSchedule {
		@Scheduled(cron = "0 */1 *  * * * ")
		public void quartzJobTest(){
			log.info("===========正在执行spring scheduler调度任务！==============");
		}
}
