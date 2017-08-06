package org.bambrikii.examples.overloads;

/**
 * Created by Alexander Arakelyan on 16/07/17 22:43.
 */
public class Overload3 {
	public static void overload3(int arg) {
		System.out.println("int");
	}

	public static void overload3(long arg) {
		System.out.println("long");
	}

	public static void overload3(Object int1) {
		System.out.println("Object");
	}

	public static void overload4(int arg) {
		System.out.println("int 4");
	}

//	public static void overload4(Object arg) {
//		System.out.println("int 4");
//	}

	public static void main(String[] args) {
		overload3((short) 1); // int
		overload3((float) 1); // Object
		overload3((Integer) 1); // Object
		overload3((Long) 1l); // Object
		overload4((Integer) 1); // int 4
	}
}
