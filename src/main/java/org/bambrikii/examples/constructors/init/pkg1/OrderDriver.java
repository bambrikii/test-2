package org.bambrikii.examples.constructors.init.pkg1;

public class OrderDriver {
	public static void main(String[] args) {
		System.out.print(Order.result + " "); // u
		System.out.print(Order.result + " "); // u
		new Order();
		new Order();
		System.out.print(Order.result + " "); // u u ucrcr
	}
}