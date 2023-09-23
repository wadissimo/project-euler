package project_euler;

import java.math.BigInteger;

public class p078_coin_partitions {
    public static void main(String[] args) {
        int N = 100000;
        BigInteger[] p = new BigInteger[N+1];
        p[0] = BigInteger.ONE;
        for (int i = 1; i <= N; i++) {
            //System.out.println("i = " + i);
            p[i] = BigInteger.ZERO;
            for (int k = 1; i-k*(3*k-1)/2 >= 0; k++) {
                if(k%2 == 0)
                    p[i] = p[i].subtract(p[i-k*(3*k-1)/2]);
                else
                    p[i] = p[i].add(p[i-k*(3*k-1)/2]);
            }
            for (int k = -1; i-k*(3*k-1)/2 >= 0; k--) {
                if(k%2 == 0)
                    p[i] = p[i].subtract(p[i-k*(3*k-1)/2]);
                else
                    p[i] = p[i].add(p[i-k*(3*k-1)/2]);
            }
            if(p[i].mod(BigInteger.valueOf(1000000)).equals(BigInteger.ZERO)){
                System.out.println("i = " + i);
                System.out.println(p[i]);
            }

        }
    }
}
