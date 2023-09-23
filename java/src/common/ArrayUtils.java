package common;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

/**
 *
 */
public class ArrayUtils {

    public static long countInversions(int[] src) {
        int length = src.length;

        if(length == 1){
            return 0;
        }
        if (length == 2) {
            if(src[0] > src[1]){
                int t = src[0];
                src[0] = src[1];
                src[1] = t;
                return 1;
            } else {
                return 0;
            }
        }
        int size1 = length / 2;
        int[] half1 = new int[size1];
        int size2 = length - size1;
        int[] half2 = new int[size2];
        System.arraycopy(src, 0, half1,0, size1);
        System.arraycopy(src, size1, half2, 0, size2);

        long i1 = countInversions(half1);
        long i2 = countInversions(half2);

        long i3 = 0;
        for(int i = 0, p = 0, q = 0; i < length; i++) {
            if (q >= size2 || p < size1 && half1[p] <= half2[q]) {
                src[i] = half1[p++];
            } else {
                src[i] = half2[q++];
                i3 += size1-p;
            }
        }
       return i1 + i2 + i3;
    }

    public static void printArray(int[][] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                sb.append(a[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    public static String printArray(int[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
            if(i != a.length-1)
                sb.append(' ');
        }
        return sb.toString();
    }

    public static void randomShuffle(Object[] a){
        Random rand = new Random();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int x = rand.nextInt(n);
            int y = rand.nextInt(n-1);
            if(y >= x)
                y++;
            Object t = a[x];
            a[x] = a[y];
            a[y] = t;
        }
    }

    public static void randomShuffle(int[] a){
        Random rand = new Random();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int x = rand.nextInt(n);
            int t = a[x];
            a[x] = a[i];
            a[i] = t;
        }
    }

    public static int[][] reverse(int[][] a){
        int n = a.length;
        int m = a[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = a[j][i];
            }
        }
        return res;
    }


    public static void mergeSortSimple(int[] src) {

        int length = src.length;

        if(length == 1){
            return;
        }
        if (length == 2) {
            if(src[0] > src[1]){
                int t = src[0];
                src[0] = src[1];
                src[1] = t;
            }
            return;
        }
        int size1 = length / 2;
        int[] half1 = new int[size1];
        int size2 = length - size1;
        int[] half2 = new int[size2];
        System.arraycopy(src, 0, half1,0, size1);
        System.arraycopy(src, size1, half2, 0, size2);


        mergeSortSimple(half1);
        mergeSortSimple(half2);

        for(int i = 0, p = 0, q = 0; i < length; i++) {
            if (q >= size2 || p < size1 && half1[p] <= half2[q])
                src[i] = half1[p++];
            else
                src[i] = half2[q++];
        }
    }

    public static void mergeSort(int[] a) {
        int [] src = a.clone();
        mergeSortInt(src, a, 0, a.length);
    }
    public static void mergeSortInt(int[] src,
                                  int[] dest,
                                  int low,
                                  int high) {
        int length = high - low;

        if(length == 1){
            return;
        }
        if (length == 2) {
            if(dest[low] > dest[high-1]){
                int t = dest[low];
                dest[low] = dest[high-1];
                dest[high-1] = t;
            }
            return;
        }


        int mid = (low + high) >>> 1;
        mergeSortInt(dest, src, low, mid);
        mergeSortInt(dest, src, mid, high);

        // Merge sorted halves (now in src) into dest
        for(int i = low, p = low, q = mid; i < high; i++) {
            if (q >= high || p < mid && src[p] <= src[q])
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    public static long numInversions(int[] a) {
        int [] src = a.clone();
        return numInversionsInt(src, a, 0, a.length);
    }

    public static long numInversionsInt(int[] src, int[] dest, int low, int high) {
        int length = high - low;

        if(length == 1){
            return 0;
        }
        if (length == 2) {
            if(dest[low] > dest[high-1]){
                int t = dest[low];
                dest[low] = dest[high-1];
                dest[high-1] = t;
                return 1;
            }
            return 0;
        }

        int mid = (low + high) / 2;
        long c1 = numInversionsInt(dest, src, low, mid);
        long c2 = numInversionsInt(dest, src, mid, high);
        long c3 = 0;
        for(int i = low, p = low, q = mid; i < high; i++) {
            if (q >= high || p < mid && src[p] <= src[q]) {
                dest[i] = src[p++];
            } else {
                dest[i] = src[q++];
                c3 += mid-p;
            }
        }
        return c1+c2+c3;
    }

    static void printCollection(Collection c){
        StringBuilder sb = new StringBuilder();
        for (Iterator it = c.iterator(); it.hasNext(); ) {
            sb.append(it.next());
            if(it.hasNext())
                sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    public static int binarySearch(int[] a, int value) { // returns first element >= value
        int left = 0, right = a.length;
        while (left < right) {
            int mid = (right + left) / 2;
            if (a[mid] < value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static int binarySearch(long[] a, long value) { // returns first element >= value
        int left = 0, right = a.length;
        while (left < right) {
            int mid = (right + left) / 2;
            if (a[mid] < value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

}
