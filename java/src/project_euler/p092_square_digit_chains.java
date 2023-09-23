package project_euler;

public class p092_square_digit_chains {
    static int LIM = 10_000_000;
    static int[] dp = new int[LIM];
    static void rec(int num_){
        int sum = 0;
        int num = num_;
        while(num > 0){
            int d = num%10;
            sum += d*d;
            num/=10;
        }
        if(dp[sum] == 0){
            rec(sum);
        }
        dp[num_] = dp[sum];
    }
    public static void main(String[] args) {
        dp[1] = 1;
        dp[89] = 89;
        int ans = 0;
        for (int i = 1; i < LIM; i++) {
            if(dp[i] == 0)
                rec(i);
            if(dp[i] == 89)
                ans++;
        }
        System.out.println("ans = " + ans);
    }
}
