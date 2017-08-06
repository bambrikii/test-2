package org.bambrikii.examples.overloads;

/**
 * Created by Alexander Arakelyan on 16/07/17 22:08.
 */
public class Overload1 {
	public void print(byte x) {
		System.out.print("byte");
	}

	public void print(int x) {
		System.out.print("int");
	}

	public void print(float x) {
		System.out.print("float");
	}

	public void print(Object x) {
		System.out.print("Object");
	}

	public static void main(String[] args) {
		Overload1 t = new Overload1();
		short s = 123;
		t.print(s); // int
		t.print(true); // Object
		t.print(6.789); // Object
	}

}