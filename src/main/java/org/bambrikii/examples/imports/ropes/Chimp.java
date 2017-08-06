package org.bambrikii.examples.imports.ropes;

/**
 * Created by Alexander Arakelyan on 16/07/17 20:58.
 */
import org.bambrikii.examples.imports.ropes.rope.*;
import static org.bambrikii.examples.imports.ropes.rope.Rope.*;
public class Chimp {
	public static void main(String[] args) {
		Rope.swing();
		new Rope().swing();
		System.out.println(LENGTH);
	}
}