package com.java.sample.basic.sp_foreach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForEachSamp {
	public static void main(String[] args) {
		ForEachSamp forEacheEx = new ForEachSamp();
		forEacheEx.mapForEach();
		forEacheEx.listForEach();
		forEacheEx.listForEachMap();
	}

	public void mapForEach() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("A", "Apple");
		map.put("B", "Banana");
		map.put("C", "Cherry");

		map.forEach((k, v) -> {
			System.out.println(k + " : " + v);
		});
	}

	public void listForEach() {
		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");

		list.forEach(x -> {
			System.out.println(x);
		});
	}

	public void listForEachMap() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("A", "Apple");
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("B", "Banana");
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("C", "Cherry");

		list.add(map1);
		list.add(map2);
		list.add(map3);

		list.forEach(x -> {
			x.forEach((k, v) -> {
				System.out.println(x + " : " + k + " - " + v);
			});
		});
	}
}
