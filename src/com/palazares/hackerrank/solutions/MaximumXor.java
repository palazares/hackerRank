package com.palazares.hackerrank.solutions;

import java.util.*;

public class MaximumXor {
    static class Trie {
        Trie zero;
        Trie one;

        void insert(int n) {
            Trie root = this;
            for (int i = 31; i >= 0; i--) {
                int value = (n >> i) & 1;
                if (value == 0) {
                    if (root.zero == null)
                        root.zero = new Trie();
                    root = root.zero;
                } else {
                    if (root.one == null)
                        root.one = new Trie();
                    root = root.one;
                }
            }
        }

        int getMax(int n) {
            Trie root = this;
            int max = 0;
            for (int i = 31; i >= 0; i--) {
                int value = (n >> i) & 1;
                if (value == 0) {
                    if (root.one != null) {
                        max += (int) Math.pow(2, i);
                        root = root.one;
                    } else
                        root = root.zero;
                } else {
                    if (root.zero != null) {
                        max += (int) Math.pow(2, i);
                        root = root.zero;
                    } else
                        root = root.one;
                }
            }
            return max;
        }
    }

    static int[] maxXor(int[] arr, int[] queries) {
        int[] result = new int[queries.length];
        Trie root = new Trie();
        for (int value : arr) {
            root.insert(value);
        }
        for (int i = 0; i < queries.length; i++) {
            result[i] = root.getMax(queries[i]);
        }
        return result;
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

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] queries = new int[m];

        for (int i = 0; i < m; i++) {
            int queriesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            queries[i] = queriesItem;
        }

        int[] result = maxXor(arr, queries);

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }

        scanner.close();
    }
}
