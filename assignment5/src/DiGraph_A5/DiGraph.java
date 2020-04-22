package DiGraph_A5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DiGraph implements DiGraphInterface, Iterable<Node> {
	private int _numEdges;
	private int _numNodes;
	private HashMap<Long,Node> _nodeIDs;
	private HashMap<String,Node> _nodeNames;
	private HashMap<Long,Edge> _edgeIDs;
	private List<Node> _inorder;

	// in here go all your data and methods for the graph

	public DiGraph ( ) { // default constructor
		// explicitly include this
		// we need to have the default constructor
		// if you then write others, this one will still be there
		_numEdges = 0;
		_numNodes = 0;
		_nodeIDs = new HashMap<Long,Node>();
		_nodeNames = new HashMap<String,Node>();
		_edgeIDs = new HashMap<Long,Edge>();
		_inorder = new ArrayList<Node>();
	}

	private boolean exists(Existence e) {
		return e != null && e.exists;
	}

	@Override
	public boolean addNode(long idNum, String label) {
		if (label == null) {
			return false;
		}
		
		boolean notUniqueID = exists(_nodeIDs.get(idNum));
		boolean notUniqueString = exists(_nodeNames.get(label)); 
		if (idNum < 0 || notUniqueID || notUniqueString) {
			return false;
		}
		
		Node newNode = new Node(idNum, label);
		_nodeIDs.put(idNum, newNode);
		_nodeNames.put(label, newNode);
		_inorder.add(newNode);
		_numNodes++;
		return true;
	}
	
	
	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		Node source = _nodeNames.get(sLabel);
		Node dest = _nodeNames.get(dLabel);
		if (!exists(source) || !exists(dest)) {
			return false;
		}
		
		if (idNum < 0 || exists(_edgeIDs.get(idNum)) || exists(source.getEdge(dLabel))) {
			return false;
		}
		
		Edge newEdge = new Edge(idNum, sLabel, dLabel, weight, eLabel);
		_edgeIDs.put(idNum, newEdge);
		source.addDest(dLabel, newEdge);
		_numEdges++;
		return true;
	}
	
	public boolean addEdge(long id, String source, String dest, long weight) {
		return addEdge(id, source, dest, weight, null);
	}
	
	public boolean addEdge(long id, String source, String dest, String name) {
		return addEdge(id, source, dest, 1, name);
	}

	public boolean addEdge(long id, String source, String dest) {
		return addEdge(id, source, dest, 1, null);
	}
	
	@Override
	public boolean delNode(String label) {
		Node deleteThis = _nodeNames.get(label);
		if (!exists(deleteThis)){
			return false;
		}
		
		deleteThis.delete();
		_numNodes--;
		return true;
	}
	
	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		Node source = _nodeNames.get(sLabel);
		Node dest = _nodeNames.get(dLabel);
		if (!exists(source) || !exists(dest)) {
			return false;
		}
		Edge e = source.getEdge(dLabel);
		if (!exists(e)) { return false; }
		e.delete();
		_numEdges--;
		return true;
	}
	
	@Override
	public long numNodes() {
		return _numNodes;
	}
	
	@Override
	public long numEdges() {
		return _numEdges;
	}
	
	public void print() {
		for(Node n : _inorder) {
			if (!exists(n)) {
				continue;
			}
			System.out.println("(" + n.id + ")" + n.name);
			for (Edge e : n.getEdges()) {
				if (!exists(e)) {
					continue;
				}
				String name = e.name == null ? "" : e.name;
				System.out.print("  (" + e.id + ")--");
				System.out.print(name + "," + e.weight);
				System.out.println("--> " + e.dest );
			}
			System.out.println();
		}
	}

	@Override
	public Iterator<Node> iterator() {
		return _inorder.iterator();
	}
}

class Existence {
	protected boolean exists;
	protected long id;
	protected String name;
	
	public Existence(long idNum, String label) {
		id = idNum;
		name = label;
		exists = true;
	}
	
	public void delete() {
		exists = false;
	}
}

class Edge extends Existence{
	protected long weight;
	protected String source;
	protected String dest;
	public Edge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		super(idNum, eLabel);
		source = sLabel;
		dest = dLabel;
		this.weight = weight;
	}
}

class Node extends Existence{
	private HashMap<String,Edge> _dest;
	
	public Node(long idNum, String label) {
		super(idNum, label);
		_dest = new HashMap<String,Edge>();
	}
	
	public void addDest(String dest, Edge e) {
		_dest.put(dest, e);
	}
	
	public Edge getEdge(String dest) {
		return _dest.get(dest);
	}
	
	public Collection<Edge> getEdges() {
		return _dest.values();
	}
	
	public void delete() {
		for (Edge e : getEdges()) {
			e.delete();
		}
		super.delete();
	}
}

