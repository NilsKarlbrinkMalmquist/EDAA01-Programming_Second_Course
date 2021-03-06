package bst;

import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertTrue;import java.util.Comparator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBinarySearchTree {
	
	private BinarySearchTree<Integer> myIntTree;
	private BinarySearchTree<String> myStringTree;
	
//	private BinarySearchTree<Integer> myIntTreeC;
//	private BinarySearchTree<String> myStringTreeC;
//	private Comparator<Integer> compInt;
//	private Comparator<String> compString;

	@BeforeEach
	void setUp() throws Exception {
		myIntTree = new BinarySearchTree<Integer>();
		myStringTree = new BinarySearchTree<String>();
		
//		myIntTreeC = new BinarySearchTree<Integer>(compInt);
//		myStringTreeC = new BinarySearchTree<String>(compString);
	}

	@AfterEach
	void tearDown() throws Exception {
		myIntTree = null;
		myStringTree = null;
		
//		myIntTreeC = null;
//		myStringTreeC = null;
	}

	@Test
	void testAdd() {
		assertTrue(myIntTree.add(5));
		assertTrue(myIntTree.add(3));
		assertTrue(myIntTree.add(8));
		assertTrue(myIntTree.add(9));
		assertTrue(myIntTree.add(11));
		assertTrue(myIntTree.add(1));
		
		assertFalse(myIntTree.add(8), "Should be false");
		
		assertTrue(myStringTree.add("Balin"));
		assertTrue(myStringTree.add("Filin"));
		assertTrue(myStringTree.add("Anna"));
		assertFalse(myStringTree.add("Anna"));
		
//		assertTrue(myIntTreeC.add(5));
//		assertTrue(myIntTreeC.add(3));
//		assertTrue(myIntTreeC.add(8));
//		assertFalse(myIntTreeC.add(8));	
	}

	@Test
	void testHeight() {
		myIntTree.add(5);
		myIntTree.add(8);
		myIntTree.add(3);
		myIntTree.add(10);
		assertEquals(3, myIntTree.height(), "Height should be 3");
		
		myStringTree.add("Balin");
		myStringTree.add("Falin");
		myStringTree.add("Aalin");
		assertEquals(2, myStringTree.height(), "Height should be 2");
	}

	@Test
	void testSize() {
		myIntTree.add(5);
		myIntTree.add(8);
		myIntTree.add(3);
		myIntTree.add(10);
		myIntTree.add(10);
		assertEquals(4, myIntTree.size(), "Size should be 3");	
		
		myStringTree.add("Balin");
		myStringTree.add("Falin");
		myStringTree.add("Aalin");
		assertEquals(3, myStringTree.size(), "Size should be 3");
	}

	@Test
	void testClear() {
		myIntTree.add(5);
		myIntTree.add(8);
		myIntTree.add(3);
		myIntTree.add(10);
		myIntTree.add(10);
		assertFalse(myIntTree.root == null);
		assertEquals(4, myIntTree.size(), "Size should be 3");
		assertEquals(3, myIntTree.height(), "Height should be 3");
		myIntTree.clear();
		assertEquals(0, myIntTree.size(), "Size should be 0");	
		assertEquals(null, myIntTree.root, "Root should be null");
		assertEquals(0, myIntTree.height(), "Height should be 0");	
		
		myStringTree.add("Balin");
		myStringTree.add("Falin");
		myStringTree.add("Aalin");
		assertFalse(myStringTree.root == null);
		assertEquals(3, myStringTree.size(), "Size should be 3");
		assertEquals(2, myStringTree.height(), "Height should be 2");
		myStringTree.clear();
		assertEquals(null, myStringTree.root, "Root should be null");
		assertEquals(0, myStringTree.size(), "Size should be 0");	
		assertEquals(0, myStringTree.height(), "Height should be 0");

	}

}
