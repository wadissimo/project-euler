package project_euler;

import java.util.LinkedList;

public class p106_special_subsets {
    public static final int LEN = 12;
    private static boolean needCompare(int a, int b){ // a with smallest bit
        int an = 0;
        int bn = 0;
        for (int bit = 0; bit < LEN; bit++) {
            if((a&(1<<bit)) > 0)
                an++;
            if((b&(1<<bit)) > 0) {
                bn++;
                if(bn > an)
                    return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        LinkedList<Integer>[] masks = new LinkedList[LEN+1];
        for (int i = 0; i < LEN+1; i++) {
            masks[i] = new LinkedList<>();
        }
        int[] lastBit = new int[1<<LEN];
        int[] firstBit = new int[1<<LEN];
        for (int mask = 1; mask < 1 << LEN; mask++) {
            int bits = 0;
            firstBit[mask] = -1;
            for (int bit = 0; bit < LEN; bit++) {
                if((mask&(1<<bit)) != 0){
                    if(firstBit[mask] == -1)
                        firstBit[mask] = bit;
                    lastBit[mask] = bit;
                    bits++;
                }
            }
            masks[bits].add(mask);
        }
        int ans = 0;
        for (int size = 2; size <= LEN/2; size++) {
            for (int mask : masks[size]) {
                for(int mask2: masks[size]){
                    if((mask & mask2) == 0){
                       if(firstBit[mask2] > firstBit[mask]){
                            if(needCompare(mask, mask2))
                                ans++;
                       }
                    }
                }
            }
            System.out.println("size = " + size);
            System.out.println("ans = " + ans);
        }
        System.out.println("ans = " + ans);
    }
}
