package ueb03;

import java.util.NoSuchElementException;

public class StackImpl<T> implements Stack<T> {
	class Element {
		T value;
		Element next;
		Element(T value, Element next) {
			this.value = value;
			this.next = next;
		}
	}

	private Element top;
	private int s = 0;


	@Override
	public void push(T c) {
		top = new Element(c, top);
		s++;
	}

	@Override
	public T pop() {
		if (top == null)
			throw new NoSuchElementException();
		T v = top.value;
		top = top.next;
		s--;
		return v;
	}

	@Override
	public int size() {
		return s;
	}
}
