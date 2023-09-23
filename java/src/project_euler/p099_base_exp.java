package project_euler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p099_base_exp {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/home/vadim/dev/tmp/p099_base_exp.txt"));
        String line ;
        int idx = 0;
        int ans = 0;
        double best = 0;
        while((line=reader.readLine()) != null){
            idx ++;
            StringTokenizer stok = new StringTokenizer(line, ",");
            long base = Long.parseLong(stok.nextToken());
            long exp = Long.parseLong(stok.nextToken());
            double log = exp * Math.log(base);
            if(log > best){
                best = log;
                ans = idx;
            }
        }
        System.out.println("ans = " + ans);

    }
}
