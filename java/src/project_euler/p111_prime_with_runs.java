package project_euler;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class p111_prime_with_runs {
    public static final int DIGS = 10;
    static boolean isPrime(long num){
        if(BigInteger.valueOf(num).isProbablePrime(3)){
            for (long div = 2; div*div <= num ; div++) {
                if(num%div == 0)
                    return false;
            }
            return true;
        } else
            return false;
    }
    static long go(int mask, int ind, int fill, long num){
        if(ind >= DIGS){
            if(isPrime(num)){
                System.out.println("num = " + num);
                return num;
            } else
                return 0;
        }
        if((mask&(1<<ind)) == 0){
            return go(mask, ind+1, fill, num*10+fill);
        }
        long cnt = 0;
        for (int d = 0; d <= 9; d++) {
            if(d == 0 && ind == 0)
                continue;
            if(d != fill){
                cnt += go(mask, ind+1, fill, num*10 + d);
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        List<Integer>[] masks = new List[DIGS];
        for (int i = 0; i < DIGS; i++) {
            masks[i] = new LinkedList<>();
        }
        for (int mask = 1; mask < (1 << DIGS)-1; mask++) {
            masks[Integer.bitCount(mask)].add(mask);
        }
        long sum = 0;
        for (int d = 0; d <= 9; d++) {
            for(int cnt = 1; cnt < DIGS; cnt++){
                long res = 0;
                for(int mask: masks[cnt]){
                    //System.out.println("Integer.toBinaryString(mask) = " + Integer.toBinaryString(mask));
                    if(d == 0 && (mask&1) == 0)
                        continue;
                    res+=go(mask, 0, d, 0);
                    //System.out.println("res = " + res);
                }
                sum += res;
                System.out.print("d = " + d);
                System.out.print(", cnt = " + (DIGS-cnt));
                System.out.println(", res = " + res);
                if(res != 0)
                    break;
            }
        }
        System.out.println("sum = " + sum);


    }
}
