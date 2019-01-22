package com.java.sample.basic.sp_basic;

import java.util.ArrayList;
import java.util.List;

public class LabelSamp {
	public static void main(String[] args) {
		new LabelSamp().labelSample();
	}

	public void labelSample() {
		List<Integer> aList = this.generateList(100);
		List<Integer> bList = this.generateList(100);

		LIST_A:
		for (Integer a : aList) {
			for (Integer b : bList) {
				if (a + b > 150)
					break LIST_A;
			}

			System.out.println(a);
		}

	}

	private List<Integer> generateList(int size) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			list.add(i);
		}
		return list;
	}
}
