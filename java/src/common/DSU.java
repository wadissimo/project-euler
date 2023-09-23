package common;

/**
 * Created on 21.09.2018.
 */
public class DSU {
    int[] rank, parent;
    int n;
    int sets;

    public DSU(int n) {
        rank = new int[n];
        parent = new int[n];
        this.n = n;
        sets = n;
        makeSet();
    }

    void makeSet() {
        for (int i=0; i<n; i++) {
            parent[i] = i;
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
        if (rank[xRoot] < rank[yRoot])
            parent[xRoot] = yRoot;
        else if (rank[yRoot] < rank[xRoot])
            parent[yRoot] = xRoot;
        else {
            parent[yRoot] = xRoot;
            rank[xRoot] = rank[xRoot] + 1;
        }
    }

}