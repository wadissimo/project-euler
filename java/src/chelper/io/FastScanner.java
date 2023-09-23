package chelper.io;

import common.GraphUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class FastScanner {
    public BufferedReader in;
    public StringTokenizer st;

    public FastScanner(InputStream stream) {
        in = new BufferedReader(new InputStreamReader(stream));
    }

    public String ns() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                String rl = in.readLine();
                if (rl == null) {
                    return null;
                }
                st = new StringTokenizer(rl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return st.nextToken();
    }

    public int ni() {
        return Integer.parseInt(ns());
    }

    public long nl() {
        return Long.parseLong(ns());
    }

    public double nd() {
        return Double.parseDouble(ns());
    }

    public int[] na(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = ni();
        return a;
    }

    public long[] nal(int n) {
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = nl();
        return a;
    }



    public char[] ns(int n) {
        char[] buf = new char[n];
        try {
            in.read(buf);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return buf;
    }


    public int[][] nmi(int n, int m) {
        int[][] map = new int[n][];
        for (int i = 0; i < n; i++){
            map[i] = na(m);
        }
        return map;
    }

    public char[][] nm(int n, int m) {
        char[][] map = new char[n][];
        for (int i = 0; i < n; i++){
            map[i] = ns(m);
            readLine();
        }
        return map;
    }


    private int readByte() {
        try {
            return in.read();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public String readLine(){
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public int[][] readGraph(int n, int m){
        int[][] g = new int[n][];
        int[] from = new int[m];
        int[] to = new int[m];
        int[] sizes = new int[n];
        for (int i = 0; i < m; i++) {
            int u = ni() - 1;
            int v = ni() - 1;
            from[i] = u;
            to[i] = v;
            sizes[u]++;
            sizes[v]++;
        }
        for (int i = 0; i < n; i++) {
            g[i] = new int[sizes[i]];
        }
        for (int i = 0; i < m; i++) {
            g[from[i]][--sizes[from[i]]] = to[i];
            g[to[i]][--sizes[to[i]]] = from[i];
        }
        return g;
    }

    public List<GraphUtil.Edge>[] readEdgesGraph(int n, int m, boolean bidirected) {
            List<GraphUtil.Edge>[] g = new List[n];
            for (int i = 0; i < n; i++) {
                g[i] = new LinkedList<>();
            }
            for (int i = 0; i < m; i++) {
                int u = ni() - 1;
                int v = ni() - 1;
                g[u].add(new GraphUtil.Edge(u, v, i));
                if (bidirected)
                    g[v].add(new GraphUtil.Edge(v, u, i));
            }

            return g;
        }

}
