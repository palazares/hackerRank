package com.palazares.hackerrank.solutions;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFSShortestReachInGraph {
    public static class Graph {

        boolean[][] matrix;

        int weight = 6;

        Graph(int size) {
            matrix = new boolean[size][size];
        }

        void addEdge(int first, int second) {
            matrix[first][second] = true;
            matrix[second][first] = true;
        }

        int[] shortestReach(int startId) { // 0 indexed
            boolean[] visited = new boolean[matrix.length];
            int[] distances = new int[matrix.length];
            visited[startId] = true;

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(startId);

            while (queue.peek() != null) {
                int node = queue.poll();
                for (int i = 0; i < distances.length; i++) {
                    if (!visited[i] && matrix[node][i]) {
                        visited[i] = true;
                        distances[i] = distances[node] + weight;
                        queue.offer(i);
                    }
                }
            }

            return distances;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int queries = scanner.nextInt();

        for (int t = 0; t < queries; t++) {

            // Create a graph of size n where each edge weight is 6:
            Graph graph = new Graph(scanner.nextInt());
            int m = scanner.nextInt();

            // read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;

                // add each edge to the graph
                graph.addEdge(u, v);
            }

            // Find shortest reach from node s
            int startId = scanner.nextInt() - 1;
            int[] distances = graph.shortestReach(startId);

            for (int i = 0; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i] == 0 ? -1 : distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }
}
