package project_euler;

public class p125_palindromic_sum {
    public static void main(String[] args) {

        int MAX = 10_000;
        int N = MAX*MAX;
        boolean[] sqrd = new boolean[N+1];
        for (int from = 1; from <=MAX; from++) {
            long sum = 0;
            for (int n = from; n <= MAX; n++) {
                sum+=n*n;
                if(n==from)
                    continue;
                if(sum<=N)
                    sqrd[(int)sum] = true;
                else
                    break;
            }
        }
        long ans = 0;
        for (int i = 1; i <= N; i++) {
            if(sqrd[i]){
                String s = Integer.toString(i);
                boolean palindrome = true;
                for (int j = 0; j < s.length() / 2; j++) {
                    if(s.charAt(j) != s.charAt(s.length()-j-1)){
                        palindrome = false;
                        break;
                    }
                }
                if(palindrome)
                    ans+=i;
            }
        }
        System.out.println("ans = " + ans);

    }
}
