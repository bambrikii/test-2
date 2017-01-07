package org.bambrikii.examples.nio_selector;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Calendar;
import java.util.Set;

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

		selector.select();

		Thread sinkThread = new Thread() {
			@Override
			public void run() {
				while (!currentThread().isInterrupted() && sink.isOpen()) {

					ByteBuffer l1 = ByteBuffer.allocate(Long.BYTES);
					l1.putLong(Calendar.getInstance().getTimeInMillis());
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
				while (!currentThread().isInterrupted()) {
					Set<SelectionKey> keys = selector.keys();
					for (SelectionKey key : keys) {
						SelectableChannel channel = key.channel();
						if (channel instanceof Pipe.SourceChannel) {
							ByteBuffer l2 = ByteBuffer.allocate(Long.BYTES);
							try {
								((Pipe.SourceChannel) channel).read(l2);
							} catch (IOException e) {
								e.printStackTrace();
							}
							l2.rewind();
							long aLong = l2.getLong();
							if (aLong != 0) {
								System.out.println(" " + aLong);
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
								break;
							}
						}
					}
//			keys.clear();
				}
			}
		};
		sourceThread.start();
		Thread.sleep(10000);
		sinkThread.interrupt();
		sourceThread.interrupt();
	}
}
