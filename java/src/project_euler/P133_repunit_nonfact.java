package project_euler;

import common.IntegerUtils;

import java.util.ArrayList;

public class P133_repunit_nonfact {
    private static int A(int n){
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
        return it;
    }
    public static void main(String[] args) {
        int MAX = 100_000;
        ArrayList<Integer> primes = new ArrayList<>();
        int[] lp = new int[MAX+1];
        long ans = 0;
        IntegerUtils.sieve(MAX, primes, lp);
        for (Integer p : primes) {
            int a = A(p*9);
            for(int d: new int[]{2,5}){
                while(a%d == 0)
                    a/=d;
            }
            if(a != 1){
                ans += p;
            }
        }
        System.out.println("ans = " + ans);
    }
}
