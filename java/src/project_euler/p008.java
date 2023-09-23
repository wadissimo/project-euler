package project_euler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p008 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(p008.class.getResourceAsStream("project_euler/p8.txt")));
        StringBuilder sb = new StringBuilder();
        String str = reader.readLine();
        while(str != null) {
            sb.append(str);
            str = reader.readLine();
        }
        str = sb.toString();
        int N = 13;

        long max = 0;
        for (int i = 0; i < str.length()-N; i++) {
            long num = 1;
            for (int j = 0; j < N; j++) {
                num *= str.charAt(i+j)-'0';
            }
            max = Math.max(max, num);
        }
        System.out.println("max = " + max);

    }
}
