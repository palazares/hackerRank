package com.palazares.hackerrank.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ReverseShuffleMerge {

    static String reverseShuffleMerge(String s) {
        final Map<Character, Integer> stringMap = stringMap(s);
        final Map<Character, Integer> stringMap2Build = stringMap2Build(stringMap);
        final Character[] sortedListOfAllChars = getSortedListOfAllChars(stringMap);
        final Map<Character, Integer> accumulatedCharacters = new HashMap<>();
        int lastSyncCharIndex = 0;
        StringBuilder result = new StringBuilder();
        final char[] strChars = s.toCharArray();
        for (int j = 0; j < strChars.length; j++) {
            Character c = strChars[j];
            final Integer charOccur = accumulatedCharacters.merge(c, 1, Integer::sum);
            if (charOccur > stringMap2Build.get(c)) {
                for (int i = sortedListOfAllChars.length - 1; i >= 0; i--) {
                    Character ch = sortedListOfAllChars[i];
                    while (accumulatedCharacters.getOrDefault(ch, 0) > 0 && stringMap2Build.getOrDefault(ch, 0) > 0) {
                        result.append(ch);
                        accumulatedCharacters.merge(ch, 1, (prev, one) -> prev - one);
                        stringMap2Build.merge(ch, 1, (prev, one) -> prev - one);
                        if (result.length() >= s.length() / 2) {
                            return result.reverse().toString();
                        }
                    }
                }
                lastSyncCharIndex =
            }
        }

        return result.reverse().toString();
    }

    private static Character[] getSortedListOfAllChars(Map<Character, Integer> map) {
        Character[] chars = new Character[map.entrySet().size()];
        int i = 0;
        for (Character ch : map.keySet()) {
            chars[i] = ch;
            i++;
        }
        Arrays.sort(chars);
        return chars;
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

        String result = reverseShuffleMerge("abcdefgabcdefg");

        System.out.println("Input: abcdefgabcdefg");
        System.out.println("Result: " + result);
        System.out.println("Expected: agfedcb");
    }
}
