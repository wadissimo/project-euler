package project_euler;

import common.IntegerUtils;

public class p094_almost_eq_tri {
    public static void main(String[] args) {
        long X_LIM = 1000_000_000;
        long P_LIM = 1_000_000_000;
        long A_LIM = P_LIM/3 + 7;
        long ans = 0;
        for (long aa = 2; aa < A_LIM; aa++) {
            //s = (aa+-1)/2*sqrt(4*aa^2-(aa+-1)^2)
            long aa2 = aa*aa;
            if(IntegerUtils.isPerfectSquare(3*aa2+2*aa-1)){
                long p = aa*3-1;
                System.out.printf("a = %d, b = %d, p = %d%n", aa, aa-1, p);
                if(p <= P_LIM){
                    ans += p;
                }
            } else if(IntegerUtils.isPerfectSquare(3*aa2-2*aa-1)){
                long p = aa*3+1;
                System.out.printf("a = %d, b = %d, p = %d%n", aa, aa+1, p);
                if(p <= P_LIM){
                    ans += p;
                }
            }
        }
        System.out.println("ans = " + ans);
        /*for (long x = 0; x < X_LIM; x++) {
            if(IntegerUtils.isPerfectSquare(3*x*x+4)){
                double a1 = (1.0+Math.sqrt(3*x*x+4))/3.0;
                if(Math.abs(a1-Math.round(a1)) < 0.0001){
                    System.out.printf("a = %d, b = %d%n", (long)a1, (long)a1+1);
                    long p1 = (long)a1*3 + 1;
                    if(p1 < LIM){
                        ans+=p1;
                    }
                    System.out.println("p1 = " + p1);
                }

                double a2 = (-1.0+Math.sqrt(3*x*x+4))/3.0;
                if(Math.abs(a2-Math.round(a1)) < 0.0001){
                    System.out.printf("a = %d, b = %d%n", (long)a2, (long)a2-1);
                    long p2 = (long)a1*3 - 1;
                    if(p2 < LIM){
                        ans+=p2;
                    }
                    System.out.println("p1 = " + p2);
                }

            }
        }
        System.out.println("ans = " + ans);
        */

    }
}
