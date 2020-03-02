package MaxBinHeap_A3;

public class MaxBinHeap implements Heap_Interface {
	private double[] array; //load this array
	private int size;
	private static final int arraySize = 10000; //Everything in the array will initially 
                                      //be null. This is ok! Just build out 
                                      //from array[1]

	public MaxBinHeap() {
	    this.array = new double[arraySize];
	    this.array[0] = Double.NaN;  //0th will be unused for simplicity 
				                     //of child/parent computations...
				                     //the book/animation page both do this.
	    size = 0;
	}
	    
	//Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public double[] getHeap() { 
		return this.array;
	}

	@Override
	public void insert(double element) {
		// TODO Auto-generated method stub
		size++;
		array[size] = element;
		if (size == 1) return;
		bubbleUp(size);	
	}
	
	private void bubbleUp(int index) {
		bubbleUp(array[index], index);
	}
	
	private void bubbleUp(double val, int index) {
		double parent = array[index/2];
		if (val > parent) {
			array[index] = parent;
			bubbleUp(val, index/2);
		}
		else {
			array[index] = val;
		}
	}
	
	@Override
	public void delMax() {
		// TODO Auto-generated method stub
		if (size > 1) {
			bubbleDown(array[size], 1);
			size--;
		}
		else {
			clear();
		}
	}
	
	private void bubbleDown(double val, int index) {
		double left = array[index*2];
		double right = array[index*2 + 1];
		if (index * 2 > size || (val > left && (val > right || index * 2 + 1 > size))) {
			array[index] = val;
		}
		else if (left > right || index * 2 + 1 > size) {
			array[index] = left;
			bubbleDown(val, index*2);
		}
		else {
			array[index] = right;
			bubbleDown(val, index*2 + 1);
		}
	}
	
	private void bubbleDown(int index) {
		bubbleDown(array[index], index);
	}
	
	@Override
	public double getMax() {
		return size == 0 ? Double.NaN : array[1];
	}
	
	@Override
	public void clear() {
		this.array = new double[arraySize];
		this.array[0] = Double.NaN;
		size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public void build(double[] elements) {
		// TODO Auto-generated method stub
		clear();
		size = elements.length;
		for (int i = 0; i < size; i++) {
			this.array[i+1] = elements[i];
		}
		int index = size/2;
		while (index >= 1) {
			bubbleDown(index--);
		}
	}
	
	@Override
	public double[] sort(double[] elements) {
		build(elements.clone());
		double sorted[] = new double[elements.length];
		for (int i = sorted.length - 1; i >= 0; i--) {
			sorted[i] = getMax();
			delMax();
		}
		return sorted;
		
	}
}