package com.palazares.hackerrank.solutions;

public class MinimumTimeRequired {

    static long minTime(long[] machines, long goal) {
        double rawDayPower = calculateRawDayPower(machines);
        long minRawDays = (long) Math.floor(goal / rawDayPower);
        long volume = calculateVolumeAtDay(machines, minRawDays);
        long totalDays = minRawDays;
        while (volume < goal) {
            totalDays++;
            for (int j = 0; j < machines.length; j++) {
                if (totalDays % machines[j] == 0) {
                    volume++;
                }
                if (volume >= goal) {
                    break;
                }
            }
        }
        return totalDays;
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
        long[] machines = {2, 3};

        long result = minTime(machines, 5);

        System.out.println(result);
    }
}
