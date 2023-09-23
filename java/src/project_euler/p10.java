package project_euler;

import common.IntegerUtils;

import java.util.ArrayList;

public class p10 {
    public static void main(String[] args) {
        int N = 2_000_000;
        ArrayList<Integer> primes = new ArrayList<>();
        int[] lp = new int[N+1];
        IntegerUtils.sieve(N, primes, lp);
        long sum = 0;
        for(int p : primes){
            sum += p;
        }
        System.out.println("sum = " + sum);
    }
}
