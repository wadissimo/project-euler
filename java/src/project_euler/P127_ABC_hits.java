package project_euler;

import common.IntegerUtils;

import java.util.*;

public class P127_ABC_hits {
    private static int rad(int a){
        int res = 1;
        int a_orig = a;
        while(a > 1){
            int p = lp[a];
            divs[a_orig][divCnt[a_orig]++] = p;
            res *= lp[a];
            while(lp[a] == p){
                a/=p;
            }
        }
        return res;
    }
    static int[][] divs;
    static int[] divCnt;
    static int[] lp;
    static int select(int mask, int[] divs, int cnt){
        int res = 1;
        for (int i = 0; i < cnt; i++) {
            if((mask&(1<<i)) != 0)
                res *= divs[i];
        }
        return res;
    }
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        int MAX = 120000;
        ArrayList<Integer> primes = new ArrayList<>();
        lp = new int[MAX+1];
        IntegerUtils.sieve(MAX, primes, lp);
        int[] rads = new int[MAX];
        divs = new int[MAX][30];
        divCnt = new int[MAX];
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        int max = 0;
        for (int i = 1; i < MAX ; i++) {
            rads[i] = rad(i);
            max = Math.max(max, divCnt[i]);
            List<Integer> list = map.computeIfAbsent(rads[i], k -> new LinkedList<>());
            list.add(i);
        }
        long ans = 0;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int rad = entry.getKey();
            if(divCnt[rad] >= 2){
                int cnt = divCnt[rad];
                int[] cur = divs[rad];
                for (int mask = 1; mask < (1<<cnt)-1; mask++) {
                    int crad = select(mask, cur, cnt);
                    for (int c : map.get(crad)) {
                        if(c > rad){
                            int rev = ((1<<cnt)-1)^mask;
                            for(int bmask = rev; bmask > 0; bmask = (bmask-1)&rev){
                                int amask = rev^bmask;
                                int brad = select(bmask, cur, cnt);
                                int arad = select(amask, cur, cnt);
                                for (int b : map.get(brad)) {
                                    if(b < c && c < 2*b && rads[c-b] == arad) {
                                        ans += c;
                                    }
                                }
                            }

                        }
                    }

                }

            }
        }
        System.out.println("ans = " + ans);
        long t2 = System.currentTimeMillis();
        System.out.println("(t2-t1) = " + (t2 - t1));

    }
}
