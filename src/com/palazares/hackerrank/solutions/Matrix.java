package com.palazares.hackerrank.solutions;

import java.util.*;

public class Matrix {
    private static class Edge {
        public Edge(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        int x;
        int y;
        int weight;
    }

    static boolean[] visited;
    static int total = 0;
    static boolean[] isMachine;
    static List<Edge>[] adj;

    static int minTime(int[][] roads, int[] machines) {
        int n = roads.length + 1;
        isMachine = new boolean[n];
        Arrays.stream(machines).forEach(m -> isMachine[m] = true);

        adj = new List[n];
        for (int[] road : roads) {
            if (adj[road[0]] == null) adj[road[0]] = new ArrayList<>();
            if (adj[road[1]] == null) adj[road[1]] = new ArrayList<>();
            adj[road[0]].add(new Edge(road[0], road[1], road[2]));
            adj[road[1]].add(new Edge(road[1], road[0], road[2]));
        }

        visited = new boolean[n];
        total = 0;
        traverse(0, 0);
        return total;
    }

    static int traverse(int city, int time) {
        visited[city] = true;
        int maxTime = 0;
        int sumTime = 0;

        for (Edge neighbor : adj[city]) {
            if (visited[neighbor.y]) continue;

            int neighborTime = traverse(neighbor.y, neighbor.weight);
            sumTime += neighborTime;
            maxTime = Math.max(maxTime, neighborTime);
        }

        if (isMachine[city]) {
            total += sumTime;
            return time;
        } else {
            total += sumTime - maxTime;
            return Math.min(maxTime, time);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] roads = new int[n - 1][3];

        for (int i = 0; i < n - 1; i++) {
            String[] roadsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int roadsItem = Integer.parseInt(roadsRowItems[j]);
                roads[i][j] = roadsItem;
            }
        }

        int[] machines = new int[k];

        for (int i = 0; i < k; i++) {
            int machinesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            machines[i] = machinesItem;
        }

        int result = minTime(roads, machines);

        System.out.println(result);

        scanner.close();
    }
}
