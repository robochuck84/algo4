import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Deque<Item> queue;
	
	public RandomizedQueue() {
		queue = new Deque<Item>();
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}

	public int size() {
		return queue.size();
	}

	public void enqueue(Item item) {
		int rnd = StdRandom.uniform(2);
		if (rnd == 0)
			queue.addFirst(item);
		else if (rnd == 1)
			queue.addLast(item);
	}

	public Item dequeue() {
		int rnd = StdRandom.uniform(2);
		Item value = null;
		if (rnd == 0)
			value = queue.removeFirst();
		else if (rnd == 1)
			value = queue.removeLast();
		return value;
	}

	public Item sample() {
		int rnd = StdRandom.uniform(2);
		Item value = null;
		if (rnd == 0) {
			value = queue.removeFirst();
			queue.addFirst(value);
		}
		else if (rnd == 1) {
			value = queue.removeLast();
			queue.addLast(value);
		}
		return value;
	}


	public Iterator<Item> iterator() {
		return new Iterator<Item>() {

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Item next() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
			
		};
	}
}
