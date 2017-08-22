package org.bambrikii.examples.log4j2;

import static org.bambrikii.examples.log4j2.Log4J2_MultiThreadedTest_Main.LOGGER_LOG4J1;

/**
 * Created by Alexander Arakelyan on 22/08/17 21:56.
 */
public class Log4J1Thread implements Runnable {
	private final int i1;

	public Log4J1Thread(int i1) {
		this.i1 = i1;
	}

	@Override
	public void run() {
		long total = 0;
		for (int i = 0; i < i1; i++) {
			long start = System.currentTimeMillis();
			LOGGER_LOG4J1.debug("asd");
			long stop = System.currentTimeMillis();
			total += stop - start;
		}
		System.out.println(Thread.currentThread().getName() + ": " + total);
	}
}
