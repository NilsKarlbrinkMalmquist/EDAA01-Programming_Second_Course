package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> newLast = new QueueNode<E>(e);
 
		if(size == 0){
			newLast.next = newLast;
		}
		else { 
			newLast.next = last.next;
			last.next = newLast;
		}	
		last = newLast;
		
		size++;
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if (size == 0){
			return null;
		}
		return last.next.element;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if (size == 0){
			return null;
		}
		else {
			QueueNode <E> p = last.next;
			last.next = p.next;				//Borde gå att skriva last.next = last.next.next?
			size--;
			return p.element;
		}

	}
	
	/**
	 * Appends the specified queue to this queue
	 * post: all elements from the specified queue are appended
	 * to this queue. The specified queue (q) is empty after the call
	 * @param q the queue to append
	 * @throws IllegalArgumentException if this queue and q are identical
	 */
	public void append(FifoQueue<E> q) {
		QueueNode<E> temp;
		
		//Om det är samma queue som man försöker lägga till, kasta exception
		if(this == q) {
			throw new IllegalArgumentException();
		}
		
		//Om this queue är tom
		if(size == 0) {
			last = q.last;
			size = q.size;
		}
		
		//Om q är tom gör inget
		else if(q.size == 0) {
			//Gör inget
		}
		
		//Om this queue och q inte är tom
		else {
			temp = last.next;			//Lagrar this queue första värde i temp
			last.next = q.last.next;	//Sätter this queue:s sista värdes next.referens till q:s första värde
			q.last.next = temp;			//Sätter q:s sista värdes next-referens till this första värde då listan är cirkulär
			last = q.last;				//Last sätts till den nya sista platsen in kön, i detta fall slutet på q då q läggs till efter allt som fanns i this
			size += q.size;				//Adderar storlekarna på köerna från listorna som nu slagits ihop
		}
		
		
		//Rensa q på innehåll
		q.last = null;
		q.size = 0;
		
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<E>{
		private QueueNode<E> pos;
		int p;
		
		private QueueIterator() {
			pos = last;
			p = 0;
		}
		
		@Override
		public boolean hasNext() {
			if(p >= size) {
				return false;
			}
			return true;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			p++;
			E element = pos.next.element;
			pos = pos.next;
			return element;
			
		}
		
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
