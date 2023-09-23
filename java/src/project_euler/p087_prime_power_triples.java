package project_euler;

import common.IntegerUtils;

import java.util.TreeSet;

public class p087_prime_power_triples {
    public static void main(String[] args) {
        int PRIME_LIM = 10_000;
        TreeSet<Integer> primeSet = IntegerUtils.getPrimes(PRIME_LIM);
        int[] primes = new int[primeSet.size()];
        int nPrimes = primes.length;
        int pIdx = 0;
        for (Integer p : primeSet) {
            primes[pIdx++] = p;
        }
        int LIM = 50_000_000;
        boolean[] used = new boolean[LIM+1];
        for (int i = 0; i < nPrimes; i++) {
            long p = primes[i];
            if(p*p*p*p > LIM)
                break;
            long rem = LIM - p*p*p*p;
            for (int j = 0; j < nPrimes; j++) {
                long q = primes[j];
                if(q*q*q > rem)
                    break;
                long rem2 = rem -q*q*q;
                for (int k = 0; k < nPrimes; k++) {
                    long r = primes[k];
                    if(r*r > rem2)
                        break;
                    if(p*p*p*p + q*q*q + r*r > LIM)
                        throw new RuntimeException("lim reached");
                    used[(int)(p*p*p*p + q*q*q + r*r)] = true;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < LIM; i++) {
            if(used[i]){
                System.out.println("i = " + i);
                ans++;
            }
        }
        System.out.println("ans = " + ans);

    }
}
