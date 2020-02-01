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
	  
	  public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
	    headCell = null; //Note that the root's data is not a true part of your data set!
	    lastCell = null;
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
}
