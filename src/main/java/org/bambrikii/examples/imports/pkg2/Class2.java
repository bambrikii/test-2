package org.bambrikii.examples.imports.pkg2;

import org.bambrikii.examples.imports.pkg1.Class1;

import static org.bambrikii.examples.imports.pkg1.Class1.INT1;
import static org.bambrikii.examples.imports.pkg1.Class1.INT1;
import static org.bambrikii.examples.imports.pkg1.Class1.getInt1;

/**
 * Created by Alexander Arakelyan on 16/07/17 20:38.
 */
public class Class2 {
	public static int INT1 = 3;

	public static int getInt1() {
		return 4;
	}

	public static void main(String[] args) {
		System.out.println(INT1);
		System.out.println(getInt1());
	}
}
