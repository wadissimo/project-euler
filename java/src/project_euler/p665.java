package project_euler;

import java.util.Arrays;
import java.util.TreeSet;

public class p665 {
    static class DSU{
        int[] rank, parent;
        boolean[] free;
        int[] next;
        int n;
        int sets;

        public DSU(int n) {
            rank = new int[n];
            parent = new int[n];
            this.n = n;
            sets = n;
            next = new int[n];
            free = new boolean[n];
            makeSet();
        }

        void makeSet() {
            for (int i=0; i<n; i++) {
                parent[i] = i;
                next[i] = i;
                free[i] = true;
            }
        }

        public int find(int x) {
            if (parent[x]!=x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int xRoot = find(x), yRoot = find(y);
            if (xRoot == yRoot)
                return;
            sets--;
            if (rank[xRoot] < rank[yRoot]) {
                parent[xRoot] = yRoot;
            }else if (rank[yRoot] < rank[xRoot]){
                parent[yRoot] = xRoot;
            }else {
                parent[yRoot] = xRoot;
                rank[xRoot] = rank[xRoot] + 1;
            }
        }

        public void occupy(int v){
            if(v < 0)
                throw new RuntimeException();
            free[v] = false;
            int nxt = next[find(v+1)];
            if(v > 0 && !free[v-1]){
                union(v,v-1);
            }
            if(!free[v+1]){
                union(v, v+1);
            }
            next[find(v)] = nxt;
        }
        public int nextFree(int v){
            return next[find(v)];
        }
    }

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        int MAX = 30_000_000;
        int LIMIT = 100_000_000;
        int HALF = MAX/2;
        DSU hor = new DSU(MAX);
        DSU diag = new DSU(MAX);
        DSU diag2 = new DSU(MAX);
        DSU odd = new DSU(MAX);
        DSU even = new DSU(MAX);
        hor.occupy(0);
        diag.occupy(HALF);
        diag2.occupy(HALF);
        even.occupy(HALF);
        int[] sol = new int[LIMIT+7];
        System.out.print("ans\t\t");
        boolean evenI = true;
        long ans = 0;
        for (int i = 1; i <= LIMIT/2; i++) {
            evenI = !evenI;
            if(sol[i] != 0)
                continue;
            int cur = 0;
            boolean found = false;
//            System.out.println("i = " + i);

            while(!found && cur+i <= LIMIT){
                int prev = cur;
                cur = hor.nextFree(cur);
                if(!diag.free[HALF + cur-i]){
                    cur = diag.nextFree(HALF + cur-i);
                    cur = cur-HALF+i;
                    if(cur <= prev)
                        throw new RuntimeException();
                    continue;
                }
                if(evenI){
                    if(!even.free[HALF + cur-i/2]){
                        cur = even.nextFree(HALF + cur-i/2);
                        cur = cur-HALF +i/2;
                        if(cur <= prev)
                            throw new RuntimeException();
                        continue;
                    }
                } else {
                    if(!odd.free[HALF + cur-i/2]){
                        cur = odd.nextFree(HALF + cur-i/2);
                        cur = cur-HALF +i/2;
                        if(cur <= prev)
                            throw new RuntimeException();
                        continue;
                    }
                }
                if(!diag2.free[HALF + cur-2*i]){
                    cur = diag2.nextFree(HALF + cur-2*i);
                    cur = cur-HALF + 2*i;
                    if(cur <= prev)
                        throw new RuntimeException();
                    continue;
                }
                found = true;
            }
            if(found && i+cur <= LIMIT) {
                sol[i] = cur;
                hor.occupy(cur);
                diag.occupy(HALF + cur - i);
                diag2.occupy(HALF + cur - 2 * i);
                if (evenI)
                    even.occupy(HALF + cur - i / 2);
                else
                    odd.occupy(HALF + cur - i / 2);

                if(sol[cur] == 0) {
                    sol[cur] = i;
                    hor.occupy(i);
                    diag.occupy(HALF + i - cur);
                    diag2.occupy(HALF + i - 2 * cur);
                    if (cur %2 == 0)
                        even.occupy(HALF + i - cur / 2);
                    else
                        odd.occupy(HALF + i - cur / 2);
                }
            }
//            System.out.print(cur + "\t");

        }
        for (int i = 0; i <= LIMIT; i++) {
            if(sol[i] != 0){
                if(i<=sol[i] && i+sol[i] <=LIMIT)
                    ans+=i + sol[i];
            }
        }
        System.out.println("ans = " + ans);
        //printSample();



        long t2 = System.currentTimeMillis();
        System.out.println("(t2-t1) = " + (t2 - t1));
    }

    private static int[][] getGrundy(int max){
        int[][] grundy = new int[max][max];
        boolean[] have = new boolean[max*max];
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                Arrays.fill(have, false);
                for (int n = 1; n <=i ; n++) {
                    have[grundy[i-n][j]] = true;
                    if(j >= 2*n)
                        have[grundy[i-n][j-2*n]] = true;
                }
                for (int n = 1; n <=j ; n++) {
                    have[grundy[i][j-n]] = true;
                    if(i >= 2*n)
                        have[grundy[i-2*n][j-n]] = true;
                }
                for (int n = 1; n <=Math.min(i,j); n++) {
                    have[grundy[i-n][j-n]] = true;
                }
                for (int k = 0; k < max*max; k++) {
                    if(!have[k]){
                        grundy[i][j] = k;
                        break;
                    }
                }
            }
        }
        return grundy;
    }
    private static void printSample(){
        int max = 100;
        int[][] grundy = getGrundy(max);
        System.out.print("+ \t");
        for (int i = 0; i < 30; i++) {
            System.out.print(i + " \t");
        }
        System.out.println();
        System.out.println("--------------------------------------------");
        for (int i = 0; i < max; i++) {
            System.out.print(i + ":\t");
            for (int j = 0; j < 30; j++) {
                if(grundy[i][j] > 0){
                    System.out.print("x\t");
                } else {
                    System.out.print("0\t");
                }
            }
            System.out.println();
        }
    }
}
