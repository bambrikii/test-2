package org.bambrikii.examples.log4j2;


import static org.bambrikii.examples.log4j2.Log4J2_MultiThreadedTest_Main.LOGGER_LOG4J2;

/**
 * Created by Alexander Arakelyan on 22/08/17 21:53.
 */
public class Log4J2Thread implements Runnable {
	private final int i1;

	public Log4J2Thread(int i1) {
		this.i1 = i1;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < i1; i++) {
			LOGGER_LOG4J2.debug("asd");
		}
		long stop = System.currentTimeMillis();
		System.out.println(Thread.currentThread().getName() + ": " + (stop - start));
	}
}
