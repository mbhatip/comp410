package MaxBinHeap_A3;
import static org.junit.Assert.*;
public class NewTests {

	public static void main(String[] args) {
		System.out.println("Hello peeps, this is my test. For this test make sure you add the assert equals jar to your project");
		System.out.println("This can be done by simply going over to the red line at import static org.junit.Assert");
		System.out.println("If you find an error in your code, you can see your heap by utilizing the printHeap function I wrote by calling printHeap(heapName)");

		System.out.println(" ");
		System.out.println("Insert Test 1");
		MaxBinHeap insertTest1 = new MaxBinHeap();
		insertTest1.insert(0.0);
		insertTest1.insert(1.0);
		insertTest1.insert(2.0);
		insertTest1.insert(5.0);
		insertTest1.insert(9.0);
		insertTest1.insert(10.0);
		insertTest1.insert(7.0);
		insertTest1.insert(6.0);
		insertTest1.insert(4.0);
		double insertTest1Expected[] = {10.0, 6.0, 9.0, 5.0, 2.0, 1.0, 7.0, 0.0, 4.0};
		checkHeap(insertTest1, insertTest1Expected);
		System.out.println("You passed insertTest1 (/o_o)/");
		
		
		// Simple Build Test

		System.out.println("Simple Build Test");
		MaxBinHeap buildTest1 = new MaxBinHeap();
		double[] buildTest1Array = {10.0, 7.0, 5.0, 4.0, 6.0, 11.0, 14.0, 20.0};
		double[] buildTest1Expected = {20.0, 10.0, 14.0, 7.0, 6.0, 11.0, 5.0, 4.0};
		buildTest1.build(buildTest1Array);
		checkHeap(buildTest1, buildTest1Expected);
		System.out.println("You passed buildTest1 -(o_o-)");
		

		System.out.println("Harder Build Test");
		MaxBinHeap buildTest2 = new MaxBinHeap();
		double[] buildTest2Array = {10, 11, 9, 12, 8, 13, 7, 14, 6};
		double[] buildTest2Expected = {14, 12, 13, 11, 8, 9, 7, 10, 6};
		buildTest2.build(buildTest2Array);
		checkHeap(buildTest2, buildTest2Expected);
		System.out.println("You passed buildTest2 (/o_o)/");
		

		System.out.println("Simple Sort Test");
		MaxBinHeap sortTest1 = new MaxBinHeap();
		double[] temp = sortTest1.sort(buildTest1Array);
		double[] sortTest1Expected = {4.0, 5.0, 6.0, 7.0, 10.0, 11.0, 14.0, 20.0};
		for (int i = 0; i < temp.length; i++) {
			assertEquals(true, temp[i] == sortTest1Expected[i]);
		}
		System.out.println("You passed sortTest1 -(o_o-)");
		

		System.out.println("Delete Test");
		buildTest1.delMax();
		double[] newBuildTest1Expected = {14.0, 10.0, 11.0, 7.0, 6.0, 4.0, 5.0};
		checkHeap(buildTest1, newBuildTest1Expected);
		buildTest1.delMax();
		buildTest1.delMax();
		buildTest1.delMax();
		double[] newBuildTest1Expected2 = {7.0, 6.0, 5.0, 4.0};
		checkHeap(buildTest1, newBuildTest1Expected2);
		buildTest1.delMax();
		buildTest1.delMax();
		buildTest1.delMax(); 
		double[] newBuildTest1Expected3 = {4.0};
		checkHeap(buildTest1, newBuildTest1Expected3);
		buildTest1.delMax();
		double[] newBuildTest1Expected4 = {0.0};
		checkHeap(buildTest1, newBuildTest1Expected4);
		System.out.println("You passed deleteTest1 (/o_o)/");
		

		System.out.println("Size Test");
		MaxBinHeap sizeTest1 =  new MaxBinHeap();
		assertEquals(0, sizeTest1.size());
		sizeTest1.insert(1.0);
		assertEquals(1, sizeTest1.size());
		sizeTest1.delMax();
		assertEquals(0, sizeTest1.size());
		sizeTest1.insert(1.0);
		sizeTest1.insert(2.0);
		sizeTest1.insert(3.0);
		assertEquals(3, sizeTest1.size());
		assertEquals(0, sortTest1.size()); //heap should be empty after sorting
		assertEquals(9, insertTest1.size());
		assertEquals(0, buildTest1.size());
		System.out.println("You passed sizeTest1 -(o_o-)");
		

		System.out.println("Everything Test");
		MaxBinHeap everythingTest1 = new MaxBinHeap();
		double[] everythingTestBuild = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 0.1};
		everythingTest1.build(everythingTestBuild);
		double[] everythingTestExpected = {9.0, 8.0, 7.0, 4.0, 5.0, 6.0, 3.0, 2.0, 1.0, 0.1};
		checkHeap(everythingTest1, everythingTestExpected);
		everythingTest1.insert(0.2);
		everythingTest1.delMax();
		assertEquals(10, everythingTest1.size());
		everythingTest1.insert(5.5);
		assertEquals(11, everythingTest1.size());
		everythingTest1.insert(4.5);
		everythingTest1.insert(6.5);
		everythingTest1.insert(3.5);
		everythingTest1.insert(100.0);
		assertEquals(true, everythingTest1.getMax() == everythingTest1.getHeap()[1]);
		double[] everythingTest1Expected1 = {100.0, 5.5, 8.0, 4.0, 5.0, 6.5, 7.0, 2.0, 1.0, 0.1, 0.2, 4.5, 6.0, 3.0, 3.5};
		assertEquals(15, everythingTest1.size());
		checkHeap(everythingTest1, everythingTest1Expected1);
		double[]everythingTestBuild1 = {};
		everythingTest1.build(everythingTestBuild1);
		assertEquals(0, everythingTest1.size());
		double[]everythingTestBuild2 = {1.0};
		everythingTest1.build(everythingTestBuild2);
		assertEquals(1, everythingTest1.size());
		everythingTest1.clear();
		assertEquals(0, everythingTest1.size());
		System.out.println("You passed everythingTest1 (/o_o)/");
		
		MaxBinHeap emptyTest = new MaxBinHeap();
		System.out.println("THIS SHOULD EQUAL NaN, IF NOT MAKE SURE U PUT IT IN YOUR CODE. Your result is ---> " + emptyTest.getMax());
		
		System.out.println(" ");
		System.out.println("YOU HAVE PASSED ALL THE TESTS O___________________O");
		System.out.println("GOOD JOB GETTING HERE. I am sure there are more edge cases to test, but these are the ones I can think of. Make sure to check them! If I made a mistake somewhere, comment it on Piazza.");
		
	
	}
	public static void printHeap(MaxBinHeap hi) {
		for (int i = 0; i < hi.size() + 1; i++) {
			System.out.println(hi.getHeap()[i]);
		}
	}
	public static void checkHeap(MaxBinHeap heap, double[] array) {
		for (int i = 1; i < heap.size() + 1; i++) {
			assertEquals(true, array[i - 1] == heap.getHeap()[i]);
		}
	}

}