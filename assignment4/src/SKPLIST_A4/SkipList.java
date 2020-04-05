package SKPLIST_A4;

import java.util.Arrays;
import java.util.Random;

public class SkipList implements SkipList_Interface {
  private SkipList_Node root;
  private final Random rand;
  private double probability;
  private final int MAXHEIGHT = 30; // the most links that a data cell may contain

  public SkipList(int maxHeight) {
	  root = new SkipList_Node(Double.NaN, _maxHeight);
	  rand = new Random();
	  probability = 0.5;
	  
	  _maxHeight = maxHeight > MAXHEIGHT ? MAXHEIGHT : maxHeight;
	  clear();
  }

  @Override
  public void setSeed(long seed) { rand.setSeed(seed); }
  
  @Override
  public void setProbability(double probability) { 
     this.probability = probability; 
  }
  
  private boolean flip() {
    // use this where you "roll the dice"
    // call it repeatedly until you determine the level
    // for a new node
    return rand.nextDouble() < probability;
  }
  
  @Override
  public SkipList_Node getRoot() { return root; }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    
    int levels;
    for(levels = 0; levels < root.getNext().length && root.getNext(levels) != null; levels ++);
    
    StringBuilder[] sbs = new StringBuilder[levels];
    
    for(int i = 0; i < sbs.length; i ++) {
      sbs[i] = new StringBuilder();
      sbs[i].append("level ").append(i).append(":");
    }
    
    SkipList_Node cur = root;
    
    while (cur.getNext(0) != null) {
      cur = cur.getNext(0);
      for(int i = levels - 1; i >= cur.getNext().length; i --) {
        sbs[i].append("\t");
      }
      for(int i = cur.getNext().length - 1; i >= 0; i --) {
        if (cur.getNext(i) == null) {
          levels --;
        }
        sbs[i].append("\t").append(cur.getValue());
      }
    }
    
    for(int i = sbs.length - 1; i >= 0; i --) {
      sb.append(sbs[i]).append("\n");
    }
    
    return sb.toString();
  }

  //---------------------------------------------------------
  // student code follows
  // implement the methods of the interface
  //---------------------------------------------------------
    
  private final double LAMBDA = .0001;
  private int _size;
  private int _maxHeight;
  
  private SkipList_Node getLeftBound(int level, double value, SkipList_Node start) {
	  SkipList_Node leftBound = start;
	  SkipList_Node rightBound = start.getNext(level);
	  while(rightBound != null && rightBound.getValue() < value)
	  {
		  leftBound = rightBound;
		  rightBound = rightBound.getNext(level);
	  }
	  return leftBound;
  }
  
  @Override
  public boolean insert(double value) {	
	int level = 0;
	while(flip() && level < _maxHeight - 1) {level++;}
	
  	if(insert_recursive(level, new SkipList_Node(value, level+1), root)) {
  		_size++;
  		return true;
  	}
  	else {
  		return false;
  	}
  }
  
  private boolean insert_recursive(int level, SkipList_Node insertThis, SkipList_Node start) {
	  SkipList_Node leftBound = getLeftBound(level, insertThis.getValue(), start);
	  SkipList_Node rightBound = leftBound.getNext(level);
	  
	  if(rightBound != null && Math.abs(rightBound.getValue() - insertThis.getValue()) < LAMBDA) {
		  return false;
	  }
	  
	  if (level == 0 || insert_recursive(level-1, insertThis, leftBound)) {
		  leftBound.setNext(level, insertThis);
		  insertThis.setNext(level, rightBound);
		  return true;
	  }
	  else {
		  return false;		  
	  }
  }

  @Override
  public boolean remove(double value) {
  	// TODO Auto-generated method stub
  	if (empty()) {return false;}
  	if(remove_recursive(level(), value, root)) {
  		_size--;
  		return true;
  	}
  	else {
  		return false;
  	}
  }
  
  private boolean remove_recursive(int level, double value, SkipList_Node start) {
	  SkipList_Node leftBound = getLeftBound(level, value, start);
	  SkipList_Node rightBound = leftBound.getNext(level);
	  
	  if(rightBound != null && Math.abs(rightBound.getValue() - value) < LAMBDA) {
		  leftBound.setNext(level, rightBound.getNext(level));
		  return level == 0 || remove_recursive(level-1, value, leftBound);
	  }
	  else if (level == 0) {
		  return false;
	  }
	  else {
		  return remove_recursive(level-1, value, leftBound);
	  }
  }

  @Override
  public boolean contains(double value) {
  	// TODO Auto-generated method stub
	  if (empty()) {return false;}
	  return contains_recursive(level(), value, root);
  }
  
  private boolean contains_recursive(int level, double value, SkipList_Node start) {
	  SkipList_Node leftBound = getLeftBound(level, value, start);
	  SkipList_Node rightBound = leftBound.getNext(level);
	  
	  if(rightBound != null && Math.abs(rightBound.getValue() - value) < LAMBDA) {
		  return true;
	  }
	  else if (level == 0){
		  return false;
	  }
	  else {
		  return contains_recursive(level-1, value, leftBound);
	  }
  }

  @Override
  public double findMin() {
	  if (empty()) {return Double.NaN;}
  	return root.getNext(0).getValue();
  }

  @Override
  public double findMax() {
	  if (empty()) {return Double.NaN;}
  	SkipList_Node max = root;
  	int level = level();
  	
  	while(level != 0 || max.getNext(level) != null) {
  		if (max.getNext(level) == null) {
  			level--;
  		}
  		else {
  			max = max.getNext(level);
  		}
  	}
	  return max.getValue();
  }

  @Override
  public boolean empty() {
  	return _size == 0;
  }

  @Override
  public void clear() {
	  root = new SkipList_Node(Double.NaN, _maxHeight);
	  _size = 0;
  }

  @Override
  public int size() {
  	return _size;
  }

  @Override
  public int level() {
  	if (empty()) {return -1;}
	  for (int i = _maxHeight - 1; i >= 0; i--) {
  		if (root.getNext(i) != null) {
  			return i;
  		}
  	}
	  return -1;
  }

  @Override
  public int max() {
  	return _maxHeight;
  }

}