package project_euler;

import common.IntegerUtils;

import java.math.BigInteger;

public class P168 {
    private static long pow(int a, int pow){
        int res = 1;
        for (int i = 0; i < pow; i++) {
            res*=a;
        }
        return res;
    }
    public static void main(String[] args) {
        int MAX = 100_000;
        int MAX_LEN = 100;
        long ans = 0;
        long ansMod = 100_000;

        for (int i = 11; i < MAX; i++) {
            int d = i%10;
            int num = i/10;
            int pow = 10;
            while(pow < num){
                pow*=10;
            }
            if((num+pow*d)%i == 0){
//                System.out.println("i = " + i);
//                ans= (ans+i)%ansMod;
                ans+=i;
//                System.out.println("i = " + i);
            }
        }
        ans %= ansMod;
        System.out.println("ans = " + ans); // 98331,98326, 98321, 98316
//if(true)return;
        int tot = 0;
        for (int n = 1; n < MAX; n++) {
            int d = n%10;
            int cnt = 0;
            for (int k = 1; k <= 9; k++) {
                for (int len = 6; len <= MAX_LEN; len++) {
                    int mod = (10*k-1)*100_000;
                    long rem = ((IntegerUtils.pow(10, len, mod)-1+mod)%mod*d%mod-n*(10*k-1)%mod + mod)%mod;
                    if(rem == 0) {
                        if(len <= 8) {
                            long x = ((pow(10, len) - 1) * d - n * (10 * k - 1));
                            if (x == 0)
                                continue;
                        }
                        BigInteger X = BigInteger.valueOf(d).multiply(BigInteger.TEN.pow(len).subtract(BigInteger.ONE)).subtract(BigInteger.valueOf(n * (10 * k - 1)))
                                .divide(BigInteger.valueOf(mod));
                        String s = X.toString();
                        if(s.length() != len-5) {
                            System.out.println("n = " + n);
                            System.out.println("s = " + s);
                            System.out.println("k = " + k);
                            //throw new RuntimeException();
                            continue;
                        }

                        cnt++;
                        tot++;
                    }
                }
            }

            ans = (ans+cnt*n)%ansMod;
        }
        System.out.println("tot = " + tot);
        System.out.println("ans = " + ans);//52568, 99758,45912,98321,45907, 98717 are wrong
    }
}
