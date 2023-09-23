package project_euler;

import common.IntegerUtils;

import java.util.ArrayList;
import java.util.HashSet;

public class p050_prime_dig_repl {
    public static void main(String[] args) {
        int MAX = 10_000_000;
        int N = 8;
        ArrayList<Integer> primes = new ArrayList<>();
        int[] lp = new int[MAX+1];
        IntegerUtils.sieve(MAX, primes, lp);
        String[] prStr = new String[primes.size()];
        int k = 0;
        HashSet<String> set = new HashSet<>(primes.size());
        for(int p: primes){
            prStr[k++] = Integer.toString(p);
            set.add(prStr[k-1]);
        }
        int[] firstDig = new int[]{0,1,2};
        for (int i = 1000; i < MAX ; i++) {
            String s = prStr[i];
            for(int dig: firstDig){
                if(s.indexOf((char)(dig + '0')) != -1){
                    int cnt = 1;
                    for (int d = dig+1; d <= 9 && N-cnt <= 9-d+1 ; d++) {
                        String updated = s.replace((char)(dig+'0'), (char)(d+'0'));
                        if(set.contains(updated))
                            cnt++;
                    }
                    if(cnt >= N){
                        System.out.println("s = " + s);
                        System.out.println("cnt = " + cnt);
                        System.out.println("dig = " + dig);
                        return;
                    }
                }
            }
        }


    }
}
