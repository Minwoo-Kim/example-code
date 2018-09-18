package com.java.sample.lambda.vo;

public class Student {
	private String name;
	private int englishScore;
	private int mathScore;
	private String sex;

	public Student(String name, int englishScore, int mathScore) {
		super();
		this.name = name;
		this.englishScore = englishScore;
		this.mathScore = mathScore;
	}

	public Student(String name, int englishScore, int mathScore, String sex) {
		super();
		this.name = name;
		this.englishScore = englishScore;
		this.mathScore = mathScore;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEnglishScore() {
		return englishScore;
	}

	public void setEnglishScore(int englishScore) {
		this.englishScore = englishScore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
