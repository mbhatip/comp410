package BST_A2;

public class BST implements BST_Interface {
  public BST_Node root;
  int size;
  static boolean REMOVE_USING_MIN = false;
  
  public BST(){ size=0; root=null; }
  
  @Override
  //used for testing, please leave as is
  public BST_Node getRoot(){ return root; }


  //--------------------------------------------------
  //
  // of course, fill in the methods implementations
  // for the interface
  //
  //--------------------------------------------------
  
	@Override
	public boolean insert(String s) {
		if (contains(s)) {
			return false;
		}
		
		BST_Node insertThis = new BST_Node(s);
		if (empty()) {
			root  = insertThis;
			size++;
			return true;
		}
		
		BST_Node parent = root;
		
		while (true) {
			String parentData = parent.getData();
			
			if (parentData.compareTo(s) > 0) {
				if (parent.left == null) {
					parent.left = insertThis;
					break;
				}
				parent = parent.left;
			}
			else {
				if (parent.right == null) {
					parent.right = insertThis;
					break;
				}
				parent = parent.right;
			}
		}
		insertThis.parent = parent;
		size++;
		return true;
	}
	
	@Override
	public boolean remove(String s) {
		BST_Node removeThis = findNode(s, root);
		
		if (removeThis == null) {
			return false;
		}

		BST_Node parent = removeThis.parent;
		BST_Node left = removeThis.left;
		BST_Node right = removeThis.right;
		
		boolean isRoot = parent == null;
		boolean isLeftChild = !isRoot && parent.left != null && parent.left.equals(removeThis);
		
		
		if (left == null && right != null) {
			if (isRoot) {
				root = right;
				root.parent = null;
			}
			else if(isLeftChild) {
				parent.left = right;
			}
			else {
				parent.right = right;
			}
			right.parent = parent;
		}
		else if (left != null && right == null) {
			if (isRoot) {
				root = left;
				root.parent = null;
			}
			else if (isLeftChild) {
				parent.left = left;
			}
			else {
				parent.right = left;
			}
			left.parent = parent;
		}
		else if (left != null && right != null) {
			String newData = REMOVE_USING_MIN ? findMinNode(right).getData()
					: findMaxNode(left).getData();
			remove(newData);
			if (isRoot) {
				root.data = newData;
			}
			removeThis.data = newData;
			return true;
		}
		else {
			if (isRoot) {
				root = null;
			}
			
			else if(isLeftChild) {
				parent.left = null;
			}
			else {
				parent.right = null;
			}
		}
		removeThis = null;
		size--;
		return true;
	}
	
	@Override
	public String findMin() {
		BST_Node min = findMinNode(root);
		return min == null ? null : min.getData();
	}
	
	private BST_Node findMinNode(BST_Node parent) {
		if(empty()) {
			return null;
		}
		while (parent.left != null) {
			parent = parent.left;
		}
		return parent;
	}
	
	@Override
	public String findMax() {
		BST_Node max = findMaxNode(root);
		return max == null ? null : max.getData();
	}
	
	private BST_Node findMaxNode(BST_Node parent) {
		if(empty()) {
			return null;
		}
		while (parent.right != null) {
			parent = parent.right;
		}
		return parent;
	}
	
	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return size() == 0;
	}
	
	@Override
	public boolean contains(String s) {
		return findNode(s, root) != null;
	}
	
	private BST_Node findNode(String s, BST_Node parent) {
		if (parent == null || s.equals(parent.getData())) {
			return parent;
		}
		BST_Node left = findNode(s, parent.left);
		BST_Node right = findNode(s, parent.right);
		
		return left != null ? left : right;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	@Override
	public int height() {
		return getHeight(root);
	}
	
	private int getHeight(BST_Node parent) {
		if (parent == null) {
			return -1;
		}
		int left = getHeight(parent.left) + 1;
		int right = getHeight(parent.right) + 1;
		return left > right ? left : right;
	}


}