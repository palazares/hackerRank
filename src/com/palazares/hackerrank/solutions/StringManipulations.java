package com.palazares.hackerrank.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StringManipulations {

    static int makeAnagram(String a, String b) {
        final Map<Character, Integer> aStringMap = getStringMap(a);
        final Map<Character, Integer> bStringMap = getStringMap(b);

        return checkDeletions(aStringMap, bStringMap) + checkDeletions(bStringMap, aStringMap);
    }

    static int checkDeletions(Map<Character, Integer> aStringMap, Map<Character, Integer> bStringMap) {
        int deletions = 0;
        for (Map.Entry<Character, Integer> entry : aStringMap.entrySet()) {
            Integer bValue = bStringMap.get(entry.getKey());
            if (bValue == null) {
                deletions += entry.getValue();
            } else if (entry.getValue() > bValue) {
                deletions += entry.getValue() - bValue;
            }
        }

        return deletions;
    }

    static int alternatingCharacters(String s) {
        int deletions = 0;
        Character currentChar = s.charAt(0);
        final char[] chars = s.toCharArray();
        for (int i = 1; i < s.length(); i++) {
            if (currentChar.equals(chars[i])) {
                deletions++;
            } else {
                currentChar = chars[i];
            }
        }
        return deletions;
    }

    static String isValid(String s) {
        final Map<Character, Integer> stringMap = getStringMap(s);
        final Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (Map.Entry<Character, Integer> entry : stringMap.entrySet()) {
            frequencyMap.merge(entry.getValue(), 1, Integer::sum);
        }
        final List<Map.Entry<Integer, Integer>> frequencies = new ArrayList<>(frequencyMap.entrySet());
        if (frequencies.size() > 2) {
            return "NO";
        }
        if (frequencies.size() == 1) {
            return "YES";
        }
        Map.Entry<Integer, Integer> firstFrequency = frequencies.get(0);
        Map.Entry<Integer, Integer> secondFrequency = frequencies.get(1);
        if (firstFrequency.getValue() > 1 && secondFrequency.getValue() > 1) {
            return "NO";
        }
        if (firstFrequency.getKey() == 1 && firstFrequency.getValue() == 1 ||
                secondFrequency.getKey() == 1 && secondFrequency.getValue() == 1) {
            return "YES";
        }
        if (Math.abs(firstFrequency.getKey() - secondFrequency.getKey()) > 1) {
            return "NO";
        }

        if (firstFrequency.getKey() > secondFrequency.getKey() && firstFrequency.getValue() > 1) {
            return "NO";
        }

        if (firstFrequency.getKey() < secondFrequency.getKey() && secondFrequency.getValue() > 1) {
            return "NO";
        }

        return "YES";
    }

    static Map<Character, Integer> getStringMap(String
                                                        s) {
        Map<Character, Integer> result = new HashMap<>();
        for (Character c : s.toCharArray()) {
            result.merge(c, 1, Integer::sum);
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String s = scanner.nextLine();

        String result = isValid(s);

        System.out.println(result);
    }
}
