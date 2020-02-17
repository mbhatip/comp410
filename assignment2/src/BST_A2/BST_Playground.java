package BST_A2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class BST_Playground {
/*
 * you will test your own BST implementation in here
 *
 * we will replace this with our own when grading, and will
 * do what you should do in here... create BST objects,
 * put data into them, take data out, look for values stored
 * in it, checking size and height, and looking at the BST_Nodes
 * to see if they are all linked up correctly for a BST
 * 
*/
  
  public static void main(String[]args){

   // you should test your BST implementation in here
   // it is up to you to test it thoroughly and make sure
   // the methods behave as requested above in the interface
  
   // do not simple depend on the oracle test we will give
   // use the oracle tests as a way of checking AFTER you have done
   // your own testing

   // one thing you might find useful for debugging is a print tree method
   // feel free to use the printLevelOrder method to verify your trees manually
   // or write one you like better
   // you may wish to print not only the node value, and indicators of what
   // nodes are the left and right subtree roots,
   // but also which node is the parent of the current node
	  
	  
	  Scanner scan = new Scanner(System.in);
	  String input = null;
	  while (input != "Q") {
		  int numNodes = 200;
		  int strLength = 1;
		  
		  BST tree = new BST();
		  List<String> listStrings = new ArrayList<String>();
		  
		  System.out.print("Input: ");
		  for (int i = 0; i < numNodes; i++) {
			  String s = MyRandom.nextString(strLength, strLength);
			  listStrings.add(s);
			  tree.insert(s);
			  System.out.print(s);
		  }
		  System.out.print("\nNo duplicates: ");
		  Set<String> setStrings = new HashSet<String>();
		  setStrings.addAll(listStrings);
		  
		  for (String s : listStrings) {
			  if (setStrings.contains(s)) {
				  System.out.print(s + ',');
			  }
			  while(setStrings.remove(s)) {}
		  }
		  System.out.println();
		  /*
		  tree.insert("6");
		  tree.insert("4");
		  tree.insert("8");
		  tree.insert("7");
		  tree.insert("3");
		  tree.insert("9");
		  */
		  printLevelOrder(tree);
		  
		  for (String s : listStrings) {
			  if (!tree.contains(s)) {
				  System.out.println("Failure: " + s);
			  }
		  }
		  String[] notInTree = new String[] {"$", "%", "A", "B", ".",","};
		  for (String s : notInTree) {
			  if (tree.contains(s)) {
				  System.out.println("Failure: " + s);
			  }
		  }
		  /*
		  while (input != "E") {
			  System.out.print("Remove: ");
			  input = scan.next();
			  if(tree.remove(input)) {
				  System.out.println(input + " was deleted");
				  printLevelOrder(tree);
			  }
		  }
		  */
		  // Randomly delete variables and post results
		  Collections.shuffle(listStrings);
		  
		  for (String s : listStrings) {
			  if (tree.remove(s)) {
				  System.out.println("***********\n" + s + " was deleted");
				  printLevelOrder(tree);
			  }
		  }
		  if(tree.remove("a") || tree.contains("a")) {
			  System.out.println("\nFAILED\n");
		  }
		  input = scan.next();
		  
	}
  }
  
  static void printLevelOrder(BST tree){ 
  //will print your current tree in Level-Order...
  //https://en.wikipedia.org/wiki/Tree_traversal
	int h=tree.height();
    for(int i=0;i<=h;i++){
      printGivenLevel(tree.getRoot(), i);
      System.out.println();
    }
	
    
    
    String root = tree.getRoot() != null ? tree.getRoot().getData() : "null";
    String min = tree.findMin() != null ? tree.findMin() : "null";
    String max = tree.findMax() != null ? tree.findMax() : "null";
	
    System.out.println(
			  "Height: " + tree.height() + 
			  "\nSize: " + tree.size() +
			  "\nRoot: " + root + 
			  "\nEmpty: " + tree.empty() +
			  "\nMinimum: " + min + 
			  "\nMaximum: " + max); 
    
  }
  static void printGivenLevel(BST_Node root,int level){
    if(root==null) {
    	if (level == 0) { System.out.print("-");}
    	System.out.print(" ");
    	return;
    }
    if(level==0)System.out.print(root.data+(" "));
    else if(level>0){
      printGivenLevel(root.left,level-1);
      printGivenLevel(root.right,level-1);
    }
  }
  static void printInOrder(BST_Node root){
  //will print your current tree In-Order
  if(root!=null){
    printInOrder(root.getLeft());
    System.out.print(root.getData() + " ");
    printInOrder(root.getRight());
  }
  }
}