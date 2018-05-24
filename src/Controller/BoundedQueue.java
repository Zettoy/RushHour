package Controller;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueue<E> {

	private Queue<E> elements;
	private int capacity;
	private Lock queueLock;
	private Condition spaceAvailable;
	private Condition valueAvailable;
	
	public BoundedQueue(int capacity) {
		this.capacity = capacity;
		elements = new LinkedList<E>();
		queueLock = new ReentrantLock();
		spaceAvailable = queueLock.newCondition();
		valueAvailable = queueLock.newCondition();
	}
	
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
