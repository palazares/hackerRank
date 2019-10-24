package com.palazares.hackerrank.solutions;

import java.util.*;

public class BalancedForest {
    static long mini, sum;
    static HashSet<Long> s = new HashSet<>();
    static HashSet<Long> q = new HashSet<>();

    static long balancedForest(int[] node_values, int[][] edges) {
        s = new HashSet<>();
        q = new HashSet<>();

        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < node_values.length; i++) {
            nodes.add(new Node(node_values[i]));
        }

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int a = edge[0] - 1;
            int b = edge[1] - 1;
            nodes.get(a).adjacent.add(b);
            nodes.get(b).adjacent.add(a);
        }

        mini = sum = dfs(0, nodes);
        traverse(0, nodes);

        return mini == sum ? -1 : mini;
    }

    private static void traverse(int p, ArrayList<Node> nodes) {
        Node node = nodes.get(p);
        if (node.v2) return;
        node.v2 = true;

        if (sum % 2 == 0 && node.cost == (sum / 2)) mini = Math.min(mini, sum / 2);
        if (s.contains(node.cost) || q.contains(2 * node.cost)) {
            if (3 * node.cost >= sum) mini = Math.min(mini, 3 * node.cost - sum);
        }

        if (s.contains(sum - 2 * node.cost) || q.contains(sum - node.cost)) {
            if (3 * node.cost >= sum) mini = Math.min(mini, 3 * node.cost - sum);
        }

        if ((sum - node.cost) % 2 == 0) {
            if (s.contains((sum - node.cost) / 2) || q.contains((sum + node.cost) / 2)) {
                if ((sum - node.cost) / 2 >= node.cost) mini = Math.min(mini, (sum - node.cost) / 2 - node.cost);
            }
        }

        q.add(node.cost);

        for (int i = 0; i < node.adjacent.size(); i++) {
            traverse(node.adjacent.get(i), nodes);
        }

        q.remove(node.cost);
        s.add(node.cost);
    }

    private static long dfs(int p, ArrayList<Node> nodes) {
        Node node = nodes.get(p);
        if (node.visited) return 0;
        node.visited = true;
        for (int i = 0; i < node.adjacent.size(); i++) {
            node.cost += dfs(node.adjacent.get(i), nodes);
        }
        return node.cost;
    }

    static class Node {
        long cost;
        boolean visited = false, v2 = false;
        ArrayList<Integer> adjacent = new ArrayList<>();

        public Node(long cost) {
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
            int[][] edges = new int[n - 1][2];
            for (int i = 0; i < n - 1; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }
            long result = balancedForest(c, edges);

            System.out.println(result);
        }
        scanner.close();
    }
}
