package common.treapflip;

import java.util.Random;

class Treap {
    static Random rgen = new Random();

    static void updParent(Treap.Node node, Treap.Node parent) {
        if (node != null)
            node.parent = parent;
    }

    static void push(Treap.Node node) {
        if (node.l != null)
            node.l.flip ^= node.flip;
        if (node.r != null)
            node.r.flip ^= node.flip;
        if (node.flip) {
            Treap.Node temp = node.l;
            node.l = node.r;
            node.r = temp;
            node.flip = false;
        }
    }

    public static Treap.Node flip(Treap.Node root, int from, int to) {
        Treap.Node[] split = splitC(root, from);
        Treap.Node left = split[0];
        Treap.Node mid = split[1];
        split = splitC(mid, to - from + 1);
        mid = split[0];
        Treap.Node right = split[1];
        mid.flip ^= true;
        left = merge(left, mid);
        return merge(left, right);
    }

    static void updCnt(Treap.Node node) {
        if (node != null) {
            node.count = 1;
            if (node.l != null)
                node.count += node.l.count;
            if (node.r != null)
                node.count += node.r.count;
        }
    }

    public static Treap.Node[] splitC(Treap.Node root, int C) { // key is a count
        if (root == null)
            return new Treap.Node[]{null, null};
        Treap.Node[] split;
        push(root);
        int cnt = count(root.l);
        if (C <= cnt) {
            updParent(root.l, null);
            split = splitC(root.l, C);
            root.l = split[1];
            updParent(root.l, root);
            split[1] = root;
        } else {
            updParent(root.r, null);
            split = splitC(root.r, C - cnt - 1);
            root.r = split[0];
            updParent(root.r, root);
            split[0] = root;
        }
        updCnt(root);
        return split;
    }

    public static Treap.Node insertC(Treap.Node root, Treap.Node node, int C) {
        if (root == null)
            return node;
        if (root.priority < node.priority) {
            Treap.Node[] split = splitC(root, C);
            node.l = split[0];
            updParent(node.l, node);
            node.r = split[1];
            updParent(node.r, node);
            updCnt(node);
            return node;
        } else {
            int cnt = count(root.l);
            if (C <= cnt) {
                root.l = insertC(root.l, node, C);
                updParent(root.l, root);
            } else {
                root.r = insertC(root.r, node, C - cnt - 1);
                updParent(root.r, root);
            }
            updCnt(root);
            return root;
        }
    }

    static int count(Treap.Node a) {
        return a == null ? 0 : a.count;
    }

    public static Treap.Node merge(Treap.Node l, Treap.Node r) {
        if (l == null)
            return r;
        if (r == null)
            return l;
        push(l);
        push(r);
        if (r.priority < l.priority) {
            updParent(l.r, null);
            updParent(r, null);
            l.r = merge(l.r, r);
            updParent(l.r, l);
            updCnt(l);
            return l;
        } else {
            updParent(r.l, null);
            updParent(l, null);
            r.l = merge(l, r.l);
            updParent(r.l, r);
            updCnt(r);
            return r;
        }
    }

    public static Treap.Node searchC(Treap.Node root, int C) {
        while (root != null) {
            push(root);
            int cnt = count(root.l);
            if (C == cnt) {
                return root;
            }
            if (C < cnt) {
                root = root.l;
            } else {
                C -= cnt + 1;
                root = root.r;
            }
        }
        return null; // not found...
    }

    public static class Node {
        public Treap.Node l;
        public Treap.Node r;
        public Treap.Node parent;
        public int count;
        public int key;
        public int priority;
        public boolean flip;

        public Node(int key) {
            this.key = key;
            priority = rgen.nextInt();
            this.count = 1;
        }

        public Node(int key, int priority) {
            this.key = key;
            this.priority = priority;
            this.count = 1;
        }

    }

}
