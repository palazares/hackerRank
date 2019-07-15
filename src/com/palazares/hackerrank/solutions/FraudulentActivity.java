package com.palazares.hackerrank.solutions;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class FraudulentActivity {
    static int activityNotifications(int[] expenditure, int d) {
        int[] window = Arrays.copyOf(expenditure, d);
        Arrays.sort(window);
        int fraudNotifications = 0;

        for (int i = d; i < expenditure.length; i++) {
            final int currentVal = expenditure[i];
            final double median = getMedian(window);
            if (currentVal >= 2 * median) {
                fraudNotifications++;
            }
            updateWindow(window, expenditure[i - d], currentVal);
        }

        return fraudNotifications;
    }

    private static double getMedian(int[] arr) {
        int midIndex = arr.length / 2;
        if (arr.length % 2 == 1) {
            return arr[midIndex];
        }

        return ((double) arr[midIndex] + arr[midIndex - 1]) / 2.0;
    }

    private static void updateWindow(int[] window, int oldElem, int newElem) {
        final int oldIndex = binarySearch(window, oldElem, 0, window.length - 1);
        window[oldIndex] = newElem;
        Arrays.sort(window);
    }

    private static int binarySearch(int[] arr, int value, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = high - low >>> 1;
        int midValue = arr[mid];
        if (midValue == value) {
            return mid;
        }
        if (value < midValue) {
            return binarySearch(arr, value, low, mid - 1);
        }
        return binarySearch(arr, value, mid + 1, high);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        scanner.close();

        System.out.println(result);
    }
}
