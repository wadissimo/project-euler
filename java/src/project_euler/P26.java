package project_euler;

import java.math.BigDecimal;

public class P26 {
    public static void main(String[] args) {
        int d = 0;
        int max = 0;
        for (int n = 2; n < 1000 ; n++) {
            int[] rems = new int[n+1];
            int rem = 1;
            int cycle = 0;
            for (int i = 0; i <= n; i++) {
                rem = 10*rem%n;
                rems[i] = rem;
                if(rem == 0)
                    break;
                for (int j = 0; j < i; j++) {
                    if(rems[j] == rem){
                        cycle = i-j;
                        break;
                    }
                }
                if(cycle != 0)
                    break;
            }
            if( cycle > max){
                max = cycle;
                d = n;
            }
//            System.out.println("n = " + n);
//            System.out.println("cycle = " + cycle);
//            System.out.println("1.0/n = " + BigDecimal.ONE.divide(BigDecimal.valueOf(n), 30, BigDecimal.ROUND_HALF_UP));
        }
        System.out.println("d = " + d);
        System.out.println("max = " + max);
    }
}
