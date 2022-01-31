package Homework3;

public class ArrayBag <T> extends ArrayCollection <T> {
	public ArrayBag() {
		
	}
	
	public ArrayBag(int size) {
		super(size);
	}
	
	public T grab() {
	// Return a random element from collection.
		int from = (int)(Math.random() * numberOfElements);

		T e = elements[from];

		// Complete your code here ...
		
		return e;
	}
}
