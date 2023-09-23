package project_euler;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class p088_product_sum {
    static List<Integer> getDivs(int num){
        List<Integer> res = new LinkedList<>();
        res.add(num);
        for (int div = 2; div*div <= num; div++) {
            if(num % div == 0){
                res.add(div);
                if(div*div < num)
                    res.add(num / div);
            }
        }
        return res;
    }
    static boolean rec(int num, Deque<Integer> seq, int max){
        boolean res = false;
        for (Integer div : divs[num]) {
            if(div > max)
                continue;
            seq.addLast(div);
            if(div == num){
                int sum = 0;
                int prod = 1;
                for (Integer d : seq) {
                    sum += d;
                    prod*=d;
                }
                //System.out.println("sum = " + sum);
                if(sum <= prod){
                    int k = prod - sum + seq.size();
                    if(k <= K && !used[k]) {
                        res = true;
                        used[k] = true;
                    }
                    //System.out.println("k = " + k);
                }
                //System.out.println("seq = " + seq);
            } else {
                res = res|rec(num /div, seq, div);
            }
            seq.removeLast();
        }
        return res;
    }
    static int K = 12_000;
    static int ans = 0;
    static boolean[]used = new boolean[K+1];
    static List<Integer>[] divs;
    public static void main(String[] args) {
        int LIM = 20000;

        divs = new List[LIM+1];

        for (int i = 2; i <= LIM; i++) {
            divs[i] = getDivs(i);
        }
        for (int i = 4; i <= LIM; i++) {
            if(rec(i, new LinkedList<>(), i)){
                ans += i;
                System.out.println("i = " + i);
            }
        }
        for (int i = 2; i <= K; i++) {
            if(!used[i])
                throw new RuntimeException("unused");
        }
        System.out.println("ans = " + ans);



    }
}
