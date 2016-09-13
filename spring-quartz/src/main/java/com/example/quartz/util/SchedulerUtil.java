package com.example.quartz.util;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.example.quartz.config.Constants;
import com.example.quartz.entity.ScheduleJob;


/**
 * Scheduler Utilities
 * 
 * @author Minu.Kim
 */
public class SchedulerUtil {

	/**
	 * Scheduler
	 */
	private static Scheduler scheduler;
	
	/**
	 * get scheduler
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public static Scheduler getScheduler() throws SchedulerException {
		if(scheduler == null) {
			SchedulerFactoryBean factory = BeanUtil.getBean(SchedulerFactoryBean.class);
			scheduler = factory.getScheduler();
		}
		
		return scheduler;
	}
	
	/**
	 * schedule job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public static boolean scheduleJob(ScheduleJob scheduleJob) throws SchedulerException {
		Class<? extends Job> jobHandlerClazz = checkJobAction(scheduleJob, Constants.ACTION_START);
		String jobName = scheduleJob.getName();
		String jobGroupName = "GROUP-1";
		String triggerName = jobName + "_trg";
		String triggerGroupName = jobGroupName;
		Integer interval = null;
		
		try {
			interval = Integer.parseInt(scheduleJob.getInterval());
		} catch (Exception e) {
		}
		
		Trigger trigger = null;
		if(!ValueUtil.isEmpty(interval)) {
			trigger = newSimpleTrigger(triggerName, triggerGroupName, interval, scheduleJob.getRepeat());
		} else {
			trigger = newCronTrigger(triggerName, triggerGroupName, scheduleJob.getInterval());
		}

		JobDetail jobDetail = JobBuilder.newJob(jobHandlerClazz).withIdentity(jobName, jobGroupName).build();
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		jobDataMap.put(Constants.STR_JOB_MODEL_CLASS, scheduleJob.getClass().getName());
		jobDataMap.put(Constants.STR_JOB_MODEL_ID, scheduleJob.getId());

		SchedulerUtil.getScheduler().scheduleJob(jobDetail, trigger);
		return true;
	}
	
	/**
	 * Cron Trigger 생성 
	 * 
	 * @param triggerName
	 * @param triggerGroupName
	 * @param cronExpr
	 * @return
	 */
	private static Trigger newCronTrigger(String triggerName, String triggerGroupName, String cronExpr) {
		return newTrigger().withIdentity(triggerName, triggerGroupName).withSchedule(cronSchedule(cronExpr)).build();
	}
	
	/**
	 * Simple Trigger 생성 
	 * 
	 * @param triggerName
	 * @param triggerGroupName
	 * @param interval
	 * @param repeatCount
	 * @return
	 */
	private static Trigger newSimpleTrigger(String triggerName, String triggerGroupName, Integer interval, Integer repeatCount) {
		if(ValueUtil.isEmpty(repeatCount) || repeatCount < 1) {
			return newTrigger().withIdentity(triggerName, triggerGroupName).withSchedule(simpleSchedule().withIntervalInSeconds(interval).repeatForever()).build();
		} else {
			return newTrigger().withIdentity(triggerName, triggerGroupName).withSchedule(simpleSchedule().withIntervalInSeconds(interval).withRepeatCount(repeatCount)).build();
		}
	}
	
	/**
	 * resume job
	 * 
	 * @param scheduleJob
	 * @return
	 * @throws SchedulerException
	 */
	public static boolean resumeJob(ScheduleJob scheduleJob) throws SchedulerException {
		return SchedulerUtil.actionJob(scheduleJob, Constants.ACTION_RESUME);
	}	
	
	/**
	 * pause job
	 * 
	 * @param scheduleJob
	 * @return
	 * @throws SchedulerException
	 */
	public static boolean pauseJob(ScheduleJob scheduleJob) throws SchedulerException {
		return actionJob(scheduleJob, Constants.ACTION_PAUSE);	
	}
	
	/**
	 * delete job
	 * 
	 * @param scheduleJob
	 * @return
	 * @throws SchedulerException
	 */
	public static boolean deleteJob(ScheduleJob scheduleJob) throws SchedulerException {
		return actionJob(scheduleJob, Constants.ACTION_DELETE);
	}
	
	/**
	 * pause or delete job
	 * 
	 * @param scheduleJob
	 * @param status
	 * @return
	 * @throws SchedulerException
	 */
	public static boolean actionJob(ScheduleJob scheduleJob, String status) throws SchedulerException {
		checkJobAction(scheduleJob, status);
		Scheduler scheduler = SchedulerUtil.getScheduler();
		String jobName = scheduleJob.getName();
		String jobGroupName = "GROUP-1";

		for (String groupName : scheduler.getJobGroupNames()) {
			if (ValueUtil.isNotEqual(groupName, jobGroupName)) {
				continue;
			}

			for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
				if (ValueUtil.isNotEqual(jobName, jobKey.getName())) {
					continue;
				}

				if (ValueUtil.isEqual(status, Constants.ACTION_PAUSE)) {
					scheduler.pauseJob(jobKey);
					return true;
				} else if (ValueUtil.isEqual(status, Constants.ACTION_RESUME)) {
					scheduler.resumeJob(jobKey);
					return true;
				} else if (ValueUtil.isEqual(status, Constants.ACTION_DELETE)) {
					scheduler.deleteJob(jobKey);
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Check Job Transaction is valid
	 *  
	 * @param scheduleJob
	 * @param action
	 * @return
	 */
	public static Class<? extends Job> checkJobAction(ScheduleJob scheduleJob, String action) {
		return SchedulerUtil.getHandlerClass(scheduleJob);
	}
	
	/**
	 * Job로 부터 handler object를 생성 
	 * 
	 * @param scheduleJob
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Class<? extends Job> getHandlerClass(ScheduleJob scheduleJob) {
		try {
			return (Class<? extends Job>) Class.forName(scheduleJob.getHandler());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	/**
	 * Job 실행 후 Action
	 * @param context
	 */
	public static void afterExecuteJob(JobExecutionContext context) {
	}
	
	/**
	 * Job 실행 Error
	 * @param context
	 * @param th
	 */
	public static void onJobError(JobExecutionContext context, Throwable th) {
	}
}