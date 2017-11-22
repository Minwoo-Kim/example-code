package com.java.sample;

public class ThreadAsynchSample {
	public static void main(String[] args) {
		ThreadAsynchSample exe = new ThreadAsynchSample();
		exe.execute();
	}

	public void execute() {
		AsynchThread thread = new AsynchThread(() -> new ThreadAsynchSample().sample("Just Test-!!"));
		thread.start();

		System.out.println("End");
	}

	public void sample(String value) {
		System.out.println(value);
	}

	private class AsynchThread implements Runnable {
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
}