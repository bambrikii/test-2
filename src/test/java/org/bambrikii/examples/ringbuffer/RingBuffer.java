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

	public RingBuffer(int size) {
		Class<T> cls = ((Class<T>) ((TypeVariable) getClass().getTypeParameters()[0]).getBounds()[0]);
		arr = (T[]) Array.newInstance(cls, size);
		pos = -1;
		len = size;
		tail = len;
	}

	@Override
	public boolean push(T elem) {
		int nextPos = calcNextPos();
		if (nextPos > len - 1) {
			if (nextPos == tail) {
				return false;
			}
			nextPos = nextPos % (len - 1) - 1;
			if (nextPos < tail) {
				return push(elem, nextPos);
			} else if (nextPos > tail) {
				return push(elem, nextPos);
			}
		} else if (nextPos < tail) {
			return push(elem, nextPos);
		} else if (nextPos > tail) {
			return push(elem, nextPos);
		}

		return false;
	}

	private int calcNextPos() {
		return pos + 1;
	}

	private boolean push(T elem, int newPos) {
		arr[newPos] = elem;
		pos = newPos;
		return true;
	}

	@Override
	public T pop() {
		if (tail == pos) {
			return null;
		}
		int nextTail = calcNextTail();
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
