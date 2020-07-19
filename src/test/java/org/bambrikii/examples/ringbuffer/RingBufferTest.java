package org.bambrikii.examples.ringbuffer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

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
		assertNull(buf.pop());
	}

	@Test
	public void testPopWrapped() {
		RingBuffer<Integer> buf = new RingBuffer<>(3);
		buf.push(1);
		buf.push(2);
		assertEquals(Integer.valueOf(1), buf.pop());
		assertEquals(Integer.valueOf(2), buf.pop());
		buf.push(3);
		assertEquals(Integer.valueOf(3), buf.pop());
		assertNull(buf.pop());
		buf.push(4);
		assertEquals(Integer.valueOf(4), buf.pop());
		buf.push(5);
		assertEquals(Integer.valueOf(5), buf.pop());
		buf.push(6);
		assertEquals(Integer.valueOf(6), buf.pop());
		assertNull(buf.pop());
		buf.push(7);
		assertEquals(Integer.valueOf(7), buf.pop());
		buf.push(8);
		assertEquals(Integer.valueOf(8), buf.pop());
		assertNull(buf.pop());
	}

	@Test
	public void testPushWrapped() {
		RingBuffer<Integer> buf = new RingBuffer<>(3);
		assertTrue(buf.push(1));
		assertTrue(buf.push(2));
		assertTrue(buf.push(3));
		assertFalse(buf.push(4));
		assertEquals(Integer.valueOf(1), buf.pop());
		assertEquals(Integer.valueOf(2), buf.pop());
		assertEquals(Integer.valueOf(3), buf.pop());
		assertNull(buf.pop());
		assertTrue(buf.push(4));
		assertTrue(buf.push(5));
		assertTrue(buf.push(6));
		assertFalse(buf.push(7));
		assertEquals(Integer.valueOf(4), buf.pop());
		assertEquals(Integer.valueOf(5), buf.pop());
		assertEquals(Integer.valueOf(6), buf.pop());
		assertNull(buf.pop());
	}
}
