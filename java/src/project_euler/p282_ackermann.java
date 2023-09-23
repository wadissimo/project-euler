package project_euler;

public class p282_ackermann {
    static long a(long m, long n){
        System.out.println("m = " + m);
        System.out.println("n = " + n);
        if(m==0)
            return n+1;
        if(n==0 && m>0)
            return a(m-1, 1);
        if(m>0 && n>0)
            return a(m-1, a(m, n-1));
        throw new RuntimeException("hm..");
    }
    public static void main(String[] args) {
        long sum = 0;
        System.out.println("a(1,0) = " + a(4, 4));
        /*for (int i = 0; i <=6; i++) {
            long res = a(i,i);
            System.out.println("res = " + res);
            sum+=res;
        }*/
        System.out.println("sum = " + sum);

    }
}
