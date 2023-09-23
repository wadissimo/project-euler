package common.data_structure;

import java.util.Arrays;

/**
 * Created by Vadim
 */
public class SegmentTreeSimple {

    public static int get(int[] t, int i) {
        return t[i + t.length / 2];
    }

    public static void addMax(int[] t, int i, int value) {
        i += t.length / 2;
        t[i] = value;
        for (; i > 1; i >>= 1)
            t[i >> 1] = Math.max(t[i], t[i ^ 1]);
    }

    // max[a, b]
    public static int max(int[] t, int a, int b) {
        int res = Integer.MIN_VALUE;
        for (a += t.length / 2, b += t.length / 2; a <= b; a = (a + 1) >> 1, b = (b - 1) >> 1) {
            if ((a & 1) != 0)
                res = Math.max(res, t[a]);
            if ((b & 1) == 0)
                res = Math.max(res, t[b]);
        }
        return res;
    }

    public static void addMin(int[] t, int i, int value) {
        i += t.length / 2;
        t[i] = value;
        for (; i > 1; i >>= 1)
            t[i >> 1] = Math.min(t[i], t[i ^ 1]);
    }

    // min[a, b]
    public static int min(int[] t, int a, int b) {
        int res = Integer.MAX_VALUE;
        for (a += t.length / 2, b += t.length / 2; a <= b; a = (a + 1) >> 1, b = (b - 1) >> 1) {
            if ((a & 1) != 0)
                res = Math.min(res, t[a]);
            if ((b & 1) == 0)
                res = Math.min(res, t[b]);
        }
        return res;
    }

    // Usage example
    public static void main(String[] args) {
        int n = 10;
        int[] t = new int[n + n];

        for (int i = 0; i < 20; i++) {
            t[i] = Integer.MAX_VALUE;
        }

        addMin(t, 1, 1);
        addMin(t, 2, 2);
        addMin(t, 3, 5);
        addMin(t, 4, 10);
        addMin(t, 5, 6);

        System.out.println(min(t, 2, 4));
        System.out.println(min(t, 2, 3));
        System.out.println(min(t, 4, 6));
    }
}