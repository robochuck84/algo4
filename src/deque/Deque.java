

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private class Node {
		Item value;
		Node next;
		Node prev;
		
		public Node(Item item) {
			value = item;
		}
	}
	
	private Node first = null;
	private Node last = null;
	private int size;
	
	public Deque() {
		this.size = 0;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return size;
	}

	public void addFirst(Item item) {
		if (item == null)
			throw new NullPointerException();
		Node temp = first;
		first = new Node(item);
		first.next = temp;
		temp.prev = first;
		size += 1;
	}

	public void addLast(Item item) {
		if (item == null)
			throw new NullPointerException();
		last.next = new Node(item);
		last.prev = last;
		last = last.next;
		size += 1;
	}

	public Item removeFirst() {
		if (first == null) 
			throw new NoSuchElementException();
		Node temp = first;
		first = first.next;
		first.prev = null;
		temp.next = null;
		temp.prev = null;
		size -= 1;
		return temp.value;
	}

	public Item removeLast() {
		if (first == null) 
			throw new NoSuchElementException();
		Node temp = last;
		last = last.prev;
		last.next = null;
		temp.next = null;
		temp.prev = null;
		size -= 1;
		return temp.value;
	}

	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
			
			Node current = first;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Item next() {
				if (current == null)
					throw new NoSuchElementException(); 
				Node temp = current.next;
				current = current.next;
				return temp.value;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
		};
	}
}
