package DiGraph_A5;

public class DiGraphPlayground {

	public static void main (String[] args) {
  
// thorough testing is your responsibility
//
  // you may wish to create methods like 
  //    -- print
  //    -- sort
  //    -- random fill
  //    -- etc.
  // in order to convince yourself your code is producing
  // the correct behavior
      exTest();
	  addNodeTest();
	  delNodeTest();
	  efficiencyTest(1000000);
	  addEdgeTest();
	  
    }
  
  private static void Assert(boolean b, String message) {
	  if (b) { return; }
	  System.out.println("Failed: Expected " + message);
	  System.exit(0);
  }
  
  private static void assertTrue(boolean b, String message) {
	  Assert(b, "True: " + message);
  }
  
  private static void assertFalse(boolean b, String message) {
	  Assert(!b, "False: " + message);
  }
  
  private static void assertFalse(boolean b) {
	  Assert(!b, "False");
  }
  
  private static void assertTrue(boolean b) {
	  Assert(b, "True");
  }
  
  	public static void addNodeTest() {
  		DiGraph d = new DiGraph();
  		assertTrue(d.addNode(0, "not bogus"));
  		assertTrue(d.addNode(1, "wow"));
  		assertFalse(d.addNode(0, "bogus"));
  		assertFalse(d.addNode(2, "wow"));
  		assertTrue(d.addNode(2, "bogus"));
  		assertFalse(d.addNode(-1, "why?"));
  		assertTrue(d.addNode(3, ""));
  		assertFalse(d.addNode(4, ""));
  		assertFalse(d.addNode(4, null));
  	}
  	
  	public static void addEdgeTest() {
  		DiGraph d = new DiGraph();
  		long numNodes = 5;
  		for (long i = 0; i < numNodes; i++) {
  			String name = "Node #" + String.valueOf(i);
  			assertTrue(d.addNode(i, name));
  		}
  		d.print();
  		for (long i = 0; i < numNodes; i++) {
  			String name = "Node #" + String.valueOf(i);
  			assertTrue(d.addEdge(i, "Node #0", name));
  		}
  		d.print();
  		for (long i = 1; i < numNodes; i++) {
  			String name = "Node #" + String.valueOf(i);
  			assertTrue(d.addEdge(i*10, "Node #1", name));
  		}
  		d.print();
  		assertFalse(d.addEdge(5, "Node #0", "Node #4"), "0 to 8");
  		assertFalse(d.addEdge(5, "Node #1", "Node #3"), "5 to 1");
  		assertFalse(d.addEdge(-1, "Node #4", "Node #4"));
  		assertFalse(d.addEdge(5, "bogus", "Node #4"));
  		assertFalse(d.addEdge(5, "Node #4", "bogus"));
  		assertFalse(d.addEdge(5, null, "Node #4"));
  		assertFalse(d.addEdge(5, "Node #4", null));
  		assertTrue(d.addEdge(5, "Node #4", "Node #4", -3, null));
  		d.print();
  		System.out.println("addEdgeTest done");
  	}
  	
  	public static void delNodeTest() {
  		DiGraph d = new DiGraph();
  		long numNodes = 5;
  		for (long i = 0; i < numNodes; i++) {
  			String name = "Node #" + String.valueOf(i);
  			assertTrue(d.addNode(i, name));
  		}
  		d.print();
  		for (long i = 0; i < numNodes; i++) {
  			String name = "Node #" + String.valueOf(i);
  			assertTrue(d.addEdge(i, "Node #0", name));
  		}
  		d.print();
  		for (long i = 1; i < numNodes; i++) {
  			String name = "Node #" + String.valueOf(i);
  			assertTrue(d.addEdge(i*10, "Node #1", name));
  		}
  		d.print();
  		assertFalse(d.delNode("bogus"));
  		for (long i = 0; i < numNodes; i++) {
  			String name = "Node #" + String.valueOf(i);
  			assertTrue(d.delNode(name));
  		}
  		assertFalse(d.delNode("Node #0"));
  		d.print();
  		System.out.println("delNodeTest done");
  	}
  	
  	public static void efficiencyTest(int loop) { 
  		long start = System.currentTimeMillis();
  		System.out.println("Adding " + loop + " nodes");
  		DiGraph d = new DiGraph();
  		for (int i = 0; i < loop; i++) {
  			d.addNode(i, String.valueOf(i));
  		}
  		long end = System.currentTimeMillis();
  		System.out.println("Finished. Total time: " + (end - start));
  		start = end;
  		
  		System.out.println("Adding " + loop + " edges");
  		for (int i = 0; i < loop; i++) {
  			d.addEdge(i, String.valueOf(i), String.valueOf(loop - i - 1), i);
  		}
  		end = System.currentTimeMillis();
  		System.out.println("Finished. Total time: " + (end - start));
  	}
  
    public static void exTest(){
      DiGraph d = new DiGraph();
      d.addNode(1, "f");
      d.addNode(3, "s");
      d.addNode(7, "t");
      d.addNode(0, "fo");
      d.addNode(4, "fi");
      d.addNode(6, "si");
      d.addEdge(0, "f", "s", 0, null);
      d.addEdge(1, "f", "si", 0, null);
      d.addEdge(2, "s", "t", 0, null);
      d.addEdge(3, "fo", "fi", 0, null);
      d.addEdge(4, "fi", "si", 0, null);
      System.out.println("numEdges: "+d.numEdges());
      System.out.println("numNodes: "+d.numNodes());
    }
}