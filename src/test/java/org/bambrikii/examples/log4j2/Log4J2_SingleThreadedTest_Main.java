package org.bambrikii.examples.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Alexander Arakelyan on 22/08/17 21:17.
 */
public class Log4J2_SingleThreadedTest_Main {
	private static final Logger LOGGER = LogManager.getLogger(Log4J2_SingleThreadedTest_Main.class);
	private static final org.apache.log4j.Logger LOGGER1 = org.apache.log4j.Logger.getLogger(Log4J2_SingleThreadedTest_Main.class);

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int i1 = 10000000;
		for (int i = 0; i < i1; i++) {
			LOGGER.debug("asd");
		}
		long stop = System.currentTimeMillis();
		System.out.println(stop - start);

		start = System.currentTimeMillis();
		for (int i = 0; i < i1; i++) {
			LOGGER1.debug("asd");
		}
		stop = System.currentTimeMillis();
		System.out.println(stop - start);
	}
}
