package common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Trie {
    public Node root;

    public Trie() {
        this.root = new Node((char)0);
    }

    public void addString(String s){
        Node cur = root;
        root.total++;
        for (int i = 0; i < s.length(); i++) {
            cur = cur.add(s.charAt(i));
        }
        cur.count++;
    }

    public class Node{
        public char c;
        public HashMap<Character, Node> children;
        public int count = 0; // ends here
        public int total = 0; // all strings and prefixes

        public Node(char c) {
            this.c = c;
            children = new HashMap<>();
        }

        public Node add(char c){
            Node child = children.computeIfAbsent(c, k -> new Node(c));
            child.total++;
            return child;
        }

    }
}
