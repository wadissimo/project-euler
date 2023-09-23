package project_euler;

public class p023_abundant {
    public static void main(String[] args) {
        int MAX = 30000;
        boolean[] abundant = new boolean[MAX+1];
        for (int i = 12; i <= MAX; i++) {
            int sum = 1;
            int div = 2;
            for (; div*div < i ; div++) {
                if(i%div == 0){
                    sum+=div;
                    sum+=i/div;
                }
            }
            if(div*div == i)
                sum += div;
            if(sum > i)
                abundant[i] = true;
//            System.out.print("i = " + i);
//            System.out.println(", abundant = " + abundant[i]);
        }
        long ans = 0;
        for (int i = 1; i <= MAX; i++) {
            boolean test = false;
            for (int j = 1; j < i; j++) {
                if(abundant[j] && abundant[i-j]){
                    test = true;
                    break;
                }
            }
            if(!test){
                //System.out.println("i = " + i);
                ans += i;
            }
        }
        System.out.println("ans = " + ans);

    }
}
