package com.palazares.hackerrank.solutions;

import java.util.Scanner;

public class Abbreviation {

    static String abbreviation(String a, String b) {
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        boolean[][] match = new boolean[a.length() + 1][b.length() + 1];
        match[0][0] = true;

        boolean containsUppercase = false;
        for (int i = 0; i < a.length(); i++) {
            if (Character.isUpperCase(aChars[i]) || containsUppercase) {
                containsUppercase = true;
                match[i + 1][0] = false;
            } else match[i + 1][0] = true;
        }

        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (aChars[i] == bChars[j]) {
                    match[i + 1][j + 1] = match[i][j];
                } else if (Character.toUpperCase(aChars[i]) == bChars[j]) {
                    match[i + 1][j + 1] = match[i][j] || match[i][j + 1];
                } else if (Character.isUpperCase(aChars[i])) {
                    match[i + 1][j + 1] = false;
                } else {
                    match[i + 1][j + 1] = match[i][j + 1];
                }
            }
        }

        return match[a.length()][b.length()] ? "YES" : "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            System.out.println(abbreviation(a, b));

        }

        scanner.close();

        System.out.println(abbreviation("KXzQ", "K"));
        System.out.println(abbreviation("beFgH", "EFG"));
        System.out.println(abbreviation("AbcDE", "ABDE"));
        System.out.println(abbreviation("daBcd", "ABC"));
        System.out.println(abbreviation("AbCdE", "AFE"));
        System.out.println(abbreviation("beFgH", "EFH"));
        System.out.println(abbreviation("AfPZN", "APZNC"));
    }
}
