package Homework3;

public class Homework3 {

	public static void main(String[] args) {
		ArrayCollection ac1=new ArrayCollection();// Complete your driver here 
		ArrayCollection ac2=new ArrayCollection(2);// You do not need to submit driver file
		ac2.add("Apple");
		ac2.add("Orange");
		ac2.add("Lemon");
		System.out.println(ac2.remove("Apple"));
		System.out.println(ac2.remove("Lemon"));
		System.out.println(ac2);
		
	}

}
