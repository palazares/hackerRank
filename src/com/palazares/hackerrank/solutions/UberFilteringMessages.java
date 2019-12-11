package com.palazares.hackerrank.solutions;

public class UberFilteringMessages {
    public static int filteringMessages(String A, String B, String virus) {
        int count = 0;
        var current = A;
        while (current.compareTo(B) <= 0) {
            if (!current.contains(virus)) {
                count++;
            }
            current = getNext(current);
        }
        return count;
    }

    private static String getNext(String s) {
        var stringArray = s.toCharArray();
        int i = stringArray.length - 1;
        while (i >= 0 && stringArray[i] == 'z') {
            stringArray[i] = 'a';
            i--;
        }
        if (i < 0) {
            return "z" + s;
        }
        var c = stringArray[i];
        stringArray[i] = ++c;
        return String.valueOf(stringArray);
    }

    public static void main(String[] args) {
        System.out.println(filteringMessages("cc", "zz", "d"));
    }
}
