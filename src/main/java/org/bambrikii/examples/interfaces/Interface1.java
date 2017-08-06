package org.bambrikii.examples.interfaces;

/**
 * Created by Alexander Arakelyan on 23/07/17 13:43.
 */
public interface Interface1 {
	public static final int INT1 = 1;
	int INT2 = 2;
//	int INT3; // fields are public static final, should be initialized in-place

//	static { // static blocks are not allowed in interfaces
//		INT3 = 3;
//	}

}

interface Interface2 {
	/*protected*/ interface Interface3 {

	}

	/*private*/ interface Interface4 {

	}

	/*final*/ interface Interface5 {

	}
}

abstract interface Interface6 { // abstract modifier is redundant, but allowed

}

interface Interface7 {
	short method1();

	Number method2();
}

interface Interface8 extends Interface7 {
	//	int method1();
	public abstract short method1();

	Integer method2();

	public static class Class8 implements Interface8 {

		public class Class8_2 {

		}

		@Override
		public short method1() {
			return 0;
		}

		@Override
		public Integer method2() {
			return null;
		}
	}

	enum Enum8 {

	}
}

class Class9 {
	public static void main(String[] args) {
		Interface8.Class8 cls = new Interface8.Class8();
		cls.new Class8_2();
	}
}