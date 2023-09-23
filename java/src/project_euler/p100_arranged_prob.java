package project_euler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

public class p100_arranged_prob {
    static List<Integer> divs(int num){
        List<Integer> list = new LinkedList<>();
        while(num > 1){
            boolean found = false;
            for (int div = 2; div*div <= num; div++) {
                if(num % div == 0){
                    list.add(div);
                    num/=div;
                    found = true;
                    break;
                }
            }
            if(!found) {
                list.add(num);
                break;
            }
        }
        return list;
    }
    static void gen(long lim){
        for (long b = 0; b < lim; b++) {
            double n = (Math.sqrt(8*b*(b-1)+1)+1)/2.0;
            if(Math.abs(Math.round(n) - n) < 0.00000001){
                if(2*b*(b-1) != (long)n*((long)n-1)) {
                    throw new RuntimeException();
                }
                System.out.printf("%d - %d%n",(long)n, b);
                System.out.println(divs((int)n));
                System.out.println(divs((int)n-1));
                System.out.println(divs((int)b));
                System.out.println(divs((int)b-1));

            }
        }
    }
    public static void main(String[] args) {
        gen(10000000);
        BigInteger n = BigInteger.valueOf(4684660);
        BigInteger b = BigInteger.valueOf(3312555);
        BigInteger c = BigInteger.valueOf(582842712);
        BigInteger f = BigInteger.valueOf(100000000);
        int RANGE = 1000;
        for (int i = 0; i < 7; i++) {
            System.out.println("i = " + i);
            BigInteger nextN = n.multiply(c).divide(f);
            BigInteger nextB = b.multiply(c).divide(f);
            BigInteger nn = nextN.subtract(BigInteger.valueOf(RANGE));
            boolean found = false;
            while(!found && nn.compareTo(nextN.add(BigInteger.valueOf(RANGE))) < 0){
                BigInteger bb = nextB.subtract(BigInteger.valueOf(RANGE));
                while(!found && bb.compareTo(nextB.add(BigInteger.valueOf(RANGE))) < 0){
                    if(bb.multiply(bb.subtract(BigInteger.ONE)).multiply(BigInteger.valueOf(2))
                            .equals(nn.multiply(nn.subtract(BigInteger.ONE)))){
                        found = true;
                        System.out.println("nn = " + nn);
                        System.out.println("bb = " + bb);
                        System.out.println(new BigDecimal(nn).divide(new BigDecimal(n), 10, RoundingMode.HALF_UP));
                        System.out.println(new BigDecimal(bb).divide(new BigDecimal(b), 10, RoundingMode.HALF_UP));
                        n = nn;
                        b = bb;
                    }
                    bb = bb.add(BigInteger.ONE);
                }
                nn = nn.add(BigInteger.ONE);
            }
        }
    }
}
