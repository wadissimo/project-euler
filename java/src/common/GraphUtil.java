package common;

import java.util.*;

/**
 *
 */
public class GraphUtil {


    public static class Node {
        public int id;
        public int parent;
        public List<GraphUtil.Node> children;
        public Node(int id, int parent) {
            this.id = id;
            this.parent = parent;
        }
        public void add(GraphUtil.Node child) {
            if (children == null) children = new LinkedList<>();
            children.add(child);
        }
    }

    public static class Edge {
        public int from;
        public int to;
        public int id;
        public long w;
        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
        public Edge(int from, int to, int id) {
            this.from = from;
            this.to = to;
            this.id = id;
        }

        public Edge(int from, int to, int id, long w) {
            this.from = from;
            this.to = to;
            this.id = id;
            this.w = w;
        }
    }

    public static class StrongConnectedComponent {
        static int time;
        static List<Integer>[] graph;
        static int[] lowlink;
        static boolean[] used;
        static List<Integer> stack;
        static List<List<Integer>> components;

        public static List<List<Integer>> scc(List<Integer>[] g) {
            graph = g;
            int n = graph.length;
            lowlink = new int[n];
            used = new boolean[n];
            stack = new ArrayList<Integer>();
            components = new ArrayList<List<Integer>>();

            for (int u = 0; u < n; u++)
                if (!used[u])
                    dfs(u);

            return components;
        }

        static void dfs(int u) {
            lowlink[u] = time++;
            used[u] = true;
            stack.add(u);
            boolean isComponentRoot = true;

            for (int v : graph[u]) {
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



    public static List<GraphUtil.Edge> getBridges(List<GraphUtil.Edge>[] g) { // for connected graph
            int n = g.length;
            int[] tin = new int[n];
            int[] lowlink = new int[n];
            Arrays.fill(lowlink, -1);
            Deque<Integer> st = new LinkedList<>();
            int timer = 0;
            GraphUtil.Edge topEdge[] = new GraphUtil.Edge[n];
            LinkedList<GraphUtil.Edge> res = new LinkedList<>();
            for (int k = 0; k < n; k++) {
                if (lowlink[k] != -1)
                    continue;

                st.push(k);
                while (!st.isEmpty()) {
                    int v = st.peek();
                    int from = topEdge[v] == null ? -1 : topEdge[v].id;
                    if (lowlink[v] != -1) {
                        st.pop();
                        if (topEdge[v] != null) {
                            int p = topEdge[v].from;
                            if (tin[p] < lowlink[v]) // bridge
                                res.add(topEdge[v]);
                            lowlink[p] = Math.min(lowlink[p], lowlink[v]);
                        }
                    } else {
                        lowlink[v] = tin[v] = timer++;
                        for (GraphUtil.Edge e : g[v]) {
                            int u = e.to;
                            if (e.id == from)
                                continue;
                            if (lowlink[u] != -1) {
                                lowlink[v] = Math.min(lowlink[v], tin[u]);
                            } else {
                                st.push(u);
                                topEdge[u] = e;
                            }
                        }
                    }
                }
            }
            return res;
        }

        public static int diameter(GraphUtil.Node tree, int n) {
            GraphUtil.Node[] st = new GraphUtil.Node[2 * n];
            int sti = 0;
            boolean[] used = new boolean[n];
            st[sti++] = tree;
            int res = 0;
            int[] h = new int[n];
            Arrays.fill(h, 1);
            while (sti > 0) {
                GraphUtil.Node node = st[sti - 1];
                if (used[node.id]) {
                    sti--;
                    if (node.parent != -1 && node.parent != node.id) {
                        h[node.parent] = Math.max(h[node.parent], h[node.id] + 1);
                    }
                    if (node.children == null)
                        continue;
                    int max = 0;
                    int max2 = 0;
                    for (GraphUtil.Node child : node.children) {
                        int ch = h[child.id];
                        if (ch >= max) {
                            max2 = max;
                            max = ch;
                        } else {
                            if (ch > max2)
                                max2 = ch;
                        }
                    }
                    res = Math.max(res, max + max2);
                } else {
                    used[node.id] = true;
                    if (node.children != null)
                        for (GraphUtil.Node child : node.children) {
                            st[sti++] = child;
                        }
                }
            }
            return res;
        }



}
