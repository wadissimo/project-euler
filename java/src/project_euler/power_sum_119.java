package project_euler;

import java.util.TreeSet;

public class power_sum_119 {
    static int digsum(long n){
        int res = 0;
        while(n > 0){
            res += n%10;
            n/=10;
        }
        return res;
    }
    public static void main(String[] args) {
        long MAX = 5_000000_000_000_000L;
        long MAXN = 1000;
        TreeSet<Long> res = new TreeSet<>();
        for (long num = 2; num < MAXN ; num++) {

            long pow = num;
            while(pow < MAX/num){
                pow *= num;
                if(digsum(pow) == num){
                    res.add(pow);
                }
            }
        }
        int i = 0;
        for(long num: res){
            System.out.printf("%d %d%n", ++i, num);
            System.out.println("digsum(num) = " + digsum(num));
        }
    }
}
