package MaxBinHeap_A3;

public class MaxBinHeap_Playground {
  public static void main(String[] args) {
    // Add more tests as methods and call them here!!
	  testNegative();
	  System.out.println();
	  testEverything();
	  System.out.println();
	  TestBuild();
    System.out.println();
    TestDelMin();
    System.out.println();
    TestSort();
    System.out.println();
    testRandBuild();
  }
  
  public static void exit() {
	  System.exit(0);
  }
  
  public static void testNegative() {
	  
  }

  public static void testEverything() {
	  MaxBinHeap mbh = new MaxBinHeap();
	  double collection[] = new double[] {3,8};
	  printHeapCollection(collection);
	  
	  mbh.build(collection);
	  printHeap(mbh.getHeap(), mbh.size());
	  
	  double toInsert[] = new double[] {1,6,4,5,7,2,10,0};
	  for (double d : toInsert) {
		  mbh.insert(d);
	  }
	  printHeap(mbh.getHeap(), mbh.size());
	  
	  mbh.delMax(); mbh.delMax(); mbh.delMax();
	  printHeap(mbh.getHeap(), mbh.size());
  }
  public static void testRandBuild() {
	  	// constructs a new maxbinheap, constructs an array of double,
	    // passes it into build function. Then print collection and heap.
	    MaxBinHeap mbh = new MaxBinHeap();
	    double collection[] = new double[10];
	    for (int i = 0; i < 10; i++) {
	    	collection[i] = (int) (-10 + 10 * Math.random());
	    }
	    mbh.build(collection);
	    printHeapCollection(collection);
	    printHeap(mbh.getHeap(), mbh.size());
  }
  
  public static void testInsert() {
	  MaxBinHeap mbh = new MaxBinHeap();
	  double[] collection = new double[] { 3, 8, 2, 1, 7, 4, 6, 5 };
	  for (double d : collection) {
		  
	  }
  }
  
  public static void TestDelMin() {
	// constructs a new maxbinheap, constructs an array of double,
	    // passes it into build function. Then print collection and heap.
	    MaxBinHeap mbh = new MaxBinHeap();
	    double[] collection = new double[] { 3, 8, 2, 1, 7, 4, 6, 5 };
	    mbh.build(collection);
	    printHeapCollection(collection);
	    for (int i = 0; i <= collection.length; i++) {
	    	printHeap(mbh.getHeap(), mbh.size());
	    	System.out.println(i + " max: " + mbh.getMax());
	    	mbh.delMax();
	    }
  }
  public static void TestBuild() {
    // constructs a new maxbinheap, constructs an array of double,
    // passes it into build function. Then print collection and heap.
    MaxBinHeap mbh = new MaxBinHeap();
    double[] collection = new double[] { 3, 8, 2, 1, 7, 4, 6, 5 };
    mbh.build(collection);
    printHeapCollection(collection);
    printHeap(mbh.getHeap(), mbh.size());
  }
  
  public static void TestSort() {
    // constructs a new maxbinheap, constructs an array of double, passes
    // it into heapsort function. Then print collection and sorted array.
    
    MaxBinHeap mbh = new MaxBinHeap();
    double[] collection = new double[] { 3, 8, 2, 1, 7, 4, 6, 5 };
    double[] sorted = mbh.sort(collection);
    printSortCollection(collection);
    printHeap(mbh.getHeap(), mbh.size());
    printArray(sorted);
  }

  public static double[] charsToDoubles(char[] chars) {
    double[] ret = new double[chars.length];
    for (int i = 0; i < chars.length; i++) {
      ret[i] = charToInt(chars[i]);
    }
    return ret;
  }

  public static int charToInt(char c) {
    return c - 'a';
  }

  public static void printHeapCollection(double[] e) {
    // this will print the entirety of an array of doubles you will pass
    // to your build function.
    System.out.println("Printing Collection to pass in to build function:");
    for (int i = 0; i <  e.length; i++) {
      System.out.print(e[i] + "\t");
    }
    System.out.print("\n");
  }

  public static void printHeap(double[] e, int len) {
    // pass in mbh.getHeap(),mbh.size()... this method skips over unused 0th
    // index....
    System.out.println("Printing Heap");
    for (int i = 1; i < len + 1; i++) {
      System.out.print(e[i] + "\t");
    }
    System.out.print("\n");
  }
  
  public static void printSortCollection(double[] e) {
    // this will print the entirety of an array of doubles you will pass
    // to your build function.
    System.out.println("Printing Collection to pass in to heap sort function:");
    for (int i = 0; i < e.length; i++) {
      System.out.print(e[i] + "\t");
    }
    System.out.print("\n");
  }

  public static void printArray(double[] array) {
    System.out.println("Printing Array");
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + "\t");
    }
    System.out.print("\n");
  }
}