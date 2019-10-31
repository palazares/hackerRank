package com.palazares.hackerrank.solutions;

import java.util.*;

public class FriendCircleQueries {
    static class Friend {
        Friend link;
        int friends;

        public Friend() {
            link = null;
            friends = 1;
        }
    }

    static int[] maxCircle(int[][] queries) {
        int[] result = new int[queries.length];
        Map<Integer, Friend> friendship = new HashMap<>();
        int maxCircle = 1;

        for (int i = 0; i < queries.length; i++) {
            Friend fCircle = getParent(friendship.computeIfAbsent(queries[i][0], k -> new Friend()));
            Friend sCircle = getParent(friendship.computeIfAbsent(queries[i][1], k -> new Friend()));

            if (fCircle != sCircle) {
                if (fCircle.friends >= sCircle.friends) {
                    fCircle.friends += sCircle.friends;
                    sCircle.friends = 0;
                    sCircle.link = fCircle;
                    maxCircle = Math.max(maxCircle, fCircle.friends);
                } else {
                    sCircle.friends += fCircle.friends;
                    fCircle.friends = 0;
                    fCircle.link = sCircle;
                    maxCircle = Math.max(maxCircle, sCircle.friends);
                }

            }

            result[i] = maxCircle;
        }

        return result;
    }

    static Friend getParent(Friend fr) {
        while (fr.link != null) {
            fr = fr.link;
        }

        return fr;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] queries = new int[q][2];

        for (int i = 0; i < q; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        int[] ans = maxCircle(queries);

        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }

        scanner.close();
    }
}
