package project_euler;

import common.IntegerUtils;

import java.util.ArrayList;

public class p027_quadratic_primes {
    public static void main(String[] args) {
        int MAX = 1000000;
        ArrayList<Integer> primes = new ArrayList<>();
        int[] lp = new int[MAX+1];
        IntegerUtils.sieve(MAX, primes, lp);
        int max = 0;
        int bestA = 0;
        int bestB = 0;
        for (int a = -999; a < 1000 ; a++) {
            for (int b = 1; b < 1000; b++) {
                for (int n = 0; n < 100; n++) {
                    int v = n * n + a * n + b;
                    if(v <= 0 || lp[v] != v){ //not prime
                        if(n > 0){
                            if(n > max){
                                max = n;
                                bestA = a;
                                bestB = b;
                            }
                        }
                        break;
                    }
                }
            }
        }
        System.out.println("max = " + max);
        System.out.println("bestA = " + bestA);
        System.out.println("bestB = " + bestB);
        System.out.println("bestB*bestA = " + bestB * bestA);
    }
}
