package com.java.sample;

public class ThreadSample {
	public static void main(String[] args) {
		ThreadSample exe = new ThreadSample();
		exe.execute();
	}

	public void execute() {
		SimpleThread thread = new SimpleThread(() -> this.sample("Just Test-!!"));
		thread.start();
	}

	public void sample(String value) {
		System.out.println(value);
	}

	private class SimpleThread {
		Runnable runnable;

		public SimpleThread(Runnable runnable) {
			this.runnable = runnable;
		}

		public void start() {
			runnable.run();
		}
	}
}
