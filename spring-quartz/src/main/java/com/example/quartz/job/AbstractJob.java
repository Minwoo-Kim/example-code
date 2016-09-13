package com.example.quartz.job;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.example.quartz.config.Constants;
import com.example.quartz.entity.ScheduleJob;
import com.example.quartz.entity.ScheduleJobRepository;
import com.example.quartz.util.BeanUtil;
import com.example.quartz.util.SchedulerUtil;

/**
 * Abstract Job
 * 
 * @author Minu.Kim
 */
public abstract class AbstractJob extends QuartzJobBean {
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob ScheduleJob = this.getScheduleJob(context);
		// context.put(STR_JOB_TRACE, jobTrace);

		try {
			// 
			/**
			 * 1. job 수행
			 * proxy-target-class 설정을 하지 않으면 Bean Loading 불가.
			 * 
			 * Property : spring.aop.proxy-target-class=true
			 * or
			 * XML : <aop:config proxy-target-class="true" />
			 */
			BeanUtil.getBean(this.getClass()).doExecuteJob(context, ScheduleJob);
			// 2. job 수행 후 jobTrace 처리 등 후처리 ...
			SchedulerUtil.afterExecuteJob(context);
		} catch (Throwable th) {
			// 3. error 처리
			SchedulerUtil.onJobError(context, th);
		}
	}

	/**
	 * Abstract method
	 * 
	 * @param context
	 * @param ScheduleJob
	 * @param jobTrace
	 * @throws JobExecutionException
	 */
	public abstract void doExecuteJob(JobExecutionContext context, ScheduleJob ScheduleJob)
			throws JobExecutionException;

	/**
	 * ScheduleJob을 리턴
	 * 
	 * @param context
	 * @return
	 */
	protected ScheduleJob getScheduleJob(JobExecutionContext context) {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

		if (jobDataMap.containsKey(Constants.STR_JOB_MODEL_ID)) {
			String scheduleJobId = (String) jobDataMap.get(Constants.STR_JOB_MODEL_ID);
			ScheduleJob scheduleJob = BeanUtil.getBean(ScheduleJobRepository.class).findOne(scheduleJobId);
			return scheduleJob;
		} else {
			return null;
		}
	}
}