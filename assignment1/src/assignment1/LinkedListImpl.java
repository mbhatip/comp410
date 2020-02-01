/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
 */
package assignment1;

public class LinkedListImpl implements LIST_Interface {
	Node headCell; //this will be the entry point to your linked list (the head)
	Node lastCell; // this is the Node at the end of the list... the starting place
	// if you wanted to traverse the list backwards

	private int numElts;   // Integer to keep track of number of elements
	
	public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
		headCell = null; //Note that the root's data is not a true part of your data set!
		lastCell = null;
		numElts = 0;
	}

	//implement all methods in interface, and include the getRoot method we made for testing 
	// purposes. Feel free to implement private helper methods!

	// add the fields you need to add to make it work... like a 

	public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
		return headCell;
	}
	public Node getLast(){ //leave this method as is, used by the grader to grab your linkedList easily.
		return lastCell;
	}

	@Override
	public boolean insert(double elt, int index) {
		// Making new node with given data
		Node insertThis = new Node(elt);
		
		// Checking to see if adding to empty list
		if (addToEmptyList(insertThis)) {
			return true;
		}
		
		// Checking to see if last or first node is to be inserted
		// Continuing with else statement otherwise
		if (index == 0) {
			// Changing values for new headCell
			Node formerHead = getRoot();
			this.headCell = insertThis;
			this.headCell.next = formerHead;
			formerHead.prev = this.headCell;
		}
		else if (index == size()) {
			// Changing values for new lastCell
			Node formerLast = getLast();
			this.lastCell = insertThis;
			this.lastCell.prev = formerLast;
			formerLast.next = this.lastCell;
		}
		else {
			// Getting node at index position
			Node nodeAtIndex = getNodeAt(index);
			
			// Checking to see index is valid
			if (nodeAtIndex == null) {
				return false;
			}
			
			// Declaring variable for prev node
			Node prev = nodeAtIndex.prev;
	
			// Changing values starting from prev to the former node at index
			prev.next = insertThis;
			insertThis.prev = prev;
			insertThis.next = nodeAtIndex;
			nodeAtIndex.prev = insertThis;
		}
		
		// Changing size value
		numElts++;

		return true;
	}

	@Override
	public boolean insort(double elt) {
		// Making new node with given data
		Node insortThis = new Node(elt);
				
		// Checking to see if adding to empty list
		if (addToEmptyList(insortThis)) {
			return true;
		}
		
		// Checking if node is new headCell or lastCell
		if (insortThis.data < this.getRoot().data) {
			this.insert(elt, 0);
			return true;
		}
		else if (insortThis.data > this.getLast().data) {
			this.insert(elt, this.size());
			return true;
		}
		
		// Otherwise we can start by comparing an element
		// and the previous one to insortThis, increment
		// until condition is not met, or a place has been found
		Node upperBound = getNodeAt(1);
		Node lowerBound = upperBound.prev;
		while(lowerBound.data > insortThis.data || 
				upperBound.data < insortThis.data) {
			upperBound = upperBound.next;
			lowerBound = upperBound;
		}
		lowerBound.next = insortThis;
		insortThis.prev = lowerBound;
		insortThis.next = upperBound;
		upperBound.prev = insortThis;
		
		
		// Checking if node is new headCell or lastCell
		if (insortThis.data < upperBound.data) {
			this.insert(elt, 0);
		}
		
		for(int i = 1; i < size(); i++) {
			
		}
		
		// Changing size value
		numElts++;
		
		return true;
	}

	@Override
	public boolean remove(int index) {
		// Gets node at index value
		Node removeThis = getNodeAt(index);
		
		// Checking if node can be found
		if (removeThis == null) {
			return false;
		}
		
		// Checking if only one cell left (clear list)
		else if (size() == 1) {
			clear();
			return true;
		}
		
		// Declare previous and next nodes
		Node prev = removeThis.prev;
		Node next = removeThis.next;
		
		// Checking if first or last node
		if (removeThis.equals(headCell)) {
			this.headCell = next;
			this.headCell.prev = null;
		}
		else if (removeThis.equals(lastCell)) {
			this.lastCell = prev;
			this.lastCell.next = null;
		}
		else {
			prev.next = next;
			next.prev = prev;
		}

		// Change size value
		numElts--;
		
		return true;
	}

	@Override
	public double get(int index) {
		// Get node from index value
		Node getNode = getNodeAt(index);
		
		// Checking if node is valid
		if (getNode == null) {
			return Double.NaN;
		}
		
		return getNode.data;
	}

	@Override
	public int size() {
		// Returns number of elements
		return numElts;
	}

	@Override
	public boolean isEmpty() {
		// Checks if number of elements is 0
		return numElts == 0;
	}

	@Override
	public void clear() {
		// Clear every field value
		this.headCell = null;
		this.lastCell = null;
		this.numElts = 0;
		
	}
	
	private Node getNodeAt(int index) {
		// Helper function finds Node at index position using a loop
		
		// Checking if index is in bounds and if list is populated
		if (index < 0 || index > size() - 1 || this.isEmpty()) {
			return null;
		}
		
		// Node of interest starts with first cell, iterates over list
		Node interest = getRoot();
		
		// Iterating through each node with for loop
		for (int i = 1; i <= index; i++) {
			interest = interest.next;
		}
		
		return interest;
		
	}
	
	private boolean addToEmptyList(Node element) {
		if (!this.isEmpty()) {
			return false;
		}
		this.headCell = element;
		this.lastCell = element;
		return true;
	}
}

