package com.palazares.hackerrank.solutions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ReverseShuffleMerge {

    static String reverseShuffleMerge(String s) {
        final Map<Character, Integer> stringMap = stringMap(s);
        final Map<Character, Integer> stringMap2Build = stringMap2Build(stringMap);
        Map<Character, Integer> accumulatedCharacters = new HashMap<>();
        StringBuilder result = new StringBuilder();
        final char[] strChars = s.toCharArray();
        for (int j = 0; j < strChars.length; j++) {
            Character c = strChars[j];
            Integer charOccur = accumulatedCharacters.merge(c, 1, Integer::sum);
            if (charOccur > stringMap2Build.get(c)) {
                StringBuilder innerResult = new StringBuilder();
                final Character minChar = Collections.min(accumulatedCharacters.keySet());
                int i = j;
                while (i >= 0 && strChars[i] != minChar) {
                    if (strChars[i] == c) {
                        if (charOccur == 1) {
                            break;
                        }
                        charOccur--;
                    }
                    i--;
                }
                Character firstChar = strChars[i];

                while (i >= 0 && stringMap2Build.getOrDefault(strChars[i], 0) > 0) {
                    if (strChars[i] >= firstChar) {
                        innerResult.append(strChars[i]);
                        stringMap2Build.merge(strChars[i], 1, (prev, one) -> prev - one);
                    }
                    i--;
                }

                result.insert(0, innerResult.toString());
                if (result.length() >= s.length() / 2) {
                    return result.toString();
                }
            }
        }

        return result.toString();
    }

    private static Map<Character, Integer> stringMap(String s) {
        final HashMap<Character, Integer> result = new HashMap<>();
        for (Character c : s.toCharArray()) {
            result.merge(c, 1, Integer::sum);
        }
        return result;
    }

    private static Map<Character, Integer> stringMap2Build(Map<Character, Integer> map) {
        final HashMap<Character, Integer> result = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            result.put(entry.getKey(), entry.getValue() / 2);
        }
        return result;
    }

    public static void main(String[] args) {

        String result = reverseShuffleMerge("bdabaceadaedaaaeaecdeadababdbeaeeacacaba");

        System.out.println("Input: bdabaceadaedaaaeaecdeadababdbeaeeacacaba");
        System.out.println("Result  : " + result);
        System.out.println("Expected: aaaaaabaaceededecbdb");
    }
}
