package org.bambrikii.examples.nio_selector;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Calendar;
import java.util.Iterator;

/**
 * Created by Alexander Arakelyan on 06/01/17 10:19.
 * <p>
 * http://tutorials.jenkov.com/java-nio/selectors.html
 * http://tutorials.jenkov.com/java-nio/channels.html
 * https://examples.javacodegeeks.com/core-java/nio/channels/selector-channels/java-nio-channels-selector-example/
 * http://www.javaworld.com/article/2073344/core-java/use-select-for-high-speed-networking.html
 * http://mindprod.com/jgloss/bytebuffer.html
 */
public class PipeTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		Selector selector = Selector.open();
		Pipe pipe = Pipe.open();

		Pipe.SinkChannel sink = pipe.sink();
		sink.configureBlocking(false);
		SelectionKey sinkSelectorKey = sink.register(selector, SelectionKey.OP_WRITE);
//		sinkSelectorKey.attach(new Object());

		Pipe.SourceChannel source = pipe.source();
		source.configureBlocking(false);
		SelectionKey sourceSelectorKey = source.register(selector, SelectionKey.OP_READ);
//		sourceSelectorKey.attach(new Object());

		Thread sinkThread = new Thread() {
			@Override
			public void run() {
				int i = 0;
				long prevMillis = 0;
				while (!currentThread().isInterrupted() && sink.isOpen()) {

					ByteBuffer l1 = ByteBuffer.allocate(Long.BYTES);
					long timeInMillis = Calendar.getInstance().getTimeInMillis();
					if (timeInMillis == prevMillis) {
						i = i + 1;
					} else {
						i = 0;
						prevMillis = timeInMillis;
					}
					l1.putLong(timeInMillis * 1000 + i);
					l1.flip();
					try {
						sink.write(l1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
						break;
					}
				}
			}
		};
		sinkThread.start();

//		selector.select();

		Thread sourceThread = new Thread() {
			@Override
			public void run() {
				w:
				while (!currentThread().isInterrupted()) {
					try {
						int selected = selector.select();
						System.out.println("selected: " + selected);
						if (selected > 0) {
							Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
							while (keys.hasNext()) {
								SelectionKey key = keys.next();
								System.out.println(" current channel: class = " + key.channel().getClass().getName() + ", OP_READ = " + (key.interestOps() & SelectionKey.OP_READ));
								if ((key.interestOps() & SelectionKey.OP_READ) > 0) {
									SelectableChannel channel = key.channel();
									ByteBuffer l2 = ByteBuffer.allocate(Long.BYTES);
									try {
										((ReadableByteChannel) channel).read(l2);
									} catch (IOException e) {
										e.printStackTrace();
									}
									l2.rewind();
									long aLong = l2.getLong();
									if (aLong != 0) {
										System.out.println(" data read: " + aLong);
									}
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										e.printStackTrace();
										break w;
									}
								}
								keys.remove();
							}
						}
					} catch (IOException ex) {
						ex.printStackTrace();
						break w;
					}
				}
			}
		};
		sourceThread.start();
		Thread.sleep(10000);
		sinkThread.interrupt();
		sourceThread.interrupt();
	}
}
