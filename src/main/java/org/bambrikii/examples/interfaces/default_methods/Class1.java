package org.bambrikii.examples.interfaces.default_methods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 28/10/17 20:30.
 */
public class Class1 {
	public static void main(String[] args) {
		Interface9 interface9 = new Interface9() {
		};
		interface9.method1();
		Interface9.method2();
		List<String> list1 = new ArrayList<String>() {

		};
	}

	class _ {

	}
}
