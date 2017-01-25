package org.bambrikii.examples.ringbuffer;

/**
 * Created by Alexander Arakelyan on 25/01/17 13:24.
 */
public interface RingBufferable<T> {
	boolean push(T elem);

	T pop();
}
