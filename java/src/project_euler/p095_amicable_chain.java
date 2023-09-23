package project_euler;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class p095_amicable_chain {
    static int LIM = 1_000_000;
    static int[] used = new int[LIM+7];
    static int[] divSum = new int[LIM+7];
    int[] cycleLen = new int[LIM+7];
    static Deque<Integer>[] cycles = new Deque[LIM+7];
    static Deque<Integer> rec(int num){
        int next = divSum[num];
        System.out.printf("%d -> %d%n", num, next);
        if(next > LIM){
            return null;
        }
        if(used[next] == 0){
            used[next] = used[num];
            Deque<Integer> res = rec(next);
            if(res != null){
                res.addLast(next);
                if(res.getFirst() == num){
                    cycles[num] = res;
                    return null;
                }
                return res;
            }
        } else {
            if(used[next] == used[num]){ // cycle found
                Deque<Integer> list = new LinkedList<>();
                list.add(next);
                return list;
            }
        }
        return null;
    }
    public static void main(String[] args) {


        long t1 = System.currentTimeMillis();
        for (int num = 1; num <= LIM; num++) {
            int sum = 0;
            for (int div = 1; div*div <= num; div++) {
                if(num%div == 0){
                    sum+=div;
                    if(div*div < num && div != 1){
                        sum+= num/div;
                    }
                }
            }
            divSum[num] = sum;
        }
        long t2 = System.currentTimeMillis();
        System.out.printf("time: %d ms%n", t2-t1);
        System.out.println("divSum = " + divSum[14288]);
        for (int i = 2; i <= LIM; i++) {
            if(used[i] == 0){
                used[i] = i;
                rec(i);
            }
        }
        int max = 0;
        int ans = LIM+1;
        for (int i = 0; i <= LIM; i++) {
            if(cycles[i] != null){
                if(cycles[i].size() > max){
                    max = cycles[i].size();
                    ans = LIM+1;
                    for (Integer num : cycles[i]) {
                        ans = Math.min(num, ans);
                    }
                }
            }
        }
        System.out.println("max = " + max);
        System.out.println("ans = " + ans);

        System.out.println();
    }
}
