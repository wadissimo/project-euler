package project_euler;

public class p085_rect {
    public static void main(String[] args) {
        int N = 1000;
        long best  = 1_000_000;
        long target = 8_000_000;
        int bestI = 0;
        int bestJ = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                long res = (1+i)*i*j*(1+j);
                if(Math.abs(target-res) < best) {
                    best = Math.abs(target-res);
                    bestI = i;
                    bestJ = j;
                }
            }
        }
        System.out.println("best = " + best);
        System.out.println("bestI = " + bestI);
        System.out.println("bestJ = " + bestJ);
        System.out.println(bestI*bestJ);
    }
}
