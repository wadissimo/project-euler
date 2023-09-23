package common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import static java.lang.Math.min;

/**
 * Created by Vadim
 */
public class SegmentTree {


    public static long[] buildSumSegmentTree(int a[]) {
        long t[] = new long[a.length*4];
        buildSum(t, a, 1, 0, a.length-1);
        return t;
    }

    public static void buildSum (long t [], int a[], int v, int tl, int tr) {
        if (tl == tr)
            t[v] = a[tl];
        else {
            int tm = (tl + tr) / 2;
            buildSum (t, a, v*2, tl, tm);
            buildSum (t, a, v*2+1, tm+1, tr);
            t[v] = t[v*2] + t[v*2+1];
        }
    }

    public static long sum (long t[], int l, int r) {
        return sum(t, 1, 0, t.length/4 - 1, l, r);
    }

    public static long sum (long t[], int v, int tl, int tr, int l, int r) {
        if (l > r)
            return 0;
        if (l == tl && r == tr)
            return t[v];
        int tm = (tl + tr) / 2;
        return sum (t, v*2, tl, tm, l, min(r,tm))
                + sum (t, v*2+1, tm+1, tr, Math.max(l,tm+1), r);
    }

    public static void updateSumSingle (long t[], int v, int tl, int tr, int pos, int new_val) {
        if (tl == tr)
            t[v] = new_val;
        else {
            int tm = (tl + tr) / 2;
            if (pos <= tm)
                updateSumSingle (t, v*2, tl, tm, pos, new_val);
            else
                updateSumSingle (t, v*2+1, tm+1, tr, pos, new_val);
            t[v] = t[v*2] + t[v*2+1];
        }
    }




    public static long[] buildMaxSegmentTree(int a[]) {
        long t[] = new long[a.length*4];
        buildMax(t, a, 1, 0, a.length-1);
        return t;
    }

    public static void buildMax (long t [], int a[], int v, int tl, int tr) {
        if (tl == tr)
            t[v] = a[tl];
        else {
            int tm = (tl + tr) / 2;
            buildMax (t, a, v*2, tl, tm);
            buildMax (t, a, v*2+1, tm+1, tr);
            t[v] = Math.max(t[v*2], t[v*2+1]);
        }
    }

    public static long max (long[] t, int l, int r) {
        return max(t, 1, 0, t.length/4 - 1, l, r);
    }

    public static long max (long[] t, int v, int tl, int tr, int l, int r) {
        if (l > r)
            return 0;
        if (l == tl && r == tr)
            return t[v];
        int tm = (tl + tr) / 2;
        return Math.max(max (t, v*2, tl, tm, l, min(r,tm)), max (t, v*2+1, tm+1, tr, Math.max(l,tm+1), r));
    }
    public static void updateMax (long[] t, int l, long new_val) {
        updateMax(t, 1, 0, t.length/4 - 1, l, new_val);
    }
    public static void updateMax (long[] t, int v, int tl, int tr, int pos, long new_val) {
        if (tl == tr)
            t[v] = new_val;
        else {
            int tm = (tl + tr) / 2;
            if (pos <= tm)
                updateMax (t, v*2, tl, tm, pos, new_val);
            else
                updateMax (t, v*2+1, tm+1, tr, pos, new_val);
            t[v] = Math.max(t[v*2], t[v*2+1]);
        }
    }


    public static void build (LazyNode[] t, long a[]) {
        build(t, a, 1, 0, a.length-1);
    }

    public static void build (LazyNode t [], long a[], int v, int tl, int tr) {
        if (tl == tr)
            t[v].value = a[tl];
        else {
            int tm = (tl + tr) / 2;
            build (t, a, v*2, tl, tm);
            build (t, a, v*2+1, tm+1, tr);
            t[v].value = t[v].func(t[v*2].value, t[v*2+1].value);
        }
    }

    public static void propagate(LazyNode[] t, int v) {
        if (t[v].lazyVal != 0) {
            t[v*2].add(t[v].lazyVal);
            t[v*2+1].add(t[v].lazyVal);
            t[v].lazyVal = 0;
        }
    }

    public static void addOnInterval (LazyNode t[], int v, int tl, int tr, int l, int r, long val) {
        if(l>r)
            return;
        if (l == tl && tr == r) {
            t[v].add(val);
        }else {
            propagate(t, v);
            int tm = (tl + tr) / 2;
            addOnInterval (t, v*2, tl, tm, l, min(r, tm), val);
            addOnInterval (t, v*2+1, tm+1, tr, Math.max(l, tm+1), r, val);
            t[v].value = t[v].func(t[v*2].value, t[v*2+1].value);
        }
    }

    public static long getVal (LazyNode t[], int v, int tl, int tr, int l, int r) {
        if (l == tl && r == tr){
            return t[v].value;
        }
        int tm = (tl + tr) / 2;
        if(r<=tm){
            return getVal (t, v*2, tl, tm, l, r);
        } else if (l > tm){
            return getVal (t, v*2+1, tm+1, tr, l, r);
        } else {
            return t[v].func(getVal (t, v*2, tl, tm, l, tm), getVal (t, v*2+1, tm+1, tr, tm+1, r));
        }
    }

    static class Node{
        long value;
    }

    static class LazyNode extends Node{
        long lazyVal;
        void add(long x){
            lazyVal += x;
            value += x;
        }
        long func(long left, long right){
            return Math.max(left, right);
        }
    }

    static class MinLazyNode extends LazyNode{
        long func(long left, long right){
            return Math.min(left, right);
        }
    }


    // fast small

    private void setMax(long[] tree, int i, long value) {
        i += tree.length / 2;
        tree[i] = value;
        for (; i > 1; i >>= 1)
            tree[i >> 1] = Math.max(tree[i], tree[i ^ 1]);
    }

    private long getMax(long[] tree, int l, int r) {
        long res = Long.MIN_VALUE;
        for (l += tree.length / 2, r += tree.length / 2; l <= r; l = (l + 1) >> 1, r = (r - 1) >> 1) {
            res = Math.max(res, tree[l]);
            res = Math.max(res, tree[r]);
        }
        return res;
    }



    public static void main(String[] args) {

        for (int test = 0; test < 100; test++) {
            System.out.print("Test "+(test+1)+"...");

            // Generate
            int n = 1000;
            long[] a = new long[n];
            Random rand = new Random(123);
            for (int i = 0; i < n; i++) {
                a[i] = rand.nextInt(50);
            }
            int nq = 100;
            int[][] q = new int[nq][4];
            for (int i = 0; i < nq; i++) {
                q[i][0] = rand.nextInt(2);
                q[i][1] = rand.nextInt(n);//left
                q[i][2] = q[i][1] + rand.nextInt(n - q[i][1]);// right
                if (q[i][0] == 1) { // increase
                    q[i][3] = rand.nextInt(50);
                }
            }
            //Bruteforce
            LinkedList<Long> ans = new LinkedList<>();
            for (int i = 0; i < nq; i++) {
                if (q[i][0] == 1) {
                    for (int j = q[i][1]; j <= q[i][2]; j++) {
                        a[j] += q[i][3];
                    }
                } else {
                    long min = Long.MAX_VALUE;
                    for (int j = q[i][1]; j <= q[i][2]; j++) {
                        min = Math.min(a[j], min);
                    }
                    ans.add(min);
                }
            }
            //check
            MinLazyNode[] t = new MinLazyNode[n * 4];
            for (int i = 0; i < n * 4; i++) {
                t[i] = new MinLazyNode();
            }
            build(t, a);
            Iterator<Long> it = ans.iterator();
            for (int i = 0; i < nq; i++) {
                if (q[i][0] == 1) {
                    addOnInterval(t, 1, 0, n - 1, q[i][1], q[i][2], q[i][3]);
                } else {
                    long val = getVal(t, 1, 0, n - 1, q[i][1], q[i][2]);
                    long correctVal = it.next();
                    assert (val == correctVal);
                }
            }
            System.out.println("OK");
        }

    }







}
