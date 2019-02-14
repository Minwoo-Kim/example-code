package com.java.sample.basic.common.sp_runnable;

public class RunnableSynchSamp {
	public static void main(String[] args) {
		RunnableSynchSamp exe = new RunnableSynchSamp();
		exe.execute();
	}

	public void execute() {
		RunnableSynch thread = new RunnableSynch(() -> this.sample("Just Test-!!"));
		thread.start();
	}

	public void sample(String value) {
		System.out.println(value);
	}

	private class RunnableSynch {
		Runnable runnable;

		public RunnableSynch(Runnable runnable) {
			this.runnable = runnable;
		}

		public void start() {
			runnable.run();
		}
	}
}
