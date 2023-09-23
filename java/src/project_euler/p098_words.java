package project_euler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class p098_words {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/home/vadim/dev/tmp/p098_words.txt"));
        String line = reader.readLine();
        List<String> words = new LinkedList<>();
        while(line != null){
            StringTokenizer tok = new StringTokenizer(line, ",");
            while(tok.hasMoreTokens()){
                String t = tok.nextToken();
                if(t.startsWith("\"")){
                    words.add(t.substring(1, t.length()-1));
                } else {
                    words.add(t);
                }
            }
            line = reader.readLine();
        }
        int maxLen = 0;
        HashMap<String, Set<String>> map = new HashMap<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            Set<String> list = map.computeIfAbsent(sorted, k -> new HashSet<>());
            list.add(word);
            maxLen = Math.max(maxLen, word.length());
        }
        List<String> toDelete = new LinkedList<>();
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            if(entry.getValue().size() < 2)
                toDelete.add(entry.getKey());
        }
        for (String s : toDelete) {
            map.remove(s);
        }
        System.out.println("map = " + map);
        System.out.println("map.size() = " + map.size());
        TreeSet<Long> squareNumbers = new TreeSet<>();
        List<String>[] sqStrings = new List[20];
        for (int i = 0; i < sqStrings.length; i++) {
            sqStrings[i] = new LinkedList<>();
        }
        int LIM = 1_000_000_000;
        long ans = 0;
        for (long i = 1; i*i < LIM; i++) {
            squareNumbers.add(i*i);
            String s = Long.toString(i * i);
            sqStrings[s.length()].add(s);
        }

        for (long i = 1; i*i < LIM; i++) {
            long num = i * i;
            String sNum = Long.toString(num);

            for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
                if (sNum.length() == entry.getKey().length()) {
                    //try to match a word
                    for (String word : entry.getValue()) {
                        char[] charMap = new char[26];
                        char[] digMap = new char[10];
                        boolean correct = true;
                        for (int j = 0; j < word.length(); j++) {
                            int d = sNum.charAt(j) - '0';
                            if (digMap[d] != 0) {
                                if (digMap[d] != word.charAt(j)) {
                                    correct = false;
                                    break;
                                }
                            } else {
                                digMap[d] = word.charAt(j);
                            }
                            if (charMap[word.charAt(j) - 'A'] == 0) {
                                charMap[word.charAt(j) - 'A'] = sNum.charAt(j);
                            } else {
                                if (charMap[word.charAt(j) - 'A'] != sNum.charAt(j)) {
                                    correct = false;
                                    break;
                                }
                            }
                        }
                        if (correct) {
                            //the word is matched!
                            System.out.println("word = " + word);
                            System.out.println("sNum = " + sNum);
                            for (String anagram : entry.getValue()) {
                                if (!anagram.equals(word)) {
                                    //remap
                                    if (charMap[anagram.charAt(0) - 'A'] != '0') { // No leading zeros
                                        long result = 0;
                                        long p = 1;
                                        for (int j = anagram.length() - 1; j >= 0; j--) {
                                            int d = charMap[anagram.charAt(j) - 'A'] - '0';
                                            result += p * d;
                                            p *= 10;
                                        }
                                        System.out.println("anagram = " + anagram);
                                        System.out.println("result = " + result);
                                        if (squareNumbers.contains(result)) {
                                            System.out.println("Found square number!");


                                            ans = Math.max(ans, result);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("ans = " + ans);

    }
}
