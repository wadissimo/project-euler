package project_euler;

public class p154_pascal_pyramid {
    private static int zeros_factorial(int n){
        if(n==0)
            return 0;
        int pow = 5;
        int res = 0;
        while(pow <= n){
            res += n/pow;
            pow*=5;
        }
        return res;
    }
    public static void main(String[] args) {
        int N = 200_000;
        int M = 180_000;
        int[] zeros = new int[N+1];
        int[] delta = new int[N+1];
        for (int i = 4; i <= N; i++) {
            zeros[i] = zeros[i - 1];
            int pow = 5;
            while (i % pow == 0) {
                zeros[i]++;
                delta[i]++;
                pow *= 5;
            }
        }
        int pow = 5;
        while(pow <= N){
            System.out.println("pow = " + pow);
            pow *=5;
        }
        int base = zeros[N];
        System.out.println("base = " + base);
        long ans = 0;
        long all = 0;
        for (int i = 0; i <= 200_000; i++) {
            for (int j = 0; j <=200_000 ; j++) {
                if(i+j<=200_000) {
                    all++;
                    if (base - zeros[i] - zeros[j] - zeros[200_000 - i - j] >= 12) {
                        ans++;
                    }
                }
            }
        }
        System.out.println("all = " + all);
        System.out.println("ans = " + ans);


    }
}
