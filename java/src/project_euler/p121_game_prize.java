package project_euler;

public class p121_game_prize {
    public static void main(String[] args) {
        long f16 = 1;
        for (int i = 2; i <=16; i++) {
            f16*=i;
        }
        long top = 0;
        for (int mask = 0; mask < 1<<15; mask++) {
            if(Integer.bitCount(mask) < 8){

                long mult = 1;
                for (int i = 0; i < 15; i++) {
                    if((mask&(1<<i)) > 0){
                        mult *= i+1;
                    }
                }
                System.out.println("Integer.toBinaryString(mask) = " + Integer.toBinaryString(mask));
                System.out.println("mult = " + mult);
                top+=mult;
            }
        }
        System.out.println("top = " + top);
        System.out.println("f16 = " + f16);
        long ans = f16/top;
        System.out.println("ans = " + ans);

    }
}
