package com.palazares.hackerrank.solutions;

public class UberStringFormation {

    private static int counter = 0;

    public static int numWays(String[] words, String target) {
        counter = 0;

        numWaysInternal(words, 0, target, 0);

        return counter;
    }

    private static void numWaysInternal(String[] words, int sourceIndex, String target, int targetIndex) {
        if (targetIndex == target.length()) {
            counter++;
            return;
        }
        int sourceLength = words[0].length();
        int restTargetLength = target.length() - targetIndex;
        if (sourceIndex >= sourceLength || sourceLength - sourceIndex < restTargetLength) {
            return;
        }
        for (int i = sourceIndex; i <= sourceLength - restTargetLength; i++) {
            for (String w : words) {
                if (w.charAt(i) == target.charAt(targetIndex)) {
                    numWaysInternal(words, i + 1, target, targetIndex + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] words = {"valal", "lyglb", "vldoh"};
        //String[] words = {"adc", "aec", "efg"};
        String target = "val";
        //String target = "ac";

        System.out.println(numWays(words, target));
    }
}
