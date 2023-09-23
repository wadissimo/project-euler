package project_euler;

import common.IntegerUtils;

public class P138 {
    public static void main(String[] args) {
        int MAX = 100_000_000;
        int cnt = 0;
        long ans = 0;
        long nMax = IntegerUtils.root2(MAX / 2);
        for (long n = 1; n <= MAX ; n++) {
            long d1 = n*n*5 + 1;
            long d2 = n*n*5 - 1;
            long root1 = IntegerUtils.rootOfPerfectSquare(d1);
            long root2 = IntegerUtils.rootOfPerfectSquare(d2);

            if(root1*root1 == d1){
                long m = (2*n+root1);
                if(IntegerUtils.gcd(n,m) == 1 && (n%2 != 1 || m%2 != 1)) {
                    printResult(n, m);
                    cnt++;
                    ans += n*n+m*m;
                    if(cnt == 12)
                        break;
                }
            }
            if(root2*root2 == d2){
                long m = (2*n+root2);
                if(IntegerUtils.gcd(n,m) == 1 && (n%2 != 1 || m%2 != 1)) {
                    printResult(n, m);
                    cnt++;
                    ans += n*n+m*m;
                    if(cnt == 12)
                        break;
                }
            }
        }
        System.out.println("ans = " + ans);
        System.out.println("cnt = " + cnt);
//        int MAX = 1000_000_000;
//        long ans = 0;
//        int cnt = 0;
//        for (long l = 15; l < MAX; l++) {
//            long d = 5*l*l-1;
//            long dr = (long) Math.sqrt(d);
//            if(dr * dr == d){
//                long b;
//                if((dr+2)%5 == 0){
//                    b = 2*(dr+2)/5;
//                } else if((dr-2)%5 == 0){
//                    b = 2*(dr-2)/5;
//                } else
//                    continue;
//                cnt++;
//                long h = IntegerUtils.rootOfPerfectSquare(l*l - b*b/4);
//                System.out.print("h = " + h);
//                System.out.print(", b = " + b);
//                System.out.println(", l = " + l);
//                ans += l;
//                if(cnt == 12)
//                    break;
//            }
//        }
//        System.out.println("ans = " + ans);
//        System.out.println("cnt = " + cnt);
    }

    private static void printResult(long n, long m) {
        long L = m*m+n*n;
        long h = m*m-n*n;
        long b = 4*m*n;
        System.out.print("m = " + m);
        System.out.println(",n = " + n);
        System.out.print("L = " + L);
        System.out.print(", h = " + h);
        System.out.println(", b = " + b);

    }
}
