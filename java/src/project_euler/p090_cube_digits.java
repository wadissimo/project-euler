package project_euler;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class p090_cube_digits {
    static List<Integer> combinationMasks = new LinkedList<>();
    static List<int[]> combinations = new LinkedList<>();
    static int[] getCombination(int mask){
        int[] comb = new int[Integer.bitCount(mask)];
        int idx = 0;
        for (int j = 0; j < 10; j++) {
            if((mask&(1<<j)) != 0){
                comb[idx++] = j;
            }
        }
        return comb;
    }
    static void rec(int mask, int max, int remToTake) {
        for (int i = 0; i < max; i++) {
            if((mask&(1<<i)) == 0){
                if(remToTake == 1){
                    int m = mask | (1 << i);
                    combinationMasks.add(m);
                } else {
                    rec(mask | (1 << i), i, remToTake - 1);
                }
            }
        }
    }

    static int[] squares = new int[]{1, 4, 9, 16,25, 36, 49, 64,81};
    static int cnt1 = 0;
    static int cnt2 = 0;
    static boolean check(int mask1, int mask2) {
        if((mask1&(1<<9)) != 0){
            mask1 |= 1<<6;
        }
        if((mask1&(1<<6)) != 0){
            mask1 |= 1<<9;
        }
        if((mask2&(1<<9)) != 0){
            mask2 |= 1<<6;
        }
        if((mask2&(1<<6)) != 0){
            mask2 |= 1<<9;
        }
        int all = mask1|mask2;
        int need = (1<<10)-1 - (1<<7);
        all &= need;
        /*if(all != need) {

            cnt1++;
            return false;
        }*/
        for (int sq : squares) {
            int d1 = sq/10;
            int d2 = sq%10;
            if(!((mask1&(1<<d1)) != 0 && (mask2&(1<<d2)) != 0 ||
                    (mask1&(1<<d2)) != 0 && (mask2&(1<<d1)) != 0)){
                cnt2++;
                return false;
            }
        }
        System.out.println(Arrays.toString(getCombination(mask1)) + " " + Arrays.toString(getCombination(mask2)));
        return true;
    }

    public static void main(String[] args) {
        rec(0, 10, 6);
        System.out.println("combinations = " + combinationMasks.size());
        int ans = 0;
        for (Integer mask1 : combinationMasks) {
            for (Integer mask2 : combinationMasks) {
                if(mask1 >= mask2 && check(mask1, mask2)){
                    ans++;
                }
            }
        }
        System.out.println("cnt1 = " + cnt1);
        System.out.println("cnt2 = " + cnt2);
        System.out.println("ans = " + ans);

    }
}
