package common;

import java.util.Arrays;

/**
 * Created by Vadim
 */
public class FenwickTree {

    int n;
    int[] t;

    public FenwickTree(int n) {
        this.n = n;
        t = new int[n];
        Arrays.fill(t, Integer.MAX_VALUE / 2);
    }

    public int get(int r) {
        int ans = Integer.MAX_VALUE / 2;
        while (r >= 0) {
            ans = Math.min(ans, t[r]);
            r = (r & (r + 1)) - 1;
        }
        return ans;
    }

    public void set(int pos, int val) {
        while (pos < n) {
            t[pos] = Math.min(t[pos], val);
            pos |= pos + 1;
        }
    }
}
