package com.palazares.hackerrank.solutions;

import java.util.Scanner;

public class BalancedForest {
    static int[] original;
    static int[][] edges;
    static boolean[] visited;
    static long[] sum;
    static long bestCandidate;

    static int balancedForest(int[] c, int[][] e) {
        int n = c.length;
        original = c;
        edges = e;
        sum = new long[n];
        visited = new boolean[n];

        fillSumGraph(0);

        visited = new boolean[n];
        bestCandidate = sum[0];

        mainTraverse(0);

        if (bestCandidate == sum[0]) return -1;
        return (int) (3 * bestCandidate - sum[0]);
    }

    static void mainTraverse(int node) {
        if (visited[node]) return;
        visited[node] = true;
        int n = original.length;
        for (int i = 0; i < n; i++) {
            if (edges[node][i] == 0) continue;
            if (sum[i] < bestCandidate || 3 * sum[i] > sum[0]) {
                candidateTraverse(node, i, sum[i], new boolean[n]);
            }
            if (3 * sum[i] > 2 * sum[0]) {
                mainTraverse(node);
            }
        }
    }

    static void candidateTraverse(int node, int candidateNode, long candidateSum, boolean[] candidateVisited) {
        if (candidateVisited[node] || candidateSum == bestCandidate) return;
        candidateVisited[node] = true;
        int n = original.length;
        for (int i = 0; i < n; i++) {
            if (edges[node][i] == 0 || i == candidateNode) continue;
            if (sum[node] - sum[i] == 2 * candidateSum) {
                bestCandidate = candidateSum;
                return;
            }
            if (sum[i] > candidateSum) {
                candidateTraverse(i, candidateNode, candidateSum, candidateVisited);
            }
        }
    }

    static void fillSumGraph(int node) {
        if (visited[node]) return;
        visited[node] = true;
        int n = original.length;
        long childrenSum = 0;
        for (int i = 0; i < n; i++) {
            if (edges[node][i] == 0) continue;
            fillSumGraph(i);
            childrenSum += sum[i];
        }
        sum[node] = original[node] + childrenSum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] c = new int[n];

            String[] cItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int cItem = Integer.parseInt(cItems[i]);
                c[i] = cItem;
            }

            int[][] edges = new int[n][n];

            for (int i = 0; i < n - 1; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                int firstNode = Integer.parseInt(edgesRowItems[0]) - 1;
                int secondNode = Integer.parseInt(edgesRowItems[1]) - 1;
                edges[firstNode][secondNode] = 1;
                edges[secondNode][firstNode] = 1;
            }

            int result = balancedForest(c, edges);
            System.out.println(result);
        }

        scanner.close();
    }
}
