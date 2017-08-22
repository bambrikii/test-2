package org.bambrikii.examples.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alexander Arakelyan on 22/08/17 21:17.
 */
public class Log4J2_MultiThreadedTest_Main {
	public static final Logger LOGGER_LOG4J2 = LogManager.getLogger(Log4J2_MultiThreadedTest_Main.class);
	public static final org.apache.log4j.Logger LOGGER_LOG4J1 = org.apache.log4j.Logger.getLogger(Log4J2_MultiThreadedTest_Main.class);

	public static void main(String[] args) throws InterruptedException {
//		int i1 = 10000000;
		int i1 = 1000000;

		int parallelism = Runtime.getRuntime().availableProcessors();
		ExecutorService pool2 = Executors.newFixedThreadPool(parallelism);
		long start = System.currentTimeMillis();
		for (int i = 0; i < parallelism; i++) {
			pool2.submit(new Log4J2Thread(i1));
		}
		pool2.shutdown();
		pool2.awaitTermination(3, TimeUnit.MINUTES);
		long stop = System.currentTimeMillis();
		System.out.println("Pool2: " + (stop - start));
//
		ExecutorService pool1 = Executors.newFixedThreadPool(parallelism);
		start = System.currentTimeMillis();
		for (int i = 0; i < parallelism; i++) {
			pool1.submit(new Log4J1Thread(i1));
		}
		pool1.shutdown();
		pool1.awaitTermination(3, TimeUnit.MINUTES);
		stop = System.currentTimeMillis();
		System.out.println("Pool1: " + (stop - start));
	}
}
