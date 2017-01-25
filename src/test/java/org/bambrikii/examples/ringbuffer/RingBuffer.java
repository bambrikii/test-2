package org.bambrikii.examples.ringbuffer;

import java.lang.reflect.Array;
import java.lang.reflect.TypeVariable;

/**
 * Created by Alexander Arakelyan on 25/01/17 13:26.
 * <p/>
 * Uses backing array to store values.
 * Return value as if popped out from a linked list.
 */
public class RingBuffer<T> implements RingBufferable<T> {
	private T[] arr;
	private int pos;
	private int tail;
	private int len;
	private boolean full;
	private boolean empty;

	public RingBuffer(int size) {
		Class<T> cls = ((Class<T>) ((TypeVariable) getClass().getTypeParameters()[0]).getBounds()[0]);
		arr = (T[]) Array.newInstance(cls, size);
		pos = -1;
		len = size;
		tail = len - 1;
		empty = true;
		full = false;
	}

	@Override
	public boolean push(T elem) {
		if (full) {
			return false;
		}
		if (pos == tail && !empty) {
			full = true;
			return false;
		}
		int nextPos = calcNextPos();
		empty = false;
		full = (nextPos == tail);
		return push(elem, nextPos);
	}

	private int calcNextPos() {
		int nextPos = pos + 1;
		if (nextPos > len - 1) {
			nextPos = 0;
		}
		return nextPos;
	}

	private boolean push(T elem, int newPos) {
		arr[newPos] = elem;
		pos = newPos;
		return true;
	}

	@Override
	public T pop() {
		if (empty) {
			return null;
		}
		if (tail == pos && !full) {
			return null;
		}
		int nextTail = calcNextTail();
		empty = (nextTail == pos);
		full = false;
		return pop(nextTail);
	}

	private int calcNextTail() {
		int nextTail = tail + 1;
		if (nextTail > len - 1) {
			nextTail = 0;
		}
		return nextTail;
	}

	private T pop(int newTail) {
		T ret = arr[newTail];
		tail = newTail;
		return ret;
	}
}
