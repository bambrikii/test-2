package org.bambrikii.examples.ringbuffer;

import java.lang.reflect.Array;
import java.lang.reflect.TypeVariable;

/**
 * Created by Alexander Arakelyan on 25/01/17 13:26.
 * <p>
 * Uses backing array to store values.
 * Last value is always stored and is non popped out.
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
		int newPos = pos + 1;
		if (newPos > len - 1) {
			if (newPos == tail) {
				return false;
			}
			newPos = newPos % (len - 1) - 1;
			if (newPos < tail) {
				return push(elem, newPos);
			} else if (newPos > tail) {
				return push(elem, newPos);
			}
		} else if (newPos < tail) {
			return push(elem, newPos);
		} else if (newPos > tail) {
			return push(elem, newPos);
		}

		return false;
	}

	private boolean push(T elem, int newPos) {
		arr[newPos] = elem;
		pos = newPos;
		return true;
	}

	@Override
	public T pop() {
		int newTail = tail + 1;
		if (newTail > len - 1) {
			newTail = 0;
			if (newTail < pos) {
				return pop(newTail);
			} else if (newTail > pos) {
				return pop(newTail);
			}
		} else {
			if (newTail > pos) {
				return pop(newTail);
			} else if (newTail < pos) {
				return pop(newTail);
			}
		}
		return null;
	}

	private T pop(int newTail) {
		T ret = arr[newTail];
		tail = newTail;
		return ret;
	}
}
