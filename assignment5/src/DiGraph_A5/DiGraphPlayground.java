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
      //exTest();
	  //addNodeTest();
	  efficiencyTest(1000000);
	  efficiencyTest(10);
    }
  
  	public static void addNodeTest() {
  		DiGraph d = new DiGraph();
  		System.out.println(d.addNode(0, "not bogus"));
  		System.out.println(d.addNode(1, "wow"));
  		System.out.println(d.addNode(0, "bogus"));
  	}
  	
  	public static void addEdgeTest() {
  		DiGraph d = new DiGraph();
  		
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
  		if (loop <= 10) {
  			d.print();
  		}
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