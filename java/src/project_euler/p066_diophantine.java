package project_euler;

import common.IntegerUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class p066_diophantine {
    static List<BigInteger> sqrtFraction(int n, int N){
        BigDecimal sqrtN = BigDecimal.valueOf(Math.sqrt(n));
        BigInteger a = BigInteger.ONE;
        BigInteger b = BigInteger.ZERO;
        BigInteger c = BigInteger.ONE;
        List<BigInteger> res = new LinkedList<>();
        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);

        for (int i = 0; i < N; i++) {

            BigDecimal val = (new BigDecimal(a).multiply(sqrtN).add(new BigDecimal(b))).divide(new BigDecimal(c), mc);
            BigInteger f = val.toBigInteger();

            res.add(f);
            //System.out.println("f = " + f);
            BigInteger a1 = c.multiply(a);
            BigInteger b1 = c.multiply(f.multiply(c).subtract(b));
            BigInteger c1 = a.multiply(a).multiply(BigInteger.valueOf(n)).subtract(b.subtract(f.multiply(c)).multiply(b.subtract(f.multiply(c))));
            BigInteger g = c1.gcd(b1).gcd(a1);
            a = a1.divide(g);
            b = b1.divide(g);
            c = c1.divide(g);
        }
        return res;
    }

    static BigInteger[] getFraction(List<BigInteger> seq){
        BigInteger num = BigInteger.ZERO;
        BigInteger den = BigInteger.ONE;
        for (ListIterator<BigInteger> it = seq.listIterator(seq.size()); it.hasPrevious(); ) {
            BigInteger a = it.previous();
            BigInteger num1 = a.multiply(num).add(den);
            BigInteger den1 = num;
            BigInteger g = num1.gcd(den1);
            num = num1.divide(g);
            den = den1.divide(g);
        }

        return new BigInteger[]{num, den};
    }
    public static void main(String[] args) {
        int ans = 0;
        BigInteger max = BigInteger.ZERO;
        for (int d = 0; d < 1000; d++) {
            int sq = (int)Math.sqrt(d);
            if(sq * sq == d){
                continue;
            }
            int N = 1000;
            System.out.print("d = " + d);
            for (int i = 2; i < N; i++) {
                List<BigInteger> seq = sqrtFraction(d, i);
                BigInteger[] fraction = getFraction(seq);
                BigInteger x = fraction[0];
                BigInteger y = fraction[1];
                if(x.multiply(x).subtract(BigInteger.valueOf(d).multiply(y).multiply(y)).equals(BigInteger.ONE)) {
                    if(x.compareTo(max) > 0){
                        max = x;
                        ans = d;
                    }
                    //System.out.println("Found a solution: ");
                    //System.out.println("i = " + i);
                    System.out.println(" x = " + x);
                    //System.out.println("y = " + y);
                    //System.out.println("(x*x-d*y*y) = " + (x * x - d * y * y));
                    break;
                }
                if(i == N-1){
                    System.out.println(" Solution is not found");
                    return;
                }
            }
        }
        System.out.println("ans = " + ans);
        System.out.println("max = " + max);



    }
}
