package org.bambrikii.examples.lambdas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 17/07/17 20:30.
 */
public class Lambda3 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.removeIf(s -> s.isEmpty());
//		list.removeIf(s -> {
//			s.isEmpty()
//		});
//		list.removeIf(s -> {
//			s.isEmpty();
//		});
		list.removeIf(s -> {
			return s.isEmpty();
		});
//		list.removeIf(String s -> s.isEmpty());
		list.removeIf((String s) -> s.isEmpty());

		method2((str1, str2) -> true);
	}

	public static void method2(Interface1 interface1) {
	}

	public interface Interface1 {
		boolean method1(String str1, String str2);
	}
}
