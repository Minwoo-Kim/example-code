package com.java.sample;

public class ThreadUtilSample {
	public static void main(String[] args) {
		new ThreadUtilSample().execute();
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
		new SimpleThread(runnable).start();
	}

	/**
	 * 비동 방식의 스레드 실행.
	 * 
	 * @param runnable
	 */
	public static void doAsynch(Runnable runnable) {
		new AsynchThread(runnable).start();
	}
}

class SimpleThread {
	Runnable runnable;

	public SimpleThread(Runnable runnable) {
		this.runnable = runnable;
	}

	public void start() {
		runnable.run();
	}
}

class AsynchThread implements Runnable {
	Runnable runnable;

	public AsynchThread(Runnable runnable) {
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