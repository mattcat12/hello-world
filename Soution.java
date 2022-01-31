package com.google.challenges;

import java.util.ArrayList;
import java.util.Arrays;

public class Soution{
public static int solution(int[] l) { 
    ArrayList<Integer> log = new ArrayList<Integer>();
    for(int i1=0; i1 < l.length-2; i1++){
        for(int i2=i1+1; i2 < l.length-1; i2++){
            for(int i3=i2+1; i3 < l.length; i3++){
     
				if(l[i3]%l[i2]==0 && l[i2]%l[i1]==0 && !log.contains(new ArrayList<Integer>(Arrays.asList(l[i1], l[i2], l[i3])))){
                    log.addAll(new ArrayList<Integer>(Arrays.asList(l[i1], l[i2], l[i3])));
                }
            }
        }
    }
    return log.size();
}
}