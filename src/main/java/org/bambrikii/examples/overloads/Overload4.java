package org.bambrikii.examples.overloads;

/**
 * Created by Alexander Arakelyan on 23/07/17 12:33.
 */
public abstract class Overload4 {
	abstract int method1();

	abstract short method2();

	abstract Number method3();
}

class Overload4_2 extends Overload4 {
	int method1() {
		return 0;
	}

	short method2() {
		return 0;
	}

	Integer method3() {
		return 0;
	}

	private class Overload4_3 {

	}
}

//private class Overload4_4 {
//
//}

abstract class Overload4_5 extends Overload4_2 {
	abstract Integer method3();
}