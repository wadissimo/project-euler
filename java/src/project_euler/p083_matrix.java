package project_euler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p083_matrix {
    static class Cell{
        int row, col;
        long val;

        public Cell(int row, int col, long val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
    public static void main(String[] args) throws IOException {
        int N = 80;
        BufferedReader reader = new BufferedReader(new FileReader("/home/vadim/dev/tmp/p083_matrix.txt"));
        String line = reader.readLine();

        int[][] matrix = new int[N][N];
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

        /*
        int[][] matrix = new int[][]{
                {131,673,234,103,18},
                {201,96,342,965,150},
                {630,803,746,422,111},
                {537,699,497,121,956},
                {805,732,524,37,331}
        };*/
        long[][] dp = new long[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = Long.MAX_VALUE;
            }
        }
        PriorityQueue<Cell> pq = new PriorityQueue<>(Comparator.comparingLong(cell -> cell.val));
        boolean[][] used = new boolean[N][N];
        dp[0][0] = matrix[0][0];
        pq.add(new Cell(0,0, matrix[0][0]));
        while(!pq.isEmpty()){
            Cell cell = pq.poll();
            if(used[cell.row][cell.col])
                continue;
            used[cell.row][cell.col] = true;
            if(cell.row > 0){
                long newVal = matrix[cell.row - 1][cell.col] + dp[cell.row][cell.col];
                if(newVal < dp[cell.row-1][cell.col]) {
                    dp[cell.row-1][cell.col] = newVal;
                    pq.add(new Cell(cell.row-1, cell.col, newVal));
                }
            }
            if(cell.col > 0){
                long newVal = matrix[cell.row][cell.col-1] + dp[cell.row][cell.col];
                if(newVal < dp[cell.row][cell.col-1]) {
                    dp[cell.row][cell.col-1] = newVal;
                    pq.add(new Cell(cell.row, cell.col-1, newVal));
                }
            }
            if(cell.row < N-1){
                long newVal = matrix[cell.row + 1][cell.col] + dp[cell.row][cell.col];
                if(newVal < dp[cell.row+1][cell.col]) {
                    dp[cell.row+1][cell.col] = newVal;
                    pq.add(new Cell(cell.row+1, cell.col, newVal));
                }
            }
            if(cell.col < N-1){
                long newVal = matrix[cell.row][cell.col+1] + dp[cell.row][cell.col];
                if(newVal < dp[cell.row][cell.col+1]) {
                    dp[cell.row][cell.col+1] = newVal;
                    pq.add(new Cell(cell.row, cell.col+1, newVal));
                }
            }
        }

        System.out.println("dp[N-1][N-1] = " + dp[N - 1][N - 1]);
    }
}
