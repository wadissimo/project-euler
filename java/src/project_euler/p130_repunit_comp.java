package project_euler;
import common.IntegerUtils;
import java.util.ArrayList;

public class p130_repunit_comp {
    static int A(int n){
        long rem = 1;
        int max = 2000000;
        int it = 1;
        while(it < max){
            rem = rem*10%n;
            if(rem == 1)
                break;
            it++;
        }

        if(it >= max)
            throw new RuntimeException("not found");
        return it;
    }
    public static void main(String[] args) {

        int MAX = 100_000;
        ArrayList<Integer> primes = new ArrayList<>();
        int[] lp = new int[MAX +1];
        IntegerUtils.sieve(MAX, primes, lp);
        int cnt = 25;
        int ans = 0;
        for (int i = 10; i <  MAX && cnt > 0; i++) {
            if(i%5 == 0 || i%2 == 0 || lp[i] == i)
                continue;
            if((i-1)%A(i*9) == 0){
               // System.out.println("A(i) = " + A(i*9));
                System.out.println("i = " + i);
                cnt--;
                ans +=i;
            }
        }
        System.out.println("cnt = " + cnt);
        System.out.println("ans = " + ans);

    }
}
