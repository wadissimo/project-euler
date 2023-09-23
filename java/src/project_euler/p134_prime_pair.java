package project_euler;

import common.IntegerUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class p134_prime_pair {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        int MAX = 1_001_000;
        int nn = 1;
        int phi5 = 1;
        int phi2 = 1;
        HashMap<Integer, BigInteger> phi1 = new HashMap<>();
        for (int i = 0; i < 6; i++) {
            nn*=10;
            phi5*=5;
            phi2*=2;
            phi1.put(nn, BigInteger.valueOf((phi5-phi5/5)*(phi2-phi2/2)-1));
        }
        ArrayList<Integer> primes = new ArrayList<>();
        int[] lp = new int[MAX+1];
        IntegerUtils.sieve(MAX, primes, lp);
        long ans = 0;
        for (int i = 2; i < primes.size(); i++) {
            int p1 = primes.get(i);
            if(p1 > 1_000_000)
                break;
            long pp = p1;
            int r = 1;
            while(pp > 0){
                pp/=10;
                r*=10;
            }
            int p2 = primes.get(i+1);
            long inv = BigInteger.valueOf(p2).modPow(phi1.get(r), BigInteger.valueOf(r)).longValue();
            long n = p2 * (p1*inv%r);
            ans += n;
        }
        long t2 = System.currentTimeMillis();
        System.out.println("(t2-t1) = " + (t2 - t1));
        System.out.println("ans = " + ans); //18613426663617118
    }
}
