package com.palazares.hackerrank.solutions;

import java.util.*;
import java.util.stream.Collectors;

public class Permutations {
    public static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 1) return result;

        Deque<Integer> left = new LinkedList<>();
        for (int el : nums) {
            left.push(el);
        }

        rec(left, result, new LinkedList<>());
        return result;
    }

    private static void rec(Deque<Integer> left, List<List<Integer>> result, Deque<Integer> cur) {
        if (left.size() == 0) {
            result.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < left.size(); i++) {
            cur.push(left.removeFirst());
            while (i < left.size() && left.peekFirst().equals(cur.peek())) {
                left.addLast(left.removeFirst());
                i++;
            }
            rec(left, result, cur);
            left.addLast(cur.pop());
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};

        for (List<Integer> p : permuteUnique(nums)) {
            System.out.println(p.stream().map(Object::toString).collect(Collectors.joining(" ")));
        }
    }
}
