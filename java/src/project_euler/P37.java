package project_euler;

import common.IntegerUtils;

import java.util.ArrayList;

public class P37 {
    static boolean check(int p){
//        System.out.println("p = " + p);
        int rem = 0;
        int pow = 10;
        int orig = p;
        while(p> 0){
            rem = orig%pow;
//            System.out.println("rem = " + rem);
            if(lp[rem] != rem)
                return false;
            p/=10;
            //System.out.println("p = " + p);
            if(lp[p] != p)
                return false;
            pow*=10;
        }
//        System.out.println(" ok ");
        return true;
    }
    static int[] lp;
    public static void main(String[] args) {
        int MAX = 10_000_000;
        ArrayList<Integer> primes = new ArrayList<>();

        lp = new int[MAX +1];
        IntegerUtils.sieve(MAX,primes, lp);

        long ans = 0;
        for (Integer prime : primes) {
            if(prime < 10)
                continue;
            boolean res = check(prime);
            if(res) {
                System.out.println("prime = " + prime);
                ans+=prime;
            }
        }
        System.out.println("ans = " + ans);

    }
}
