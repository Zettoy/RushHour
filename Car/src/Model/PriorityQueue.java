/**
 * A generic priority queue implementation
 */

package Model;
import java.util.ArrayList;
import java.util.Comparator;

public class PriorityQueue<E> {
    private ArrayList<E> array;
    private Comparator<E> comparator;

    /**
     * Constructor
     * @param comparator The comparator to compare two objects in the queue
     */
	public PriorityQueue(Comparator<E> comparator)
    {
        array = new ArrayList<E>();
        array.add(null);
        this.comparator = comparator;
    }

    /**
     * Adds element to the queue in the correct order based on comparator
     * @param element The element to be added to the queue
     */
	public void add(E element)
    {
        int position = array.size();
        array.add(element);
        while (position > 1 && comparator.compare(element, array.get(position / 2)) < 0)
        {
            array.set(position, array.get(position / 2));
            position /= 2;
        }
        array.set(position, element);
    }

    /**
     * Returns if the queue is empty or not
     * @return true if empty and false otherwise
     */
    public boolean isEmpty()
    {
        return array.size() <= 1;
    }

    /**
     * Removes the first element of the priority queue
     * @return The first element of the queue
     */
    public E remove()
    {
    	if (isEmpty()) return null;
        E top = array.get(1);
        E bottom = array.get(array.size() - 1);
        int position = 1;
        int nextPosition = (2 * position + 1 < array.size() - 1 ? positionOfChild(position) : 2 * position);
        while (nextPosition < array.size() - 1 && comparator.compare(bottom, array.get(nextPosition)) > 0)
        {
            array.set(position, array.get(nextPosition));
            position = nextPosition;
            nextPosition = (2 * position + 1 < array.size() - 1 ? positionOfChild(position) : 2 * position);
        }
        array.set(position, bottom);
        array.remove(array.size() - 1);
        return top;
    }

    /**
     * Gets the position of a child of a given elements position
     * @param position Elements position to find the child's position of
     * @return The int position of the child
     */
    private int positionOfChild(int position)
    {
        E leftChild = array.get(2 * position);
        E rightChild = array.get(2 * position + 1);
        return (comparator.compare(leftChild, rightChild) < 0 ? 2 * position : 2 * position + 1);
    }
}
