
/*
 *   An implementation of the Map interface.
 *   
 *   A doubly linked list implementation is used.
 *   Front and rear sentinel nodes are used.
 */

public class Map <K, V> implements MapInterface<K, V>
{
	private Node header;   // Pointer to the first item in the Map.
	
	
	// The Constructor sets up the sentinel nodes with
	// header pointing to the front.
	
	public Map()
	{
		Node front = new Node();   // Construct the sentinel
		Node rear = new Node();    // nodes.
		
		front.prev = null;   // Link the sentinels.  All map
		front.next = rear;   // items will be between the sentinels
		rear.prev  = front;  // so each items is assured to have 
		rear.next  = null;   // a node in front and in back.
		
		header = front;  // The header points to the front node.
	}
	
	
	public int getSize()
	{
		return 0;   // A function stub
	}
	
	public void makeEmpty()
	{
		return;  // A function stub
	}
	
	
	public void insert(K key, V value)
	{
		return; // A function stub
	}
	
	
	public void remove(K key)
	{
		return;  // A function stub
	}
	
	
	public V getValue(K key)
	{
		return null;  // A function stub
	}
	
	public boolean isEmpty()
	{
		return true;  //  A function stub
	}
	
	/*
	 *   toString() - return a String representation
	 *                of the map.
	 */
	
	public String toString()
	{
		String str = "The Map\n--------------\n";
		
		Node ptr = header.next;
		
		while (ptr.next != null) {   // For each item ...
			str = str + "key: ";              // use the toString()
		    str = str + ptr.key.toString();   // method for each key
		    str = str + "   ";                // and value to append
			str = str + "value: ";            // its String  
			str = str + ptr.value.toString(); // representation to
			str = str + "\n";                 // the Map's String
			ptr = ptr.next;                   // representation.
		}
		
		str = str + "--------------\n";
		
		return str;
	}
	
	/*
	 *    Inner Class - Node objects for Map items
	 *                  in a doubly linked list.
	 *    
	 */

	private class Node
	{
		public K key;       // The item's key.
		public V value;     // The item's value.
		public Node prev;   // Pointer to the previous Node.
		public Node next;   // Pointer to the next node.
	}

}
