package common.data_structure;

import java.util.Arrays;

/**
 * Created by Vadim
 */
public class BinaryHeapWithIncreasePriority<E extends Comparable<E>> {
    Comparable[] h;
    int[] pos2Id;
    int[] id2Pos;
    int size;

    public BinaryHeapWithIncreasePriority(int maxSize) {
        h = new Comparable[maxSize];
        pos2Id = new int[maxSize];
        id2Pos = new int[maxSize];
        Arrays.fill(id2Pos, -1);
        size = 0;
    }

    public void add(int id, E node) {
        h[size] = node;
        pos2Id[size] = id;
        id2Pos[id] = size;
        moveUp(size++);
    }

    public boolean contains (int id) {
        return id2Pos[id] != -1;
    }

    public E remove(int minId) {
        int pos = id2Pos[minId];
        E removedNode = (E) h[pos];
        E lastNode = (E) h[--size];
        id2Pos[minId] = -1;
        h[size] = null;

        if (size != pos && size > 0) {
            h[pos] = lastNode;
            int id = pos2Id[size];
            id2Pos[id] = pos;
            pos2Id[pos] = id;
            moveDown(pos);
            if(id2Pos[id] == pos) {
                moveUp(pos);
            }
        }
        return removedNode;
    }


    public void increasePriority(int id, E node) {
        int pos = id2Pos[id];
        h[pos] = node;
        moveUp(pos);
    }

    void swap(int i, int j) {
        Comparable x = h[i];
        h[i] = h[j];
        h[j] = x;
        int t = pos2Id[i];
        pos2Id[i] = pos2Id[j];
        pos2Id[j] = t;
        id2Pos[pos2Id[i]] = i;
        id2Pos[pos2Id[j]] = j;
    }

    void moveUp(int pos) {
        while (pos > 0) {
            int parent = (pos - 1) >> 1;
            if (h[pos].compareTo(h[parent]) >= 0) {
                break;
            }
            swap(pos, parent);
            pos = parent;
        }
    }

    public E remove() {
        E removedNode = (E) h[0];
        int minId = pos2Id[0];
        id2Pos[minId] = -1;
        Comparable lastNode = h[--size];
        h[size] = null;
        if (size > 0) {
            h[0] = lastNode;
            int id = pos2Id[size];
            id2Pos[id] = 0;
            pos2Id[0] = id;
            moveDown(0);
        }
        return removedNode;
    }

    public E peek() {
        return (E)h[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }
    void moveDown(int pos) {
        while (pos < size / 2) {
            int child = 2 * pos + 1;
            if (child < size - 1 && h[child].compareTo(h[child + 1]) > 0) {
                ++child;
            }
            if (h[pos].compareTo(h[child]) <= 0) {
                break;
            }
            swap(pos, child);
            pos = child;
        }
    }

    // Usage example
    public static void main(String[] args) {
        BinaryHeapWithIncreasePriority<Integer> heap = new BinaryHeapWithIncreasePriority<Integer>(4);
        heap.add(0, 2);
        heap.add(1, 5);
        heap.add(2, 1);

        heap.increasePriority(0, 0);

        // print elements in sorted order
        while (heap.size != 0) {
            int x = heap.remove();
            System.out.println(x);
        }
    }
}
