package project_euler;

import common.IntegerUtils;

import java.util.ArrayList;

public class p128_hexagonal {
    public static void main(String[] args) {
        int MAX = 1_000_000;
        int SEARCH = 2000;
        ArrayList<Integer> primes = new ArrayList<>();
        int[] lp = new int[MAX +1];
        IntegerUtils.sieve(MAX, primes, lp);
        long start = 2;
        int cnt = 6;
        long ans = 0;
        int ansCnt = 2;
        for (int layer = 2; layer < 200000; layer++) {
            start+=cnt;
            cnt = layer*6;
            //System.out.println("start = " + start);
            if(lp[cnt-1] == cnt-1 && lp[cnt+1] == cnt+1 && lp[2*cnt+5] == 2*cnt+5){
                ans = start;
                ansCnt++;
            }
            if(ansCnt == SEARCH)
                break;
            if(lp[cnt-1] == cnt-1 && lp[cnt+5] == cnt+5 && lp[2*cnt-7] == 2*cnt-7){
                ans = start+cnt-1;
                ansCnt++;
            }
            if(ansCnt == SEARCH)
                break;
        }
        System.out.println("ans = " + ans);
        System.out.println("ansCnt = " + ansCnt);//14516824220

    }
}
