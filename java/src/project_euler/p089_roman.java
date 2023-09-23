package project_euler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class p089_roman {

    static int getNum(String roman){
        int N = roman.length();
        int[] map = new int[N];
        for (int i = 0; i < N; i++) {
            char c = roman.charAt(i);
            if(c == 'M')
                map[i] = 1000;
            else if(c == 'D')
                map[i] = 500;
            else if(c == 'C')
                map[i] = 100;
            else if(c == 'L')
                map[i] = 50;
            else if(c == 'X')
                map[i] = 10;
            else if(c == 'V')
                map[i] = 5;
            else if (c == 'I')
                map[i] = 1;
            else throw new RuntimeException("Wrong literal");
        }
        int res = 0;
        for (int i = N-1; i >= 0 ; i--) {
            if(i < N-1 && map[i] < map[i+1]){
                res -= map[i];
            } else {
                res += map[i];
            }
        }
        return res;
    }
    static String getRoman(int num){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < num / 1000; i++) {
            sb.append('M');
        }
        num %= 1000;

        if(num/100 == 9){
            sb.append("CM");
        } else if(num/100 == 4){
            sb.append("CD");
        } else{
            if(num >= 500){
                sb.append('D');
                num -= 500;
            }
            for (int i = 0; i < num/100; i++) {
                sb.append('C');
            }
        }
        num %= 100;

        if(num/10 == 9){
            sb.append("XC");
        } else if(num/10 == 4){
            sb.append("XL");
        } else{
            if(num >= 50){
                sb.append('L');
                num -= 50;
            }
            for (int i = 0; i < num/10; i++) {
                sb.append('X');
            }
        }
        num %= 10;

        if(num == 9){
            sb.append("IX");
        } else if(num == 4){
            sb.append("IV");
        } else{
            if(num >= 5){
                sb.append('V');
                num -= 5;
            }
            for (int i = 0; i < num; i++) {
                sb.append('I');
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/home/vadim/dev/tmp/p089_roman.txt"));
        String line = reader.readLine();
        int ans = 0;
        while(line != null){
            String compressed = getRoman(getNum(line));
            System.out.printf("%s -> %s . Saved: %d%n", line, compressed, line.length() - compressed.length());
            ans += line.length() - compressed.length();
            line = reader.readLine();
        }
        System.out.println("ans = " + ans);
    }
}
