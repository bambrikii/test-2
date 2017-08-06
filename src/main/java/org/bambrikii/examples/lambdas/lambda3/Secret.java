package org.bambrikii.examples.lambdas.lambda3;

interface Secret {
	String magic(double d);
}

class MySecret implements Secret {
	public String magic(double d) {
		return "Poof";
	}
}

class Secret2 {
	public static void main(String[] args) {
		caller((e) -> "Poof");
//		caller((e) -> {
//			"Poof"
//		});
//		caller((e) -> {
//			String e = "";
//			"Poof"
//		});
//		caller((e) -> {
//			String e = "";
//			return "Poof";
//		});
//		caller((e) -> {
//			String e = "";
//			return "Poof"
//		});
		caller((e) -> {
			String f = "";
			return "Poof";
		});
	}

	public static void caller(Secret secret) {
		System.out.println("caller " + secret.magic(0));
	}
}