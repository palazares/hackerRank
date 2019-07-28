package com.palazares.hackerrank.solutions;

import java.util.Arrays;

public class IceCreamParlor {
    static void whatFlavors(int[] cost, int money) {
        int[] sortedCost = cost.clone();
        Arrays.sort(sortedCost);
        for(int i = 0; i < sortedCost.length; i++) {
            int complimentaryIndex = Arrays.binarySearch(sortedCost, money - sortedCost[i]);
            if(complimentaryIndex >= 0){
                int fisrtIndex = getIndex(cost, sortedCost[i], -1);
                int secondIndex = getIndex(cost, sortedCost[complimentaryIndex], fisrtIndex);
                if(fisrtIndex <= secondIndex) {
                    System.out.println((fisrtIndex + 1) + " " + (secondIndex + 1));
                }
                else {
                    System.out.println((secondIndex + 1) + " " + (fisrtIndex + 1));
                }
                break;
            }
        }
    }

    private static int getIndex(int[] ar, int el, int existingIndex){
        for(int i = 0; i < ar.length; i++){
            if(ar[i] == el && i != existingIndex){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        whatFlavors(new int[]{7,2,5,4,11}, 12);
    }
}
