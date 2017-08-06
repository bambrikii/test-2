package org.bambrikii.examples.imports.rope3;

/**
 * Created by Alexander Arakelyan on 16/07/17 21:58.
 */
import org.bambrikii.examples.imports.rope3.rope.*;
import static org.bambrikii.examples.imports.rope3.rope.Rope.*;
public class RopeSwing {
	private static Rope rope1 = new Rope();
	private static Rope rope2 = new Rope();
	{
		System.out.println(rope1.length);
	}
	public static void main(String[] args) {
		rope1.length = 2;
		rope2.length = 8;
		System.out.println(rope1.length); // 8
	}
}
