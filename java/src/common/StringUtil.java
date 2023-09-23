package common;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 */
public class StringUtil {

    /**
     * Calculate z function - for each index equals to lcp length of the string and substring from imdex
     * O (n)
     * @param s - string to search (usually substr#string)
     * @return z array
     */
    public static int[] z(String s) {
        int[] z = new int[s.length()];
        int l = 0, r = 0;
        int j;
        z[0] = s.length();
        for (int i = 1; i < s.length(); i++)
            if (i > r){
                for (j = 0; ((j + i) < s.length()) && (s.charAt(i + j) == s.charAt(j)) ; j++);
                z[i] = j;
                l = i;
                r = i + j - 1;
            }
            else
            if (z[i - l] < r - i + 1)
                z[i] = z[i - l];
            else{
                for (j = 1; ((j + r) < s.length())&& (s.charAt(r + j) == s.charAt(r - i + j)); j++);
                z[i] = r - i + j;
                l = i;
                r = r + j - 1;
            }
        return z;
    }

    public static List<Integer> naiveStringMatcher(String pattern, String text) {
        List<Integer> matches = new LinkedList<Integer>();
        for (int i = 0; i < text.length()-pattern.length() + 1; i++) {

            boolean match = true;
            int j = 0;
            for (; j < pattern.length() && pattern.charAt(j) == text.charAt(i+j); j++) {
            }
            if(j==pattern.length() - 1 && pattern.charAt(j) == text.charAt(i+j)) {
                matches.add(i);
            }
        }
        return matches;

    }

    /**
     * O(n*m)
     */
    public static String LCS(String a, String b) {
        int n = a.length();
        int m = b.length();
        int[][]dp = new int[n+1][m+1];
        int[][]path = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(a.charAt(i-1) == b.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    path[i][j]=2;
                } else {
                    if(dp[i][j-1]>dp[i-1][j]) {
                        dp[i][j] = dp[i][j-1];
                        path[i][j]=1;
                    }else {
                        dp[i][j] = dp[i-1][j];
                        path[i][j]=-1;
                    }
                }
            }
        }
        int i = n;
        int j = m;
        StringBuilder sb = new StringBuilder();
        while(i>0 && j>0) {
            if(path[i][j] == 2) {
                sb.append(a.charAt(i-1));
                i++; j++;
            }else if(path[i][j] == 1) {
                j++;
            }else{
                i++;
            }
        }
        return sb.reverse().toString();
    }

    static void KMPSearch(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0; // index for pat[]

        // Preprocess the pattern (calculate lps[]
        // array)
        computePrefixFunc(pat, M, lps);

        int i = 0; // index for txt[]
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println("Found pattern "
                        + "at index " + (i - j));
                j = lps[j - 1];
            }

            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
    }

    static void computePrefixFunc(String pat, int M, int lps[])
    {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];

                    // Also, note that we do not increment
                    // i here
                }
                else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    public static class Hashing {
        public String s;
        public long[] hash;
        public long[] inv;
        public int p;
        public long mod;

        public Hashing(String s) {
            this(s, 43, BigInteger.probablePrime(30, new Random()).longValue());
        }

        public Hashing(String s, int p, long mod) {
            this.s = s;
            this.p = p;
            this.mod = mod;
            int n = s.length();
            hash = new long[n + 1];
            inv = new long[n + 1];
            long pow = 1;
            long invPow = 1;
            long pInv = BigInteger.valueOf(p).modInverse(BigInteger.valueOf(mod)).longValue();
            for (int i = 0; i < n; i++) {
                hash[i + 1] = (hash[i] + (s.charAt(i) - 'a' + 1) * pow) % mod;
                inv[i] = invPow;
                pow = pow * p % mod;
                invPow = invPow * pInv % mod;
            }
        }

        public long getHash(int from, int len) {
            return (hash[from + len] - hash[from] + mod) * inv[from] % mod;
        }

    }

    public static int[][] lcpArray(String s, int logN, int[] p){
        int n = s.length();
        int abc = 256;
        int[][] c = new int[logN+1][n];
        int[] cnt = new int[Math.max(n,abc)];
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i)]++;
        }
        for (int i = 1; i < abc; i++) {
            cnt[i] += cnt[i-1];
        }
        for (int i = 0; i < n; i++) {
            p[--cnt[s.charAt(i)]] = i;
        }
        int classes = 1;
        for (int i = 1; i < n; i++) {
            if(s.charAt(p[i]) != s.charAt(p[i-1]))
                classes++;
            c[0][p[i]] = classes-1;
        }

        int[] pn = new int[n];

        for (int log = 0; (1<<log) < n; log++) {
            int len = 1<<log;
            for (int i = 0; i < n; i++) {
                pn[i] = p[i] - len;
                if(pn[i] < 0) pn[i] += n;
            }
            Arrays.fill(cnt, 0);
            for (int i = 0; i < n; i++) {
                cnt[c[log][pn[i]]]++;
            }
            for (int i = 1; i < classes; i++) {
                cnt[i] += cnt[i-1];
            }
            for (int i = n-1; i >= 0; i--) {
                p[--cnt[c[log][pn[i]]]] = pn[i];
            }
            c[log+1][p[0]] = 0;
            classes = 1;
            for (int i = 1; i < n; i++) {
                if(c[log][p[i]] != c[log][p[i-1]] || c[log][(p[i] + len)%n] != c[log][(p[i-1]+ len)%n])
                    classes ++;
                c[log+1][p[i]] = classes-1;
            }
        }
        return c;
    }

    public static int lcp(int i, int j, int logN, int n, int[][] c){
        int ans = 0;
        int maxAns = Math.min(n-i, n-j);
        for (int k = logN; k >= 0 && i < n && j < n; k--) {
            if(c[k][i] == c[k][j]){
                ans += 1<<k;
                i+=1<<k;
                j+=1<<k;
            }
        }
        ans = Math.min(ans, maxAns);
        return ans;
    }

    public static void suffixArray(String s, int[] pres, int[] cres){
        int n = s.length();
        int abc = 256;
        int[] p = new int[n], cnt = new int[Math.max(n,abc)], c = new int[n];
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i)]++;
        }
        for (int i = 1; i < abc; i++) {
            cnt[i] += cnt[i-1];
        }
        for (int i = 0; i < n; i++) {
            p[--cnt[s.charAt(i)]] = i;
        }
        int classes = 1;
        for (int i = 1; i < n; i++) {
            if(s.charAt(p[i]) != s.charAt(p[i-1]))
                classes++;
            c[p[i]] = classes-1;
        }

        int[] pn = new int[n];
        int[] cn = new int[n];
        for (int log = 0; (1<<log) < n; log++) {
            int len = 1<<log;
            for (int i = 0; i < n; i++) {
                pn[i] = p[i] - len;
                if(pn[i] < 0) pn[i] += n;
            }
            Arrays.fill(cnt, 0);
            for (int i = 0; i < n; i++) {
                cnt[c[pn[i]]]++;
            }
            for (int i = 1; i < classes; i++) {
                cnt[i] += cnt[i-1];
            }
            for (int i = n-1; i >= 0; i--) {
                p[--cnt[c[pn[i]]]] = pn[i];
            }
            cn[p[0]] = 0;
            classes = 1;
            for (int i = 1; i < n; i++) {
                if(c[p[i]] != c[p[i-1]] || c[(p[i] + len)%n] != c[(p[i-1]+ len)%n])
                    classes ++;
                cn[p[i]] = classes-1;
            }
            System.arraycopy(cn, 0, c, 0, n);
        }
        System.arraycopy(p, 0, pres, 0, n);
        System.arraycopy(c, 0, cres, 0, n);

    }

}
