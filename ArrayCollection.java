package Homework3;

public class ArrayCollection<T> {
	protected static final int DEFAULT_CAPACITY = 100;
	protected T[] elements;
	protected int numberOfElements;

	public ArrayCollection() {
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayCollection(int size) {
		elements = (T[]) new Object[size];
		numberOfElements = 0;
	}
	
	public boolean isEmpty() {
		return numberOfElements == 0;
	}
	
	public boolean isFull() {
		return numberOfElements == elements.length;
	}
	
	public int size() {
		return numberOfElements;
	}
	
	public String toString() {
		String collection = "";
		
		for (int i = 0; i < numberOfElements; i++)
			collection += elements[i] + "\n";
	
		return collection;
	}
	
	public boolean add(T element) {
		if(!isFull()) {
			elements[numberOfElements++]=element;// Complete your code here
			return true;
		}
		
		return false;
	}
	
	public boolean remove(T target) {
//		if(elements.length==0)return false;
//		int i=0;
//		while(elements[i]!=target) {
//		i++;
//		}
//		for(int k=i;k<=elements.length;k++) {
//		elements[k]=elements[k+1];
//		}
//		elements[elements.length-1]=null;
//		numberOfElements--;
		
//		for(int index=0;index<numberOfElements;index++) {
//			if(elements[index].equals(target)) {
//				while(elements[numberOfElements-1].equals(target))numberOfElements--; 
//				elements[index]=elements[numberOfElements-1];
//				numberOfElements--;
				
//			}
//		}
		if(!contains(target))return false;
		elements[findIndex(target)]=elements[numberOfElements-1];
		numberOfElements--;
		return true;
		
	}
	
	public boolean removeAll(T target) {
		// Complete your code here
		do {
			remove(target);
		}
		while(contains(target));
		
		return true;
	}
	
	public void removeDuplicate() {
	// Remove any duplicated elements

	}
	
	public boolean equals(ArrayCollection that) {
	// Return true if ArrayCollection are identical.
		boolean result = true;
		for(int index=0;index<this.numberOfElements;index++) {
			if (!this.elements[index].equals(that.elements[index]))
				result=false;
		}
		// Complete your code here.
		
		return result && this.size() == that.size();
	}
	
	public int count(T target) {
	// Return count of target occurrences
		int c = 0;
		for (int index=0; index<numberOfElements;index++)
		// Complete your code here
		if(elements[index].equals(target))
		c++;
		return c;
	}
	
	public void merge(ArrayCollection that) {
	// Merge that ArrayCollection into this ArrayCollection
		// Complete your code here
	}
	
	public void enlarge(int size) {
	// Enlarge elements[] with additional size
	// Complete your code here
	
	}
	
	public void clear() {
	// Remove all elements in the collection
		numberOfElements=0;
	}
	
	
	//Note: Different from textbook, this implementation has no 'found' and 'location' attributes.
	//		There is no find() method.
	// 		There is a new methods findIndex().
	public boolean contains(T target) {
	// Return true if target is found
		boolean found = false;
		for(int index=0;index<numberOfElements;index++) {
			if (elements[index].equals(target)) found=true;
			
		}
		// Complete your code here
 		
		return found;
	}

	public int findIndex(T target) {
	// Return index of target
		int index = -1; 
		for(int i=0;i<numberOfElements;i++) {
			if(elements[i].equals(target))return i;
		}
			
		// Complete your code here
		
		return index;
	}
	
	
	
}
