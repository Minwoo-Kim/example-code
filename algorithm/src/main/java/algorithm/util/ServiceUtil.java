package algorithm.util;

import java.util.StringJoiner;

public class ServiceUtil {

	/**
	 * Service 실행
	 * 
	 * @param api
	 * @return ElapseTime(milliseconds)
	 */
	public static long doExecute(Runnable api) {
		long startTime = System.currentTimeMillis();

		api.run();

		return System.currentTimeMillis() - startTime;
	}

	/**
	 * Print ElapseTime
	 * 
	 * @param elapseTimes
	 */
	public static void doPrintElpaseTime(long... elapseTimes) {
		StringJoiner contents = new StringJoiner(System.lineSeparator());

		for (int i = 0; i < elapseTimes.length; i++)
			contents.add(String.format("Exam_%s : %s ms", (i + 1), elapseTimes[i]));

		System.out.println(contents);
	}
}