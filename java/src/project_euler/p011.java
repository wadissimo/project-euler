package project_euler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p011 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(p008.class.getResourceAsStream("project_euler/p11.txt")));
        int[][] grid = new int[20][20];
        for (int i = 0; i < 20; i++) {
            String s = reader.readLine();
            StringTokenizer stok = new StringTokenizer(s);
            for (int j = 0; j < 20; j++) {
                grid[i][j] = Integer.parseInt(stok.nextToken());
            }
        }
        long max = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                long right = 1, down = 1, diagleft = 1, diagright = 1;
                for (int k = 0; k < 4; k++) {
                    if(j+k < 20)
                        right *= grid[i][j+k];
                    if(i+k < 20)
                        down *= grid[i+k][j];
                    if(i+k < 20 && j+k < 20)
                        diagright *= grid[i+k][j+k];
                    if(i+k < 20 && j-k >= 0)
                        diagleft *= grid[i+k][j-k];
                }
                max = Math.max(max, right);
                max = Math.max(max, down);
                max = Math.max(max, diagleft);
                max = Math.max(max, diagright);
            }
        }
        System.out.println("max = " + max);
    }
}
