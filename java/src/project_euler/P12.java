package project_euler;

import common.IntegerUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class P12 {
    public static void main(String[] args) {
        int MAX = 1_000_000;
        ArrayList<Integer> primes = new ArrayList<>();
        int[] lp = new int[MAX+1];
        IntegerUtils.sieve(MAX, primes, lp);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n = 2; n <= MAX ; n++) {
            map.clear();
            for(int num: new int[]{n, n-1}) {
                while (num > 1) {
                    map.put(lp[num], map.getOrDefault(lp[num], 0) + 1);
                    num /= lp[num];
                }
            }
            map.put(2, map.get(2)-1);//n*(n-1)/2
            int cnt = 1;
            for (Integer value : map.values()) {
                cnt*=value+1;
            }
            System.out.print("n = " + n);
            System.out.print(", (n*(n-1)/2) = " + (n * (long)(n - 1) / 2));
            System.out.print(", cnt = " + cnt);
            System.out.println(", map = " + map);
            if(cnt > 500){
                System.out.println("(n*(n-1)/2) = " + (n * (long)(n - 1) / 2));
                break;
            }



        }
    }
}
