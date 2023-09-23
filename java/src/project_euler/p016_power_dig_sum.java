package project_euler;

import java.math.BigInteger;

public class p016_power_dig_sum {
    public static void main(String[] args) {
        int N = 15;
        String s = BigInteger.valueOf(2).pow(1000).toString();
        long sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += s.charAt(i)-'0';
        }
        System.out.println("sum = " + sum);
    }
}
