package edu.cust.course.Course.common.quartz;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzTriggerConfiguration {
		/**
		 * 按指定时间触发调度(cron表达式)，使用JobDetailFactoryBean，设置特定的类为执行调度
		 * */
		@Bean
		public CronTriggerFactoryBean  cronTriggerFactoryBean(){
			CronTriggerFactoryBean  bean = new CronTriggerFactoryBean();
			bean.setCronExpression("0 * * * * ?");
			bean.setJobDetail(cronDetail().getObject());
			bean.setTimeZone(TimeZone.getTimeZone("UTC+8:00"));
			return bean;
		}
		@Bean
		public JobDetailFactoryBean cronDetail(){
			JobDetailFactoryBean bean = new JobDetailFactoryBean();
			bean.setJobClass(new QuartzJob_JobDetailFactoryBean().getClass());
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("timeout", 5);
			bean.setJobDataAsMap(map);
			return bean;
		}
		/**
		 * 按时间频率执行调度任务,使用MethodInvokingJobDetailFactoryBean在特定的对象方法启用调度
		 * */
		@Bean
		public SimpleTriggerFactoryBean simpleTriggerFactoryBean(){
			SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
			bean.setStartDelay(10000);
			bean.setRepeatInterval(10000);
			bean.setJobDetail(simpleDetail().getObject());
			return bean;
		}
		@Bean
		public MethodInvokingJobDetailFactoryBean simpleDetail(){
			MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
			bean.setTargetObject(new QuartzJob());
			bean.setTargetMethod ("doSimple");
			bean.setConcurrent(false);
			return bean;
		}
		/**
		 * 将cron和simple注入调度工厂
		 * */
		@Bean
		public SchedulerFactoryBean  schedulerFactoryBean(){
			SchedulerFactoryBean bean = new SchedulerFactoryBean();
			/**这个是一个list*/
			bean.setTriggers(simpleTriggerFactoryBean().getObject(),cronTriggerFactoryBean().getObject());
			return bean;
		}
}
