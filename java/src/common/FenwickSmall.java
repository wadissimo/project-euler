package common;

public class FenwickSmall {
    public int n;
    public short[] t;

    public FenwickSmall(int n) {
        this.n = n;
        t = new short[n];
    }

    public int get(int r) {
        int ans = 0;
        while (r >= 0) {
            ans += t[r];
            r = (r & (r + 1)) - 1;
        }
        return ans;
    }

    public void add(int pos, short val) {
        while (pos < n) {
            t[pos] += val;
            pos |= pos + 1;
        }
    }
}
