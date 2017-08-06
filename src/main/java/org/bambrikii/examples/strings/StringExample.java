package org.bambrikii.examples.strings;

/**
 * Created by Alexander Arakelyan on 16/07/17 14:25.
 */
public class StringExample {
	public static void main(String[] args) {
		stringConcatEquals();
		stringBuilderSubstring();
		stringBuilderDelete();
		stringBuilderDelete2();
	}

	private static void stringBuilderDelete2() {
//		StringBuilder b = "rumble"; // Compilation Error
//		b.append(4).deleteCharAt(3).delete(3, b.length() - 1);
//		System.out.println(b);
	}

	private static void stringBuilderDelete() {
		StringBuilder numbers = new StringBuilder("0123456789");
		numbers.delete(2, 8);
		numbers.append("-").insert(2, "+");
		System.out.println(numbers);
	}

	private static void stringBuilderSubstring() {
		try {
			int total = 0;
			StringBuilder letters = new StringBuilder("abcdefg");
			total += letters.substring(1, 2).length();
			total += letters.substring(6, 6).length();
			total += letters.substring(6, 5).length();
			System.out.println(total);
		} catch (StringIndexOutOfBoundsException ex) {
			ex.printStackTrace();
		}
	}

	private static void stringConcatEquals() {
		String a = "";
		a += 2;
		a += 'c';
		a += false;
		if (a == "2cfalse") System.out.println("==");
		if (a.equals("2cfalse")) System.out.println("equals");
	}
}
