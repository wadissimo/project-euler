package project_euler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P18_67_max_path_sum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(P18_67_max_path_sum.class.getResourceAsStream("project_euler/p067_triangle.txt")));
        String s = reader.readLine();
        LinkedList<ArrayList<Integer>> p = new LinkedList<>();
        while(s != null){
            ArrayList<Integer> layer = new ArrayList<>();
            for (StringTokenizer stringTokenizer = new StringTokenizer(s); stringTokenizer.hasMoreTokens(); ) {
                layer.add(Integer.parseInt(stringTokenizer.nextToken()));
            }
            p.add(layer);
            s = reader.readLine();
        }
        int max = p.getLast().size();
        long[] prevDp = new long[max];
        long[] curDp = new long[max];
        curDp[0] = p.getFirst().get(0);
        for(ArrayList<Integer> layer : p){
            for (int i = 0; i < layer.size(); i++) {
                int val = layer.get(i);
                if(i!=layer.size()-1)
                    curDp[i] = Math.max(curDp[i], val + prevDp[i]);
                if(i != 0)
                    curDp[i] = Math.max(curDp[i], val + prevDp[i-1]);
            }
            long[] tmp = prevDp; prevDp = curDp; curDp = tmp;
            Arrays.fill(curDp, 0);
        }
        long ans = 0;
        for (int i = 0; i < max; i++) {
            ans = Math.max(ans, prevDp[i]);
        }
        System.out.println("ans = " + ans);
    }
}
