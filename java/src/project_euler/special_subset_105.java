package project_euler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class special_subset_105 {
    private static boolean check(int[] a, int len){
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int mask = 1; mask < (1<<len); mask++) {
            int sum = 0;
            int bits = 0;
            for (int bit = 0; bit < len; bit++) {
                if((mask&(1<<bit)) > 0){
                    sum+=a[bit];
                    bits++;
                }
            }
            if(map.put(sum, bits)!=null){
                return false;
            }
        }
        int prev = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() < prev)
                return false;
            prev = entry.getValue();
        }
        return true;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(special_subset_105.class.getResourceAsStream("project_euler/p105_sets.txt")));

        String str = reader.readLine();
        int[] a = new int[20];
        long sum = 0;
        while(str != null){
            StringTokenizer tok = new StringTokenizer(str, ",");
            int len = 0;
            int curSum = 0;
            while(tok.hasMoreTokens()){
                a[len++] = Integer.parseInt(tok.nextToken());
                curSum += a[len-1];
            }


            if(check(a, len)){
                sum += curSum;
            }

            str = reader.readLine();
        }
        System.out.println("sum = " + sum);
    }
}
