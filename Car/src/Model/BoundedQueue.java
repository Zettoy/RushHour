package Model;
/**
 * Ordered collection of items with fixed capacity and operations for adding and removing items from the collection
 * @param <E> the type of the elements contained in the queue
 */
public interface BoundedQueue<E> {
	/**
	 * Adds an element to the back of the queue
	 * @param the element to be added 
	 * @throws InterruptedException
	 */
	void add(E element) throws InterruptedException;
	/**
	 * Removes the element at the front of the queue 
	 * @return the element that was removed
	 * @throws InterruptedException
	 */
	E remove() throws InterruptedException;
}
