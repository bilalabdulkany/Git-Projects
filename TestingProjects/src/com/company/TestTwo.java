package com.company;

import java.util.Arrays;
import java.util.stream.Stream;

public class TestTwo {
    public static void main (String [] args){

        String subs= "ophvlbhyc";
        int subsLength=subs.length();
        //feat: find the substring that has both start and end letters matching:
    /*
    *       a   b   c   d   a   a   b   g
    *   a   1   0   0   0   1   1   0   0
    *   b   0   1   0   0   0   0   1   0
    *   c   0   0   1   0   0   0   0   0
    *   d   0   0   0   1   0   0   0   0
    *   a   1   0   0   0   1   1   0   0
    *   a   1   0   0   0   1   1   0   0
    *   b   0   1   0   0   0   0   1   0
    *   g   0   0   0   0   0   0   0   1
    *
    * */
        char[][] charMatrix= new char[subsLength+1][subsLength+1];
        char[] subsCharArray = subs.toCharArray();
        int subStringCount=0;
        char startChar,endChar;
        for(int i=1; i<subsLength+1;i++){
            charMatrix[i][0]= subsCharArray[i-1];
            charMatrix[0][i]= subsCharArray[i-1];
            startChar= subsCharArray[i-1];
            for(int j=i; j< subsLength+1;j++){

                endChar=subsCharArray[j-1];
                if(i>0 && j > 0) {

                    //System.out.println(start);
                    if(startChar == endChar) {
                        charMatrix[i][j] = 'M';
                        subStringCount++;
                    }
                }
            }
        }

        Arrays.stream(charMatrix).forEach(c-> System.out.println(c));
        System.out.println("Number of Substrings possible: " +subStringCount);
    }
}
