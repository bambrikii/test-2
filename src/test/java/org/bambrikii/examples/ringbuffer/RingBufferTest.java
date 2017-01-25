package org.bambrikii.examples.ringbuffer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alexander Arakelyan on 25/01/17 14:17.
 */
public class RingBufferTest {
	@Test
	public void testPush() {
		RingBuffer<Integer> buf = new RingBuffer<>(3);
		assertTrue(buf.push(1));
		assertTrue(buf.push(2));
		assertTrue(buf.push(3));
		assertFalse(buf.push(4));
	}

	@Test
	public void testPop() {
		RingBuffer<Integer> buf = new RingBuffer<>(3);
		buf.push(1);
		buf.push(2);
		buf.push(3);
		assertEquals(Integer.valueOf(1), buf.pop());
		assertEquals(Integer.valueOf(2), buf.pop());
		assertEquals(Integer.valueOf(3), buf.pop());
		assertEquals(null, buf.pop());
	}

	@Test
	public void testPop2() {
		RingBuffer<Integer> buf = new RingBuffer<>(3);
		buf.push(1);
		buf.push(2);
		assertEquals(Integer.valueOf(1), buf.pop());
		assertEquals(Integer.valueOf(2), buf.pop());
		buf.push(3);
		assertEquals(Integer.valueOf(3), buf.pop());
		assertEquals(null, buf.pop());
		buf.push(4);
		assertEquals(Integer.valueOf(4), buf.pop());
		buf.push(5);
		assertEquals(Integer.valueOf(5), buf.pop());
		buf.push(6);
		assertEquals(Integer.valueOf(6), buf.pop());
		buf.push(7);
		assertEquals(Integer.valueOf(7), buf.pop());
		buf.push(8);
		assertEquals(Integer.valueOf(8), buf.pop());
		assertEquals(null, buf.pop());
	}
}
