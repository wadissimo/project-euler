package project_euler;

public class p117_tiles {
    public static void main(String[] args) {
        int N = 50;

        int[] lens = new int[]{2,3,4};
        long ans = 0;
        long[] dp = new long[N + 1];
        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1];
            for(int len: lens) {
                if (i - len >= 0) {
                    dp[i] += dp[i - len];
                }
            }
        }
        ans = dp[N];
        System.out.println("ans = " + ans);
    }
}
