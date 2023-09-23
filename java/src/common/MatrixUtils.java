package common;

import java.math.BigInteger;
import java.util.Arrays;

import static java.lang.Math.abs;

public class MatrixUtils {
    public static long[][] binPow(int[][] a, long k, long mod){
        int n = a.length;
        long[][] res = new long[n][n];
        long[][] aa = new long[n][n];
        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
            for (int j = 0; j < n; j++) {
                aa[i][j] = a[i][j];
            }
        }
        long[][] nxt = new long[n][n];
        long[][] tmp;
        while(k > 0) {
            if(k%2 == 0){
                multiply(aa, aa, nxt, mod);
                tmp = aa; aa = nxt; nxt = tmp;
                k /= 2;
            } else {
                multiply(aa, res, nxt, mod);
                tmp = nxt; nxt = res; res = tmp;
                k--;
            }
        }
        return res;
    }
    public static long[][] binPow(long[][] a, long k, long mod){
        int n = a.length;
        long[][] res = new long[n][n];
        long[][] aa = new long[n][n];
        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
            for (int j = 0; j < n; j++) {
                aa[i][j] = a[i][j];
            }
        }
        long[][] nxt = new long[n][n];
        long[][] tmp;
        while(k > 0) {
            if(k%2 == 0){
                multiply(aa, aa, nxt, mod);
                tmp = aa; aa = nxt; nxt = tmp;
                k /= 2;
            } else {
                multiply(aa, res, nxt, mod);
                tmp = nxt; nxt = res; res = tmp;
                k--;
            }
        }
        return res;
    }
    public static void multiply(long[][] a, long[][] b, long[][] res, long mod){
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                res[i][j] = 0;
                for (int k = 0; k < b.length; k++) {
                    res[i][j] =  (res[i][j] + a[i][k]*b[k][j])%mod;
                }
            }
        }
    }
    public static void multiply(long[][] a, long[]b, long[] res, long mod){
        for (int i = 0; i < res.length; i++) {
            res[i] = 0;
            for (int k = 0; k < b.length; k++) {
                res[i] =  (res[i] + a[i][k]*b[k])%mod;
            }
        }
    }

    public static void gaussMod(long[][] a, long[] ans, long mod){
        BigInteger MOD = BigInteger.valueOf(mod);
        BigInteger MOD2 = BigInteger.valueOf(mod-2);

        int n = a.length;
        int m = a[0].length - 1;
        int[] where  = new int[m];
        Arrays.fill(where, -1);
        long tmp = 0;
        for (int col=0, row=0; col<m && row<n; ++col) {
            int sel = row;
            for (int i=row; i<n; ++i)
                if (a[i][col] != 0) {
                    sel = i;
                    break;
                }
            if (a[sel][col] == 0)
                continue;
            for (int i=col; i<=m; ++i) {
                tmp = a[sel][i];
                a[sel][i] = a[row][i];
                a[row][i] = tmp;
            }
            where[col] = row;

            for (int i=0; i<n; ++i)
                if (i != row) {
                    long c = a[i][col]*BigInteger.valueOf(a[row][col]).modPow(MOD2, MOD).longValue()%mod;
                    for (int j=col; j<=m; ++j)
                        a[i][j] = (a[i][j] + mod - a[row][j]*c %mod)%mod;
                }
            ++row;
        }

        for (int i=0; i<m; ++i)
            if (where[i] != -1)
                ans[i] = a[where[i]][m]*BigInteger.valueOf(a[where[i]][i]).modPow(MOD2, MOD).longValue()%mod;
    }

    public static void gaussMod(long[][] a, long[] ans, long mod, long[] invs){
        int n = a.length;
        int m = a[0].length - 1;
        int[] where  = new int[m];
        Arrays.fill(where, -1);
        long tmp = 0;
        for (int col=0, row=0; col<m && row<n; ++col) {
            int sel = row;
            for (int i=row; i<n; ++i)
                if (a[i][col] != 0) {
                    sel = i;
                    break;
                }
            if (a[sel][col] == 0)
                continue;
            for (int i=col; i<=m; ++i) {
                tmp = a[sel][i];
                a[sel][i] = a[row][i];
                a[row][i] = tmp;
            }
            where[col] = row;

            for (int i=0; i<n; ++i)
                if (i != row) {
                    long c = a[i][col]*invs[(int)a[row][col]]%mod;
                    for (int j=col; j<=m; ++j)
                        a[i][j] = (a[i][j] + mod - a[row][j]*c %mod)%mod;
                }
            ++row;
        }

        for (int i=0; i<m; ++i)
            if (where[i] != -1)
                ans[i] = a[where[i]][m]* invs[(int)a[where[i]][i]]%mod;
    }

    public static void gauss (double[][] a, double[] ans) {
        int n = a.length;
        int m = a[0].length - 1;
        double EPS = 0.000001;
        int[] where  = new int[m];
        Arrays.fill(where, -1);
        double tmp = 0;
        for (int col=0, row=0; col<m && row<n; ++col) {
            int sel = row;
            for (int i=row; i<n; ++i)
                if (abs (a[i][col]) > abs (a[sel][col]))
                    sel = i;
            if (abs(a[sel][col])  < EPS)
                continue;
            for (int i=col; i<=m; ++i) {
                tmp = a[sel][i];
                a[sel][i] = a[row][i];
                a[row][i] = tmp;
            }
            where[col] = row;

            for (int i=0; i<n; ++i)
                if (i != row) {
                    double c = a[i][col] / a[row][col];
                    for (int j=col; j<=m; ++j)
                        a[i][j] -= a[row][j] * c;
                }
            ++row;
        }

        for (int i=0; i<m; ++i)
            if (where[i] != -1)
                ans[i] = a[where[i]][m] / a[where[i]][i];

    }



    public static void main(String[] args) {
        int[][] a = new int[][]{{1, 1, 0},
                                {1, 1, 1},
                                {0, 1, 1}};
        long[][] b = binPow(a, 25, 1_000_000_007L);
        for (int i = 0; i < 3; i++) {
            System.out.println(Arrays.toString(b[i]));
        }
    }
}
