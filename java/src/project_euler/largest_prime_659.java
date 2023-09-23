package project_euler;

import common.IntegerUtils;

import java.util.ArrayList;

public class largest_prime_659 {
    public static void main(String[] args) {
        int k = 2;
        int MAX = 1000;
        ArrayList<Integer> primes = new ArrayList<>();
        int[] lp = new int[MAX+1];
        IntegerUtils.sieve(MAX, primes, lp);
        for (int n = 1; n <= 100; n++) {
            int val = n*n+ k*k;
            int next = (n+1)*(n+1) + k*k;
            int p = 0;
            for(int prime: primes){
                if(val%prime == 0 && next%prime == 0)
                    p = prime;
            }
            System.out.print("val = " + val);
            System.out.print(", next = " + next);
            System.out.println(", p = " + p);
        }
    }
}
