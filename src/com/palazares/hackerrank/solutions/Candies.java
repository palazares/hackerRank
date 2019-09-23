package com.palazares.hackerrank.solutions;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Candies {
    static long candies(int n, int[] arr) {
        int[] resultingArray = new int[arr.length];
        resultingArray[0] = 1;

        for (int i = 1; i < n; i++) {
            resultingArray[i] = 1;
            if (arr[i] > arr[i - 1]) {
                resultingArray[i] = resultingArray[i] + resultingArray[i - 1];
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1] && resultingArray[i] <= resultingArray[i + 1]) {
                resultingArray[i] = resultingArray[i + 1] + 1;
            }
        }

        System.out.println(Arrays.stream(resultingArray).mapToObj(String::valueOf).collect(Collectors.joining(",")));

        return Arrays.stream(resultingArray).mapToLong(x -> x).sum();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        long result = candies(n, arr);

        System.out.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(",")));
        System.out.println(result);


        scanner.close();
    }

    public static void main1(String[] args) {

        int[] arr = new int[]{2, 4, 3, 5, 2, 6, 4, 5};
        long result = candies(arr.length, arr);

        System.out.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(",")));
        System.out.println(result);

        arr = new int[]{4, 6, 4, 5, 6, 2};
        result = candies(arr.length, arr);

        System.out.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(",")));
        System.out.println(result);

        arr = new int[]{1, 2, 2, 2, 2, 2, 2};
        result = candies(arr.length, arr);

        System.out.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(",")));
        System.out.println(result);
    }
}
