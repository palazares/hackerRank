package com.palazares.hackerrank.solutions;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SwapNodes {
    static int[][] swapNodes(int[][] indexes, int[] queries) {
        int[][] result = new int[queries.length][indexes.length];
        for(int i = 0; i < queries.length; i++){
            swapNodes(indexes, queries[i]);
            result[i] = inDepthTraversal(indexes);
        }
        return result;
    }

    static void swapNodes(int[][] tree, int query){
        swapNodesRec(tree, 0, 1, query);
    }

    static void swapNodesRec(int[][] tree, int elemIndex, int depth, int query){
        if(tree[elemIndex][0] == -1 && tree[elemIndex][1] == -1){
            return;
        }
        if(depth % query == 0){
            int tmp = tree[elemIndex][0];
            tree[elemIndex][0] = tree[elemIndex][1];
            tree[elemIndex][1] = tmp;
        }
        if(tree[elemIndex][0] != -1){
            swapNodesRec(tree, tree[elemIndex][0] - 1, depth + 1, query);
        }
        if(tree[elemIndex][1] != -1){
            swapNodesRec(tree, tree[elemIndex][1] - 1, depth + 1, query);
        }
    }

    static int resultIndex;

    static int[] inDepthTraversal(int[][] tree){
        int[] result = new int[tree.length];
        resultIndex = 0;
        inDepthTraversalRec(tree, 0, result);
        return result;
    }

    static void inDepthTraversalRec(int[][] tree, int elemIndex, int[] result){
        if(tree[elemIndex][0] == -1 && tree[elemIndex][1] == -1){
            result[resultIndex++] = elemIndex + 1;
            return;
        }

        if(tree[elemIndex][0] != -1){
            inDepthTraversalRec(tree, tree[elemIndex][0] - 1, result);
        }

        result[resultIndex++] = elemIndex + 1;

        if(tree[elemIndex][1] != -1){
            inDepthTraversalRec(tree, tree[elemIndex][1] - 1, result);
        }
    }

    public static void main(String[] args){
        int[][] tree = {
                {2, 3},
                {4, -1},
                {5, -1},
                {6, -1},
                {7, 8},
                {-1, 9},
                {-1, -1},
                {10, 11},
                {-1, -1},
                {-1, -1},
                {-1, -1}};
        int[] queries = {2, 4};

        int[][] result = swapNodes(tree, queries);

        Arrays.stream(result).forEach(SwapNodes::printArr);
    }

    private static void printArr(int[] arr){
        System.out.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }
}
