package project_euler;

import common.IntegerUtils;

import java.util.ArrayList;

public class p007 {
    public static void main(String[] args) {
        int N = 1000_000;
        ArrayList<Integer> primes = new ArrayList<>();
        int[] lp = new int[N+1];
        IntegerUtils.sieve(N, primes, lp);
        System.out.println("primes.get(0) = " + primes.get(0));
        System.out.println("primes.get(10001) = " + primes.get(10000));
    }
}
