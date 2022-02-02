import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        //Scanner keyboard = new Scanner(System.in);
        int score = 2;

        String str = getInput();
        
        char[] word = new char[str.length()];
        int used = 0;        

        for (int i = 0; i < str.length(); i++) {
            if (Character.isAlphabetic(str.charAt(i))) {
                word[used++] = str.charAt(i);
            }   
        }
        
        if (isPalindrome(word, used))
            System.out.println("It seems to be a palindrome.");
        else
            System.out.println("It seems not a palindrome.");
        
        String [] testStrings = {   "warts n straw.", 
                                    "radar.", 
                                    "Able was I ere I saw Elba.",
                                    "tacocat.",
                                    "I am not."};
    
        for (int i = 0; i < testStrings.length; i++) {
            word = new char[testStrings[i].length()];
            used = 0;
            testStrings[i] = testStrings[i].toLowerCase();
            
            for (int j = 0; j < testStrings[i].length(); j++) {
                if (Character.isAlphabetic(testStrings[i].charAt(j))) {
                    word[used++] = testStrings[i].charAt(j);
                }   
            } 
            
            if (isPalindrome(word, used))
                score += 12;
        }
    
        System.out.printf("\nScore: %d/50\n", score);
    }
    
    public static boolean isPalindrome(char[] a, int used) {        
    	for(int i = 0; i<used; i++) 
    		if(a[i] != a[used-i-1])
    			return false;	
    	return true;
    }
    
    public static String getInput() {
        Scanner keyboard = new Scanner(System.in);
        String str;
        
        do {
            System.out.print("Enter a palindrome ending with period: ");
            str = keyboard.nextLine();
        } while (str.indexOf('.') < 0);
        
        str = str.substring(0, str.indexOf('.'));
        return str;
    }
}

