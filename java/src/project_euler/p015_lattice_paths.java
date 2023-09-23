package project_euler;

import common.IntegerUtils;

public class p015_lattice_paths {
    public static void main(String[] args) {
        long[][] C = IntegerUtils.pascal_triangle(40);
        System.out.println("C[40][20] = " + C[40][20]);
    }
}
