package com.palazares.hackerrank.solutions;

import java.util.Arrays;

public class SearchTriplets {
    static long triplets(int[] a, int[] b, int[] c) {
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);
        long triplets = 0;
        int j = 0;
        long distinctj = 0;
        int k = 0;
        long distinctk = 0;
        for(int i = 0; i < b.length; i++){
            while(j < a.length && a[j] <= b[i]){
                if(j == 0 || a[j] != a[j-1]){
                    distinctj++;
                }
                j++;
            }
            while(k < c.length && c[k] <= b[i]){
                if(k == 0 || c[k] != c[k-1]){
                    distinctk++;
                }
                k++;
            }

            if(i == 0 || b[i] != b[i-1]){
                triplets += distinctj * distinctk;
            }
        }
        return triplets;
    }

    public static void main(String[] args){
        int[] a = {1, 4, 5};
        int[] b = {2, 3, 3};
        int[] c = {1, 2, 3};
        long result = triplets(a, b, c);
        System.out.println(result);
    }
}
