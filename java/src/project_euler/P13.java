package project_euler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class P13 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(P13.class.getResourceAsStream("project_euler/P13.txt")));
        String s = reader.readLine();
        String[] input = new String[100];
        int i = 0;
        while(s!= null){
            input[i++] = s;
            s = reader.readLine();
        }
        BigInteger sum = BigInteger.ZERO;
        for(String str : input){
            sum = sum.add(new BigInteger(str));
        }
        String res = sum.toString();
        System.out.println("res = " + res.substring(0, 10));
    }
}
