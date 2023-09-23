package common;

public class XorTrie {
    Node root;
    int bits;
    public XorTrie(int bits){
        this.bits = bits;
        root = new Node(null);
    }
    public void add(int a){
        Node cur = root;
        for(int i = bits-1;i>=0;i--){
            if((a&(1<<i)) == 0){
                if(cur.c0 == null){
                    cur.c0 = new Node(cur);
                }
                cur = cur.c0;
            } else {
                if(cur.c1 == null){
                    cur.c1 = new Node(cur);
                }
                cur = cur.c1;
            }
        }
        cur.cnt++;
    }
    public int minXor(int a){
        Node cur = root;
        int res = 0;
        for(int i = bits-1; i>=0;i--){
            if((a&(1<<i)) == 0){
                if(cur.c0 != null){
                    cur = cur.c0;
                } else {
                    res |= (1<<i);
                    cur = cur.c1;
                }
            } else {
                if(cur.c1 != null){
                    cur = cur.c1;
                } else {
                    res |= (1<<i);
                    cur = cur.c0;
                }
            }
        }

        return res;
    }
    public Node minXorNode(int a){
        Node cur = root;
        for(int i = bits-1; i>=0;i--){
            if((a&(1<<i)) == 0){
                cur = cur.c0;
                if(cur.c0 != null){
                    cur = cur.c0;
                } else {
                    cur = cur.c1;
                }
            } else {
                if(cur.c1 != null){
                    cur = cur.c1;
                } else {
                    cur = cur.c0;
                }
            }
        }
        return cur;
    }

    public boolean remove(int a){
        Node cur = root;
        for(int i = bits-1; i>=0;i--){
            if(cur == null)
                return false;
            if((a&(1<<i)) == 0){
                cur = cur.c0;
            } else {
                cur = cur.c1;
            }
        }
        cur.cnt--;
        if(cur.cnt == 0){
            Node del = cur;
            while((cur.c0 == null || cur.c1 == null) && cur.parent != null) {
                del = cur;
                cur = cur.parent;
            }
            if(cur.c0 == del)
                cur.c0 = null;
            else
                cur.c1 = null;
        }
        return true;
    }
    public class Node{
        Node parent;
        Node c0, c1;
        int cnt;

        public Node(Node parent) {
            this.parent = parent;
        }
    }
}
