package project_euler;

import common.IntegerUtils;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class p093_arithmetic_expr {
    static double op(double a, double b, char op){
        if(op == '+')
            return a+b;
        if(op == '-')
            return a-b;
        if(op == '*')
            return a*b;
        if(op == '/')
            return a/b;
        throw new RuntimeException("unknown operation");
    }
    static double calc1(int a, int b, int c, int d, char[] ops){
        return op(a, op(op(b,c, ops[1]), d, ops[2]), ops[0]);
    }
    static double calc2(int a, int b, int c, int d, char[] ops){
        return op(op(a, op(b,c, ops[1]), ops[0]), d, ops[2]);
    }
    static double calc3(int a, int b, int c, int d, char[] ops){
        return op(a, op(b, op(c,d, ops[2]), ops[1]), ops[0]);
    }
    static double calc4(int a, int b, int c, int d, char[] ops) {
        return op(op(op(a,b, ops[0]), c, ops[1]), d, ops[2]);
    }
    static double calc5(int a, int b, int c, int d, char[] ops){
        return op(op(a,b, ops[0]), op(c,d, ops[1]), ops[2]);
    }
    static List<char[]> operations = new LinkedList<>();
    static void genOps(){
        char[] ops = new char[]{'+', '-', '*', '/'};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    operations.add(new char[]{ops[i], ops[j], ops[k]});
                }
            }
        }
    }
    static List<int[]> perms = new LinkedList<>();
    static void genPerms(int mask, int n, Deque<Integer> seq) {
        if(mask+1 == 1<<n){
            int[] perm = new int[seq.size()];
            int pi = 0;
            for (Integer num : seq) {
                perm[pi++] = num;
            }
            perms.add(perm);
        }
        for (int i = 0; i < n; i++) {
            if((mask&(1<<i)) == 0) {
                seq.addLast(i);
                genPerms(mask|(1<<i), n, seq);
                seq.removeLast();
            }
        }
    }
    static boolean checkInteger(double d){
        if(d < -0.001)
            return false;
        long r = Math.round(d);
        return (Math.abs(d - (double)r) < 0.0001);
    }
    public static void main(String[] args) {
        genPerms(0, 4, new LinkedList<>());
        genOps();


        int LIM = 20;
        int max = 0;
        int[] ans = null;
        for (int i = 1; i < LIM; i++) {
            for (int j = i+1; j < LIM; j++) {
                for (int k = j+1; k < LIM; k++) {
                    for (int l = k+1; l < LIM; l++) {
                        int[] nums = new int[]{i,j,k,l};
                        boolean[] done = new boolean[100000];
                        for (int[] perm : perms) {
                            for (char[] ops : operations) {
                                double v1 = calc1(nums[perm[0]], nums[perm[1]], nums[perm[2]], nums[perm[3]], ops);
                                if(checkInteger(v1)) done[(int)Math.round(v1)] = true;
                                double v2 = calc2(nums[perm[0]], nums[perm[1]], nums[perm[2]], nums[perm[3]], ops);
                                if(checkInteger(v2)) done[(int)Math.round(v2)] = true;
                                double v3 = calc3(nums[perm[0]], nums[perm[1]], nums[perm[2]], nums[perm[3]], ops);
                                if(checkInteger(v3)) done[(int)Math.round(v3)] = true;
                                double v4 = calc4(nums[perm[0]], nums[perm[1]], nums[perm[2]], nums[perm[3]], ops);
                                if(checkInteger(v4)) done[(int)Math.round(v4)] = true;
                                double v5 = calc5(nums[perm[0]], nums[perm[1]], nums[perm[2]], nums[perm[3]], ops);
                                if(checkInteger(v5)) done[(int)Math.round(v5)] = true;
                            }
                        }
                        for (int m = 1; m < done.length; m++) {
                            if(!done[m]){
                                if(m > max){
                                    max = m;
                                    ans = nums;
                                }
                                System.out.printf("%s - %d%n", Arrays.toString(nums), m-1);
                                break;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Answer: ");
        System.out.printf("%s - %d%n", Arrays.toString(ans), max);





    }
}
