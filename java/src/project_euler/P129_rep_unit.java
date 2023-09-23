package project_euler;

import chelper.io.FastScanner;
import common.IntegerUtils;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class P129_rep_unit {
    int A(int n){
        long rem = 1;
        int max = 2000000;
        int it = 1;
        while(it < max){
            rem = rem*10%n;
            if(rem == 1)
                break;
            it++;
        }
        
        if(it >= max)
            return -1;
            //throw new RuntimeException("not found");
        return it;
    }

    public void solve(int testNumber, FastScanner in, PrintWriter out) {
        int MAX = 100;
        System.out.println("A(41) = " + A(41*9));
        System.out.println("A(7) = " + A(7*9));
        System.out.println("A(1000171) = " + A(1000171*9));
        ArrayList<Integer> primes = new ArrayList<>();
        int[] lp = new int[MAX +1];
        IntegerUtils.sieve(MAX, primes, lp);

        int max = 0;
        for (int i = 2; i < MAX; i++) {
//            Integer p = primes.get(i);
//            if(p > 990_000){
            if(i%2 != 0 && i%5 != 0) {
                //if(i > 1_000_000){
                int res = A(i *9);
                System.out.print("i = " + i);
                System.out.println(", res = " + res);
                if(res > max){
                    max = res;
                    System.out.println("max = " + max);
                }
                if (res > 1_000_000) {
                    System.out.println("FOUND");
                    System.out.println("p = " + i);
                    System.out.println("A(p) = " + A(i*9));
                    break;
                }


            }
        }

    }
}
