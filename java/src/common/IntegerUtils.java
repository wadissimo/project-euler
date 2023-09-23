package common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;


public class IntegerUtils {

    public static TreeSet<Integer> getPrimes(int n) {
        boolean[] primes=new boolean[n];
        Arrays.fill(primes, true);
        primes[0]=primes[1]=false;
        for (int i=2;i<primes.length;i++) {
            if(primes[i]) {
                for (int j=2;i*j<primes.length;j++) {
                    primes[i*j]=false;
                }
            }
        }
        TreeSet<Integer> res = new TreeSet<Integer>();
        for (int i = 0; i < primes.length; i++) {
            if(primes[i]) {
                res.add(i);
            }
        }
        return res;
    }

    public static long gcd(long a, long b) {
        while (b != 0){
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    public static long gcdExt(long a, long b, long[] x) {
        if(a == 0){
            x[0] = 0;
            x[1] = 1;
            return b;
        }
        long[]x1 = new long[2];
        long d = gcdExt(b%a, a, x1);
        x[0] = x1[1] - (b/a)*x1[0];
        x[1] = x1[0];
        return d;
    }

    public static long pow(long a, long p, long mod) {
        if (p == 0) {
            return 1;
        }
        long t = pow(a, p / 2, mod);

        if (p % 2 != 0) {
            return (((t * t) % mod) * a) % mod;
        } else {
            return (t * t) % mod;
        }
    }

    public static long invl(long a, long mod) { // == pow(a, mod-2, mod) for prime mod
        long b = mod;
        long p = 1, q = 0;
        while (b > 0) {
            long c = a / b;
            long d;
            d = a;
            a = b;
            b = d % b;
            d = p;
            p = q;
            q = d - c * q;
        }
        return p < 0 ? p + mod : p;
    }

    public static long phi(long n){
        long res = n;
        for (long div = 2; div*div <= n ; div++) {
            if(n%div == 0){
                res -= res/div;
                while(n%div == 0)
                    n/=div;
            }
        }
        if(n>1)
            res -= res/n;
        return res;
    }

    public static void sieve(int n, ArrayList<Integer> pr, int[] lp) {
        for (int i=2;i<n+1;i++) {
            if(lp[i] == 0) {
                lp[i] = i;
                pr.add(i);
            }
            int j=0;
            while(j < pr.size() && pr.get(j) <= lp[i] && i*pr.get(j) <= n) {
                lp[i * pr.get(j)] = pr.get(j);
                j ++;
            }
        }
    }



    public static long [][] pascal_triangle(int maxn) {
        long C[][] = new long[maxn + 1][maxn + 1];
        for (int n = 0; n <= maxn; ++n) {
            C[n][0] = C[n][n] = 1;
            for (int k = 1; k < n; ++k)
                C[n][k] = C[n - 1][k - 1] + C[n - 1][k];
        }
        return C;
    }

    public static long[][] pascal_triangle_modulo(int maxn, long mod) {
            long C[][] = new long[maxn + 1][maxn + 1];
            for (int n = 0; n <= maxn; ++n) {
                C[n][0] = C[n][n] = 1;
                for (int k = 1; k < n; ++k)
                    C[n][k] = (C[n - 1][k - 1] + C[n - 1][k]) % mod;
            }
            return C;
        }

    public static long root2(long x) {
        long s = (long) Math.sqrt(x);
        while (s * s < x) s++;
        while (s * s > x) s--;
        return s;
    }
    public static long root3(long x) {
        long s = (long) Math.pow(x, 1.0/3.0);
        while (s * s * s < x) s++;
        while (s * s * s > x) s--;
        return s;
    }
    public static long rootOfPerfectSquare(long x) {
        return (long) Math.sqrt(x);
    }

    public static boolean isPerfectSquare(long x) {
        long xx = (long) Math.sqrt(x);
        return xx * xx == x;
    }

    public static long[] invs(int n, long p){
        long[] res = new long[n];
        res[1] = 1;
        for (int i = 2; i < n; i++) {
            res[i] = (p-(p/i)*res[(int)(p%i)]%p)%p;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(getPrimes(1400));
        //ArrayUtils.printCollection(pr);
    }
}
