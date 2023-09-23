package project_euler;

import common.IntegerUtils;

import java.util.ArrayList;

public class P123_prime_sq_rem {
    public static void main(String[] args) {
        int MAX = 1000_000;
        long N = 10_000_000_000L;
        ArrayList<Integer> primes = new ArrayList<>();
        int[] lp = new int[MAX+1];
        IntegerUtils.sieve(MAX, primes, lp);
        for (long i = 1; i < MAX; i+=2) {
            long p = primes.get((int)i-1);
            long rem = 2*i*p % (p*p);
            if(i==3) {
                System.out.println("p = " + p);
                System.out.println("rem = " + rem);
            }
            if(rem > N){
                System.out.println("p = " + p);
                System.out.println("i = " + i);
                break;
            }
        }
    }
}
