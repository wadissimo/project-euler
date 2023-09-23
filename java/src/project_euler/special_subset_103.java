package project_euler;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class special_subset_103 {
    private static final int MAX = 7;
    private static int[] a = new int[MAX];
    private static int min = Integer.MAX_VALUE;
    private static void go(int ind){
        if(ind == MAX){
            if(a[0]+a[1] <= a[MAX-1])
                return;
            if(a[0]+a[1]+a[2] <= a[MAX-1]+a[MAX-2])
                return;
            if(check(a, MAX)){
                int sum = 0;
                for (int i = 0; i < MAX; i++) {
                    sum+=a[i];
                }
                if(sum < min){
                    System.out.println("sum = " + sum);
                    System.out.println("Arrays.toString(a) = " + Arrays.toString(a));
                    min = sum;
                }
            }
        } else {
            for (int num = a[ind - 1]; num < a[ind - 1] + 20; num++) {
                a[ind] = num;
                go(ind + 1);
            }
        }
    }
    private static boolean check(int[] a, int len){
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int mask = 1; mask < (1<<len); mask++) {
            int sum = 0;
            int bits = 0;
            for (int bit = 0; bit < len; bit++) {
                if((mask&(1<<bit)) > 0){
                    sum+=a[bit];
                    bits++;
                }
            }
            if(map.put(sum, bits)!=null){
                return false;
            }
        }
        int prev = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() < prev)
                return false;
            prev = entry.getValue();
        }
        return true;

    }
    public static void main(String[] args) {
        for (int num = 5; num < 30; num++) {
            System.out.println("num = " + num);
            a[0] = num;
            go(1);
        }

    }
}
