package project_euler;

import common.IntegerUtils;

import java.math.BigInteger;

public class p097_non_mersenne_primes {
    public static void main(String[] args) {
        long mod = 10_000_000_000L;
        BigInteger MOD = BigInteger.valueOf(mod);
        BigInteger NUM = BigInteger.valueOf(2).modPow(BigInteger.valueOf(7830457), MOD);
        NUM = NUM.multiply(BigInteger.valueOf(28433)).add(BigInteger.ONE).mod(MOD);

        System.out.println("NUM = " + NUM);
    }
}
