package project_euler;

import java.math.BigInteger;

public class p020_fac_dig_sum {
    public static void main(String[] args) {
        BigInteger f = BigInteger.valueOf(2);
        for (int i = 3; i <=100; i++) {
            f = f.multiply(BigInteger.valueOf(i));
        }
        String s = f.toString();
        long ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int d = s.charAt(i)- '0';
            ans+=d;
        }
        System.out.println("ans = " + ans);
    }
}
