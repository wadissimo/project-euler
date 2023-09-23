package project_euler;

import common.IntegerUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class P124_ord_radical {
    public static void main(String[] args) {
        int N = 100_000;
        ArrayList<Integer> primes = new ArrayList<>();
        int[] lp = new int[N+1];
        int[] rads = new int[N+1];
        IntegerUtils.sieve(N, primes, lp);
        for (int n = 1; n <= N; n++) {
            int rad = 1;
            int num = n;
            while(num > 1){
                int p = lp[num];
                rad*=p;
                while(lp[num] == p){
                    num/=p;
                }
            }
            rads[n] = rad;
        }
        Integer[] ord = new Integer[N+1];
        for (int i = 0; i <= N; i++) {
            ord[i] = i;
        }
        Arrays.sort(ord, (a, b)-> Integer.compare(rads[a], rads[b]));
        System.out.println("ord[10000] = " + ord[10000]);
    }
}
