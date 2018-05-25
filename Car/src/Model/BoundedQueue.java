package Model;

public interface BoundedQueue<E> {
	void add(E element) throws InterruptedException;
	E remove() throws InterruptedException;
}
