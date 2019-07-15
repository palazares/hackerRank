package com.palazares.hackerrank.solutions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ArraySwap {

    // Complete the rotLeft function below.
    private static int[] rotLeft(int[] a, int d) {
        int len = a.length;
        int swapCur;
        int swapNext;
        int divider = len / d;
        int outerIters = divider == 1 ? 1 : d;
        for(int i = 0; i < outerIters; i++) {
            swapCur = a[i];
            int next = getModulus(i - d, len);
            while(next != i){
                swapNext = a[next];
                a[next] = swapCur;
                next = getModulus(next - d, len);
                swapCur = swapNext;
            }
            a[next] = swapCur;
        }

        return a;
    }

    private static int getModulus(int num, int mod) {
        int res = num % mod;
        if(res < 0) res += mod;
        return res;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("2_act.txt"));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int[] result = rotLeft(a, d);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
