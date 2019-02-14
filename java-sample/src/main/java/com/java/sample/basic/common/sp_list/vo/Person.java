package com.java.sample.basic.common.sp_list.vo;

public class Person {
	private int pid;
	private String name;

	public Person() {
	}

	public Person(int pid, String name) {
		this.pid = pid;
		this.name = name;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return Integer.toString(this.pid) + '-' + this.name;
	}
}