package BST_A2;
import java.util.Scanner;

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
	  int input = 1;
	  while (input != 0) {
		  input = scan.nextInt();
		  BST tree = new BST();
		  
		  int max = 10;
		  String[] randString = new String[max];
		  
		  System.out.print("Input: ");
		  
		  for (int i = 0; i < 10; i++) {
			  randString[i] = MyRandom.nextString(1,1);
			  System.out.print(randString[i]);
			  tree.insert(randString[i]);
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
		  System.out.println(
				  "\nHeight: " + tree.height() + 
				  "\nSize: " + tree.size() +
				  "\nRoot: " + tree.getRoot().getData() + 
				  "\nEmpty: " + tree.empty() +
				  "\nMinimum: " + tree.findMin() + 
				  "\nMaximum: " + tree.findMax()); 
		  
		  for (String s : randString) {
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