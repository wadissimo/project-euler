package common;

import java.util.Arrays;

public class Heap<E extends Comparable<E>> {
    public Comparable[] h;
    private int maxSize;
    public int size;

    public Heap(int maxSize) {
        this.maxSize = maxSize;
        h = new Comparable[maxSize];
        this.size = 0;
    }

    public Heap(Comparable[] sortedArray) {
        this.size = this.maxSize = sortedArray.length;
        h = sortedArray;
    }

    private void swap(int i, int j) {
        Comparable x = h[i];
        h[i] = h[j];
        h[j] = x;
    }

    public void moveUp(int pos) {
        while (pos > 0) {
            int parent = (pos - 1) >> 1;
            if (h[pos].compareTo(h[parent]) >= 0) {
                break;
            }
            swap(pos, parent);
            pos = parent;
        }
    }

    public void moveDown(int pos) {
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

    public E first(){
        return (E) h[0];
    }

    public void offer(E node) {
        h[size] = node;
        moveUp(size++);
    }

    public E poll() {
        E removedNode = (E) h[0];
        Comparable lastNode = h[--size];
        h[size] = null;
        if (size > 0) {
            h[0] = lastNode;
            moveDown(0);
        }
        return removedNode;
    }

    public static void main(String[] args) {

        Integer[] a = new Integer[]{ 1, 2, 3, 3, 10,10,20};
        Heap<Integer> heap = new Heap<>(a);
        System.out.println("Arrays.toString(heap.h) = " + Arrays.toString(heap.h));
        for (int i = 0; i < a.length - 1; i++) {
            Integer polled = heap.poll();
            System.out.println("polled = " + polled);
            System.out.println("heap.first() = " + heap.first());
        }
        System.out.println("Arrays.toString(heap.h) = " + Arrays.toString(heap.h));
    }
}
