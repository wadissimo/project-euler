package common;


import java.util.HashSet;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;

public class Treap {
    static Random rgen = new Random();

    public static class Node{
        Node l,r;
        Node parent;
        int count;
        public int key, priority;

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
        public Node copy(){
            Node n = new Node(this.key, this.priority);
            n.count = this.count;
            return n;
        }
    }
    static void updParent(Node node, Node parent){
        if(node!= null)
            node.parent= parent;
    }

    static void updCnt(Node node){
        if(node!= null){
            node.count = 1;
            if (node.l!=null)
                node.count+=node.l.count;
            if (node.r!=null)
                node.count+=node.r.count;
        }
    }
    public static Node[] split(Node root, int key){
        if(root == null)
            return new Node[]{null,null};
        Node[] split;
        if(key <= root.key){
            updParent(root.l, null);
            split = split(root.l, key);
            root.l = split[1];
            updParent(root.l, root);
            split[1] = root;
        } else {
            updParent(root.r, null);
            split = split(root.r, key);
            root.r = split[0];
            updParent(root.r, root);
            split[0] = root;
        }
        updCnt(root);
        return split;
    }

    public static Node[] splitC(Node root, int C){ // key is a count
        if(root == null)
            return new Node[]{null,null};
        Node[] split;
        int cnt = count(root.l);
        if(C <= cnt){
            updParent(root.l, null);
            split = splitC(root.l, C);
            root.l = split[1];
            updParent(root.l, root);
            split[1] = root;
        } else {
            updParent(root.r, null);
            split = splitC(root.r, C-cnt-1);
            root.r = split[0];
            updParent(root.r, root);
            split[0] = root;
        }
        updCnt(root);
        return split;
    }

    public static Node insert(Node root, Node node){
        if(root == null)
            return node;
        if(root.priority<node.priority){
            Node[] split = split(root, node.key);
            node.l=split[0];
            updParent(node.l, node);
            node.r=split[1];
            updParent(node.r, node);
            updCnt(node);
            return node;
        } else{
            if(node.key <= root.key){
                root.l = insert(root.l, node);
                updParent(root.l, root);
            } else {
                root.r = insert(root.r, node);
                updParent(root.r, root);
            }
            updCnt(root);
            return root;
        }
    }

    public static Node insertC(Node root, Node node, int C){
        if(root == null)
            return node;
        if(root.priority<node.priority){
            Node[] split = splitC(root, C);
            node.l=split[0];
            updParent(node.l, node);
            node.r=split[1];
            updParent(node.r, node);
            updCnt(node);
            return node;
        } else{
            int cnt = count(root.l);
            if(C <= cnt){
                root.l = insertC(root.l, node, C);
                updParent(root.l, root);
            } else {
                root.r = insertC(root.r, node, C-cnt-1);
                updParent(root.r, root);
            }
            updCnt(root);
            return root;
        }
    }



    public static int count(Node a) {
        return a == null ? 0 : a.count;
    }



    public static Node merge(Node l, Node r){
        if(l==null)
            return r;
        if(r==null)
            return l;
        if(r.priority < l.priority){
            updParent(l.r, null);
            updParent(r, null);
            l.r = merge(l.r, r);
            updParent(l.r, l);
            updCnt(l);
            return l;
        }else{
            updParent(r.l, null);
            updParent(l, null);
            r.l = merge(l, r.l);
            updParent(r.l, r);
            updCnt(r);
            return r;
        }
    }

    public static int bisectLeft(Node root, int keyBound){ //a[:rank] < bound <= a[rank:]
        int cnt = 0;
        while(root != null){
            if(keyBound <= root.key){
                root = root.l;
            }else{
                cnt++;
                if(root.l != null)
                    cnt += root.l.count;
                root = root.r;
            }
        }
        return cnt;
    }

    public static Node next(Node node) {
        if(node == null)
            return null;
        if(node.r != null){
            node = node.r;
            while(node.l != null)
                node = node.l;
            return node;
        }else{
            while(true){
                Node p = node.parent;
                if(p == null)
                    return null;
                if(p.l == node)
                    return p;
                node = p;
            }
        }
    }

    public static Node min(Node root) {
        if(root == null)
            return null;
        while(root.l != null){
            root = root.l;
        }
        return root;
    }

    public static Node max(Node root) {
        if(root == null)
            return null;
        while(root.r != null){
            root = root.r;
        }
        return root;
    }

    public static Node prev(Node node) {
        if(node == null)
            return null;
        if(node.l != null){
            node = node.l;
            while(node.r != null)
                node = node.r;
            return node;
        }else{
            while(true){
                Node p = node.parent;
                if(p == null)
                    return null;
                if(p.r == node)
                    return p;
                node = p;
            }
        }
    }

    public static Node search(Node root, int key){
        while(root != null){
            if(root.key == key){
                return root;
            }
            if(key < root.key){
                root = root.l;
            }else{
                root = root.r;
            }
        }
        return null; // not found
    }

    public static Node searchC(Node root, int C){
        while(root != null){
            int cnt = count(root.l);
            if(C == cnt){
                return root;
            }
            if(C < cnt){
                root = root.l;
            }else{
                C -= cnt+1;
                root = root.r;
            }
        }
        return null; // not found...
    }

    public static int rank(Node root, int key){ // arbitrary for non-unique keys
        int cnt = 0;
        while(root != null){
            if(root.key == key){
                if(root.l != null)
                    cnt += root.l.count;
                return cnt;
            }
            if(key < root.key){
                root = root.l;
            }else{
                cnt++;
                if(root.l != null)
                    cnt += root.l.count;
                root = root.r;
            }
        }
        return -cnt-1; // not found, but should be at cnt (-res-1)
    }

    public static int countLower(Node root, int key){ // count items < key
        int cnt = 0;
        while(root != null){
            if(root.key == key){
                if(root.l != null)
                    cnt += root.l.count;
                return cnt;
            }
            if(key < root.key){
                root = root.l;
            }else{
                cnt++;
                if(root.l != null)
                    cnt += root.l.count;
                root = root.r;
            }
        }
        return cnt;
    }

    public static int rank(Node node){ // returns C
        if(node == null)
            return -1; // not found
        int cnt = count(node.l);
        while(node != null){
            if(node.parent != null && node.parent.r == node)
                cnt += count(node.parent.l)+1;
            node = node.parent;
        }
        return cnt;
    }



    public static Node erase(Node root, int key){
        if(root == null)
            return null;
        if(root.key == key){
            updParent(root.l, null);updParent(root.r, null);
            return merge(root.l, root.r);
        } else if(key < root.key){
            root.l = erase(root.l, key);
            updParent(root.l, root);
            updCnt(root);
            return root;
        } else {
            root.r = erase(root.r, key);
            updParent(root.r, root);
            updCnt(root);
            return root;
        }
    }

    public static Node eraseC(Node root, int C){
        if(root == null)
            return null;
        int cnt = count(root.l);
        if(C == cnt){
            updParent(root.l, null);updParent(root.r, null);
            return merge(root.l, root.r);
        } else if(C < cnt){
            root.l = eraseC(root.l, C);
            updParent(root.l, root);
            updCnt(root);
            return root;
        } else {
            root.r = eraseC(root.r, C-cnt-1);
            updParent(root.r, root);
            updCnt(root);
            return root;
        }
    }

    public static Node copy(Node root){
        Node n = root.copy();
        if(root.l != null){
            Node child = copy(root.l);
            n.l = child;
            child.parent = n;
        }
        if(root.r != null){
            Node child = copy(root.r);
            n.r = child;
            child.parent = n;
        }
        return n;
    }

    public static void main(String[] args) {
        int N = 300000;
        Node root = null;
        HashSet<Integer> toRemove = new HashSet<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            Node node = new Node(rgen.nextInt());
            root = insert(root, node);
            if(i%2 == 0)
                toRemove.add(node.key);

        }
        long mid1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            search(root, rgen.nextInt());
        }
        long mid2 = System.currentTimeMillis();
        for (Integer key : toRemove) {
            erase(root, key);
        }
        long end = System.currentTimeMillis();
        System.out.println("Size "+root.count);
        System.out.println("Treap: search " + (mid2-mid1) + " ms");
        System.out.println("Treap: erase " + (end-mid2) + " ms");
        System.out.println("Treap: " + (end-start) + " ms");
        toRemove.clear();
        start = System.currentTimeMillis();
        TreeMap<Integer, Node> treeMap = new TreeMap<>();
        for (int i = 0; i < N; i++) {
            Node node = new Node(rgen.nextInt());
            treeMap.put(node.key, node);

            if(i%2 == 0)
                toRemove.add(node.key);
        }
        mid1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            treeMap.get(rgen.nextInt());
        }
        mid2 = System.currentTimeMillis();
        for (Integer key : toRemove) {
            treeMap.remove(key);
        }
        end = System.currentTimeMillis();
        System.out.println("Size "+treeMap.size());
        System.out.println("Java TreeSet: search " + (mid2-mid1) + " ms");
        System.out.println("Java TreeSet: remove " + (end-mid2) + " ms");
        System.out.println("Java TreeSet: " + (end-start) + " ms");


    }


}