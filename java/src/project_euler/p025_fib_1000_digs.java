package project_euler;

import java.math.BigInteger;

public class p025_fib_1000_digs {
    public static void main(String[] args) {
        int MAX = 10000;
        BigInteger prev = BigInteger.ONE;
        BigInteger cur = BigInteger.ONE;
        for (int i = 3; i < MAX; i++) {
            BigInteger nxt = prev.add(cur);
            prev = cur;
            cur = nxt;
            String s = nxt.toString();
            if(s.length() >= 1000){
                System.out.println("i = " + i);
                System.out.println("s = " + s);
                break;
            }
        }
    }
}
