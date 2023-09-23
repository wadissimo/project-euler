package project_euler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class p022_names {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(p022_names.class.getResourceAsStream("project_euler/p022_names.txt")));
        ArrayList<String> s = new ArrayList<>();
        StringTokenizer tok = new StringTokenizer(reader.readLine(), ",");
        while(tok.hasMoreTokens()){
            String str = tok.nextToken();
            s.add(str.substring(1, str.length()-1));
        }
        Collections.sort(s);
        BigInteger ans = BigInteger.ZERO;
        for (int i = 0; i < s.size(); i++) {
            long score = 0;
            String name = s.get(i);
            for (int j = 0; j < name.length(); j++) {
                score += 1+ (name.charAt(j)-'A');
            }
            ans = ans.add(BigInteger.valueOf(score).multiply(BigInteger.valueOf(i+1)));
        }
        System.out.println("ans = " + ans);
    }
}
