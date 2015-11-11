
/*
 *    A program to decode a file using Huffman Code Algorithm.
 */

import java.io.*;


public class HDecode {

	private Node root = null; // Root of the Huffman Code Tree.

	private String inputFilename;
	private String outputFilename;
	private int fileSize; // The number of bytes
							// in the original file.

	private BitReader bitr;
	private FileOutputStream outF = null;

	public static void main(String[] args) throws FileNotFoundException, IOException {

		HDecode decoder = new HDecode(args[0]); // Construct a Huffman Decoder
		decoder.decode();
	}

	public HDecode(String inputFilename) throws FileNotFoundException {
		this.inputFilename = inputFilename;
		this.outputFilename = inputFilename.substring(0, inputFilename.indexOf(".huf"));
		
		outF = new FileOutputStream(this.outputFilename + ".orig");
	}

	public void decode() throws IOException {
		bitr = new BitReader(inputFilename);
		fileSize = bitr.readInt();

		root = readTree();
		printTree();
		int bit = 0;
		for (int i = 0; i < fileSize; i++) {
			Node temp = root;
			while (temp.lchild != null || temp.rchild != null) { 
				bit = bitr.readBit();
				if (bit == 1)
					temp = temp.rchild;
				else
					temp = temp.lchild;
			} 
			outF.write(temp.data);
			temp = root;
		}
	}

	public Node readTree() {
		int bit = bitr.readBit();

		if (bit == 0) {
			byte data = bitr.readByte();
			Node temp = new Node();
			temp.data = data;
			return temp;
		} else {
			Node lchild = readTree();
			Node rchild = readTree();
			Node temp = new Node();
			temp.lchild = lchild;
			temp.rchild = rchild;
			lchild.parent = temp;
			rchild.parent = temp;
			return temp;
		}
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
			System.out.print("         ");

		if (r.data > (byte) 31)
			System.out.printf("%c-%d\n", (char) r.data, r.frequency);
		else
			System.out.printf("%c-%d\n", '*', r.frequency);

		rPrintTree(r.lchild, level + 1);
	}
	
	public class Node implements Comparable<Node> {
		byte data; // A byte of data - stored in an Integer.
		Node lchild; // Left child pointer.
		Node rchild; // Right child pointer.
		Node parent; // Pointer to parent node.
		Integer frequency; // Frequency the data within
							// a file being encoded.
		/*
		 * Basic node constructor.
		 */

		public Node() {
			data = 0; // Each Huffman Code Tree node
			lchild = null; // contains data, pointers to
			rchild = null; // children and parent nodes
			parent = null; // plus a frequency count
			frequency = 0; // associated with the data.
		}

		/*
		 * Constructor specifying all values of the node instance variables.
		 */

		public Node(byte data, Node lchild, Node rchild, Node parent, int frequency) {
			this.data = data;
			this.lchild = lchild;
			this.rchild = rchild;
			this.parent = parent;
			this.frequency = frequency;
		}

		/*
		 * compareTo() - Compare two frequency values. We want Nodes with lower
		 * frequencies to have higher priority in the priority queue.
		 * 
		 */

		public int compareTo(Node other) {
			if (this.frequency.compareTo(other.frequency) < 0)
				return 1;
			else if (this.frequency.compareTo(other.frequency) > 0)
				return -1;
			else
				return 0;
		}

		public String toString() {
			char ch = (char) this.data;

			String str = "byte: " + data + "  char: ";

			if (data > (byte) 31)
				str = str + (char) data + "  freq: " + frequency;
			else
				str = str + " " + "  freq: " + frequency;

			return str;
		}

	}
}
