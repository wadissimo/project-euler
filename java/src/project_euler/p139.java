package project_euler;

import common.IntegerUtils;

public class p139 {
    public static final int MAX = 100_000_000;
    private static int generate(){
        int res = 0;
        for(long n = 1;4*n*n < MAX; n++){
            for(long m = n+1; 2*m*(n+m) < MAX; m++){
                if(IntegerUtils.gcd(n, m) == 1){
                    if(n%2 == 0 || m%2 == 0){
                        long a = m*m-n*n;
                        long b = 2*n*m;
                        long r = Math.abs(a-b);
                        long c = m*m + n*n;
                        if(c%r == 0){
                            System.out.print("a = " + a);
                            System.out.print(", b = " + b);
                            System.out.print(", c = " + c);
                            System.out.println(", r = " + r);
                            long p = a + b + c;
                            res += MAX/p;
                        }
                    }
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int ans = generate();
        System.out.println("ans = " + ans);
    }
}
