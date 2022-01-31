public class MySolution2 {
   // int[] pegs= new int[] {4,30,50};             
    //System.out.println(solution(pegs));
    public static int[] solution(int[] pegs) {
        // Your code here       
        int sign =-1;
        int x = pegs[0];    
        for(int peg: pegs){ 
            x += 2 * peg * sign;
            sign*=-1;
        }
        x+= pegs[pegs.length-1] * sign;
        x*=2;
        int y = (pegs.length%2==0) ? 3 : 1;
         if(x%y==0) {
            x /= y;
            y = 1;
         }
        
        
        
        
        return new int[] {x,y};
        
        //3x/2+2y=y0-x0
        //3x/2+2y=46
        //x/2=z
        //y=-3x/4+23
    }
}