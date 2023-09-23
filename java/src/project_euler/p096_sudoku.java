package project_euler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class p096_sudoku {
    static void printGrid(int[][] grid){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + " ");
                if(j%3 == 2)
                    System.out.print("| ");
            }
            System.out.println();
            if(i%3 == 2){
                System.out.println("---------------------");
            }

        }
    }
    static class Cell {
        Set<Integer> possible;

        public Cell() {
            this.possible = new HashSet<>();
        }
    }
    static Set<Integer> calcPossible(int row, int col, int[][] grid){
        Set<Integer> notPossible = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if(grid[row][i] != 0){
                notPossible.add(grid[row][i]);
            }
        }
        for (int i = 0; i < 9; i++) {
            if(grid[i][col] != 0){
                notPossible.add(grid[i][col]);
            }
        }
        int sqRow = (row/3)*3;
        int sqCol = (col/3)*3;
        for (int i = sqRow; i < sqRow+3 ; i++) {
            for (int j = sqCol; j < sqCol + 3; j++) {
                if(grid[i][j] != 0){
                    notPossible.add(grid[i][j]);
                }
            }
        }
        Set<Integer> possible = new HashSet<>();
        for (int i = 1; i <=9 ; i++) {
            if(!notPossible.contains(i)){
                possible.add(i);
            }
        }
        return possible;

    }
    static boolean checkSolved(int[][] grid){
        boolean solved = true;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if(grid[row][col] == 0){
                    solved = false;
                }
            }
        }
        return solved;
    }

    static int fill(int[][] grid){
        Cell[][] cells = new Cell[9][9];
        boolean update = true;
        int updates = 0;
        while(update) {
            update = false;
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    if (grid[row][col] == 0) {
                        Set<Integer> possible = calcPossible(row, col, grid);
                        cells[row][col] = new Cell();
                        cells[row][col].possible = possible;
                        if(possible.size() == 0){
                            return -1;
                        }
                        if (possible.size() == 1) {
                            grid[row][col] = possible.iterator().next();
                            updates++;
                            update = true;
                        }
                    }
                }
            }
            if(!update){

                for (int number = 1; number <= 9; number++) {
                    outerRow:
                    for (int row = 0; row < 9; row++) {
                        int howMany = 0;
                        int colFound = 0;
                        for (int col = 0; col < 9; col++) {
                            if(grid[row][col] == number)
                                continue outerRow;
                            if(grid[row][col] == 0){
                                if(cells[row][col].possible.contains(number)){
                                    howMany++;
                                    colFound = col;
                                }
                            }
                        }
                        if(howMany == 1){
                            grid[row][colFound] = number;
                            update = true;
                            updates++;
                        }
                    }
                    outerCol:
                    for (int col = 0; col < 9; col++) {
                        int howMany = 0;
                        int rowFound = 0;
                        for (int row = 0; row < 9; row++) {
                            if(grid[row][col] == number)
                                continue outerCol;
                            if(grid[row][col] == 0){
                                if(cells[row][col].possible.contains(number)){
                                    howMany++;
                                    rowFound = row;
                                }
                            }
                        }
                        if(howMany == 1){
                            grid[rowFound][col] = number;
                            update = true;
                            updates++;
                        }
                    }
                    outerCell:
                    for (int sqRow = 0; sqRow < 3; sqRow++) {
                        for (int sqCol = 0; sqCol < 3; sqCol++) {
                            int howMany = 0;
                            int rowFound = 0;
                            int colFound = 0;
                            for (int row = sqRow*3; row < sqRow*3 + 3 ; row++) {
                                for (int col = sqCol*3; col < sqCol*3 +3 ; col++) {
                                    if(grid[row][col] == number)
                                        continue outerCell;
                                    if(grid[row][col] == 0){
                                        if(cells[row][col].possible.contains(number)){
                                            howMany++;
                                            rowFound = row;
                                            colFound = col;
                                        }
                                    }
                                }
                            }
                            if(howMany == 1){
                                grid[rowFound][colFound] = number;
                                update = true;
                                updates++;
                            }
                        }
                    }
                }
            }
        }
        return updates;
    }

    static void guess(int[][] grid){
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] == 0) {
                    Set<Integer> possible = calcPossible(row, col, grid);
                    for (Integer number : possible) {
                        int[][] newGrid = copyGrid(grid);
                        newGrid[row][col] = number;
                        int result = fill(newGrid);
                        if(result == -1) {
                            continue;
                        }
                        if(checkSolved(newGrid)){
                            copyIn(newGrid, grid);
                            return;
                        }
                    }
                }
            }
        }




    }
    static void copyIn(int[][] from, int[][] to){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                to[i][j] = from[i][j];
            }
        }
    }
    static int[][] copyGrid(int[][] grid){
        int[][] newGrid = new int[9][9];
        copyIn(grid, newGrid);
        return newGrid;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/home/vadim/dev/tmp/p096_sudoku.txt"));
        String line;
        ArrayList<int[][]> grids = new ArrayList<>();
        while((line=reader.readLine()) != null){
            if(line.startsWith("Grid")){
                int[][] grid = new int[9][9];
                for (int i = 0; i < 9; i++) {
                    String row = reader.readLine();
                    for (int j = 0; j < 9; j++) {
                        grid[i][j] = row.charAt(j)-'0';
                                       }
                }
                grids.add(grid);
            }
        }
        int unsolved = 0;
        int sum = 0;
        for (int gridNumber = 0; gridNumber < grids.size(); gridNumber++) {
            int[][] grid = grids.get(gridNumber);

            //System.out.println("Before:");
            //printGrid(grid);
            //System.out.println("After:");
            fill(grid);
            System.out.println("gridNumber = " + gridNumber);
            if(!checkSolved(grid)){
                guess(grid);
                if(!checkSolved(grid)) {
                    unsolved++;
                    System.out.println("Failed !!!");
                    System.out.println("gridNumber = " + gridNumber);
                    printGrid(grid);
                }
            }
            System.out.println("Solution:");
            printGrid(grid);


            //printGrid(grid);
            int ans = grid[0][0]*100 + grid[0][1]*10 +grid[0][2];
            System.out.println("ans = " + ans);
            sum+= ans;
        }
        System.out.println("sum = " + sum);
        System.out.println("unsolved = " + unsolved);



    }

}
