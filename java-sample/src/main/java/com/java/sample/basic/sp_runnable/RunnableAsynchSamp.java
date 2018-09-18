package com.java.sample.basic.sp_runnable;

public class RunnableAsynchSamp {
	public static void main(String[] args) {
		RunnableAsynchSamp exe = new RunnableAsynchSamp();
		exe.execute();
	}

	public void execute() {
		RunnableAsynch thread = new RunnableAsynch(() -> new RunnableAsynchSamp().sample("Just Test-!!"));
		thread.start();

		System.out.println("End");
	}

	public void sample(String value) {
		System.out.println(value);
	}

	private class RunnableAsynch implements Runnable {
		Runnable runnable;

		public RunnableAsynch(Runnable runnable) {
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