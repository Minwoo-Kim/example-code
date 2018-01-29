package com.java.sample.advanced;

public class RunnableSamp {
	public static void main(String[] args) {
		new RunnableSamp().execute();
	}

	public void execute() {
		Runnable runnable = () -> this.sample();

		// Sample-1
		ThreadUtil.doSynch(runnable);

		// Sample-2
		ThreadUtil.doSynch(() -> this.sample());

		// Sample-3
		ThreadUtil.doAsynch(runnable);

		// Sample-4
		ThreadUtil.doSynch(() -> this.sample());

		System.out.println("End");
	}

	public void sample() {
		System.out.println("Just Test-!!");
	}
}

class ThreadUtil {
	/**
	 * 동 방식의 스레드 실행.
	 * 
	 * @param runnable
	 */
	public static void doSynch(Runnable runnable) {
		new SimpleThreadClass(runnable).start();
	}

	/**
	 * 비동 방식의 스레드 실행.
	 * 
	 * @param runnable
	 */
	public static void doAsynch(Runnable runnable) {
		new AsynchThreadClass(runnable).start();
	}
}

class SimpleThreadClass {
	Runnable runnable;

	public SimpleThreadClass(Runnable runnable) {
		this.runnable = runnable;
	}

	public void start() {
		runnable.run();
	}
}

class AsynchThreadClass implements Runnable {
	Runnable runnable;

	public AsynchThreadClass(Runnable runnable) {
		this.runnable = runnable;
	}

	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		runnable.run();
	}
}