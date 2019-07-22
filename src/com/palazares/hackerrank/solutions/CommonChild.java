package com.palazares.hackerrank.solutions;

public class CommonChild {

    static int commonChild(String s1, String s2) {
        int maxLength = 0;
        int[][] countMatrix = new int[s1.length() + 1][s2.length() + 1];

        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();

        for(int i = 1; i <= s1.length(); i++){
            for(int j = 1; j <= s2.length(); j++){
                if (s1Chars[i-1] == s2Chars[j-1]){
                    countMatrix[i][j] =  countMatrix[i-1][j-1] + 1;
                    if(countMatrix[i][j] > maxLength){
                        maxLength = countMatrix[i][j];
                    }
                }
                else {
                    countMatrix[i][j] = countMatrix[i-1][j] > countMatrix[i][j-1] ? countMatrix[i-1][j] : countMatrix[i][j-1];
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {

        int result = commonChild("ACBDKF", "ABCDMEARTY");

        System.out.println(result);
    }
}
