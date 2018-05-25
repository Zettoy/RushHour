package Model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Bounded thread-safe implementation of BoundedQueue with first-in-first-out ordering that allows for concurrent access by producers and consumers
 * @param <E> The type of item that the queue holds
 */
public class BoundedLinkedQueue<E> implements BoundedQueue<E> {

	private Queue<E> elements;
	private int capacity;
	private Lock queueLock;
	private Condition spaceAvailable;
	private Condition valueAvailable;
	/**
	 * Constructs a BoundedLinkedQueue object
	 * @param capacity the maximum number of items that can be held in the queue at any one time
	 */
	public BoundedLinkedQueue(int capacity) {
		this.capacity = capacity;
		elements = new LinkedList<E>();
		queueLock = new ReentrantLock();
		spaceAvailable = queueLock.newCondition();
		valueAvailable = queueLock.newCondition();
	}
	/**
	 * Inserts the specified element at the tail of the queue
	 * @param element the item to be inserted
	 */
	public void add(E element) throws InterruptedException {
		queueLock.lock();
		try {
			while (elements.size() == capacity) spaceAvailable.await();
			elements.add(element);
			valueAvailable.signalAll();
		} 
		finally {
			queueLock.unlock();
		}
	}
	/**
	 * Removes the element at the head of the queue
	 * @return the element of the queue that was removed
	 * @postcondition element != null
	 */
	public E remove() throws InterruptedException {
		queueLock.lock();
		try {
			while (elements.isEmpty()) valueAvailable.await();
			E element = elements.poll();
			spaceAvailable.signalAll();
			return element;
		}
		finally {
			queueLock.unlock();
		}
	}
}
