package project_euler;

import common.DSU;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class network_107 {
    static class Edge{
        int from, to, val;

        public Edge(int from, int to, int val) {
            this.from = from;
            this.to = to;
            this.val = val;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(special_subset_105.class.getResourceAsStream("project_euler/p107_network.txt")));
        int N = 40;
        ArrayList<Edge> edges = new ArrayList<>();
        long tot = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer tok = new StringTokenizer(reader.readLine(), ",");
            int j = 0;
            while(tok.hasMoreTokens()){
                String str = tok.nextToken();
                if(!"-".equals(str) && i<j){
                    int val = Integer.parseInt(str);
                    edges.add(new Edge(i, j, val));
                    tot += val;
                }
                j++;
            }
        }
        DSU dsu = new DSU(N);
        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });
        long sum = 0;
        int size = 0;
        for(Edge e: edges){
            if(dsu.find(e.from) != dsu.find(e.to)){
                sum += e.val;
                dsu.union(e.from, e.to);
                size++;
            }
            if(size == N-1)
                break;
        }
        System.out.println("tot = " + tot);
        System.out.println("sum = " + sum);
        System.out.println("(tot-sum) = " + (tot-sum));

    }
}
