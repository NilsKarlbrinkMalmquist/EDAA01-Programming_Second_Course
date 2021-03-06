package bst;

import java.util.ArrayList;
import java.util.Comparator;


public class BinarySearchTree<E> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size = 0;            // Används också i BSTVisaulizer
  boolean check;
  
  private Comparator<E> ccomparator;
  
  
	public static void main(String[] args) {
		BSTVisualizer bst = new BSTVisualizer("Test", 500, 500);
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(5);
		tree.add(1);
		tree.add(3);
		tree.add(9);
		tree.add(7);
		tree.add(11);
		tree.add(13);
		tree.add(12);
		
		//tree.rebuild();
		bst.drawTree(tree);
		tree.printTree();
	}
	
    
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
		ccomparator = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);	
		} 
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		root = null;
		this.ccomparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		return add(root, x);
	}
	
	private boolean add(BinaryNode<E> n, E x) {
		if (root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		} 
		else if (ccomparator.compare(n.element, x) > 0) {
			if (n.left == null) {
				n.left = new BinaryNode<E>(x);
				size++;
				return true;
			} else {
				return add(n.left, x);
			}
		}
		else if (ccomparator.compare(n.element, x) < 0) {
			if (n.right == null) {
				n.right = new BinaryNode<E>(x);
				size++;
				return true;
			} else {
				return add(n.right, x);
			}
		}
		else {
			return false;
		}
		
	}

	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	private int height(BinaryNode<E> n) {
		if(n != null) {
			return 1 + Math.max(height(n.left), height(n.right));
		}
		return 0;
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
		size = 0;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		print(root);
	}

	private void print(BinaryNode<E> n) {
		if(n != null) {
			print(n.left);
			System.out.println(n.element);
			print(n.right);
		}
	}
	

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> list = new ArrayList<E>();
		toArray(root, list);
		clear();
		buildTree(list, 0, list.size()-1);
		
//		for(int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> list) {
		if(n != null) {
			toArray(n.left, list);
			list.add(n.element);
			toArray(n.right, list);
		}
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private void buildTree(ArrayList<E> list, int first, int last) {
		if(first > last || first == list.size()) {
			return;
		}
		else {
			int mid =first + (last - first) / 2;
			BinaryNode<E> node = new BinaryNode<E>(list.get(mid));
			add(node.element);
			buildTree(list, first, mid - 1);
			buildTree(list, mid + 1, last);
		}
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}
