package project_euler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class p109_darts {
    public static void main(String[] args) {
        Map<String,Integer> points = new HashMap<>();
        Map<String,Integer> doubles = new HashMap<>();

        for (int i = 1; i <= 20; i++) {
            points.put("S"+i, i);
            points.put("D"+i, i*2);
            doubles.put("D"+i, i*2);
            points.put("T"+i, i*3);
        }
        points.put("C", 25);
        points.put("B", 50);
        doubles.put("B", 50);
        int LIM = 200;
        int[] ans = new int[LIM+1];


        List<String>[] options = new List[LIM+1];
        for (int i = 0; i <= LIM   ; i++) {
            options[i] = new LinkedList<>();
        }
        for (Map.Entry<String, Integer> dbEntry : doubles.entrySet()) {
            int db = dbEntry.getValue();
            ans[db]++;
            options[db].add(dbEntry.getKey());
            for (Map.Entry<String, Integer> v1Entry : points.entrySet()) {
                int v1 = v1Entry.getValue();
                ans[db+v1]++;
                options[db+v1].add(dbEntry.getKey() + " " + v1Entry.getKey());
                for (Map.Entry<String, Integer> v2Entry : points.entrySet()) {
                    int v2 = v2Entry.getValue();
                    if(v1Entry.getKey().compareTo(v2Entry.getKey()) >= 0) {
                        ans[db + v1 + v2]++;
                        options[db+v1+v2].add(dbEntry.getKey() + " " + v1Entry.getKey() + " " + v2Entry.getKey());
                    }
                }
            }
        }
        System.out.println("options[6] = " + options[6]);
        System.out.println("ans[6] = " + ans[6]);
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += ans[i];
        }
        System.out.println("sum = " + sum);
    }
}
