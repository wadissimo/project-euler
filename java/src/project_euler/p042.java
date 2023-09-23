package project_euler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class p042 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(p042.class.getResourceAsStream("project_euler/p042_words.txt")));
        HashSet<Integer> triangles = new HashSet<>();
        int MAX = 1000;
        for (int i = 1; i < MAX; i++) {
            triangles.add(i*(i+1)/2);
        }
        int ans = 0;
        String line = reader.readLine();
        while(line != null){
            StringTokenizer stok = new StringTokenizer(line, ",");
            while(stok.hasMoreTokens()){
                String s = stok.nextToken();
                int val = 0;
                for (int i = 1; i < s.length() - 1; i++) {
                    val += (s.charAt(i) - 'A'+1);
                }
                if(triangles.contains(val))
                    ans++;
            }

            line = reader.readLine();
        }
        System.out.println("ans = " + ans);
    }
}
