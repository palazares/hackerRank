package com.palazares.hackerrank.solutions;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BinarySearchLCA {

    static class Node {

        public Node(int data) {
            this.data = data;
        }

        int data;
        Node left;
        Node right;
    }

    static List<Node> v1Path;
    static List<Node> v2Path;
    static Node result;

    public static Node lca(Node root, int v1, int v2) {
        traverse(root, v1, v2, new ArrayList<>());
        return result;
    }

    static void traverse(Node node, int v1, int v2, List<Node> path) {
        if (node == null) return;
        List<Node> newList = new ArrayList<>(path);
        newList.add(node);

        if (node.data == v1) {
            v1Path = new ArrayList<>(newList);
        }
        if (node.data == v2) {
            v2Path = new ArrayList<>(newList);
        }

        if (v1Path != null && v2Path != null) {
            result = getCommonAncestor(v1Path, v2Path);
            return;
        }
        traverse(node.left, v1, v2, newList);
        traverse(node.right, v1, v2, newList);
    }

    static Node getCommonAncestor(List<Node> path1, List<Node> path2) {
        Iterator<Node> i1 = path1.iterator();
        Iterator<Node> i2 = path2.iterator();
        Node val1 = null;
        Node val2 = null;
        Node lastCommon = null;
        while (val1 == val2 && i1.hasNext() && i2.hasNext()) {
            lastCommon = val1;
            val1 = i1.next();
            val2 = i2.next();
        }

        if (!i1.hasNext()) {
            lastCommon = val1;
        }

        if (!i2.hasNext()) {
            lastCommon = val2;
        }

        return lastCommon;
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        int v1 = scan.nextInt();
        int v2 = scan.nextInt();
        scan.close();
        Node ans = lca(root, v1, v2);
        System.out.println(ans.data);
    }
}
