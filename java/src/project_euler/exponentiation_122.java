package project_euler;

import java.util.Arrays;
import java.util.TreeSet;

public class exponentiation_122 {
    public static void main(String[] args) {
        int N = 200;
        int[] m = new int[N+1];
        TreeSet<Integer>[] nums = new TreeSet[N+1];
        for (int i = 0; i <=N; i++) {
            nums[i] = new TreeSet<>();
        }
        m[1] = 0;
        nums[1].add(1);
        int sum = 0;
        for (int i = 2; i <= N; i++) {
            int min = i;
            for (int j = 1; j < i ; j++) {
                if(nums[i-j].contains(j)) {
                    if(m[i-j] < min){
                        min = m[i-j];
                    }
                }
            }
            for (int j = 1; j < i; j++) {
                if(m[i-j] == min){
                    nums[i].addAll(nums[i-j]);
                }
            }
            nums[i].add(i);
            m[i] = min+1;
            sum += m[i];
        }
        for (int i = 0; i <=N; i++) {
            System.out.printf("%d %d %s%n", i, m[i], nums[i].toString());
        }
        System.out.println("sum = " + sum);
    }
}
