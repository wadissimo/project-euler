package project_euler;

import common.IntegerUtils;

public class p113_non_bouncy {
    public static void main(String[] args) {
        long ans = 9;
        int N = 100;
        long[][] C = IntegerUtils.pascal_triangle(120);
        for (int n = 2; n <= N; n++) {
            for (int first = 1; first <= 9; first++) {
                for (int last = 0; last <= 9; last++) {
                    if (first == last)
                        ans++;
                    else {
                        int diff = Math.abs(last - first);
                        //System.out.println("C[n + diff - 2][n - 2] = " + C[n + diff - 2][n - 2]);
                        ans += C[n + diff - 2][n - 2];
                    }
                }
            }
            System.out.println("n = " + n);
            System.out.println("ans = " + ans);
        }
        System.out.println("ans = " + ans);
    }
}
