package com.java.sample.basic.enumsp;

public enum EnumSampleThree {
	USER_INIT_PASS("user.init.pass", "apple"), 
	USER_EXPIRE_DATE("user.expire.date", "10"), 
	USER_ID_LENGTH("user.id.length", "8"), 
	USER_PASSWORD_LENGTH("user.password.length"),
	USER_PASSWORD_LOCK_COUNT("user.password.lock.count");

	public String key;
	public String defaultValue;
	
	EnumSampleThree(String key) {
		this.key = key;
	}

	EnumSampleThree(String key, String defaultValue) {
		this.key = key;
		this.defaultValue = defaultValue;
	}
}
