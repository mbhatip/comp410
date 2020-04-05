package SKPLIST_A4;

import java.util.Arrays;
import java.util.Random;

public class SkipList implements SkipList_Interface {
  private SkipList_Node root;
  private final Random rand;
  private double probability;
  private int _maxHeight;
  private int _size;
  private final int MAXHEIGHT = 30; // the most links that a data cell may contain

  public SkipList(int maxHeight) {
	  _maxHeight = maxHeight > MAXHEIGHT ? MAXHEIGHT : maxHeight;
	  rand = new Random();
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
  
  @Override
  public boolean insert(double value) {	
	int level = 0;
	while(flip() && level < _maxHeight) {level++;}

  	if(insert_recursive(level, new SkipList_Node(value, level), root)) {
  		_size++;
  		return true;
  	}
  }
  
  private boolean insert_recursive(int level, SkipList_Node insertThis, SkipList_Node leftBound) {
	  SkipList_Node rightBound = leftBound.getNext(level);
	  while(rightBound != null && rightBound.getValue() < insertThis.getValue())
	  {
		  leftBound = rightBound;
		  rightBound = rightBound.getNext(level);
	  }
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
  }

  @Override
  public boolean contains(double value) {
  	// TODO Auto-generated method stub
	  if (empty()) {return false;}
	  return contains_recursive(level(), value, root);
  }
  
  private boolean contains_recursive(int level, double value, SkipList_Node leftBound) {
	  SkipList_Node rightBound = leftBound.getNext(level);
	  while(rightBound != null && rightBound.getValue() < value)
	  {
		  leftBound = rightBound;
		  rightBound = rightBound.getNext(level);
	  }
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
  	// TODO Auto-generated method stub
	  if (empty()) {return Double.NaN;}
  	return 0;
  }

  @Override
  public double findMax() {
  	// TODO Auto-generated method stub
	  if (empty()) {return Double.NaN;}
  	return 0;
  }

  @Override
  public boolean empty() {
  	return _size == 0;
  }

  @Override
  public void clear() {
	  root = new SkipList_Node(Double.NaN, _maxHeight);
	  _size = 0;
	  probability = 0.5;
  }

  @Override
  public int size() {
  	return _size;
  }

  @Override
  public int level() {
  	// TODO Auto-generated method stub
  	return 0;
  }

  @Override
  public int max() {
  	return _maxHeight;
  }

}