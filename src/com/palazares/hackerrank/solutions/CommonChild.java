package com.palazares.hackerrank.solutions;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CommonChild {

    static int commonChild(String s1, String s2) {

        final Set<Character> string1Chars = getStringChars(s1);
        final Set<Character> string2Chars = getStringChars(s2);

        final String newS1 = removeCharsFromString(string2Chars, s1);
        final String newS2 = removeCharsFromString(string1Chars, s2);

        if (newS1.isEmpty() || newS2.isEmpty()) {
            return 0;
        }

        String biggerString = newS1.length() > newS2.length() ? newS1 : newS2;
        String lesserString = newS1.length() <= newS2.length() ? newS1 : newS2;

        Set<String> lesserStringSubset = new HashSet<>();
        lesserStringSubset.add(lesserString);
        Set<String> biggerStringSubset = new HashSet<>();
        biggerStringSubset.add(biggerString);

        int lengthDifference = biggerString.length() - lesserString.length();

        for (int i = lengthDifference; i > 0; i--) {
            biggerStringSubset = getSubstringsSet(biggerStringSubset);
        }

        for (int i = lesserString.length(); i > 0; i--) {
            final Set<String> finalBiggerStringSubset = biggerStringSubset;
            if (lesserStringSubset.stream().anyMatch(s -> finalBiggerStringSubset.contains(s))) {
                return i;
            }
            biggerStringSubset = getSubstringsSet(biggerStringSubset);
            lesserStringSubset = getSubstringsSet(lesserStringSubset);
        }

        return 1;
    }

    static Set<String> getSubstringsSet(Set<String> set) {
        Set<String> resultingSet = new HashSet<>();
        for (String s : set) {
            resultingSet.addAll(getSubstringsSet(s));
        }
        return resultingSet;
    }

    static Set<String> getSubstringsSet(String s) {

        StringBuilder builder = new StringBuilder();
        final HashSet<String> strings = new HashSet<>();
        for (int i = 0; i < s.length() - 1; i++) {
            strings.add(builder.toString() + s.substring(i + 1));
            builder.append(s, i, i + 1);
        }
        strings.add(builder.toString());

        return strings;
    }

    static Set<Character> getStringChars(String s) {
        final HashSet<Character> chars = new HashSet<>();
        for (char c : s.toCharArray()) {
            chars.add(c);
        }

        return chars;
    }

    static String removeCharsFromString(Set<Character> chars, String s) {
        StringBuilder builder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (chars.contains(c)) {
                builder.append(c);
            }
        }

        return builder.toString();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        String s1 = scanner.nextLine();
//        String s2 = scanner.nextLine();
//        scanner.close();

        int result = commonChild("ACBDKF", "ABCDMEA");

        System.out.println(result);
    }
}
