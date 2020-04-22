package SKPLIST_A4;

import java.util.Arrays;

public class SkipList_Playground {
  public static void main(String[] args) {
    mytest();
  }
  
  private static void mytest() {
	  SkipList_Interface list = new SkipList(100);
	  if(list.max() != 30) {
		  throw new RuntimeException("max function broken");
	  }
	  
	  int numElem = 5000;
	  double[] array = new double[numElem];
	  for (int i=0; i < numElem;i++) {
		  array[i] = Math.round(Math.random() * numElem);
		  list.insert(array[i]);
	  }
	  Arrays.sort(array);
	  if(list.findMax() != array[numElem-1]) {
		  throw new RuntimeException("findmax function broken");
	  }
	  if(list.findMin() != array[0]) {
		  throw new RuntimeException("findmin function broken");
	  }
	  for(double d : array) {
		  if(!list.contains(d)) {
			  throw new RuntimeException("contains function broken");
		  }
	  }
	  System.out.println(list);
	  System.out.println("success");
  }
  
  private static void test2() {
    SkipList_Interface list = new SkipList(5);
    System.out.println("=== INSERT ===");
    for(double i = 0; i < 5; i ++) {
      list.insert(i);
      System.out.println(list);
    }
    System.out.println("=== REMOVE ===");
    for(double i = 4; i >= 0; i --) {
      if (list.remove(i)) {
        System.out.println(list);
      }
    }
    System.out.println("=== INSERT ===");
    for(double i = 0; i < 5; i ++) {
      list.insert(i);
      System.out.println(list);
    }
  }
  
  private static void test1() {
    SkipList_Interface list = new SkipList(5);
    System.out.println("=== INSERT ===");
    for(double i = 0; i < 10; i ++) {
      list.insert(i);
      System.out.println(list);
    }
//    System.out.println(list);
//    System.out.println("=== CONTAINS ===");
//    for(double i = -5; i < 15; i ++) {
//      System.out.println(i + ": " + list.contains(i));
//    }
    System.out.println("=== REMOVE ===");
    for(double i = -5; i < 15; i +=2) {
//      System.out.println(i + ": " + list.remove(i));
      if (list.remove(i)) {
        System.out.println(list);
      }
    }
//    System.out.println(list);
//    System.out.println("=== CONTAINS ===");
//    for(double i = -5; i < 15; i ++) {
//      System.out.println(i + ": " + list.contains(i));
//    }
    System.out.println("=== INSERT ===");
    for(double i = 0; i < 10; i ++) {
      list.insert(i);
      System.out.println(list);
    }
//    System.out.println(list);
  }
}
