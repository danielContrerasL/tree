package structure;

public class NodeTree <T>{
	private T info;
	private NodeTree<T> right;
	private NodeTree<T> left;

	public NodeTree(T info) {
		this.info = info;
	}

	public T getInfo() {
		return info;
	}

	public NodeTree<T> getRight() {
		return right;
	}

	public void setRight(NodeTree<T> right) {
		this.right = right;
	}

	public NodeTree<T> getLeft() {
		return left;
	}

	public void setLeft(NodeTree<T> left) {
		this.left = left;
	}

	public void setInfo(T info) {
		this.info = info;
	}
	
	


}
