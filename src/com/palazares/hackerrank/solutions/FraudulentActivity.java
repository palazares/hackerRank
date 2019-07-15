package com.palazares.hackerrank.solutions;

import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FraudulentActivity {
    static int activityNotifications(int[] expenditure, int d) {
        PriorityQueue<Integer> greater = new PriorityQueue<>();
        PriorityQueue<Integer> less = new PriorityQueue<>(Collections.reverseOrder());

        int fraudNotifications = 0;

        for (int i = 0; i < expenditure.length; i++) {
            final int currentVal = expenditure[i];
            if (i >= d) {
                Integer median2x = greater.size() == less.size() ? greater.peek() + less.peek() : 2 * greater.peek();
                if (currentVal >= median2x) {
                    fraudNotifications++;
                }
                int elemToRemove = expenditure[i - d];
                if (greater.peek() <= elemToRemove) {
                    greater.remove(elemToRemove);
                } else {
                    less.remove(elemToRemove);
                }
                rebalance(greater, less);
            }

            if (greater.size() == 0 || greater.peek() <= currentVal) {
                greater.add(currentVal);
            } else {
                less.add(currentVal);
            }
            rebalance(greater, less);
        }

        return fraudNotifications;
    }

    static void rebalance(PriorityQueue<Integer> greater, PriorityQueue<Integer> less) {
        if (greater.size() > less.size() + 1) {
            less.add(greater.poll());
        } else if (greater.size() < less.size()) {
            greater.add(less.poll());
        }
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
