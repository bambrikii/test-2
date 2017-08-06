package org.bambrikii.examples.daemon_shutdown;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alexander Arakelyan on 30/01/17 20:44.
 * <p>
 * http://stackoverflow.com/questions/8663107/how-does-the-jvm-terminate-daemon-threads-or-how-to-write-daemon-threads-that-t
 */
public class DaemonThreadPlay {
	public static void main(String[] args) throws InterruptedException {
		Runnable runnable = () -> {
			while (!Thread.currentThread().isInterrupted()) {
//			while (true) {
				try {
					System.out.println("Try block executed " + Thread.currentThread().getName() + "@" + Thread.currentThread().getId());
					Thread.sleep(1000);
				} catch (Throwable t) {
					t.printStackTrace();
					return;
				}
			}
		};
		Thread daemonThread = new Thread(runnable, "thread1") {
			@Override
			public void finalize() {
				System.out.println("Finalize method called");
			}
		};
//		daemonThread.setDaemon(true);
//		daemonThread.start();

		ScheduledExecutorService execPool = Executors.newScheduledThreadPool(1, runnable1 -> {
			Thread thread = Executors.defaultThreadFactory().newThread(runnable1);
//			thread.setDaemon(true);
			return thread;
		});
		execPool.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);

//		daemonThread.interrupt();
		try {
			Thread.sleep(1500l);
			System.out.println(daemonThread.getState());
			execPool.shutdownNow();
//			Thread.sleep(100500l);
			Runtime.getRuntime().addShutdownHook(daemonThread);
		} catch (Throwable t) {
			//NO-OP
		}
	}
}
