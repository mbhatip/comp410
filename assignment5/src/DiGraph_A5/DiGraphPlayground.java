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
	    int limit = 1000000;
		
	    
	    exTest();
		addNodeTest();
		delNodeTest(limit);
		efficiencyTest(limit);
		addEdgeTest(limit);
		algorithmEfficiencyTest(limit);
		System.out.println("DONE!!");
		//efTest();
    }
  
  	public static void addNodeTest() {
  		String name = "addNodeTest";
  		timer(name + " start");
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
  		timer(name);
  		
  	}
  	
  	public static void delNodeTest(long numNodes) {
  		String func = "delNodeTest";
  		timer(func + " start");
  		DiGraph d = new DiGraph();
  		for (int loop = 0; loop < 2; loop++) {
  			for (long i = 0; i < numNodes; i++) {
	  			String name = "Node #" + String.valueOf(i);
	  			assertTrue(d.addNode(i, name), "adding nodes");
	  		}
	  		for (long i = 0; i < numNodes; i++) {
	  			String name = "Node #" + String.valueOf(i);
	  			assertTrue(d.addEdge(i*2, "Node #0", name), "adding edges");
	  		}
	  		for (long i = 1; i < numNodes; i++) {
	  			String name = "Node #" + String.valueOf(i);
	  			assertTrue(d.addEdge(1 + i*2, "Node #1", name), "adding edges 2");
	  		}
	  		assertFalse(d.delNode("bogus"));
	  		for (long i = 0; i < numNodes; i++) {
	  			String name = "Node #" + String.valueOf(i);
	  			assertTrue(d.delNode(name), "deleting nodes");
	  		}
	  		assertFalse(d.delNode("Node #0"));
  		}
  		timer(func);
  	}
  	
  	public static void efficiencyTest(int loop) { 
  		String name = "efficiencyTest";
  		timer(name + " start");
  		DiGraph d = new DiGraph();
  		
  		System.out.println("Adding " + loop + " nodes");
  		for (int i = 0; i < loop; i++) {
  			assertTrue(d.addNode(i, String.valueOf(i)));
  		}
  		
  		System.out.println("Adding " + loop + " edges");
  		for (int i = 0; i < loop; i++) {
  			assertTrue(d.addEdge(i, String.valueOf(i), String.valueOf(loop - i - 1), i));
  		}
  		
  		System.out.println("Deleting " + loop + " edges");
  		for (int i = 0; i < loop; i++) {
  			assertTrue(d.delEdge(String.valueOf(i), String.valueOf(loop - i - 1)));
  		}
  		
  		System.out.println("Deleting " + loop + " nodes");
  		for (int i = 0; i < loop; i++) {
  			assertTrue(d.delNode(String.valueOf(i)));
  		}
  		timer(name);
  	}
  	
  	public static void addEdgeTest(long numNodes) {
  		String func = "addEdgeTest";
  		timer(func + " start");
  		DiGraph d = new DiGraph();
  		for (long i = 1; i < numNodes; i++) {
  			String name = "Node #" + String.valueOf(i);
  			assertTrue(d.addNode(i, name), "add Node");
  		}
  		for (long i = 1; i < numNodes; i++) {
  			String name = "Node #" + String.valueOf(i);
  			assertTrue(d.addEdge(i*2, "Node #1", name), "addEdge");
  		}
  		for (long i = 2; i < numNodes; i++) {
  			String name = "Node #" + String.valueOf(i);
  			assertTrue(d.addEdge(1+i*2, "Node #2", name), "addEdge");
  		}
  		assertFalse(d.addEdge(0, "Node #0", "Node #4"), "0 to 8");
  		assertFalse(d.addEdge(0, "Node #1", "Node #3"), "5 to 1");
  		assertFalse(d.addEdge(-1, "Node #4", "Node #4"));
  		assertFalse(d.addEdge(0, "bogus", "Node #4"));
  		assertFalse(d.addEdge(0, "Node #4", "bogus"));
  		assertFalse(d.addEdge(0, null, "Node #4"));
  		assertFalse(d.addEdge(0, "Node #4", null));
  		assertTrue(d.addEdge(0, "Node #4", "Node #4", -3, null), "add last node");
  		timer(func);
  	}
  	
  	public static void algorithmEfficiencyTest(long loop) { 
  		String func = "algorithmEfficiencyTest";
  		DiGraph d = new DiGraph();
  		for (int i = 0; i < loop; i++) {
  			assertTrue(d.addNode(i, String.valueOf(i)));
  		}
  		
  		for (int i = 0; i < loop; i++) {
  			d.addEdge(i, String.valueOf(i), String.valueOf(i + 1), i);
  		}
  		timer(func + " start");
  		d.shortestPath(String.valueOf(0));
  		timer(func);
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
    
    public static void efTest() {
    	DiGraph d = new DiGraph();
    	int x = 1000000; // change number
    	String[] arr = new String[x];

    	for (int i =0; i < x; i++) {
    	// just gives us a random label  		
    	if( i == 0) {
    	d.addNode(50000000, "0"); // needed solid start node
    	}
    	d.addNode(i,arr[i]);

    	if( i % 2 == 0) {
    	d.addEdge(i, "0", arr[i], i, "");
    	}
    	d.addEdge(i,arr[i], arr[i%(arr.length-1)], 1, "");
    	//d.addEdge(i,arr[i], arr[i%(arr.length-2)],1, "");
    	}


    	System.out.println("start");
    	long start = System.currentTimeMillis();

    	ShortestPathInfo[] t = d.shortestPath("0");
    	//This prints each statement of ShortInfo
    	for (int i = 0; i < t.length; i++) {
    	// System.out.println(t[i]);
    	}
    	System.out.println("done");
    	long end = System.currentTimeMillis();
    	long elapsedTime = end - start;
    	System.out.println(elapsedTime); // total time in milliseconds
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
    
    static long start = Long.MAX_VALUE;
    private static void timer(String message) {
    	long currTime =  System.currentTimeMillis();
    	if (start != Long.MAX_VALUE) {
    		message += " took " + String.valueOf(currTime-start) + "\n";
    		start = Long.MAX_VALUE;
    	}
    	else {
    		start = currTime;
    	}
    	System.out.println(message);
    }
}