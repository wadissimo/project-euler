package project_euler;

public class p086_cuboid {
    static boolean check(int a, int b, int c) {
        long res = Math.min(Math.min(getPath(a, b, c), getPath(b, a, c)), getPath(c, a, b));
        long sqrt = (long)Math.sqrt(res);
        return sqrt * sqrt == res;
    }
    static long getPath(int a, int b, int c){
        return (long)a*a + (long)(b+c)*(long)(b+c);
    }
    public static void main(String[] args) {
        int LIM = 2000;
        int nSol = 0;
        for (int m = 1; m <= LIM; m++) {
            for (int b = 1; b <= m; b++) {
                for (int c = 1; c <= b; c++) {
                    if(check(m, b, c))
                        nSol++;
                }
            }
            if(nSol > 1_000_000){
                System.out.println("m = " + m);
                break;
            }
        }
        System.out.println("nSol = " + nSol);
    }
}
