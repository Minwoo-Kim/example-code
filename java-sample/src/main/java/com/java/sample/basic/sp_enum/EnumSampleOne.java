package com.java.sample.basic.sp_enum;

public enum EnumSampleOne {
	BANANA("Banana", "Yellow"), 
	APPLE("Apple", "Red"), 
	LEMON("Lemon", "Yellow"), 
	TOMATO("Tomato","Red"), 
	MELON("Melon", "Green");

	private String m_sName;
	private String m_sColor;

	EnumSampleOne(String sName, String sColor) {
		m_sName = sName;
		m_sColor = sColor;
	}

	public String getColor() {
		return m_sColor;
	}

	public String getName() {
		return m_sName;
	}
}
