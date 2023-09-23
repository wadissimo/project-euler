package project_euler;

import common.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class p084_monopoly_our {
    static class Cell{
        String name;
        int idx;
        double p;

        public Cell(String name, int idx, double p) {
            this.name = name;
            this.idx = idx;
            this.p = p;
        }
    }
    public static void main(String[] args) {
        String[] field = new String[]{"GO", "A1", "CC1", "A2", "T1", "R1", "B1", "CH1", "B2", "B3",
                "JAIL", "C1", "U1", "C2", "C3", "R2", "D1", "CC2", "D2", "D3",
                "FP", "E1", "CH2", "E2", "E3", "R3", "F1", "F2", "U2", "F3",
                "G2J", "G1", "G2", "CC3", "G3", "R4", "CH3", "H1", "T2", "H2"
        };
        int N_ITERATIONS = 10000;
        int N = field.length;
        int[] stats = new int[N];
        Random rand = new Random(42);
        int N_GAMES = 1000;
        for (int g = 0; g < N_GAMES; g++) {
            int cur = 0;
            int nDouble = 0;
            int[] chCards= new int[16];
            int[] ccCards = new int[16];
            for (int i = 0; i < 16; i++) {
                chCards[i] = ccCards[i] = i;
            }
            ArrayUtils.randomShuffle(chCards);
            ArrayUtils.randomShuffle(ccCards);
            int chIdx = 0;
            int ccIdx = 0;
            for (int i = 0; i < N_ITERATIONS; i++) {
                int c1 = rand.nextInt(6) + 1;
                int c2 = rand.nextInt(6) + 1;
                //int c1 = rand.nextInt(4) + 1;
                //int c2 = rand.nextInt(4) + 1;
                //System.out.println("c1 = " + c1);
                //System.out.println("c2 = " + c2);
                if (c1 == c2)
                    nDouble++;
                else nDouble = 0;
                int toMove = c1 + c2;
                //System.out.println("toMove = " + toMove);
                cur = (cur + toMove) % N;
                if (nDouble == 3) {
                    nDouble = 0;
                    cur = 10; // Jail
                } else if (field[cur].startsWith("CC")) {
                    int choice = ccCards[ccIdx];
                    ccIdx = (ccIdx+1)%16;
                    //System.out.println("CCchoice = " + choice);
                    if (choice == 0) {
                        cur = 0;
                    } else if (choice == 1) {
                        cur = 10; // jail
                    } else if (choice == 2) {
                        cur = 1;
                    }
                } else if (field[cur].startsWith("CH")) {
                    int choice = chCards[chIdx];
                    //System.out.println("CHchoice = " + choice);
                    chIdx = (chIdx+1)%16;
                    if (choice == 0)
                        cur = 0;
                    else if (choice == 1)
                        cur = 10;
                    else if (choice == 2)
                        cur = 11;//C1 -cristo redento
                    else if (choice == 3)
                        cur = 24; //E3 Petra
                    else if (choice == 4)
                        cur = 39; // H2 -pyramiden
                    else if (choice == 5)
                        cur = 15;//R1 - charles de gaulle
                    else if (choice == 9) {
                        cur = (cur + N - 3) % N;
                    }
                } else if (cur == 30)
                    cur = 10;
                //System.out.println("cur = " + cur);
                //System.out.println("field[cur] = " + field[cur]);
                stats[cur]++;
            }
        }
        p084_monopoly.Cell[] res = new p084_monopoly.Cell[N];
        for (int i = 0; i < N; i++) {
            res[i] = new p084_monopoly.Cell(field[i],i, -(double)stats[i]/N_ITERATIONS/N_GAMES);
        }
        Arrays.sort(res, Comparator.comparingDouble(cell -> cell.p));
        for (int i = 0; i < res.length; i++) {
            System.out.printf("%s (%d) %f%n", res[i].name,res[i].idx, -res[i].p);
        }

    }
}
