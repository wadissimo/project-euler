package project_euler;

public class P14_collatz_seq {
    public static final int N = 30_000_000;
    private static Long[] memo = new Long[N+1];
    static long go(long num){
        if(num == 1)
            return 1;
        if(num <= N && memo[(int)num] != null)
            return memo[(int)num];
        long res;
        if(num%2 == 0) {
            res = go(num / 2);
        }else{
            res = go(3*num+1);
        }
        if(num <= N)
            memo[(int)num] = res+1;
        return res+1;
    }
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        for (int num = 1; num < 1000_000 ; num++) {
            go(num);
        }
        long max = 0;
        int ans = 0;
        for (int i = 2; i < 1000_000; i++) {
            if(memo[i] > max){
                max = memo[i];
                ans = i;
            }
        }
        long t2 = System.currentTimeMillis();
        System.out.println("(t2-t1) = " + (t2 - t1));
        System.out.println("max = " + max);
        System.out.println("ans = " + ans);
    }
}
