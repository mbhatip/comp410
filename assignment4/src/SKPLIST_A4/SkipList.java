package SKPLIST_A4;

import java.util.Arrays;
import java.util.Random;

public class SkipList implements SkipList_Interface {
  private SkipList_Node root;
  private final Random rand;
  private double probability;
  private final int MAXHEIGHT = 30; // the most links that a data cell may contain

  public SkipList(int maxHeight) {
	  root = new SkipList_Node(Double.NaN, maxHeight);
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
  
  private int _size;
  
  @Override
  public boolean insert(double value) {
  	// TODO Auto-generated method stub
  	return false;
  }

  @Override
  public boolean remove(double value) {
  	// TODO Auto-generated method stub
  	return false;
  }

  @Override
  public boolean contains(double value) {
  	// TODO Auto-generated method stub
  	return false;
  }

  @Override
  public double findMin() {
  	// TODO Auto-generated method stub
  	return 0;
  }

  @Override
  public double findMax() {
  	// TODO Auto-generated method stub
  	return 0;
  }

  @Override
  public boolean empty() {
  	// TODO Auto-generated method stub
  	return false;
  }

  @Override
  public void clear() {
	  rand = new Random();
	  probability = 0.5;
  }

  @Override
  public int size() {
  	// TODO Auto-generated method stub
  	return 0;
  }

  @Override
  public int level() {
  	// TODO Auto-generated method stub
  	return 0;
  }

  @Override
  public int max() {
  	// TODO Auto-generated method stub
  	return 0;
  }

}