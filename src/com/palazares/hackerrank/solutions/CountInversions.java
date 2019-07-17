package com.palazares.hackerrank.solutions;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CountInversions {

    static long invertionsCount = 0;

    static long countInversions(int[] arr) {
        invertionsCount = 0;
        mergeSort(arr, 0, arr.length - 1);
        return invertionsCount;
    }

    static void mergeSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = (high + low) / 2;

        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);

        merge(arr, low, mid, high);
    }

    static void merge(int[] arr, int low, int mid, int high) {
        int[] buffer = new int[high - low + 1];
        int i = low, j = mid + 1, k = 0;
        while (i <= mid || j <= high) {
            if (i <= mid && (j > high || arr[i] <= arr[j])) {
                buffer[k++] = arr[i++];
            } else {
                if (arr[i] > arr[j]) {
                    invertionsCount += mid - i + 1;
                }
                buffer[k++] = arr[j++];
            }
        }

        for (i = 0; i <= high - low; i++) {
            arr[i + low] = buffer[i];
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            System.out.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(",")));

            long result = countInversions(arr);

            System.out.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(",")));
            System.out.println(result);
        }

        scanner.close();
    }
}
