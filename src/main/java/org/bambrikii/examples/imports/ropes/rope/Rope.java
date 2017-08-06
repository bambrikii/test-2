package org.bambrikii.examples.imports.ropes.rope;

/**
 * Created by Alexander Arakelyan on 16/07/17 20:58.
 */

public class Rope {
	public static int LENGTH = 5;

	static {
		LENGTH = 10;
	}

	public static void swing() {
		System.out.print("swing ");
	}
}