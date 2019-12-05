package com.palazares.hackerrank.solutions;

import java.util.*;

public class DecibinaryNumbers {
    static Map<String, List<String>> cache = new HashMap<>();

    final int MAX_SIZE = 1;

    static long decibinaryNumbers(long x) {

        return 0;
    }

    static byte[] getBinary(int x) {
        char[] chars = Integer.toBinaryString(x).toCharArray();
        byte[] binary = new byte[chars.length];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) (chars[i] == '1' ? 1 : 0);
        }
        return binary;
    }

    static List<String> numberOptions(int number){
        List<String> options = List.of(Integer.toBinaryString(number));
        byte[] binary = getBinary(number);
        for(int i = 0; i < binary.length; i++){

        }

        return options;
    }

    static String getDecibinaryString(byte[] ar, int startIndex){
        StringBuffer sb = new StringBuffer();
        for(int i = startIndex; i < ar.length; i++){
            sb.append(ar[i]);
        }
        return sb.toString();
    }

    private static long db2deci(long value){
        long result = 0;
        int i = 0;
        while(value >= 10){
            long cur = value % 10;
            result += cur * (long)Math.pow(2, i);
            value /= 10;
            i++;
        }
        return result + value * (long)Math.pow(2, i);
    }

    public static void main(String[] args)  {
        long result = decibinaryNumbers(0);
        long x = 0;
        for(int j = 0; j < 101; j++){
            System.out.print(j * 10);
            System.out.print(": ");
            for(int i = 0; i < 10; i++){
                System.out.print(db2deci(x));
                System.out.print(" ");
                x++;
            }
            System.out.println();
        }
    }
}
