package com.palazares.hackerrank.solutions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParenthesis {

    private static Set<String> result;

    public static List<String> removeInvalidParentheses(String s) {
        result = new HashSet<>();
        String cleanedS = removeTailAndHead(s);

        int left = 0, right = 0;

        for (int i = 0; i < cleanedS.length(); i++) {
            if (cleanedS.charAt(i) == '(') {
                left++;
            } else if (cleanedS.charAt(i) == ')') {
                right = left == 0 ? right + 1 : right;
                left = left > 0 ? left - 1 : left;
            }
        }

        rec(cleanedS, 0, 0, 0, left, right, new StringBuilder());
        return List.copyOf(result);
    }

    private static void rec(String s, int index, int leftCount, int rightCount, int leftRem, int rightRem, StringBuilder sb) {
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0) {
                result.add(sb.toString());
            }
            return;
        }
        char c = s.charAt(index);
        int length = sb.length();
        if (c == '(') {
            if (leftRem > 0) rec(s, index + 1, leftCount, rightCount, leftRem - 1, rightRem, sb);
            sb.append(c);
            rec(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, sb);
        } else if (c == ')') {
            if (rightRem > 0) rec(s, index + 1, leftCount, rightCount, leftRem, rightRem - 1, sb);
            sb.append(c);
            if (rightCount < leftCount) rec(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, sb);
        } else {
            sb.append(c);
            rec(s, index + 1, leftCount, rightCount, leftRem, rightRem, sb);
        }
        sb.deleteCharAt(length);
    }

    private static String removeTailAndHead(String s) {
        StringBuilder sb = new StringBuilder(s);
        int i = 0;
        while (i < sb.length() && sb.charAt(i) != '(') {
            if (sb.charAt(i) == ')') {
                sb.deleteCharAt(i);
            } else {
                i++;
            }
        }
        i = sb.length() - 1;
        while (i >= 0 && i < sb.length() && sb.charAt(i) != ')') {
            if (sb.charAt(i) == '(') sb.deleteCharAt(i);
            i--;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        //var result = String.join(", ", removeInvalidParentheses("()())()"));
        //var result = String.join(", ", removeInvalidParentheses("(()"));
        //var result = String.join(", ", removeInvalidParentheses("(j))("));
        //var result = String.join(", ", removeInvalidParentheses("())(((()m)("));
        var result = String.join(", ", removeInvalidParentheses("))"));
        System.out.println(result);
    }
}
