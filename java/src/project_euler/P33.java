package project_euler;

public class P33 {
    public static void main(String[] args) {
        int ans = 1;
        for (int num = 10; num <=99; num++) {
            for (int denom = num+1; denom <=99; denom++) {
                if(num %10 == 0 && denom%10 == 0)
                    continue;
                if(num%10 == denom/10 && num*(denom%10) == denom*(num/10)){
                    System.out.printf("%d/%d = %d/%d%n", num, denom, num/10, denom%10);
                    ans *= denom%10;
                }
                if(num/10 == denom%10 && denom*(num%10) == num*(denom/10)){
                    System.out.printf("%d/%d = %d/%d%n", num, denom, num%10, denom/10);
                    ans *= denom/10;
                }
            }
        }
        System.out.println("ans = " + ans);

    }
}
