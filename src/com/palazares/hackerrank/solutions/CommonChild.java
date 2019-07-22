package com.palazares.hackerrank.solutions;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CommonChild {

    static int longestChildLength = 0;
    static String longestChild = "";
    static char[] biggerStringChars;
    static char[] lesserStringChars;

    static int commonChild(String s1, String s2) {

        longestChildLength = 0;

        final Set<Character> string1Chars = getStringChars(s1);
        final Set<Character> string2Chars = getStringChars(s2);

        final String newS1 = removeCharsFromString(string2Chars, s1);
        final String newS2 = removeCharsFromString(string1Chars, s2);

        if (newS1.isEmpty() || newS2.isEmpty()) {
            return 0;
        }

        String biggerString = newS1.length() > newS2.length() ? newS1 : newS2;
        String lesserString = newS1.length() <= newS2.length() ? newS1 : newS2;

        lesserStringChars = lesserString.toCharArray();
        biggerStringChars = biggerString.toCharArray();

        checkString("", 0, 0);

        System.out.println(longestChild);

        return longestChildLength;
    }

    static void checkString(String s, int indexRestItself, int indexRestCompare) {
        if (s.length() > longestChildLength) {
            longestChildLength = s.length();
            longestChild = s;
            if (longestChildLength >= lesserStringChars.length) {
                return;
            }
        }

        if (indexRestItself >= lesserStringChars.length || indexRestCompare >= biggerStringChars.length) {
            return;
        }

        for (int i = indexRestItself; i < lesserStringChars.length; i++) {
            int j = indexRestCompare;
            while (j < biggerStringChars.length && biggerStringChars[j] != lesserStringChars[i]) {
                j++;
            }
            if (j < biggerStringChars.length) {
                checkString(s + lesserStringChars[i], i + 1, j + 1);
            }
        }
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
