package project_euler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class p80_square_root {
    static BigInteger sqrt(BigInteger num){
        BigInteger TWO = BigInteger.valueOf(2);
        BigInteger left = BigInteger.ZERO;
        BigInteger right = num;
        while(left.compareTo(right) < 0) {
            BigInteger mid = left.add(right).add(BigInteger.ONE).divide(TWO);
            if(mid.multiply(mid).compareTo(num) > 0) {
                right = mid.subtract(BigInteger.ONE);
            } else {
                left = mid;
            }
        }
        return left;
    }
    public static void main(String[] args) {
        int sum = 0;
        for (int num = 2; num <= 100; num++) {
            BigInteger powd = BigInteger.valueOf(num).multiply(BigInteger.valueOf(10).pow(200));
            BigInteger sqrt = sqrt(powd);
            if(!sqrt.multiply(sqrt).equals(powd)) {
                System.out.println("num = " + num);
                String s = sqrt.toString();

                for (int i = 0; i < 100; i++) {
                    int d = s.charAt(i) - '0';
                    sum += d;
                }
            }
        }
        System.out.println("sum = " + sum);



    }
}
