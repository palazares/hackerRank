package com.palazares.hackerrank.solutions;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class LuckBalance {
    static int luckBalance(int k, int[][] contests) {
        PriorityQueue<Integer> importantContests = new PriorityQueue<>(Collections.reverseOrder());
        int totalLuck = 0;
        for (int i = 0; i < contests.length; i++) {
            if (contests[i][1] == 1) {
                importantContests.add(contests[i][0]);
            } else {
                totalLuck += contests[i][0];
            }
        }
        for (int i = 0; i < k; i++) {
            Integer luck = importantContests.poll();
            if (luck == null) {
                break;
            }
            totalLuck += luck;
        }
        while (importantContests.peek() != null) {
            totalLuck -= importantContests.poll();
        }

        return totalLuck;
    }

    static int maxMin(int k, int[] arr) {
        Arrays.sort(arr);
        int minUnfairness = 1000000001;
        for (int i = 0; i < arr.length - k; i++) {
            int unfairness = arr[i + k - 1] - arr[i];
            if (unfairness < minUnfairness) {
                minUnfairness = unfairness;
            }
        }
        return minUnfairness;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        maxMin(3, new int[]{100, 200, 300, 350, 400, 401, 402});

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] contests = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] contestsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int contestsItem = Integer.parseInt(contestsRowItems[j]);
                contests[i][j] = contestsItem;
            }
        }

        scanner.close();

        int result = luckBalance(k, contests);

        System.out.println(result);
    }
}
