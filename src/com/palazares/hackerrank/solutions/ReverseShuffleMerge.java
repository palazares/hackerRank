package com.palazares.hackerrank.solutions;

import java.util.HashMap;
import java.util.Map;

public class ReverseShuffleMerge {

    static String reverseShuffleMerge(String s) {
        final Map<Character, Integer> stringMap = stringMap(s);
        final Map<Character, Integer> stringMap2Build = stringMap2Build(stringMap);
        final char[] strChars = s.toCharArray();
        char[] stack = new char[s.length()];
        int top = -1;
        for (int j = strChars.length; --j >= 0; ) {
            Character c = strChars[j];
            stringMap.merge(c, 1, (o, n) -> o - n);
            if (stringMap2Build.getOrDefault(c, 0) <= 0) {
                continue;
            }
            stringMap2Build.merge(c, 1, (o, n) -> o - n);

            while (top >= 0 && stack[top] > c &&
                    stringMap.getOrDefault(stack[top], 0) > stringMap2Build.getOrDefault(stack[top], 0)) {
                stringMap2Build.merge(stack[top--], 1, Integer::sum);
            }
            stack[++top] = c; // Push c on to the stack
        }

        return String.valueOf(stack).trim();
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
