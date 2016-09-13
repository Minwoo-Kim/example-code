/**
 * 
 */
package com.example.quartz.config;

/**
 * Constants
 * 
 * @author Minu.Kim
 */
public class Constants {
	public static final String APP_CONTEXT_NAME = "APP";
	
	/**
	 * JOB MODEL ID
	 */
	public static final String STR_JOB_MODEL_ID = "SCHEDULE_JOB_ID";
	
	/**
	 * JOB MODEL CLASS
	 */
	public static final String STR_JOB_MODEL_CLASS = "JOB_MODEL_CLASS";
	
	/**
	 * JOB Status - 실행 중 
	 */
	public static final String STATUS_RUNNING = "RUNNING";
	/**
	 * JOB Status - 일시 중지 
	 */
	public static final String STATUS_PAUSED = "PAUSED";
	/**
	 * JOB Status - 삭제됨 
	 */
	public static final String STATUS_DELETED = "DELETED";
	
	/**
	 * JOB Action - 실행 
	 */
	public static final String ACTION_START = "START";
	/**
	 * JOB Action - 재실행 
	 */
	public static final String ACTION_RESUME = "RESUME";	
	/**
	 * JOB Action - 일시 중지  
	 */
	public static final String ACTION_PAUSE = "PAUSE";
	/**
	 * JOB Action - 변경  
	 */
	public static final String ACTION_CHANGE = "CHANGE";
	/**
	 * JOB Action - 삭제 
	 */
	public static final String ACTION_DELETE = "DELETE";
	
	/**
	 * Execution - OK
	 */
	public static final String EXECUTION_OK = "OK";
	/**
	 * Execution - NG
	 */
	public static final String EXECUTION_NG = "NG";	
}