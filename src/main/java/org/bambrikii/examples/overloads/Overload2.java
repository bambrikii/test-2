package org.bambrikii.examples.overloads;

/**
 * Created by Alexander Arakelyan on 16/07/17 22:38.
 */
public class Overload2 {
	public static void overload2(int[] arr) {
		System.out.println("int arr");
	}

	public static void overload2(Integer[] arr) {
		System.out.println("Integer arr");
	}

	public static void overload2(long[] arr) {
		System.out.println("long arr");
	}

	public static void overload2(Object[] arr) {
		System.out.println("Object arr");
	}

	public static void main(String[] args) {
//		overload2(new short[]{});
		overload2(new int[]{}); // int
		overload2(new long[]{}); // long
		overload2(new Long[]{}); // Object
	}
}
