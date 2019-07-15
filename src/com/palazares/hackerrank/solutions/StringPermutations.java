package com.palazares.hackerrank.solutions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StringPermutations {

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        int sum = 0;
        Map<String, Map<Character, Integer>> dictionary = new HashMap<>();
        for (int wrdLen = 1; wrdLen < s.length(); wrdLen++){
            for (int startIndex = 0; startIndex <= s.length() - wrdLen; startIndex++){
                String word = s.substring(startIndex, startIndex + wrdLen);
                Map<Character, Integer> footprint = dictionary.computeIfAbsent(word, k -> getWordFootPrint(k));
                for(int j = startIndex + 1; j <= s.length() - wrdLen; j++){
                    String compareWord = s.substring(j, j + wrdLen);
                    Map<Character, Integer> footprintToCompare = dictionary.computeIfAbsent(compareWord, k -> getWordFootPrint(k));
                    if(compareFootprints(footprint, footprintToCompare)){
                        sum++;
                    }
                }
            }
        }

        return sum;
    }

    static boolean compareFootprints(Map<Character, Integer> first, Map<Character, Integer> second) {
        if(first.size() != second.size()){
            return false;
        }
        for (Map.Entry<Character, Integer> e : first.entrySet()){
            if(!second.containsKey(e.getKey())){
                return false;
            }
            if(!second.get(e.getKey()).equals(e.getValue())){
                return false;
            }
        }
        return true;
    }

    static Map<Character, Integer> getWordFootPrint(String s) {
        Map<Character, Integer> footprint = new HashMap<>();
        for (Character c : s.toCharArray()) {
            footprint.merge(c, 1, Integer::sum);
        }
        return footprint;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\resources\\stringPermutations\\1_act.txt"));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);


            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

