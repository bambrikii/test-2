package org.bambrikii.examples.class_design1;

/**
 * Created by Alexander Arakelyan on 22/07/17 22:46.
 */
public class Class3 {
	int int1 = 1;

	public void print() {
		System.out.println(int1);
	}

	public static void main(String[] args) {
		Class3 cls3 = new Class4();
		cls3.print();
	}
}

class Class4 extends Class3 {
	int int1 = 2;

	public void print() {
		System.out.println(int1);
	}
}