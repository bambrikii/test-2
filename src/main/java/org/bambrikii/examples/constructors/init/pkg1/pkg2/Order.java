package org.bambrikii.examples.constructors.init.pkg1.pkg2;

/**
 * Created by Alexander Arakelyan on 16/07/17 22:34.
 */
public class Order {
	String value = "t";

	{
		value += "a";
	}

	{
		value += "c";
	}

	public Order() {
		value += "b";
	}

	public Order(String s) {
		value += s;
	}

	public static void main(String[] args) {
		Order order = new Order("f");
		order = new Order();
		System.out.println(order.value); // tacb
	}
}