package test;

import java.util.Comparator;

import structure.NodeTree;
import structure.Tree;

public class TestTree {
	public static int[] aux = { 50, 43, 99, 45, 74, 6, 83, 33, 78, 2, 77, 47, 69, 16, 70, 12, 72, 9, 54, 17, 22, 100,
			15, 68, 13, 56, 18, 61, 19, 84 };
	public static int[] aux2 = { 10, 5, 15, 3, 7, 12, 20, 1 };
	public static int[] aux3 = { 50, 25, 75, 20, 37, 65, 90, 6, 22, 64, 68, 95, 3, 70, 200 };

	public static void main(String[] args) {
		Tree<Integer> tree = new Tree<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
		for (int i : TestTree.aux2) {
			tree.addNode(new NodeTree<Integer>(i));
		}
		
		
		while (tree.getRoot() != null) {
			System.out.println(tree.getRoot().getInfo());
			tree.delete(tree.getRoot().getInfo());
		}
		// tree.search(20);
		// tree.printTreeByLevel();
		// System.out.println("------------------------------------------");
//		tree.delete(50);
//		tree.printTreeByLevel();
		// System.out.println(tree.search(37));
		// int x = tree.size();
		// System.out.println(tree.searchLess(tree.getRoot()));
		// tree.delete(50);
		// tree.printTreeByLevel();
		// tree.addNode(new Node<Integer>(18));
		// System.out.println("------------------------------------------");
		// tree.printTreeByLevel();
	}
	/**
	 * generic social network is better for tv shows is better than search
	 * delete size depth isPerfect isBalanced isCompleted
	 * 
	 */
	
	
	// this.setPreferredSize(new Dimension(2*this.getWidth() ,
				// this.getHeight()));
}
