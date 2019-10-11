package com.palazares.hackerrank.solutions;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FindTheNearestClone {
    static int shortest = Integer.MAX_VALUE;

    static int findShortest(int graphNodes, List<Integer>[] matrix, long[] ids, int val) {
        int countColor = 0;

        for (long c : ids) {
            if (c == val) {
                countColor++;
            }
            if (countColor >= 2) {
                break;
            }
        }
        if (countColor < 2) {
            return -1;
        }

        boolean[] visited = new boolean[graphNodes];
        visit(0, visited, matrix, ids, val);

        return shortest;
    }

    static int visit(int nodeIndex, boolean[] visited, List<Integer>[] matrix, long[] ids, int val) {
        visited[nodeIndex] = true;
        int firstLeg = Integer.MAX_VALUE;
        int secondLeg = Integer.MAX_VALUE;
        if (ids[nodeIndex] == val) {
            firstLeg = 0;
        }

        for (int node : matrix[nodeIndex]) {
            if (!visited[node]) {
                int distanceToColor = visit(node, visited, matrix, ids, val);
                if (distanceToColor < firstLeg && firstLeg > secondLeg) {
                    firstLeg = distanceToColor;
                } else if (distanceToColor < secondLeg) {
                    secondLeg = distanceToColor;
                }
            }
        }

        if (firstLeg != Integer.MAX_VALUE && secondLeg != Integer.MAX_VALUE && shortest > firstLeg + secondLeg) {
            shortest = firstLeg + secondLeg;
        }

        if (firstLeg == Integer.MAX_VALUE && secondLeg == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        return Math.min(firstLeg, secondLeg) + 1;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] graphNodesEdges = scanner.nextLine().split(" ");
        int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
        int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

        List<Integer>[] matrix = new List[graphNodes];

        for (int i = 0; i < graphEdges; i++) {
            String[] graphFromTo = scanner.nextLine().split(" ");
            Integer from = Integer.parseInt(graphFromTo[0].trim()) - 1;
            Integer to = Integer.parseInt(graphFromTo[1].trim()) - 1;
            if (matrix[from] == null) {
                matrix[from] = new LinkedList<>();
            }
            if (matrix[to] == null) {
                matrix[to] = new LinkedList<>();
            }
            matrix[from].add(to);
            matrix[to].add(from);
        }

        long[] ids = new long[graphNodes];

        String[] idsItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < graphNodes; i++) {
            long idsItem = Long.parseLong(idsItems[i]);
            ids[i] = idsItem;
        }

        int val = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int ans = findShortest(graphNodes, matrix, ids, val);

        System.out.println(ans);

        scanner.close();
    }
}
