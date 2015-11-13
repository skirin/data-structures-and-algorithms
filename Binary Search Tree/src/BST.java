
/*
 *    An implementation of a generic Binary Search Tree.
 */

public class BST<T extends Comparable<T>> implements BSTInterface<T> {
	private Node root = null; // The root of the binary tree.

	/*
	 * BST Constructor
	 */

	public BST() {
		root = null; // Redundant - I know
	}

	/*
	 * equals() - return true if two BST's have the same structure and data
	 * values, else return false.
	 */

	public boolean equals(BST<T> other) {
		// Call a recursive function to check if
		// two BSTs are equal. The arguments are
		// pointers to the roots of the trees.

		return requals(this.root, other.root);
	}

	public boolean isEmpty() {
		
	}
	
	//
	// requals() - determines if the trees with root Nodes
	// r and b have the same structure and same
	// data. Return true if they are equal,
	// otherwise return false.

	public boolean requals(Node r, Node b) {
		if (r == null && b == null)
			return true;
		else if ((r == null && b != null) || (r != null && b == null)) {
			return false;
		} else {
			if (r.data.equals(b.data)) {
				return requals(r.lchild, b.lchild) && requals(r.rchild, b.rchild);
			} else {
				return false;
			}
		}
	}

	/*
	 * insert() - insert a node with data value x in the Binary Search Tree.
	 */

	public void insert(T x) {
		this.root = rinsert(this.root, x);
	}

	/*
	 * rinsert() - return a pointer to the root node of a BST with data item x
	 * inserted. Do not insert duplicate data items.
	 */

	public Node rinsert(Node root, T x) {
		if (root == null) // Base Step - Empty tree
			root = new Node(x, null, null);
		else if (x.compareTo(root.data) < 0) // Smaller values go in
			root.lchild = rinsert(root.lchild, x); // the left subtree,
		else if (x.compareTo(root.data) > 0) // larger values go in
			root.rchild = rinsert(root.rchild, x); // the right subtree.

		return root; // return the pointer to the root node.

	}

	public void printTree() {
		rPrintTree(root, 0);
	}

	/*
	 * rPrintTree() - the usual quick recursive method to print a tree.
	 */

	public void rPrintTree(Node r, int level) {

		if (r == null) // Empty tree.
			return;

		rPrintTree(r.rchild, level + 1); // Print the right subtree.

		for (int i = 0; i < level; i++)
			System.out.print("   ");

		System.out.println(r.data.toString());

		rPrintTree(r.lchild, level + 1); // Print the left subtree.
	}

	/*
	 * Perform an inorder traversal of the tree.
	 */

	public void inorder() {
		rinorder(this.root);
	}

	/*
	 * rinorder() - a recursive routine to perform an inorder traversal of a
	 * BST. We will simply write the data items the order they are visited by
	 * the traversal.
	 */

	public void rinorder(Node r) {
		if (r == null) // Empty tree - nothing to do.
			return;
		else {
			rinorder(r.lchild); // Traverse the left subtree
								//
			System.out.print(" " + // "Visit" the root node.
					r.data.toString()); //
			rinorder(r.rchild); // Traverse the right subtree.
		}
	}

	//
	// find() - search the tree to determine if data
	// item x is in the tree, if x is in the tree
	// simply return the data item, else return null.
	//

	public T find(T x) {
		Node temp = root;

		while (temp != null) {
			if (x.equals(temp.data))
				return temp.data;
			else if (x.compareTo(temp.data) > 0)
				temp = temp.rchild;
			else
				temp = temp.lchild;
		}
		return null;
	}

	//
	// findMax() - return the value of the largest data
	// item in the tree - if the tree is
	// empty, return null.
	//

	public T findMax() {
		Node parent = root;

		while (parent.rchild != null)
			parent = parent.rchild;

		return parent.data;
	}

	//
	// findMin() - return the value of the smallest data
	// item in the tree - if the tree is
	// empty, return null.
	//

	public T findMin() {
		Node parent = root;

		while (parent.lchild != null)
			parent = parent.lchild;

		return parent.data;
	}

	//
	// removeMin() - remove the Node containing the
	// smallest data item. If the tree
	// is empty, do nothing, just return.

	public void removeMin() {
		Node follow = root;
		Node ptr = root;
		
		while (ptr.lchild != null)
			follow = ptr;
			ptr = ptr.lchild;
		
		follow.lchild = null;
	}

	//
	// removeMax() - remove the Node containing the
	// largest data item. If the tree
	// is empty, do nothing, just return.

	public void removeMax() {
		Node follow = root;
		Node ptr = root;
		
		while (ptr.rchild != null)
			follow = ptr;
			ptr = ptr.rchild;
		
		follow.rchild = null;
	}

	//
	// remove(x) - remove the Node containing the
	// data item x. If the tree
	// is empty or does not contain x,
	// do nothing, just return.

	public void remove(T x) {
		return; // stub

		// Removing an arbitrary node is handled by
		// considering several cases. Implement and
		// test each case, finishing one case before
		// tackling the next.

		// Case 1: The node to delete is a leaf.

		// Case 2: The the node to delete has a left subtree
		// but no right subtree.

		// Case 3: The node to delete has a right subtree
		// but no left subtree.

		// Case 4: The node to delete has both left and right subtrees.
		//
		// In this case, we swap the data in the node with the
		// smallest data value in its right subtree, then we delete this
		// "smallest data" node which is either a leaf or a node
		// with only a single subtree (left or right, but not both.)

	}

	//
	// inner Node class
	//

	private class Node {
		T data; // Data stored in the node
		Node lchild; // Pointer to left subtree / child
		Node rchild; // Pointer to right subtree / child

		// Default constructor - all entries are null.

		public Node() {
			this.data = null;
			this.lchild = null;
			this.rchild = null;
		}

		// Constructor specifying the data to be
		// stored in the node as will as pointers to the
		// left and right subtrees / children.

		public Node(T data, Node lchild, Node rchild) {
			this.data = data;
			this.lchild = lchild;
			this.rchild = rchild;
		}
	}
}