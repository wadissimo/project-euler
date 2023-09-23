package project_euler;

public class p135_differences {
    public static void main(String[] args) {
        int ans = 0;
        for (int num = 1; num < 1000_000; num++) {
            int cnt = 0;
            for (int div = 1; div * div <= num; div++) {
                if (num % div == 0 && (div + num / div) % 4 == 0) {
                    int d = (div + num / div) / 4;
                    if (div > d)
                        cnt++;
                    if (num/div != div && num / div > d)
                        cnt++;
                }
            }
            if(cnt == 10)
                ans++;
        }
        System.out.println("ans = " + ans);
    }
}
