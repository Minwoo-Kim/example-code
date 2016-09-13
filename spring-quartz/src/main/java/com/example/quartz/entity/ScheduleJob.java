package com.example.quartz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "schedule_job", indexes = { @Index(name = "ix_schedule_job_0", columnList = "name", unique = true) })
public class ScheduleJob {
	@Id
	private String id;

	@Column(name = "name", nullable = false, length = 64)
	private String name;

	@Column(name = "description", length = 255)
	private String description;

	@Column(name = "handler_type", length = 15)
	private String handlerType;

	@Column(name = "handler", length = 255)
	private String handler;

	@Column(name = "interval", length = 80)
	private String interval;

	@Column(name = "repeat")
	private Integer repeat;

	@Column(name = "ok_count")
	private Integer okCount;

	@Column(name = "ng_count")
	private Integer ngCount;

	@Column(name = "status", length = 10)
	private String status;

	@Column(name = "trace")
	private Boolean trace;

	@Column(name = "result", length = 4000)
	private String result;

	public ScheduleJob() {
	}

	public ScheduleJob(ScheduleJob scheduleJob) {
		this.id = scheduleJob.getId();
		this.name = scheduleJob.getName();
		this.description = scheduleJob.getDescription();
		this.handlerType = scheduleJob.getHandlerType();
		this.handler = scheduleJob.getHandler();
		this.interval = scheduleJob.getInterval();
		this.repeat = scheduleJob.getRepeat();
		this.okCount = scheduleJob.getOkCount();
		this.ngCount = scheduleJob.getNgCount();
		this.status = scheduleJob.getStatus();
		this.trace = scheduleJob.getTrace();
		this.result = scheduleJob.getResult();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHandlerType() {
		return handlerType;
	}

	public void setHandlerType(String handlerType) {
		this.handlerType = handlerType;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public Integer getRepeat() {
		return repeat;
	}

	public void setRepeat(Integer repeat) {
		this.repeat = repeat;
	}

	public Integer getOkCount() {
		return okCount;
	}

	public void setOkCount(Integer okCount) {
		this.okCount = okCount;
	}

	public Integer getNgCount() {
		return ngCount;
	}

	public void setNgCount(Integer ngCount) {
		this.ngCount = ngCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getTrace() {
		return trace;
	}

	public void setTrace(Boolean trace) {
		this.trace = trace;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}