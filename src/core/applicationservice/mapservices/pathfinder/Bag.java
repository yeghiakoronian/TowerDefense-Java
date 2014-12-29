package core.applicationservice.mapservices.pathfinder;

import infrastructure.loggin.Log4jLogger;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {
	private int N;               // number of elements in bag
	private Node<Item> first;    // beginning of bag
	/**
	 * application logger definition
	 */
	private static final Log4jLogger logger = new Log4jLogger();
	
	// helper linked list class
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}

	/**
	 * Initializes an empty bag.
	 */
	public Bag() {
		first = null;
		N = 0;
	}

	/**
	 * Is this bag empty?
	 * @return true if this bag is empty; false otherwise
	 */
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Returns the number of items in this bag.
	 * @return the number of items in this bag
	 */
	public int size() {
		return N;
	}

	/**
	 * Adds the item to this bag.
	 * @param item the item to add to this bag
	 */
	public void add(Item item) {
		try {
			Node<Item> oldfirst = first;
			first = new Node<Item>();
			first.item = item;
			first.next = oldfirst;
			N++;
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
	}
	/**
	 * Returns an iterator that iterates over the items in the bag in arbitrary order.
	 * @return an iterator that iterates over the items in the bag in arbitrary order
	 */
	public Iterator<Item> iterator()  {
		return new ListIterator<Item>(first);  
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator<Item> implements Iterator<Item> {
		private Node<Item> current;

		public ListIterator(Node<Item> first) {
			current = first;
		}

		public boolean hasNext() {
			return current != null;
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			try {
				if (!hasNext()) throw new NoSuchElementException();
				Item item = current.item;
				current = current.next; 
				return item;
			} catch (Exception e) {
				logger.writer(this.getClass().getName(), e);
				return null;
			}
		}
	}
}