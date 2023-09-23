package project_euler;

import common.IntegerUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;


public class p060_prime_pair_set {
    private static ArrayList<Integer> primes;
    private static TreeSet<Integer> primeSet;
    private static String[] primeStrings;
    private static final int MAX_SIEVE = 100_000_000;
    private static final int MAX_CONSIDER = 5000;
    private static int[] a = new int[5];
    private static boolean[][] memo = new boolean[MAX_CONSIDER][MAX_CONSIDER];
    static boolean check(String p, String q){
        long p1 = Long.parseLong(p+q);
        long p2 = Long.parseLong(q+p);
        if(p1 >= MAX_SIEVE || p2 >= MAX_SIEVE)
            return false;
        return primeSet.contains((int) p1) && primeSet.contains((int) p2);
    }
    private static long minSum = Long.MAX_VALUE;
    static void go(int ind, int pi){
        if(ind == a.length){
            long sum = 0;
            for(int i: a){
                sum += primes.get(i);
                System.out.print(primes.get(i) + " ");
            }
            System.out.println();
            System.out.println("sum = " + sum);
            minSum = Math.min(minSum, sum);
            System.out.println("minSum = " + minSum);
            return;
        }
        for (int i = pi+1; i < MAX_CONSIDER ; i++) {
            a[ind] = i;
            boolean valid = true;
            for (int j = 0; j < ind; j++) {
                if(!memo[a[j]][i]){
                    valid = false;
                    break;
                }
            }
            if(valid){
                go(ind+1, i);
            }
        }
    }

    public static void main(String[] args) {

        primes = new ArrayList<>();

        int[] lp = new int[MAX_SIEVE +1];
        IntegerUtils.sieve(MAX_SIEVE, primes, lp);

        primeStrings = new String[primes.size()];
        int pi = 0;
        for(Integer prime: primes){
            primeStrings[pi++] = prime.toString();
        }
        primeSet = new TreeSet<>(primes);
        for (int i = 0; i < MAX_CONSIDER; i++) {
            for (int j = i+1; j < MAX_CONSIDER; j++) {
                memo[i][j] = memo[j][i] = check(primeStrings[i], primeStrings[j]);
            }
        }
        go(0, 0);
    }
}
