package common;

import java.util.Arrays;

public class DSUSize {
    public int[] size, parent;
    public int n;
    public int sets;

    public DSUSize(int n) {
        size = new int[n];
        parent = new int[n];
        this.n = n;
        sets = n;
        makeSet();
    }

    void makeSet() {
        for (int i=0; i<n; i++) {
            parent[i] = i;
            size[i] = 1;
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
        int newSize = size[xRoot] + size[yRoot];
        if (size[xRoot] < size[yRoot]) {
            parent[xRoot] = yRoot;
            size[yRoot] = newSize;
            size[xRoot] = 0;
        } else {
            parent[yRoot] = xRoot;
            size[xRoot] = newSize;
            size[yRoot] = 0;
        }
    }
}

