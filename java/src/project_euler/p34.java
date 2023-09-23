package project_euler;

public class p34 {
    public static void main(String[] args) {
        int[] fact = new int[10];
        fact[1] = fact[0] = 1;
        for (int i = 2; i < 10; i++) {
            fact[i] = i*fact[i-1];
        }
        int MAX = 3_000_000;
        long ans = 0;
        for (int i = 10; i < MAX ; i++) {
            int sum = 0;
            int num = i;
            while(num > 0){
                int dig = num%10;
                sum += fact[dig];
                num/=10;
            }
            if(sum == i){
                ans += i;
                System.out.println("i = " + i);
            }
        }

        System.out.println("ans = " + ans);
    }
}
