package structure;

import java.util.Comparator;

public class Tree<T> {

	private NodeTree<T> root;
	private Comparator<T> compare;

	public Tree(Comparator<T> compare) {
		this.compare = compare;
	}

	public NodeTree<T> getRoot() {
		return root;
	}

	public void addNode(NodeTree<T> node) {
		this.addNode(this.root, node);
	}

	private void addNode(NodeTree<T> actualNode, NodeTree<T> newNode) {
		if (actualNode != null) {
			if (compare.compare(newNode.getInfo(), actualNode.getInfo()) < 0) {
				if (actualNode.getLeft() != null)
					this.addNode(actualNode.getLeft(), newNode);
				else
					actualNode.setLeft(newNode);
			} else {
				if (actualNode.getRight() != null)
					this.addNode(actualNode.getRight(), newNode);
				else
					actualNode.setRight(newNode);
			}
		} else {
			if (this.root == null)
				this.root = newNode;
		}
	}

	public boolean search(T info) {
		return this.search(this.root, info);
	}

	private boolean search(NodeTree<T> actualNode, T info) {
		if (actualNode != null) {
			if (compare.compare(info, actualNode.getInfo()) == 0)
				return true;
			else if (compare.compare(info, actualNode.getInfo()) < 0) {
				if (actualNode.getLeft() != null)
					return this.search(actualNode.getLeft(), info);
			} else {
				if (actualNode.getRight() != null)
					return this.search(actualNode.getRight(), info);
			}
		}
		return false;
	}

	public void delete(T info) {
		if (search(info))
			this.delete(this.root, info);
	}

	private void delete(NodeTree<T> actualNode, T info) {
		if (actualNode != null) {
			if (compare.compare(info, actualNode.getInfo()) < 0) {
				if (actualNode.getLeft() != null) {
					if (compare.compare(info, actualNode.getLeft().getInfo()) == 0) {
						if (isSheet(actualNode.getLeft())) {
							actualNode.setLeft(null);
							return;
						} else if (isASheetWithSon(actualNode))
							actualNode.setLeft(deleteSheetWithSon(actualNode.getLeft()));
						else {
							deleteNode(actualNode.getLeft());
							return;
						}
					}
				}
			} else if (compare.compare(info, actualNode.getInfo()) > 0) {
				if (actualNode.getRight() != null) {
					if (compare.compare(info, actualNode.getRight().getInfo()) == 0) {
						if (isSheet(actualNode.getRight())) {
							actualNode.setRight(null);
							return;
						} else if (isASheetWithSon(actualNode))
							actualNode.setRight(deleteSheetWithSon(actualNode.getRight()));
						else {
							deleteNode(actualNode.getRight());
							return;
						}
					}
				}
			} else if (compare.compare(root.getInfo(), actualNode.getInfo()) == 0) {
				deleteNode(root);
				return;
			}

//			if (compare.compare(info, actualNode.getInfo()) < 0)
//				delete(actualNode.getLeft(), info);
//			else
//				delete(actualNode.getRight(), info);
		}
	}

	private void deleteNode(NodeTree<T> actualNode) {
		T auxLessInfo = searchLess(actualNode);
		T auxHigherInfo = searchHigher(actualNode);
		int base = (int) actualNode.getInfo();
		int less = (int) auxLessInfo;
		int higher = (int) auxHigherInfo;
		int base_less = Math.abs(base - less);
		int base_higher = Math.abs(base - higher);
		if (base_higher < base_less) {
			delete(actualNode, auxHigherInfo);
			actualNode.setInfo(auxHigherInfo);
		} else {
			delete(actualNode, auxLessInfo);
			actualNode.setInfo(auxLessInfo);
		}

	}

	private NodeTree<T> deleteSheetWithSon(NodeTree<T> actualNode) {
		Tree<T> auxTree = new Tree<>(compare);
		auxTree.addNode(actualNode.getRight());
		auxTree.addNode(actualNode.getLeft());
		return auxTree.root;
	}

	private boolean isASheetWithSon(NodeTree<T> actualNode) {
		return (actualNode.getLeft() == null && actualNode.getRight() != null)
				|| (actualNode.getLeft() != null && actualNode.getRight() == null);
	}

	public boolean isSheet(NodeTree<T> actualNode) {
		return actualNode.getLeft() == null && actualNode.getRight() == null;
	}

	public void printTree() {
		this.printTree(this.root);
	}

	private void printTree(NodeTree<T> actual) {
		if (actual != null) {
			this.printTree(actual.getLeft());
			this.printTree(actual.getRight());
		}
	}

	public void printTreeByLevel() {
		this.printTreeByLevel(this.root);
	}

	private void printTreeByLevel(NodeTree<T> actual) {
		if (actual != null) {
			this.printTreeByLevel(actual.getLeft());
			int iz = actual.getLeft() == null ? -1 : (int) actual.getLeft().getInfo();
			int der = actual.getRight() == null ? -1 : (int) actual.getRight().getInfo();
			System.out.println( iz + " <-- " + actual.getInfo()+ " --> " + der);
			this.printTreeByLevel(actual.getRight());
		}
	}

	public int size() {
		return size(this.root);
	}

	private int size(NodeTree<T> node) {
		if (node == null)
			return 0;
		else
			return (this.size(node.getLeft()) + 1 + this.size(node.getRight()));
	}

	public T searchLess(NodeTree<T> actualNode) {
		NodeTree<T> aux = actualNode.getRight();
		if (aux != null) {
			while (aux.getLeft() != null) {
				aux = aux.getLeft();
			}
			return aux.getInfo();
		}
		return actualNode.getInfo();
	}

	public T searchHigher(NodeTree<T> actualNode) {
		NodeTree<T> aux = actualNode.getLeft();
		if (aux != null) {
			while (aux.getRight() != null) {
				aux = aux.getRight();
			}
			return aux.getInfo();
		}
		return actualNode.getInfo();
	}

	public int level(T info) {
		if (search(info))
			return level(root, info);
		return 0;
	}

	private int level(NodeTree<T> actual, T info) {
		if (actual != null) {
			if (compare.compare(info, actual.getInfo()) == 0)
				return 1;
			else if (compare.compare(info, actual.getInfo()) < 0)
				return (this.level(actual.getLeft(), info) + 1);
			else
				return (this.level(actual.getRight(), info) + 1);
		}
		return 0;
	}

}
