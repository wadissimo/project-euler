package project_euler;

import common.IntegerUtils;

import java.util.ArrayList;

public class singleton_diff_136 {
    static int bf(int n){
        int ans = 0;
        for (int i = 3; i < n; i++) {
            int cnt = 0;
            int y = 0;
            int a = 0;
            for (int div = 1; div*div <= i && cnt < 2; div++) {
                if(i%div == 0){
                    y = i/div;
                    if((div+y)%4 == 0 ){
                        if(3*y > div) {
                            a = div;
                            cnt++;
                        }
                        if(div != y && 3*div > y) {
                            a=div;
                            cnt++;
                        }
                    }
                }
            }
            if(cnt == 1) {
                System.out.print("i = " + i);
                if(i%4 == 0)
                    System.out.print(", i/4 = " + (i / 4));
                System.out.print(", y = " + a);
                System.out.println(", (i/y) = " + (i / a));

                ans++;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int N = 1_100; // 2544559
        ArrayList<Integer> primes = new ArrayList<>();
        int[] lp = new int[N+1];
        IntegerUtils.sieve(N, primes, lp);

        System.out.println("ans = " + bf(N));

    }
}
