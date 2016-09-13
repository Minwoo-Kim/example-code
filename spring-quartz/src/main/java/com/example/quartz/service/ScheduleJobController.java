package com.example.quartz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.quartz.config.Constants;
import com.example.quartz.entity.ScheduleJob;
import com.example.quartz.entity.ScheduleJobRepository;
import com.example.quartz.util.SchedulerUtil;
import com.example.quartz.util.ValueUtil;

@Transactional
@RestController
@RequestMapping("/rest/schedule_jobs")
public class ScheduleJobController {

	@Autowired
	private ScheduleJobRepository rep;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ScheduleJob get(@PathVariable("id") String id) {
		return rep.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ScheduleJob save(@RequestBody ScheduleJob scheduleJob) {
		return rep.save(scheduleJob);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {
		rep.delete(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<ScheduleJob> findAll() {
		return rep.findAll();
	}

	// @RequestMapping(method = RequestMethod.GET)
	// public List<ScheduleJob> findByName(@RequestParam("name") String name) {
	// return rep.findByNameLike("%" + name + "%");
	// }

	@RequestMapping(value = "/start/{id}", method = RequestMethod.GET)
	public ScheduleJob start(@PathVariable("id") String id) {
		ScheduleJob scheduleJob = rep.findOne(id);

		try {
			if (ValueUtil.isEqual(scheduleJob.getStatus(), Constants.STATUS_PAUSED)) {
				SchedulerUtil.resumeJob(scheduleJob);
			} else {
				scheduleJob.setOkCount(0);
				scheduleJob.setNgCount(0);
				SchedulerUtil.scheduleJob(scheduleJob);
			}

			scheduleJob.setStatus(Constants.STATUS_RUNNING);
		} catch (Exception e) {
			StringBuilder result = new StringBuilder();
			result.append("Failed to schedule job - ");
			result.append((e.getCause() == null) ? e.getMessage() : e.getCause().getMessage());

			scheduleJob.setResult(result.toString());
			scheduleJob.setStatus(Constants.STATUS_PAUSED);
		}

		rep.save(scheduleJob);
		return scheduleJob;
	}

	@RequestMapping(value = "/puase/{id}", method = RequestMethod.GET)
	public ScheduleJob puase(@PathVariable("id") String id) {
		ScheduleJob scheduleJob = rep.findOne(id);

		try {
			SchedulerUtil.pauseJob(scheduleJob);
			scheduleJob.setStatus(Constants.STATUS_PAUSED);
		} catch (Exception e) {
			StringBuilder result = new StringBuilder();
			result.append("Failed to schedule job - ");
			result.append((e.getCause() == null) ? e.getMessage() : e.getCause().getMessage());

			scheduleJob.setResult(result.toString());
		}

		rep.save(scheduleJob);

		return scheduleJob;
	}

	@RequestMapping(value = "/stop/{id}", method = RequestMethod.GET)
	public ScheduleJob stop(@PathVariable("id") String id) {
		ScheduleJob scheduleJob = rep.findOne(id);

		try {
			SchedulerUtil.deleteJob(scheduleJob);
			scheduleJob.setStatus(Constants.STATUS_DELETED);
		} catch (Exception e) {
			StringBuilder result = new StringBuilder();
			result.append("Failed to schedule job - ");
			result.append((e.getCause() == null) ? e.getMessage() : e.getCause().getMessage());

			scheduleJob.setResult(result.toString());
		}

		rep.save(scheduleJob);

		return scheduleJob;
	}
}