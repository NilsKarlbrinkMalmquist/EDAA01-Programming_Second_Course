package bst;

public class OtherTest {
	
	public static void main (String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(4);
		tree.add(5);
		tree.add(2);
		tree.add(6);
		tree.add(6);
		System.out.println(tree.size() + " " + tree.height());
		tree.printTree();
		
		
	}

}

