package com.example.quartz.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User Repository
 * 
 * @author Minu.Kim
 */
public interface ScheduleJobRepository extends JpaRepository<ScheduleJob, String> {
	
	/**
	 * find ScheduleJob by ID
	 *  
	 * @param id
	 * @return
	 */
	public ScheduleJob readUserById(String id);

	/**
	 * find ScheduleJob by name
	 * 
	 * @param name
	 * @return
	 */
	public List<ScheduleJob> findByNameLike(String name);
}
