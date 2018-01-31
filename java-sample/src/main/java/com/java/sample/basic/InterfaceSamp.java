package com.java.sample.basic;

public interface InterfaceSamp {
	// 상수 필드
	public final int MIN_VALUE = 0;
	public static final int MAX_VALUE = 100;

	// 추상 메소드
	public abstract void run();

	// 기본 인터페이스 메소드
	public String getData(String contents);

	// 디폴트 메소드 : 실행 내용까지 작성이 가능하다.
	public default void setState(boolean state) {
		if (state) {
			System.out.println("현재 상태는 정상입니다");
		} else {
			System.out.println("현재 상태는 비정상입니다");
		}
	}

	// 정적 메소드
	public static void change() {
		System.out.println("상태를 변경합니다.");
	}
}
