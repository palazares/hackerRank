package com.palazares.hackerrank.solutions;

import java.util.Arrays;

public class MinimumTimeRequired {

    static long minTime(long[] machines, long goal) {
        Arrays.sort(machines);
        double rawDayPower = calculateRawDayPower(machines);
        long minRawDays = (long) Math.floor(goal / rawDayPower);
        long maxRawDays = minRawDays + machines[machines.length - 1];
        long previousMiddle = 0;
        long middle = 1;
        long volume = 0;

        while (minRawDays < maxRawDays && previousMiddle != middle) {
            previousMiddle = middle;
            middle = (minRawDays + maxRawDays) / 2;
            volume = calculateVolumeAtDay(machines, middle);
            if (volume >= goal) {
                maxRawDays = middle;
            } else {
                minRawDays = middle;
            }
        }
        if (volume < goal) {
            middle++;
        }
        return middle;
    }

    static double calculateRawDayPower(long[] machines) {
        double rawPower = 0;
        for (int i = 0; i < machines.length; i++) {
            rawPower += 1 / (double) machines[i];
        }
        return rawPower;
    }

    static long calculateVolumeAtDay(long[] machines, long day) {
        long volume = 0;
        for (int i = 0; i < machines.length; i++) {
            volume += day / machines[i];
        }
        return volume;
    }

    public static void main(String[] args) {
        long[] machines = {1, 3, 4};
        long result = minTime(machines, 10);
        System.out.println(result);
        System.out.println("Should be: 7");

        machines = new long[]{4, 5, 6};
        result = minTime(machines, 12);
        System.out.println(result);
        System.out.println("Should be: 20");

        machines = new long[]{2, 3};
        result = minTime(machines, 5);
        System.out.println(result);
        System.out.println("Should be: 6");
    }
}
