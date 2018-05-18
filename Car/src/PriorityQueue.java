import java.util.ArrayList;
import java.util.Comparator;

public class PriorityQueue<E> {
    private ArrayList<E> array;
    private Comparator<E> comparator;
	
	public PriorityQueue(Comparator<E> comparator)
    {
        array = new ArrayList<E>();
        array.add(null);
        this.comparator = comparator;
    }

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

    public boolean isEmpty()
    {
        return array.size() <= 1;
    }

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

    private int positionOfChild(int position)
    {
        E leftChild = array.get(2 * position);
        E rightChild = array.get(2 * position + 1);
        return (comparator.compare(leftChild, rightChild) < 0 ? 2 * position : 2 * position + 1);
    }
}
