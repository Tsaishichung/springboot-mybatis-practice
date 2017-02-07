package edu.cust.course.Course.common.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.log4j.Log4j;

@Log4j
public class QuartzJob_JobDetailFactoryBean extends QuartzJobBean{
	
	@SuppressWarnings("unused")
	private int timeout;
	
	public void setTimeout(int timeout) {
        this.timeout = timeout;
    }


	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		log.info("======Quartz Cron定时任务！！==========");
		
	}
}
