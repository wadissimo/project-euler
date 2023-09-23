package project_euler;

import common.IntegerUtils;

import java.util.ArrayList;

public class P35_circ_primes {
    static int rotate(int p){
        if(p<10)
            return p;
        int pow = 1;
        int num = p;
        while(num > 9){
            int dig = num%10;
            if(dig == 0)
                return -1;
            num/=10;
            pow*=10;
        }
        return (p-num*pow)*10 + num;
    }
    public static void main(String[] args) {
        int MAX = 1_000_000;
        ArrayList<Integer> primes = new ArrayList<>();
        int[] lp = new int[MAX+1];
        IntegerUtils.sieve(MAX, primes, lp);
        int ans = 0;
        for (Integer p : primes) {
            int num = rotate(p);;
            while(num != -1 && num != p && lp[num] == num){
                num = rotate(num);
            }
            if(p == num){
                System.out.println("p = " + p);
                ans++;
            }
        }
        System.out.println("ans = " + ans);
        
    }
}
