package algorithm.performance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import algorithm.util.ServiceUtil;

public class Stream_ {
	public static void main(String[] args) {
		Stream_ stream = new Stream_();
		// stream.exam1();
		stream.exam2();
	}

	public void exam1() {
		int limit = 1000000;

		long elapseTime1 = ServiceUtil.doExecute(() -> IntStream.range(1, limit).forEach(System.out::println));

		long elapseTime2 = ServiceUtil.doExecute(() -> {
			for (int i = 1; i < limit; i++)
				System.out.println(i);
		});

		ServiceUtil.doPrintElpaseTime(elapseTime1, elapseTime2);
	}

	public void exam2() {
		int limit = 10;

		int[] arr = IntStream.range(1, limit).toArray();

		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < arr.length; i++)
			list.add(i);
		
		long elapseTime1 = ServiceUtil.doExecute(() -> {
			long sum = IntStream.range(1, limit).mapToLong(i -> i).sum();
			System.out.println(sum);
		});

		long elapseTime2 = ServiceUtil.doExecute(() -> {
			long sum = 0;

			for (int i = 1; i < limit; i++)
				sum += i;

			System.out.println(sum);
		});

		ServiceUtil.doPrintElpaseTime(elapseTime1, elapseTime2);
	}
	
	public void exam3() {
		int limit = 10;
		
		int[] arr = IntStream.range(1, limit).toArray();
		
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < arr.length; i++)
			list.add(i);
		
		
		long elapseTime1 = ServiceUtil.doExecute(() -> {
			long sum = list.stream().mapToInt(i -> i).sum();
			System.out.println(sum);
		});
		
		long elapseTime2 = ServiceUtil.doExecute(() -> {
			long sum = 0;
			
			for (int i = 1; i < limit; i++)
				sum += i;
			
			System.out.println(sum);
		});
		
		ServiceUtil.doPrintElpaseTime(elapseTime1, elapseTime2);
	}
}
