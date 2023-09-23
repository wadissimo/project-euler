package project_euler;

import java.io.*;
import java.util.*;

public class p79_passcode {
    static int[] getDigs(int num){
        ArrayList<Integer> list = new ArrayList<>();
        while(num > 0){
            int d = num % 10;
            list.add(d);
            num /= 10;
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[list.size()-1-i] = list.get(i);
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/home/vadim/dev/tmp/p079_keylog.txt"));
        String line = reader.readLine();
        List<int[]> list = new LinkedList<>();
        int[][] adj = new int[10][10];
        Set<Integer> set = new HashSet<>(10);
        while(line != null){
            int[] digs = new int[3];
            for (int i = 0; i < 3; i++) {
                digs[i] = line.charAt(i) - '0';
                set.add(digs[i]);
            }
            list.add(digs);
            adj[digs[0]][digs[1]] = 1;
            adj[digs[1]][digs[2]] = 1;
            line = reader.readLine();
        }
        while(!set.isEmpty()) {
            Integer nextNum = null;
            for (Iterator<Integer> iterator = set.iterator(); iterator.hasNext(); ) {
                Integer num = iterator.next();
                boolean found = true;
                for (int j = 0; j < 10; j++) {
                    if (adj[j][num] == 1) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    nextNum = num;
                    System.out.print(num);
                    iterator.remove();
                    break;
                }
            }
            if(nextNum == null){
                for (Integer n : set) {
                    System.out.println(" "+n);
                }
                System.out.println();
                System.out.println("not found");
                return;
            } else {
                for (int i = 0; i < 10; i++) {
                    adj[nextNum][i] = 0;
                }
            }

        }

    }
}
