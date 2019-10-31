package com.palazares.hackerrank.solutions;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Primality {
    static String primality(int n) {
        if (n == 1) return "Not prime";
        if (n <= 3) return "Prime";
        double sq = Math.sqrt(n);
        int i = 2;
        while (i <= sq) {
            if (n % i == 0) {
                return "Not prime";
            }
            i++;
        }

        Map<Integer, Integer> x = new HashMap<>();
        x.computeIfAbsent(1, k -> 2);
        return "Prime";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int p = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int pItr = 0; pItr < p; pItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            System.out.println(primality(n));
        }

        scanner.close();
    }
}
