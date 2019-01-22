package com.java.sample.basic.sp_others;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HashCodeSamp {
	public static void main(String[] args) {
		// new HashCodeSamp().samp1();
		new HashCodeSamp().samp2();
	}

	public void samp1() {
		String a = "AA";
		String b = "BB";
		String c = "AA";
		String d = new String("AA");

		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		System.out.println(c.hashCode());
		System.out.println(d.hashCode());

		System.out.println(a.equals(c));
		System.out.println(a.equals(d));
		System.out.println(a == c);
		System.out.println(a == d);

		Integer aa = 10000000;
		Integer bb = 10000000;

		System.out.println(aa.hashCode());
		System.out.println(bb.hashCode());

		System.out.println(aa.equals(bb));
		System.out.println(aa == bb);

		Object o = new Object();
		Object o1 = new Object();
		System.out.println(o.hashCode());
		System.out.println(o1.hashCode());

		System.out.println(o.equals(o1));
		System.out.println(o == o1);
	}

	public void samp2() {
		{
			String value = "FGHJKLUK".chars()
					.distinct()
					.mapToObj(c -> String.valueOf((char) c))
					.sorted()
					.collect(Collectors.joining());

			System.out.println(value);
		}

		{
			List<String> arrays = Arrays.asList("aaa", "bbb", "cccc", "bbb");

			String value = arrays.stream()
					.distinct()
					.sorted()
					.collect(Collectors.joining());

			System.out.println(value);
		}

		{
			String value = Pattern.compile(":")
					.splitAsStream("foobar:foo:bar")
					.filter(s -> s.contains("bar"))
					.sorted()
					.collect(Collectors.joining(":"));

			System.out.println(value);
		}

		{
			List<String> list = Arrays.asList("java", "python", "nodejs", "ruby");

			// java | python | nodejs | ruby
			String value = list.stream()
					.map(x -> x)
					.collect(Collectors.joining(" | "));

			System.out.println(value);
		}
	}
}
