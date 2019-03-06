package algorithm.euler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import algorithm.util.ServiceUtil;

/**
 * 10보다 작은 자연수 중에서 3 또는 5의 배수는 3, 5, 6, 9 이고, 이것을 모두 더하면 23입니다.
 * 1000보다 작은 자연수 중에서 3 또는 5의 배수를 모두 더하면 얼마일까요?
 */
public class Problem1 {
	public static void main(String[] args) {
		int param = 10000000;

		Problem1 p1 = new Problem1();
		long exam1ElapsTime = ServiceUtil.doExecute(() -> p1.example1(param));
		long exam2ElapsTime = ServiceUtil.doExecute(() -> p1.example2(param));
		long exam3ElapsTime = ServiceUtil.doExecute(() -> p1.example3(param));
		long exam4ElapsTime = ServiceUtil.doExecute(() -> p1.example4(param));

		ServiceUtil.doPrintElpaseTime(exam1ElapsTime, exam2ElapsTime, exam3ElapsTime, exam4ElapsTime);
	}

	/**
	 * Pure Code (ElapseTime : 18)
	 * 
	 * @param value
	 */
	public void example1(int value) {
		long sum = 0;
		for (int i = 1; i < value; i++) {
			if (i % 3 == 0 || i % 5 == 0)
				sum += i;
		}

		System.out.println(sum);
	}

	/**
	 * With Predicate & IntStream.range & stream
	 * (ElapseTime : 873)
	 * 
	 * @param value
	 */
	public void example2(int value) {
		List<Integer> list = new ArrayList<Integer>();
		Predicate<Integer> cal = v -> (v % 3 == 0 || v % 5 == 0);

		IntStream.range(1, value).forEach(i -> {
			if (cal.test(i))
				list.add(i);
		});

		Long sum = list.stream().mapToLong(v -> v).sum();
		System.out.println(sum);
	}

	/**
	 * With IntStream.range & stream
	 * (ElapseTime : 417)
	 * 
	 * @param value
	 */
	public void example3(int value) {
		List<Integer> list = new ArrayList<Integer>();

		IntStream.range(1, value).forEach(i -> {
			if (i % 3 == 0 || i % 5 == 0)
				list.add(i);
		});

		Long sum = list.stream().mapToLong(v -> v).sum();
		System.out.println(sum);
	}

	/**
	 * With Predicate
	 * (ElapseTime : 181)
	 * 
	 * @param value
	 */
	public void example4(int value) {
		long sum = 0;
		Predicate<Integer> cal = v -> (v % 3 == 0 || v % 5 == 0);

		for (int i = 1; i < value; i++) {
			if (cal.test(i))
				sum += i;
		}

		System.out.println(sum);
	}
}
