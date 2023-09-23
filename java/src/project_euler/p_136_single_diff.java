package project_euler;

public class p_136_single_diff {
    public static void main(String[] args) {
        int ans = 0;
        int MAX = 50_000_000;
        /*
        int [] test = new int[MAX +1];
        for (int num = 1; num <= MAX; num++) {
            int cnt = 0;
            int div = 1;
            for (; div * div <= num; div++) {
                if (num % div == 0 && (div + num / div) % 4 == 0) {
                    int d = (div + num / div) / 4;
                    if (div > d)
                        cnt++;
                    if (num/div != div && num / div > d)
                        cnt++;
                }
            }
            test[num] = cnt;
        }
        */
        int[] cnt = new int[MAX +1];
        long y = 2;
        for ( ;y*y <= MAX ; y++) {
            for (int d = (int)y/4+1; d < y && 4*d-y > 0 && y*(4*d-y) <= MAX; d++) {

                cnt[(int)(y*(4*d-y))]++;
            }
        }
        int sqN = 0;
        for (;sqN*sqN <= MAX ; sqN++);
//        System.out.println("sqN = " + sqN);
        int maxY = (int)y;

        for (long d = 1; 4*d <=MAX || (4*d-MAX)*(4*d-MAX) <= MAX; d++) {
//            System.out.println("d = " + d);
//            System.out.println("Math.max(maxY, 4*d-sqN) = " + Math.max(maxY, 4 * d - sqN));
//            System.out.println("(y < 4*d && y <= MAX && y*(4*d-y) <= MAX) = " + (y < 4 * d && y <= MAX && y * (4 * d - y) <= MAX));
            long lim1 = d;
            long lim2 = d;
            if(4*d*d > MAX){
                lim1 = 2*d - (long)(Math.sqrt(4*d*d-MAX)) + 3;
                lim2 = 2*d + (long)(Math.sqrt(4*d*d-MAX));
            }

            for (y = Math.max(maxY, 4*d-sqN); y <=lim1 && y < 4*d && y <= MAX; y++) {
                if(y*(4*d-y) > MAX)
                    continue;
                cnt[(int)(y*(4*d-y))]++;
            }
            for (y = Math.max(lim2, Math.max(maxY, 4*d-sqN)); y < 4*d && y <= MAX; y++) {
                if(y*(4*d-y) > MAX)
                    continue;
                cnt[(int)(y*(4*d-y))]++;
            }
        }
        /*
        for (int i = 0; i < MAX; i++) {
            if(cnt[i] == test[i])continue;
            System.out.print("i = " + i);
            System.out.print(", cnt[i] = " + cnt[i]);
            System.out.print(", test[i] = " + test[i]);
            System.out.println(", (cnt[i] == test[i]) = " + (cnt[i] == test[i]));
        }*/

        for (int i = 1; i < MAX; i++) {
            if(cnt[i] == 1)
                ans++;
        }
        System.out.println("ans = " + ans);

    }
}
