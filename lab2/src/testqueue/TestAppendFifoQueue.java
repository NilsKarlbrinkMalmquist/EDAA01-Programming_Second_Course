package testqueue;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Iterator;


import queue_singlelinkedlist.FifoQueue;

public class TestAppendFifoQueue {

	private FifoQueue<Integer> myIntQueue;
	private FifoQueue<Integer> myIntQueue2;
	
	@BeforeEach
	public void setUp(){
		myIntQueue = new FifoQueue<Integer>();
		myIntQueue2 = new FifoQueue<Integer>();
	}

	@AfterEach
	public void tearDown(){
		myIntQueue = null;
		myIntQueue2 = null;
	}

	@Test
	public void testTwoEmpty() {
		assertTrue(myIntQueue.isEmpty(), "Queue should be empty");
		assertTrue(myIntQueue2.isEmpty(), "Queue should be empty");
		myIntQueue2.append(myIntQueue);
	}
	
	@Test
	public void testAppendEmptyQueueToNonEmptyQueue() {
		myIntQueue.offer(1);
		myIntQueue.offer(3);
		assertFalse(myIntQueue.isEmpty(), "Queue should not be empty");
		assertTrue(myIntQueue2.isEmpty(), "Queue should be empty");
		myIntQueue2.append(myIntQueue); //2 st i nya, 2an ska vara tom, första element 1, sista element 3 
		assertEquals(2, myIntQueue2.size(), "Size should be 2");								//Ska finnas 2 element i den nya
		assertEquals(1, myIntQueue2.poll(), "First element should have the value 1");		//Första elementet ska ha värdet 1
		assertEquals(3, myIntQueue2.peek(), "Second element should have the value 3");		//Andra elementet ska ha värdet 2
		assertTrue(myIntQueue.isEmpty(), "Appended queue should be empty");				//Den appenderade kön ska vara tom efteråt
	}
	@Test
	public void testAppendNonEmptyQueueToEmptyQueue() {
		myIntQueue2.offer(1);
		myIntQueue2.offer(3);
		assertTrue(myIntQueue.isEmpty(), "Queue should be empty");
		assertFalse(myIntQueue2.isEmpty(), "Queue should not be empty");
		myIntQueue2.append(myIntQueue);
		assertEquals(2, myIntQueue2.size(), "Size should be 2");								//Ska finnas 2 element i den nya
		assertEquals(1, myIntQueue2.poll(), "First element should have the value 1");		//Första elementet ska ha värdet 1
		assertEquals(3, myIntQueue2.peek(), "Second element should have the value 3");		//Andra elementet ska ha värdet 2
		assertTrue(myIntQueue.isEmpty(), "Appended queue should be empty");				//Den appenderade kön ska vara tom efteråt
		
	}
	@Test
	public void testAppendNonEmptyQueueToNonEmptyQueue() {
		myIntQueue2.offer(3);
		myIntQueue2.offer(4);
		myIntQueue.offer(1);
		myIntQueue.offer(2);
		assertFalse(myIntQueue.isEmpty(), "Queue should not be empty");
		assertFalse(myIntQueue2.isEmpty(), "Queue should not be empty");
		myIntQueue2.append(myIntQueue);
		assertEquals(4, myIntQueue2.size(), "Size should be 4");
		assertEquals(3, myIntQueue2.poll(), "First element should have the value 3");
		assertEquals(4, myIntQueue2.poll(), "Second element should have the value 4");
		assertEquals(1, myIntQueue2.poll(), "Third element should have the value 1");
		assertEquals(2, myIntQueue2.peek(), "Fourth element should have the value 2");
		assertTrue(myIntQueue.isEmpty(), "Appended queue should be empty");

	}
	@Test
	public void testAppendQueueToItSelf() {
		myIntQueue.offer(1);
		myIntQueue.offer(2);
		
		
		
		try {
			myIntQueue.append(myIntQueue);
			
			fail();
		}
		
		
		catch(IllegalArgumentException e){
			
		}
		
	}
	@Test
	public void testLabb() {
		myIntQueue.offer(1);
		myIntQueue.append(myIntQueue2);
	}
	
}
