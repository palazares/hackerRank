package com.palazares.hackerrank.solutions;

import java.util.Scanner;

public class MaxArraySum {

    static int maxSubsetSum(int[] arr) {
        int prevPrevMax = 0;
        int prevMax = Math.max(arr[0], 0);
        boolean lastIncluded = prevMax > 0;
        for (int i = 1; i < arr.length; i++) {
            int tmpPrevMax = prevMax;
            if (arr[i] <= 0) {
                lastIncluded = false;
            }
            // arr[i] > 0
            else if (!lastIncluded) {
                prevMax += arr[i];
                lastIncluded = true;
            }
            // lastIncluded == true && arr[i] > 0 && arr[i] <= arr[i-1]
            else if (prevPrevMax + arr[i] > prevMax) {
                prevMax = prevPrevMax + arr[i];
            } else {
                lastIncluded = false;
            }
            prevPrevMax = tmpPrevMax;
        }
        return prevMax;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = maxSubsetSum(arr);

        //76497274
        System.out.println(res);

        scanner.close();

        arr = new int[]{5, 7, 4, 6, 5, 5, 5, 5};
        res = maxSubsetSum(arr);
        System.out.println(res);
    }
}
