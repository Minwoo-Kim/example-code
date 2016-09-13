package com.example.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.quartz.entity.ScheduleJob;

/**
 * Sample Job
 * 
 * @author Minu.Kim
 */
@Service
public class SampleJob extends AbstractJob {

	@Override
	@Transactional
	public void doExecuteJob(JobExecutionContext context, ScheduleJob scheduleJob) throws JobExecutionException {
		System.out.println(System.currentTimeMillis());
	}
}
