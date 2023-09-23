package project_euler;

public class p006 {
    public static void main(String[] args) {
        int N = 100;
        long sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += i*i;
        }
        long ans = (1+N)*(1+N)*N*N/4 - sum;
        System.out.println("ans = " + ans);

    }
}
