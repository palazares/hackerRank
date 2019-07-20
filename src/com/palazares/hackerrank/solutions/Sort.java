package com.palazares.hackerrank.solutions;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Sort {

    public static void quickSort(int[] arr){
        sort(arr, 0, arr.length - 1);
    }

    static void sort(int[] arr, int low, int high){
        if(low >= high){
            return;
        }

        int pivot = partition(arr, low, high);

        if(pivot > 0) {
            sort(arr, low, pivot - 1);
        }
        if(pivot + 1 < arr.length) {
            sort(arr, pivot + 1, high);
        }
    }

    static int partition(int[] arr, int low, int high){
        int pivot = (low + high) / 2;
        int elem = arr[pivot];
        int i = low;
        int j = high;

        while(i < j) {
            while(i + 1 < high && arr[i] < elem ){
                i++;
            }
            while(j > low && arr[j] > elem){
                j--;
            }
            if(arr[i] <= arr[j] && i < j){
                i++;
            }
            else{
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        return i;
    }

    public static void main(String[] args){
        int[] arr = {1, 5, 4, 14, 6, 7, 14, 1, 5, 6, 8, 1, 10, 1, 1, 1, 1, 1, 1};

        printArray(arr);
        System.out.println();

        quickSort(arr);

        System.out.println();
        printArray(arr);
    }

    private static void printArray(int[] arr){
        System.out.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(",")));
    }
}
