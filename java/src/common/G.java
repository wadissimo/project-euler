package common;

import java.util.*;

public class G {

    public G(List<GraphUtil.Edge>[] g){
        adj = g;
    }
    public List<GraphUtil.Edge>[] adj;
    public int[] tin;
    public int[] lowlink;
    int timer;

    void bridgeSearchRec(LinkedList<GraphUtil.Edge> res, int v, int id){
        lowlink[v] = tin[v] = timer++;
        for (GraphUtil.Edge e : adj[v]) {
            int u = e.to;
            if (e.id == id)
                continue;
            if (lowlink[u] != -1) {
                lowlink[v] = Math.min(lowlink[v], tin[u]);
            } else {
                bridgeSearchRec(res, u, e.id);
                if (tin[v] < lowlink[u])
                    res.add(e);
                lowlink[v] = Math.min(lowlink[v], lowlink[u]);
            }
        }

    }
    public List<GraphUtil.Edge> getBridges() {
            int n = adj.length;
            tin = new int[n];
            lowlink = new int[n];
            Arrays.fill(lowlink, -1);
            timer = 0;
            LinkedList<GraphUtil.Edge> res = new LinkedList<>();
            for (int k = 0; k < n; k++) {
                if (lowlink[k] != -1)
                    continue;
                bridgeSearchRec(res, k, -1);
            }
            return res;
    }

    public G condense() {
            List<List<Integer>> sccs = new SCC().scc();
            int n = adj.length;
            int[] remap = new int[n];
            for (int i = 0; i < n; i++) {
                remap[i] = i;
            }
            for (List<Integer> scc : sccs) {
                int head = -1;
                for (int v : scc) {
                    if (head == -1)
                        head = v;
                    else {
                        remap[v] = head;
                    }
                }
            }
            List<GraphUtil.Edge>[] res = new List[n];
            for (int i = 0; i < n; i++) {
                if (remap[i] == i)
                    res[i] = new ArrayList<>(); // todo: null - condensed
            }
            for (int i = 0; i < n; i++) {
                for (GraphUtil.Edge edge : adj[i]) {
                    int to = edge.to;
                    if (remap[to] != remap[i]) {
                        res[remap[i]].add(new GraphUtil.Edge(remap[i], remap[to], edge.id));
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                if (remap[i] != i)
                    continue;
                List<GraphUtil.Edge> edges = res[i];
                edges.sort(Comparator.comparingInt(e -> e.to));
                List<GraphUtil.Edge> list = new LinkedList<>();
                GraphUtil.Edge prev = null;
                for (GraphUtil.Edge edge : edges) {
                    if (prev == null || prev.to != edge.to) {
                        list.add(edge);
                        prev = edge;
                    }
                }
                res[i] = list;
            }
            return new G(res);
        }

        class SCC {
            int time;
            int[] lowlink;
            boolean[] used;
            List<Integer> stack;
            List<List<Integer>> components;

            public List<List<Integer>> scc() {
                int n = adj.length;
                lowlink = new int[n];
                used = new boolean[n];
                stack = new ArrayList<Integer>();//todo:
                components = new ArrayList<List<Integer>>();//todo:

                for (int u = 0; u < n; u++)
                    if (!used[u])
                        dfs(u);

                return components;
            }

            void dfs(int u) {
                lowlink[u] = time++;
                used[u] = true;
                stack.add(u);
                boolean isComponentRoot = true;

                for (GraphUtil.Edge e : adj[u]) {
                    int v = e.to;
                    if (!used[v])
                        dfs(v);
                    if (lowlink[u] > lowlink[v]) {
                        lowlink[u] = lowlink[v];
                        isComponentRoot = false;
                    }
                }

                if (isComponentRoot) {
                    List<Integer> component = new ArrayList<Integer>();
                    while (true) {
                        int k = stack.remove(stack.size() - 1);
                        component.add(k);
                        lowlink[k] = Integer.MAX_VALUE;
                        if (k == u)
                            break;
                    }
                    components.add(component);
                }
            }

        }

        public Dijkstra dijkstra(){
            return new Dijkstra();
        }

        public class Dijkstra{
            public long INF = 1234567890123456789L;
            public class Pair{
                long w;
                int v;

                public Pair(long w, int v) {
                    this.w = w;
                    this.v = v;
                }
            }
            public void dijkstra(long d[], GraphUtil.Edge[] p) {

                Arrays.fill(d, INF);
                d[0] = 0;
                PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
                    @Override
                    public int compare(Pair o1, Pair o2) {
                        if(o1.w == o2.w)
                            return Integer.compare(o1.v, o2.v);
                        else
                            return Long.compare(o1.w, o2.w);
                    }
                });
                pq.offer(new Pair(0,0));
                while(!pq.isEmpty()){
                    Pair top = pq.poll();
                    int v = top.v;
                    if(top.w > d[v])
                        continue;
                    for (GraphUtil.Edge e : adj[v]) {
                        if(d[v] + e.w < d[e.to]){
                            d[e.to] = d[v] + e.w;
                            p[e.to] = e;
                            pq.offer(new Pair(d[e.to], e.to));
                        }
                    }
                }
            }

        }


}
