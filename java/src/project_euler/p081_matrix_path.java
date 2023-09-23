package project_euler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class p081_matrix_path {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/home/vadim/dev/tmp/p081_matrix.txt"));
        String line = reader.readLine();
        int[][] matrix = new int[80][80];
        int row = 0;
        while(line != null){
            StringTokenizer stok = new StringTokenizer(line, ",");
            int col = 0;
            while(stok.hasMoreTokens()){
                int d = Integer.parseInt(stok.nextToken());
                matrix[row][col++] = d;
            }
            line = reader.readLine();
            row++;
        }
        long [][] dp = new long[80][80];
        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < 80; j++) {
                dp[i][j] = Long.MAX_VALUE;
            }
        }
        dp[0][0] = matrix[0][0];
        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < 80; j++) {
                if(j > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + matrix[i][j]);
                }
                if(i > 0){
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + matrix[i][j]);
                }
            }
        }
        System.out.println("dp[79][79] = " + dp[79][79]);


    }
}
